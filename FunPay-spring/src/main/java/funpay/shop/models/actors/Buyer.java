package funpay.shop.models.actors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import funpay.shop.models.system.Bet;
import funpay.shop.models.system.Order;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "buyers")
@Entity(name = "Buyer")
@EqualsAndHashCode(callSuper = true)
public class Buyer extends User {
    @OneToMany
    private List<Order> orders;
    @OneToMany
    private List<Bet> bets;

    public Buyer(String username, String password, UserStatus userStatus, List<Order> orders, List<Bet> bets) {
        super(username, password, userStatus);
        this.orders = orders;
        this.bets = bets;
    }

    public Buyer(String username, String password) {
        super(username, password);
    }
}
