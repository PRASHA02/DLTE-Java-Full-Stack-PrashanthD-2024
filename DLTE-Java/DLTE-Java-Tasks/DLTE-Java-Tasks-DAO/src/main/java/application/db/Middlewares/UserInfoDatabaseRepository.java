package application.db.Middlewares;

import application.db.Entities.Customer;
import application.db.Exception.UserNotFoundException;
import application.db.Remotes.UserInfoRepository;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserInfoDatabaseRepository implements UserInfoRepository {
    private Connection connection;
    private ArrayList<Customer> userList=new ArrayList<>();
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("information");
    private Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserInfoDatabaseRepository(Connection connection){
        try{
            this.connection = connection;
            FileHandler fileHandler=new FileHandler("credit-card-logs.txt",true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public Customer validateUser(String username, String password) throws SQLException {
        String query = "select username, password from user_info where username=? and password=?";
        preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, username);
          preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Compare fetched username and password with provided ones
            if (resultSet.getString(1).equals(username) &&  resultSet.getString(2).equals(password)) {
                return new Customer(username, password);
            }
        }
        return null;
    }



    @Override
    public void DepositAmountInto(String username, String password, Long amount) {

    }

    @Override
    public void addInformation(Customer customer) {
        try{
            String query = "insert into  user_info values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getPassword());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setLong(5,customer.getContact());
            preparedStatement.setLong(6,customer.getInitialBalance());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet != 0) {
                logger.log(Level.INFO, resourceBundle.getString("db.push.ok"));
                System.out.println(resourceBundle.getString("db.push.ok"));
            } else {
                logger.log(Level.INFO, resourceBundle.getString("db.push.fail"));
                System.out.println(resourceBundle.getString("db.push.fail"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
