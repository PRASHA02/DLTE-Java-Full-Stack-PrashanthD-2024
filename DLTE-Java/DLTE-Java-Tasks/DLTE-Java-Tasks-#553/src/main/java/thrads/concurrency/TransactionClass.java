package thrads.concurrency;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionClass implements Runnable{
    Lock lock = new ReentrantLock();
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

        TransactionClass analysis = new TransactionClass();
        //Menu
        System.out.println("Enter your Choice :\n1.Analysis");
        int choice = scanner.nextInt();
        lock.lock();
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
                analysis.remarks(transaction,remarks);
                return;
            default:
                System.out.println("Invalid Identifier");
                break;
        }
        lock.unlock();
    }

    public static void range(Transaction[] transaction,int start,int stop){
        for(Transaction each:transaction){
            if(each.getTransactionDate().getDate()>=start && each.getTransactionDate().getDate()<=stop){
                System.out.println(each.getTransactionDate()+" "+each.getAmountInTransaction()+" "+each.getSentTo()+" "+ each.getRemarks());
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
            if(transaction[select].getAmountInTransaction()>=maximumAmount){
                maximumAmount = transaction[select].getAmountInTransaction();
            }
        }
        System.out.println("The minimum transaction amount is "+maximumAmount);

    }

    public void transactionBeneficiary(Transaction[] transaction,String beneficiaryName){
        for(Transaction each: transaction){
            if(each.getSentTo().equalsIgnoreCase(beneficiaryName)){
                System.out.println("The amount returned is "+ each.getAmountInTransaction());
            }
        }
    }
    public  void remarks(Transaction[] transaction,String remarks){
        Transaction backup = null;
        for(int select=0;select<transaction.length-1;select++){
            for(int next = select+1;next<transaction.length;next++){
                if(transaction[select].getRemarks().compareTo(transaction[next].getRemarks())>0){
                    backup = transaction[select];
                    transaction[select] = transaction[next];
                    transaction[next] = backup;
                }
            }
        }
        for(Transaction each:transaction){
            System.out.println(each.toString());
        }
    }


}
