package debit.cards.dao.services;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


//Stereotype annotation for Service based Implementation
@Service
public class DebitCardServices implements DebitCardRepository {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    private static final Logger logger = LoggerFactory.getLogger(DebitCardServices.class);

    @Override
    public List<DebitCard> getDebitCard(String username) throws SQLException {
        List<DebitCard> debitCardList = null;
        try {
            debitCardList = jdbcTemplate.query("SELECT d.debitcard_number,d.account_number,d.customer_id,d.debitcard_cvv,d.debitcard_pin,d.debitcard_expiry,d.debitcard_status,d.debitcard_domestic_limit,d.debitcard_international_limit FROM mybank_app_debitcard d JOIN mybank_app_customer c ON d.customer_id = c.customer_id JOIN mybank_app_account a on a.account_number=d.account_number WHERE NOT debitcard_status = 'block' AND  a.account_status='active'  AND c.customer_status='active' AND c.username = ?", new Object[]{username}, new DebitCardMapper());
            logger.info(resourceBundle.getString("card.fetch.success"));
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("sql.syntax.invalid"));
            throw new SQLException(resourceBundle.getString("sql.syntax.invalid"));
        }
        if (debitCardList.size() == 0) {
            logger.warn(resourceBundle.getString("card.list.null"));
            throw new DebitCardException((resourceBundle.getString("card.list.null")));
        }
        return debitCardList;
    }
    //Update the limits when all the customer,account and debit card status is active otherwise it gives respective error messages
    @Override
    public String updateDebitLimit(DebitCard debitCard) throws SQLException {

        DebitCard fetchedDebitCard=null;
        fetchedDebitCard = jdbcTemplate.queryForObject(
                    "SELECT debitcard_number,account_number,customer_id,debitcard_cvv,debitcard_pin,debitcard_expiry,debitcard_status,debitcard_domestic_limit,debitcard_international_limit FROM mybank_app_debitcard WHERE account_number = ?",
                    new Object[]{debitCard.getAccountNumber()},
                    new DebitCardMapper());
        if(!(fetchedDebitCard.getAccountNumber().equals(debitCard.getAccountNumber()))){
            throw new DebitCardException(resourceBundle.getString("no.account.found"));
        }


        try {
            // Prepare the callable statement to update the debit card limit, Stored procedures often take input parameters.
            debitCard.setCustomerId(fetchedDebitCard.getCustomerId());
            debitCard.setDebitCardCvv(fetchedDebitCard.getDebitCardCvv());
            debitCard.setDebitCardPin(fetchedDebitCard.getDebitCardPin());
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call UPDATE_DEBITCARD_LIMIT(?, ?, ?, ?)}");
                statement.setLong(1, debitCard.getAccountNumber());
                statement.setDouble(2, debitCard.getDomesticLimit());
                statement.setDouble(3, debitCard.getInternationalLimit());
                statement.registerOutParameter(4, Types.VARCHAR);
                return statement;
            };
            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.DOUBLE),
                            new SqlParameter(Types.DOUBLE),
                            new SqlOutParameter("status", Types.VARCHAR)
                    }
            ));
            String resultMessage = returnedExecution.get("status").toString();
            if(resultMessage.equals("SQLCODE-000")) {
                logger.info(resourceBundle.getString("limit.update.success"));
            } else if(resultMessage.equals("SQLCODE-003")){
                throw new DebitCardException(resourceBundle.getString("limit.update.failed"));
             } else if(resultMessage.equals("SQLCODE-005")){
                    throw new SQLException(resourceBundle.getString("internal.error"));
            }
        } catch (DataAccessException dataAccessException) {
            logger.error(resourceBundle.getString("internal.error"));
            throw new SQLException(resourceBundle.getString("internal.error"));
        }
        return resourceBundle.getString("limit.update.success");
    }


    //Row Mapper is used for getting the data from database and mapping it to Java objects.
    public class DebitCardMapper implements RowMapper<DebitCard> {

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
