package basics.project.middleware;

import basics.project.entities.UserInformation;
import basics.project.remotes.UserInfoRepository;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserInfoFileRepository implements UserInfoRepository {

    private ArrayList<UserInformation> userList = new ArrayList<>();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String filePath;

    UserInfoFileRepository() {
        filePath = "user.doc";
        try {
            FileHandler fileHandler = new FileHandler("user-info-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {

        }
    }

    private void readIntoFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userList = (ArrayList<UserInformation>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException | ClassNotFoundException e) {


        }

    }

    @Override
    public void validateUser(UserInformation userInformation) {

    }

    @Override
    public void deposit(UserInformation userInformation, Double addAmount) {

    }

    @Override
    public void updateBalance(UserInformation userInformation) {

    }
}
