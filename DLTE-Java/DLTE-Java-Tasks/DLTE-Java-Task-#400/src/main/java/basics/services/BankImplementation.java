package basics.services;

import java.util.Date;
import java.util.Scanner;

public class BankImplementation implements MyBank{
    private LoanProduct[] loans = new LoanProduct[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoanProduct[] loans = new LoanProduct[5];
        BankImplementation bank = new BankImplementation();

        System.out.println("--WELCOME TO MY BANK--");

        System.out.println("Enter your choice\n1->addNewLoans\n2->availableLoans\n3->checkClosedLoans");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:bank.addNewLoan();
                break;
            case 2:bank.checkAvailableLoans();
                break;
            case 3:bank.checkClosedLoans();
                break;
        }

    }



    @Override
    public void addNewLoan() {
        Scanner scanner = new Scanner(System.in);
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
        int temp=0;
        if(temp>loans.length){
            System.out.println("Sorry, Loan Array is full");
        }else{
            loans[temp] = new LoanProduct(loanNumber, loanAmount, date, status, name, mobileNumber);
            System.out.println("The new Loan added successfully");
            System.out.println("Enter the choices to view further\n2->availableLoans\n3->closedLoans");
            int choice = scanner.nextInt();
            if(choice ==2){
                checkAvailableLoans();
            }
            if(choice==3){
                checkClosedLoans();
            }
        }



    }

    @Override
    public void checkAvailableLoans() {
        System.out.println("The available loans are :");
        for(LoanProduct each:loans){
            if(each!=null){
                System.out.println(each.toString());
            }
        }

    }

    @Override
    public void checkClosedLoans() {

        for(LoanProduct each:loans){
            if(each.getLoanStatus().equalsIgnoreCase("closed")){
                if(each!=null){
                    System.out.println(each.toString());
                }
            }
        }

    }
}
