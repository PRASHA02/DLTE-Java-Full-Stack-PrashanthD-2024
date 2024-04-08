package debit.cards.dao.services;


import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.DebitCardException;
import debit.cards.dao.exceptions.DebitCardNullException;
import debit.cards.dao.remotes.DebitCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ResourceBundle;

//This services retrieves all the data from my oracle database and if any exception occurs it handles it.

//Stereotype annotation for Service based Implementation
@Service
public class DebitCardServices implements DebitCardRepository {
    //Injects the dependency of one objects with another
     @Autowired
     public JdbcTemplate jdbcTemplate;

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    private static final Logger logger = LoggerFactory.getLogger(DebitCardServices.class);

    //performs the business logic of getting list of cards
    @Override
    public  List<DebitCard> getDebitCard() {
        List<DebitCard> debitCardList=null;
        try{
            debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where not debitcard_status='Blocked'",new DebitCardMapper());
            logger.info(resourceBundle.getString("card.fetch.success"));
        }catch(DataAccessException sqlException){
            logger.error(resourceBundle.getString("sql.syntax.invalid"));
            throw new DebitCardException(resourceBundle.getString("sql.syntax.invalid"));
        }
        if(debitCardList.size()==0){
            logger.warn(resourceBundle.getString("card.list.null"));
            throw new DebitCardNullException();
        }
        return debitCardList;
    }
  //Row Mapper is used for getting the data from database and mapping it to Java objects.
   public class DebitCardMapper implements RowMapper<DebitCard>{

       @Override
       public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
           DebitCard debitCard = new DebitCard();
           debitCard.setDebitCardNumber(rs.getLong(1));
           debitCard.setAccountNumber(rs.getLong(2));
           debitCard.setCustomerId(rs.getInt(3));
           debitCard.setDebitCardCvv(rs.getInt(4));
           debitCard.setDebitCardPin(rs.getInt(5));
           debitCard.setDebitCardExpiry(rs.getDate(6));
           debitCard.setDebitCardStatus(rs.getString(7));
           debitCard.setDomesticLimit(rs.getDouble(8));
           debitCard.setInternationalLimit(rs.getDouble(9));
           return debitCard;
       }
   }
}
