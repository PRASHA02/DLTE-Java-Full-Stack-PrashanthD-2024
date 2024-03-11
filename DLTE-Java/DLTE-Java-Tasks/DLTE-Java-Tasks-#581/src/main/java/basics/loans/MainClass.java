package basics.loans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass implements MyBank {

    static Logger logger = LoggerFactory.getLogger(MainClass.class);

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("loan");

    static ArrayList<LoanProduct> arrayListLoanProduct = new ArrayList<>();

    public static void main(String[] args) {
//        System.setProperty("system.output.ansi.enabled","always");
        Scanner scanner = new Scanner(System.in);
        MainClass mainClass = new MainClass();
         LoanProduct[] loan = {
                new LoanProduct(117,50000.0,new Date(2024,11,11),"closed","prashanth",789267517L),
                new LoanProduct(111,60000.0,new Date(2025,10,9),"opened","vikhyath",9836774457L),
                new LoanProduct(120,400000.0,new Date(2023,12,30),"closed","vignesh",908765432L),
        };
         arrayListLoanProduct.addAll(Stream.of(loan).collect(Collectors.toList()));
         mainClass.writeFile();
         logger.info(resourceBundle.getString("write.successful"));
         while(true){
             System.out.println("Welcome to My Banking");
             System.out.println("1->Add New Loan");
             System.out.println("2->Available loans");
             System.out.println("3->Closed Loans");
             System.out.println("Enter your choice");
             int choice = scanner.nextInt();
             switch (choice){
                 case 1:
                     System.out.println("Enter the loan Number");
                     Integer loanNumber = scanner.nextInt();
                     System.out.println("Enter the loan Amount");
                     Double loanAmount = scanner.nextDouble();
                     System.out.println("Enter the loan Date");
                     System.out.println("Enter the Day");
                     int day = scanner.nextInt();
                     System.out.println("Enter the Month");
                     int month = scanner.nextInt();
                     System.out.println("Enter the Year");
                     int year = scanner.nextInt();
                     Date date = new Date(year,month,day);
                     System.out.println("Enter the loan Status");
                     String status= scanner.next();
                     System.out.println("Enter the borrower Name");
                     String name= scanner.next();
                     System.out.println("Enter the borrower number");
                     Long mobileNumber= scanner.nextLong();
                     mainClass.addNewLoan(new LoanProduct(loanNumber,loanAmount,date,status,name,mobileNumber));
                     break;
                 case 2:mainClass.availableLoans();
                        logger.info(resourceBundle.getString("available.loans"));
                        break;
                 case 3: mainClass.closedLoans();
                        break;
                 default:
                     System.out.println("Invalid choice");
                     return;
             }
         }


    }


    @Override
    public void writeFile() {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("loan.doc");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayListLoanProduct);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void readFile() {
        try{
            FileInputStream fileInputStream = new FileInputStream("loan.doc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayListLoanProduct = (ArrayList<LoanProduct>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void addNewLoan(LoanProduct loanProduct) {
        try{
            readFile();
            arrayListLoanProduct.add(loanProduct);
            System.out.println("The new Loan is inserted Successfully");
            writeFile();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void availableLoans() {
        try{
            readFile();
            List<LoanProduct> loanProducts = arrayListLoanProduct.stream().filter(each->each.getLoanStatus().equals("opened")).collect(Collectors.toList());
            loanProducts.forEach(System.out::println);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void closedLoans() {
        try{
            readFile();
            List<LoanProduct> loans = arrayListLoanProduct.stream().filter(each->each.getLoanStatus().equals("closed")).collect(Collectors.toList());
            loans.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
