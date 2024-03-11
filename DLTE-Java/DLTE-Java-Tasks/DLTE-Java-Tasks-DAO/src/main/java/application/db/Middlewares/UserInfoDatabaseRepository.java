package application.db.Middlewares;

import application.db.Remotes.UserInfoRepository;
import org.example.Entities.Customer;
import org.example.Exception.UserNotFoundException;

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
    public Customer validateUser(String username) {
        Customer customer = null;
        try {
            String query = "select * from user_info where username=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(!resultSet.getString(1).equalsIgnoreCase(username)){
                    logger.log(Level.INFO,resourceBundle.getString("user.name"));
                    throw new UserNotFoundException();
                }
                else{
                    customer = new Customer();
                    customer.setUsername(resultSet.getString(username));
                }
            }
        }catch(SQLException sqlException){
            System.out.println(sqlException);
        }
        return customer;

    }

    @Override
    public void DepositAmountInto(String username, Long amount) {


    }

    @Override
    public void addInformation(Customer customer) {
        try{
            String query = "insert into  user_info values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getPassword());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setString(4,customer.getAddress());
            preparedStatement.setLong(5,customer.getContact());
            preparedStatement.setLong(6,customer.getInitialBalace());
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
