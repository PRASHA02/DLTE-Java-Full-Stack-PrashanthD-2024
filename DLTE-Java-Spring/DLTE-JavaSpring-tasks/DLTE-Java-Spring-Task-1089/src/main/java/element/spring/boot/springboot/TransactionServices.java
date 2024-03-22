package element.spring.boot.springboot;

import com.sun.rowset.internal.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {

    //creates an instance of jdbcTemplate for Transaction Services
    @Autowired
    public JdbcTemplate jdbcTemplate;

    //create method
    public TransactionsModel apiSave(TransactionsModel transactionsModel){
//        int ack = jdbcTemplate.update("insert into transactions_model values(?,?,?,?,?,?)",new Object[]{
//                transactionsModel.getTransactionId(),
//                transactionsModel.getTransactionDate(),
//                transactionsModel.getTransactionBy(),
//                transactionsModel.getTransactionTo(),
//                transactionsModel.getTransactionAmount(),
//                transactionsModel.getTransactionFor()
//        });
//        if(ack!=0)
//            return transactionsModel;
//        else
//            throw  new TransactionException("Insertion Failed");
        jdbcTemplate.update("insert into transactions_model values(?,?,?,?,?,?)",new Object[]{
                transactionsModel.getTransactionId(),
                transactionsModel.getTransactionDate(),
                transactionsModel.getTransactionBy(),
                transactionsModel.getTransactionTo(),
                transactionsModel.getTransactionAmount(),
                transactionsModel.getTransactionFor()
        });
        return transactionsModel;
    }

    //findBY method
    public List<TransactionsModel> apiFindBySender(String sender){
        return jdbcTemplate.query("select * from transactions_model where transaction_by=?",new Object[]{sender},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));

    }

    //FindTo method
    public List<TransactionsModel> apiFindByReceiver(String receiver){
        return jdbcTemplate.query("select * from transactions_model where transaction_to=?",new Object[]{receiver},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));

    }

    //FindAmount
    public List<TransactionsModel> apiFindByAmount(Long amount){
        return jdbcTemplate.query("select * from transactions_model where transaction_amount=?",new Object[]{amount},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));

    }
    private class CardMapper implements RowMapper<TransactionsModel>{

        @Override
        public TransactionsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionsModel transactionsModel = new TransactionsModel();
            transactionsModel.setTransactionId(rs.getLong(1));
            transactionsModel.setTransactionDate(rs.getDate(2));
            transactionsModel.setTransactionBy(rs.getString(3));
            transactionsModel.setTransactionTo(rs.getString(4));
            transactionsModel.setTransactionAmount(rs.getLong(5));
            transactionsModel.setTransactionFor(rs.getString(6));
            return transactionsModel;
        }
    }



}
