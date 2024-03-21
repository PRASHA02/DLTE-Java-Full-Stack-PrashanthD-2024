package element.spring.boot.springboot.remotes;

import element.spring.boot.springboot.models.AccountInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<AccountInfo,Long> {

//   AccountInfo save(AccountInfo accountInfo);
//   AccountInfo update(AccountInfo accountInfo);
//   List<AccountInfo>  findAll();

}
