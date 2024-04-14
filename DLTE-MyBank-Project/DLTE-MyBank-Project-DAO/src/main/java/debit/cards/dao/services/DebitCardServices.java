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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

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
    public List<DebitCard> getDebitCard() {
        List<DebitCard> debitCardList = null;
        try {
            debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard where not debitcard_status='Blocked'", new DebitCardMapper());
            logger.info(resourceBundle.getString("card.fetch.success"));
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("sql.syntax.invalid"));
            throw new DebitCardException(resourceBundle.getString("sql.syntax.invalid"));
        }
        if (debitCardList.size() == 0) {
            logger.warn(resourceBundle.getString("card.list.null"));
            throw new DebitCardNullException();
        }
        return debitCardList;
    }

    @Override
    public String updateDebitLimit(DebitCard debitCard) throws SQLException {
        try {
            // Fetch the debit card details from the database using the provided account number
            DebitCard fetchedDebitCard = jdbcTemplate.queryForObject(
                    "SELECT * FROM mybank_app_debitcard WHERE account_number = ?",
                    new Object[]{debitCard.getAccountNumber()},
                    new DebitCardMapper());

            // Check if fetched debit card is null
            if (debitCard == null || fetchedDebitCard == null) {
                throw new DebitCardNullException("No debit card found for account number: " + debitCard.getAccountNumber());
            }

            // Check if any attributes are incorrect
            List<String> incorrectData = getIncorrectData(debitCard, fetchedDebitCard);
            if (!incorrectData.isEmpty()) {
                // Throw an exception with the list of incorrect attributes
                throw new ValidationException("Incorrect Data: " + incorrectData);
            }

            // Prepare the callable statement to update the debit card limit
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call UPDATE_DEBITCARD_LIMIT(?, ?, ?, ?)}");
                statement.setLong(1, debitCard.getAccountNumber());
                statement.setDouble(2, debitCard.getDomesticLimit());
                statement.setDouble(3, debitCard.getInternationalLimit());
                statement.registerOutParameter(4, Types.VARCHAR);
                return statement;
            };

            // Execute the update operation
            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.DOUBLE),
                            new SqlParameter(Types.DOUBLE),
                            new SqlOutParameter("status", Types.VARCHAR)
                    }
            ));

            // Handle the result of the update operation
            String resultMessage = returnedExecution.get("status").toString();
            if (resultMessage.equals("SQLCODE-000")) {
                logger.error(resourceBundle.getString("limit.update.success"));
            } else {
                handleUpdateFailure(resultMessage);
            }
        } catch (DataAccessException dataAccessException) {
            // Handle JDBC data access exception
            logger.error(resourceBundle.getString("internal.error"));
            throw new SQLException(resourceBundle.getString("internal.error"));
        }
        return resourceBundle.getString("limit.update.success");
    }

    // Method to handle update failure based on the result message
    private void handleUpdateFailure(String resultMessage) throws SQLException {
        switch (resultMessage) {
            case "SQLCODE-001":
                logger.error(resourceBundle.getString("customer.not.found"));
                throw new CustomerException(resourceBundle.getString("customer.not.found"));
            case "SQLCODE-002":
                logger.error(resourceBundle.getString("account.not.found"));
                throw new AccountException(resourceBundle.getString("account.not.found"));
            case "SQLCODE-003":
                logger.error(resourceBundle.getString("limit.update.failed"));
                throw new DebitCardException(resourceBundle.getString("limit.update.failed"));
            case "SQLCODE-004":
                logger.error(resourceBundle.getString("no.data.found"));
                throw new DebitCardNullException(resourceBundle.getString("no.data.found"));
            case "SQLCODE-005":
                logger.error(resourceBundle.getString("internal.error"));
                throw new SQLException(resourceBundle.getString("internal.error"));
        }
    }

    // Method to compare the provided debit card attributes with the fetched debit card attributes and return a list of incorrect attributes
    private List<String> getIncorrectData(DebitCard providedDebitCard, DebitCard fetchedDebitCard) {
        List<String> incorrectData = new ArrayList<>();

        // Check if any of the debit cards is null
        if (providedDebitCard == null || fetchedDebitCard == null) {
            incorrectData.add("Provided or fetched debit card is null.");
            return incorrectData;
        }
        if (!Objects.equals(providedDebitCard.getDebitCardNumber(), fetchedDebitCard.getDebitCardNumber())) {
            incorrectData.add("Debit card number is incorrect");
        }
        if (!Objects.equals(providedDebitCard.getCustomerId(), fetchedDebitCard.getCustomerId())) {
            incorrectData.add("Customer ID is incorrect");
        }
        if (!Objects.equals(providedDebitCard.getDebitCardCvv(), fetchedDebitCard.getDebitCardCvv())) {
            incorrectData.add("CVV is incorrect");
        }
        if (!Objects.equals(providedDebitCard.getDebitCardPin(), fetchedDebitCard.getDebitCardPin())) {
            incorrectData.add("PIN is incorrect");
        }
        return incorrectData;
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
