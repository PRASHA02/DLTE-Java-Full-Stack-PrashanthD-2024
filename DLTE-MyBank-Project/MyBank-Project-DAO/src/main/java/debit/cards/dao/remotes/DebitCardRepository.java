package debit.cards.dao.remotes;



import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.DebitCardException;


import java.sql.SQLException;
import java.util.List;


public interface DebitCardRepository {

    List<DebitCard> getDebitCard() throws SQLException, DebitCardException;
}
