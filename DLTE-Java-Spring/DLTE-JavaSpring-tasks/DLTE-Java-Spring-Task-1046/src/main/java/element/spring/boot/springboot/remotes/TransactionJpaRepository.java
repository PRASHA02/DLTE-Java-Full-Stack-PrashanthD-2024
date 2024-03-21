package element.spring.boot.springboot.remotes;

import element.spring.boot.springboot.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity,Long> {

    //native query
    @Query(value = "select * from transaction_entity where username=:userName and transaction_type=:type",nativeQuery = true)
    List<TransactionEntity> lookByUsernameAndTransactionType(String userName,String type);

    //HQL
    @Query(value = "from TransactionEntity where amount between :rangeAmount1 and :rangeAmount2")
    List<TransactionEntity> lookByRangeAmount(Long rangeAmount1,Long rangeAmount2);
}
