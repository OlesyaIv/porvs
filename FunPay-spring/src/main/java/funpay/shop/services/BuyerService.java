package funpay.shop.services;

import funpay.shop.models.actors.Buyer;
import funpay.shop.models.system.Bet;
import funpay.shop.repositories.actors.BuyerRepository;
import funpay.shop.repositories.system.BetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {
    private final BetRepository betRepository;
    private final BuyerRepository buyerRepository;
    private final MarketplaceService marketplaceService;

    public BuyerService(BetRepository betRepository, BuyerRepository buyerRepository,
                        MarketplaceService marketplaceService) {
        this.betRepository = betRepository;
        this.buyerRepository = buyerRepository;
        this.marketplaceService = marketplaceService;
    }

    public Long saveBet(Bet bet, Long buyerId) {
        final Buyer buyer = buyerRepository.findById(buyerId).get();
        bet.setBuyer(buyer);
        final Bet savedBet = betRepository.save(bet);
        buyer.getBets().add(savedBet);
        buyerRepository.save(buyer);
        marketplaceService.checkIfDeal(bet);
        return savedBet.getId();
    }

    public Optional<Buyer> getBuyer(Long id) {
        return buyerRepository.findById(id);
    }
}
