package debit.cards.dao.remotes;


//import debit.cards.dao.entities.Account;
import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.DebitCardException;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

//Interface of Debit Card Remotes
@Repository
public interface DebitCardRepository {
    //List of Debit cards will be Fetched
    List<DebitCard> getDebitCard(String username) throws SQLException;
    String updateDebitLimit(DebitCard debitCard) throws SQLException;
//    List<Account> accountList(String username) throws SQLSyntaxErrorException;
}
