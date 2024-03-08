package files.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyBankDatabase <T> implements Activity <T>{

    static Scanner scanner = new Scanner(System.in);

   static  int counter=0;

    ArrayList<T> myObjects = new ArrayList<>();



    public static void main(String[] args) throws IOException, ClassNotFoundException {
              MyBankDatabase<CreditCard> myBankDatabase = new MyBankDatabase<>();
              CreditCard creditCard1  = new CreditCard(8765678765678L,"Prashanth D",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111);
              CreditCard creditCard2   =  new CreditCard(2343234345445L,"Vignesh",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999);
              CreditCard creditCard3   =     new CreditCard(8765678764545L,"Sanath",new Date(2031,5,15),955,200000,new Date(2024,3,10),new Date(2024,03,11),9864);
              CreditCard creditCard4   =     new CreditCard(1234565456767L,"Akash",new Date(2028,8,11),767,10000,new Date(2024,3,18),new Date(2024,03,29),1945);

              System.out.println(myBankDatabase.createNewData(creditCard1));
              System.out.println(  myBankDatabase.createNewData(creditCard2));
              System.out.println(myBankDatabase.createNewData(creditCard3));
              System.out.println(myBankDatabase.createNewData(creditCard4));

              myBankDatabase.writeFile();
              myBankDatabase.readFile();



    }


    @Override
    public String createNewData(T objects) {
            myObjects.add(objects);
            counter++;
            return "Credit card " +counter + " successfully added";

    }

    public void writeFile(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("credit-card.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(myObjects);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            System.out.println("Not able to write into file");
        }
    }

    public void readFile() {
        try{
            FileInputStream fileInputStream = new FileInputStream("credit-card.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            myObjects = (ArrayList<T>) objectInputStream.readObject();
            myObjects.forEach(System.out::println);
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException | ClassNotFoundException e){

            System.out.println("Not able to read the file");
        }

    }

}
