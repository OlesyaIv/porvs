package funpay.shop.controllers;

import funpay.shop.models.actors.User;
import funpay.shop.models.system.Ask;
import funpay.shop.models.system.Bet;
import funpay.shop.models.system.Item;
import funpay.shop.services.MarketplaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("marketplace")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    public MarketplaceController(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<? extends User> login(@RequestBody final Map<String, Object> data) {
        return marketplaceService.login(data)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping("items")
    public ResponseEntity<List<Item>> loadItems() {
        return new ResponseEntity<>(marketplaceService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("getLowestAsks")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Ask>> getLowestAsk() {
        return new ResponseEntity<>(marketplaceService.getLowestAsk(), HttpStatus.OK);
    }

    @GetMapping("getHighestBets")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Bet>> getHighestBets() {
        return new ResponseEntity<>(marketplaceService.getHighestBets(), HttpStatus.OK);
    }
}
