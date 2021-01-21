package funpay.shop.services;

import funpay.shop.models.actors.Administrator;
import funpay.shop.models.actors.Buyer;
import funpay.shop.models.actors.Seller;
import funpay.shop.models.actors.User;
import funpay.shop.models.system.*;
import funpay.shop.repositories.actors.AdministratorRepository;
import funpay.shop.repositories.actors.BuyerRepository;
import funpay.shop.repositories.actors.SellerRepository;
import funpay.shop.repositories.system.*;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MarketplaceService {

    private final AdministratorRepository administratorRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    private final ItemRepository itemRepository;
    private final BetRepository betRepository;
    private final AskRepository askRepository;
    private final DealRepository dealRepository;
    private final OrderRepository orderRepository;

    public MarketplaceService(AdministratorRepository administratorRepository,
                              BuyerRepository buyerRepository, SellerRepository sellerRepository,
                              ItemRepository itemRepository, BetRepository betRepository,
                              AskRepository askRepository, DealRepository dealRepository,
                              OrderRepository orderRepository) {
        this.administratorRepository = administratorRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.itemRepository = itemRepository;
        this.betRepository = betRepository;
        this.askRepository = askRepository;
        this.dealRepository = dealRepository;
        this.orderRepository = orderRepository;
    }

    public Optional<? extends User> login(@NonNull Map<String, Object> data) {
        final String username = data.get("username").toString();
        final String password = data.get("password").toString();

        final Optional<Administrator> administrator =
                administratorRepository.findOne(Example.of(new Administrator(username, password)));
        final Optional<Buyer> buyer = buyerRepository.findOne(Example.of(new Buyer(username, password)));
        final Optional<Seller> seller = sellerRepository.findOne(Example.of(new Seller(username, password)));

        return administrator.isPresent() ? administrator
                : seller.isPresent() ? seller
                : buyer.isPresent() ? buyer
                : Optional.empty();
    }

    public List<Item> getAllItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Bet> getHighestBets() {
        final Map<Long, Integer> highestBets = StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(bet -> bet.getItem().getId(), Bet::getBet, (b1, b2) -> b1 > b2 ? b1 : b2));
        return StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .filter(bet -> highestBets.get(bet.getItem().getId()).equals(bet.getBet()))
                .collect(Collectors.toList());
    }

    public List<Ask> getLowestAsk() {
        final Map<Long, Integer> lowestAsk = StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(ask -> ask.getItem().getId(), Ask::getAsk, (b1, b2) -> b1 < b2 ? b1 : b2));
        return StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .filter(ask -> lowestAsk.get(ask.getItem().getId()).equals(ask.getAsk()))
                .collect(Collectors.toList());
    }

    public void checkIfDeal(Bet bet) {
        StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .filter(ask -> ask.getItem().getId().equals(bet.getItem().getId()) && ask.getAsk() == bet.getBet())
                .findFirst()
                .ifPresent(ask -> createDeal(bet, ask));
    }

    public void checkIfDeal(Ask ask) {
        StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .filter(bet -> bet.getItem().getId().equals(ask.getItem().getId()) && bet.getBet() == ask.getAsk())
                .findFirst()
                .ifPresent(bet -> createDeal(bet, ask));
    }

    private Deal createDeal(Bet bet, Ask ask) {
        final Administrator administrator = administratorRepository.findAll().stream().findAny().orElseThrow();
        final Deal deal = dealRepository.save(new Deal(ask, bet, administrator));
        administrator.getDeals().add(deal);
        administratorRepository.save(administrator);
        return deal;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void newOrder(Long dealId){
        final Deal deal = dealRepository.findById(dealId).get();
        final Buyer buyer = deal.getBet().getBuyer();
        int nmb = getRandomNumber(0,5000000);
        final Order order = new Order(deal.getBet().getItem(), nmb, buyer);
        orderRepository.save(order);
        buyer.getOrders().add(order);
        buyerRepository.save(buyer);
    }

}
