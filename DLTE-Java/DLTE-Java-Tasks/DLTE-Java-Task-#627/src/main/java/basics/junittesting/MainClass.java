package basics.junittesting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass implements MyBank {

    static ArrayList<LoanProduct> arrayListLoanProduct = new ArrayList<>();

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        Scanner scanner = new Scanner(System.in);
        LoanProduct[] loan = {
                new LoanProduct(117,50000.0,new Date(2024,11,11),"closed","prashanth",789267517L),
                new LoanProduct(111,60000.0,new Date(2025,10,9),"opened","vikhyath",9836774457L),
                new LoanProduct(120,400000.0,new Date(2023,12,30),"closed","vignesh",908765432L),
        };
        arrayListLoanProduct.addAll(Stream.of(loan).collect(Collectors.toList()));
        try{
            while(true){
                System.out.println("Welcome to My Bank");
                System.out.println("1->add new loan\n2->view available loans\n3->view closed loans");
                System.out.println("Enter your Choice");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1: System.out.println("Enter the loan Number");
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
                        break;
                    case 3: mainClass.closedLoans();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    @Override
    public void addNewLoan(LoanProduct loanProduct) {
        try{
            arrayListLoanProduct.add(loanProduct);
            System.out.println("The new Loan is inserted Successfully");
        }catch (Exception e){
            System.out.println(e);
        }



    }

    @Override
    public void availableLoans() {

        List<LoanProduct> loanProducts = arrayListLoanProduct.stream().filter(each-> each.getLoanStatus().equalsIgnoreCase("opened")).collect(Collectors.toList());
        loanProducts.forEach(System.out::println);

    }

    @Override
    public void closedLoans() {
        List<LoanProduct> loanProducts = arrayListLoanProduct.stream().filter(each-> each.getLoanStatus().equalsIgnoreCase("closed")).collect(Collectors.toList());
        loanProducts.forEach(System.out::println);
    }
}
