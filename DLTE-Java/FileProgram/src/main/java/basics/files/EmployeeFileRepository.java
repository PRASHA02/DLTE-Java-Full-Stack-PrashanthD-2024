package basics.files;

import java.io.*;
import java.util.ArrayList;

public class EmployeeFileRepository  implements EmployeeInterface{
    static String filePath = "emp.txt";
    EmployeeFileRepository(){
    }
    @Override
    public void display() {

    }
    public void writeFile(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Employee employee = new Employee("prashanth",117,90987654L);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {

            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Employee employee = (Employee) objectInputStream.readObject();
            System.out.println(employee);
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException | ClassNotFoundException e) {


        }
    }
}
