package funpay.shop.repositories.actors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import funpay.shop.models.actors.Buyer;

@RepositoryRestResource
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
