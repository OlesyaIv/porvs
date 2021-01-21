package funpay.shop.models.actors;

import funpay.shop.models.AbstractDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class User extends AbstractDomain {
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_status")
    private UserStatus userStatus;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public enum UserStatus {
        OFFLINE,
        ONLINE
    }
}
