package funpay.shop.models.system;

import funpay.shop.models.AbstractDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Item")
@EqualsAndHashCode(callSuper = true)
@Table(name = "items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "server"})}
)
public class Item extends AbstractDomain {
    private String name;
    private String server;
}
