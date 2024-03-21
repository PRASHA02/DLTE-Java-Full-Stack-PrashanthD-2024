package element.spring.boot.springboot.remotes;

import element.spring.boot.springboot.model.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity,Long> {
}
