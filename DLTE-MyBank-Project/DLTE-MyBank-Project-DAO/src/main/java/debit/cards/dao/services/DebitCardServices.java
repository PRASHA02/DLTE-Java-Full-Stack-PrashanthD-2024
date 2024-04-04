package debit.cards.dao.services;


import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.remotes.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DebitCardServices implements DebitCardRepository {
     @Autowired
     private JdbcTemplate jdbcTemplate;


    @Override
    public List<DebitCard> getDebitCard(){
        List<DebitCard> debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where debitcard_status='Active' or debitcard_status='InActive'",new DebitCardMapper());
        return debitCardList;

    }

   protected class DebitCardMapper implements RowMapper<DebitCard>{

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
