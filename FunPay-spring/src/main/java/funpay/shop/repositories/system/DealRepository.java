package funpay.shop.repositories.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import funpay.shop.models.system.Deal;

@RepositoryRestResource
public interface DealRepository extends CrudRepository<Deal, Long> {
}
