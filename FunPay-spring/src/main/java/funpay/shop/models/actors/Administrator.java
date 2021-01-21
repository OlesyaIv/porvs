package funpay.shop.models.actors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import funpay.shop.models.system.Deal;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "administrators")
@Entity(name = "Administrator")
@EqualsAndHashCode(callSuper = true)
public class Administrator extends User {
    @OneToMany
    private List<Deal> deals;

    public Administrator(String username, String password, UserStatus userStatus, List<Deal> deals) {
        super(username, password, userStatus);
        this.deals = deals;
    }

    public Administrator(String username, String password) {
        super(username, password);
    }
}
