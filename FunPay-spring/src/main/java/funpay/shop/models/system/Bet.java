package funpay.shop.models.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import funpay.shop.models.AbstractDomain;
import funpay.shop.models.actors.Buyer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "bets")
@Entity(name = "Bet")
@EqualsAndHashCode(callSuper = true)
public class Bet extends AbstractDomain {
    @OneToOne
    private Item item;

    private int bet;

    @OneToOne
    @JsonIgnore
    private Buyer buyer;

    public Bet(Item item, int bet, Buyer buyer) {
        this.item = item;
        this.bet = bet;
        this.buyer = buyer;
    }
}
