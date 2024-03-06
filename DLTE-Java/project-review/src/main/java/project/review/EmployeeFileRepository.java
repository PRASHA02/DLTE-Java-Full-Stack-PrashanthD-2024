import java.io.*;
import java.util.ArrayList;

public class EmployeeFileRepository {
    String filepath="employee.doc";
    ArrayList<String> employeeList = new ArrayList<>();

    private void writeFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {

        }
    }

    private void read(){
        try{
            FileInputStream fileInputStream = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employeeList = (ArrayList<String>) objectInputStream.readObject(objectInputStream);
            objectInputStream.close();
            fileInputStream.close();
        }catch(IOException e){

        }
    }

}
