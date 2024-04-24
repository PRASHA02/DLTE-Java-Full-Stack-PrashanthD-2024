package element.spring.web.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServices {

    //jdbc template
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //insertion of data
    public Transactions newTransaction(Transactions transactions) {
        int ack = jdbcTemplate.update("insert into transactions values(?,?,?,?,?,?)", new Object[]{transactions.getTransactionId(), transactions.getTransactionDate(), transactions.getTransactionBy(), transactions.getTransactionTo(), transactions.getTransactionAmount(), transactions.getTransactionFor()});
        if (ack != 0)
            return transactions;
        else
            return null;

    }

    //find based on sender
    public List<Transactions> findBySender(String sender) {
        List<Transactions> transactions = jdbcTemplate.query("select * from transactions where transactionby= ?", new Object[]{sender}, new TransactionMapper());
        return transactions;
    }

    //find based on receiver
    public List<Transactions> findByReceiver(String receiver) {
        List<Transactions> transactions = jdbcTemplate.query("select * from transactions where transactionto= ?", new Object[]{receiver}, new TransactionMapper());
        return transactions;
    }

    //find based on amount
    public List<Transactions> findByAmount(long amount) {
        List<Transactions> transactions = jdbcTemplate.query("select * from transactions where transactionamount= ?", new Object[]{amount}, new TransactionMapper());
        return transactions;
    }

    //update based on remarks
    public Transactions findByRemarks(Transactions transactions) {
        int ack = jdbcTemplate.update("update transactions set transactionFor= ? where transactionid=?", new Object[]{transactions.getTransactionFor(), transactions.getTransactionId()});
        if (ack != 0)
            return transactions;
        else
            return null;
    }

    //filter between start and end date
    public String removeByDate(Date start, Date end) {
        int ack = jdbcTemplate.update("delete from transactions where transactiondate between ? and ?", new Object[]{start, end});
        if (ack != 0)
            return "removed";
        else
            return null;
    }


    //row mapper used for mapping the result set into java objects
    protected class TransactionMapper implements RowMapper<Transactions> {

        @Override
        public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transactions transactions = new Transactions();
            transactions.setTransactionId(rs.getLong(1));
            transactions.setTransactionDate(rs.getDate(2));
            transactions.setTransactionBy(rs.getString(3));
            transactions.setTransactionTo(rs.getString(4));
            transactions.setTransactionAmount(rs.getLong(5));
            transactions.setTransactionFor(rs.getString(6));
            return transactions;
        }
    }
}
