package funpay.shop.models.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import funpay.shop.models.AbstractDomain;
import funpay.shop.models.actors.Buyer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "orders")
@Entity(name = "Order")
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractDomain {
    @OneToOne
    private Item item;

    private Number uid;

    @ManyToOne
    @JsonIgnore
    private Buyer buyer;

    public Order(Item item, Number uid, Buyer buyer) {
        this.item = item;
        this.uid = uid;
        this.buyer = buyer;
    }
}
