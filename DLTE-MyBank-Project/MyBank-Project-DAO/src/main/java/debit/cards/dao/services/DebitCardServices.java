//package debit.cards.dao.services;

//
//
//import debit.cards.dao.database.Database;
//import debit.cards.dao.entities.DebitCard;
//import debit.cards.dao.exceptions.DebitCardException;
//import debit.cards.dao.remotes.DebitCardRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ResourceBundle;
//
//@Service
//public class DebitCardServices implements DebitCardRepository {
//     @Autowired
//     private JdbcTemplate jdbcTemplate;
//    public Connection connection;
//     public DebitCardServices(){
//         try {
//             Database database = new Database();
//             connection = database.getConnection();
//         } catch (SQLException e) {
//
//         }
//     }
//    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//
//    @Override
//    public List<DebitCard> getDebitCard(){
//        List<DebitCard> debitCardList = jdbcTemplate.query("SELECT * FROM mybank_app_debitcard",new DebitCardMapper());
//        return debitCardList;
//
//    }
//
//
//    private class DebitCardMapper implements RowMapper<DebitCard> {
//
//        @Override
//        public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
//            DebitCard debitCard = new DebitCard();
//            debitCard.setDebitCardNumber(rs.getLong(1));
//            debitCard.setAccountNumber(rs.getLong(2));
//            debitCard.setCustomerId(rs.getInt(3));
//            debitCard.setDebitCardCvv(rs.getInt(4));
//            debitCard.setDebitCardPin(rs.getInt(5));
//            debitCard.setDebitCardExpiry(rs.getDate(6));
//            debitCard.setDebitCardStatus(rs.getString(7));
//            debitCard.setDomesticLimit(rs.getDouble(8));
//            debitCard.setInternationalLimit(rs.getDouble(9));
//            return debitCard;
//        }
//    }
//}


