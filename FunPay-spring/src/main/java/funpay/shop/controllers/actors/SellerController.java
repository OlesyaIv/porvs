package funpay.shop.controllers.actors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import funpay.shop.models.system.Ask;
import funpay.shop.models.system.Item;
import funpay.shop.services.SellerService;

@RestController
@RequestMapping("seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("ask/{sellerId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Long> ask(@RequestBody final Ask ask, @PathVariable Long sellerId) {
        return new ResponseEntity<>(sellerService.placeAsk(ask, sellerId), HttpStatus.OK);
    }

    @PostMapping("createItem/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Long> item(@RequestBody final Item item) {
        return new ResponseEntity<>(sellerService.createItem(item), HttpStatus.OK);
    }

}
