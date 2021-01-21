package funpay.shop.controllers.actors;

import funpay.shop.models.system.Bet;
import funpay.shop.models.system.Order;
import funpay.shop.services.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("buyer")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("bet/{buyerId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Long> bet(@RequestBody final Bet bet, @PathVariable Long buyerId) {
        return new ResponseEntity<>(buyerService.saveBet(bet, buyerId), HttpStatus.OK);
    }

    @GetMapping("getBets")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Bet>> getBets(@RequestParam Long id) {
        return buyerService.getBuyer(id)
                .map(buyer -> new ResponseEntity<>(buyer.getBets(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("getOrders")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Order>> getOrders(@RequestParam Long id) {
        return buyerService.getBuyer(id)
                .map(buyer -> new ResponseEntity<>(buyer.getOrders(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
