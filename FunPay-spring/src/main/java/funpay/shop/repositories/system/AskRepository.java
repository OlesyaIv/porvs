package funpay.shop.repositories.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import funpay.shop.models.system.Ask;

@RepositoryRestResource
public interface AskRepository extends CrudRepository<Ask, Long> {
}
