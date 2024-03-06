package files.oops;

import technical.review.Employee;
import technical.review.EmployeeAddress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRepository {
    static Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
     static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    String filePathEmployee = "emp.txt";
    String filePathAddress = "address.txt";
    public void writeFileEmployee(ArrayList<Employee> employeeArrayList){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePathEmployee);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
             objectOutputStream.writeObject(employeeArrayList);
            logger.log(Level.INFO,resourceBundle.getString("write.done"));
            objectOutputStream.close();
            fileOutputStream.close();
        }catch(IOException e) {
            System.out.println("Something error occurred "+e);
        }

    }
    public void writeFileAddress(ArrayList<EmployeeAddress> permanentAddress,ArrayList<EmployeeAddress> temporaryAddress){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePathAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(permanentAddress);
            objectOutputStream.writeObject(temporaryAddress);
            logger.log(Level.INFO,resourceBundle.getString("write.done"));
            objectOutputStream.close();
            fileOutputStream.close();
        }catch(IOException e) {
            System.out.println("Something error occurred "+e);
        }
    }
    public ArrayList<Employee> readEmployee(){
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePathEmployee);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employeeArrayList = (ArrayList<Employee>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred"+e);
        }
        return employeeArrayList;
    }
    public ArrayList<EmployeeAddress> readPermanentAddress(){
        ArrayList<EmployeeAddress> permanentAddress = new ArrayList<>();

        try{
            FileInputStream fileInputStream = new FileInputStream(filePathAddress);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
             permanentAddress = (ArrayList<EmployeeAddress>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred"+e);
        }
        return permanentAddress;
    }

    public ArrayList<EmployeeAddress> readTemporaryAddress(){
        ArrayList<EmployeeAddress> temporaryAddress  = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePathAddress);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temporaryAddress = (ArrayList<EmployeeAddress>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred"+e);
        }
        return temporaryAddress;
    }
}
