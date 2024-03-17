package jmi.services;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
     try {
    InitialContext context = new InitialContext();
    DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
    try (Connection connection = dataSource.connection()) {
        String accountNumberParam = req.getParameter("accountNumber");
        String emailParam = req.getParameter("email");

        if (accountNumberParam != null && emailParam != null) {
            int accountNumber = Integer.parseInt(accountNumberParam);

            String sql = "UPDATE my_bank SET email = ? WHERE account_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, emailParam);
                preparedStatement.setLong(2, accountNumber);
                preparedStatement.executeUpdate();
            }
        } else {
            throw new ServletException("Account Number Is Incorrect Or Doesn't Exist");
        }
    }
} catch (
    SQLException ex) {
    ex.printStackTrace();
} catch (
    NamingException ex) {
    ex.printStackTrace();
}
}
