package funpay.shop.repositories.actors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import funpay.shop.models.actors.Seller;

@RepositoryRestResource
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
