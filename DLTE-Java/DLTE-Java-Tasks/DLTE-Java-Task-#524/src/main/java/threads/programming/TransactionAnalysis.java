package threads.programming;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis implements  Runnable{

    static Transaction[] transaction = {
            new Transaction(new Date(2024,02,29),200000.0,"Prashanth","Education"),
            new Transaction(new Date(2025,04,9),80000.0,"Vignesh","Family"),
            new Transaction(new Date(2026,01,30),100000.0,"Shreyas","Emergency"),
            new Transaction(new Date(2032,07,19),2000.0,"Elroy","Bills"),
            new Transaction(new Date(2030,02,9),50000.0,"Rakesh","Friend")
    };

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        TransactionAnalysis analysis = new TransactionAnalysis();
        //Menu
        System.out.println("Enter your Choice :\n1.Analysis\n2.Sorting");
        int choice = scanner.nextInt();
        switch(choice){
            //Analysis
            case 1: System.out.println("Enter the start date");
                Integer start = scanner.nextInt();
                System.out.println("Enter the stop date");
                int stop = scanner.nextInt();
                analysis.range(transaction,start,stop);
                analysis.leastAmountTransferred(transaction);
                analysis.maximumAmount(transaction);
                System.out.println("Enter the beneficiary name");
                String beneficiaryName = scanner.next();
                analysis.transactionBeneficiary(transaction,beneficiaryName);
                System.out.println("Enter the remarks name");
                String remarks = scanner.next();
                analysis.transactionBeneficiary(transaction,remarks);
                break;
            //Sorting
            case 2: System.out.println("The Beneficiary name in descending is: ");
                analysis.BeneficiaryDescending(transaction);
                System.out.println();
                System.out.println("The Amount  in ascending is: ");
                analysis.amountAscending(transaction);
                break;
            default:
                System.out.println("Invalid Identifier");
        }
    }

    public void range(Transaction[] transaction,int start,int stop){
        for(Transaction each:transaction){
            if(each.getDateOfTransaction().getDate()>=start && each.getDateOfTransaction().getDate()<=stop){
                System.out.println(each.getDateOfTransaction()+" "+each.getAmountInTransaction()+" "+each.getTo()+" "+ each.getRemarks());
            }
        }
    }

    public void leastAmountTransferred(Transaction[] transaction){
        Double minAmount = transaction[0].getAmountInTransaction();
        for(int select=1;select<transaction.length;select++){
            if(transaction[select].getAmountInTransaction()<=minAmount){
                minAmount = transaction[select].getAmountInTransaction();
            }
        }
        System.out.println("The minimum transaction amount is "+minAmount);

    }
    public void maximumAmount(Transaction[] transaction){
        Double maximumAmount = transaction[0].getAmountInTransaction();
        for(int select=1;select<transaction.length;select++){
            if(transaction[select].getAmountInTransaction()<=maximumAmount){
                maximumAmount = transaction[select].getAmountInTransaction();
            }
        }
        System.out.println("The minimum transaction amount is "+maximumAmount);

    }

    public void transactionBeneficiary(Transaction[] transaction,String beneficiaryName){
        for(Transaction each: transaction){
            if(each.getTo().equalsIgnoreCase(beneficiaryName)){
                System.out.println("The amount returned is "+ each.getAmountInTransaction());
            }
        }
    }
    public  void marks(Transaction[] transaction,String remarks){
        for(Transaction each: transaction){
            if(each.getRemarks().equalsIgnoreCase(remarks)){
                System.out.println("The remarks returned is "+ each.getRemarks());
            }
        }
    }

    public void BeneficiaryDescending(Transaction[] transaction){
        Transaction backup = null;
        for(int select=0;select<transaction.length-1;select++){
            for(int next = select+1;next<transaction.length;next++){
                if(transaction[select].getTo().compareTo(transaction[next].getTo())<0){
                    backup = transaction[select];
                    transaction[select] = transaction[next];
                    transaction[next] = backup;
                }
            }
        }
        for(Transaction each:transaction){
            System.out.println(each.getTo());
        }
    }

    public void amountAscending(Transaction[] transaction){

        Transaction backup = null;
        for(int select=0;select<transaction.length-1;select++){
            for(int next = select+1;next<transaction.length;next++){
                if(transaction[select].getAmountInTransaction().compareTo(transaction[next].getAmountInTransaction())>0){
                    backup = transaction[select];
                    transaction[select] = transaction[next];
                    transaction[next] = backup;
                }
            }
        }
    }

    public void displayReceipt(){
        System.out.println("The details are ");
        for(Transaction each:transaction){
            System.out.println(each.getTo());
        }
    }

    public void displayAmount(){
        System.out.println("The details are ");
        for(Transaction each:transaction){
            System.out.println(each.getAmountInTransaction());
        }
    }
    public void displayRemarks(){
        System.out.println("The details are ");
        for(Transaction each:transaction){
            System.out.println(each.getRemarks());
        }
    }

}

