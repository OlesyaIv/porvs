package funpay.shop.services;

import funpay.shop.models.actors.Seller;
import funpay.shop.models.system.Ask;
import funpay.shop.models.system.Item;
import funpay.shop.repositories.actors.SellerRepository;
import funpay.shop.repositories.system.AskRepository;
import funpay.shop.repositories.system.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final AskRepository askRepository;
    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    private final MarketplaceService marketplaceService;

    public SellerService(AskRepository askRepository, ItemRepository itemRepository, SellerRepository sellerRepository,
                         MarketplaceService marketplaceService) {
        this.askRepository = askRepository;
        this.itemRepository = itemRepository;
        this.sellerRepository = sellerRepository;
        this.marketplaceService = marketplaceService;
    }

    public Long placeAsk(final Ask ask, final Long sellerId) {
        final Seller seller = sellerRepository.findById(sellerId).get();
        ask.setSeller(seller);
        final Ask savedAsk = askRepository.save(ask);
        seller.getAsks().add(savedAsk);
        sellerRepository.save(seller);
        marketplaceService.checkIfDeal(ask);
        return savedAsk.getId();
    }

    public Long createItem(final Item item) {
        final Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }
}
