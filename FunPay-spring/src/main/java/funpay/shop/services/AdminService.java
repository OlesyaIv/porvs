package funpay.shop.services;

import funpay.shop.models.actors.Administrator;
import funpay.shop.models.system.Item;
import funpay.shop.repositories.actors.AdministratorRepository;
import funpay.shop.repositories.system.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdministratorRepository administratorRepository;
    private final MarketplaceService marketplaceService;
    private final ItemRepository itemRepository;

    public AdminService(AdministratorRepository administratorRepository, MarketplaceService marketplaceService, ItemRepository itemRepository) {
        this.administratorRepository = administratorRepository;
        this.marketplaceService = marketplaceService;
        this.itemRepository = itemRepository;
    }

    public Optional<Administrator> getDeals(Long id) {
        return administratorRepository.findById(id);
    }

    public void approve(Long dealId) {
        marketplaceService.newOrder(dealId);
    }

    public Long deleteItem(final Item item) {
        itemRepository.delete(item);
        return item.getId();
    }
}
