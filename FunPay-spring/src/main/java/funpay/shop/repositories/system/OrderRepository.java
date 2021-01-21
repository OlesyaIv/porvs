package funpay.shop.repositories.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import funpay.shop.models.system.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
}
