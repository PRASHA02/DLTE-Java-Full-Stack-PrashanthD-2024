



package basics.services;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //initialization
        TransactionClass[] amount = {
               new TransactionClass(new Date(2024,02,29),200000.0,"Prashanth","Education"),
                new TransactionClass(new Date(2025,04,9),80000.0,"Vignesh","Family"),
                new TransactionClass(new Date(2026,01,30),100000.0,"Shreyas","Emergency"),
                new TransactionClass(new Date(2032,07,19),2000.0,"Elroy","Bills"),
                new TransactionClass(new Date(2030,02,9),50000.0,"Rakesh","Friend")
        };

        TransactionAnalysis analysis = new TransactionAnalysis();
        //Menu
        System.out.println("Enter your Choice :\n1.Analysis\n2.Sorting");
        int choice = scanner.nextInt();
        switch(choice){
            //Analysis
            case 1: System.out.println("Enter the start date");
                int start = scanner.nextInt();
                System.out.println("Enter the stop date");
                int stop = scanner.nextInt();
                analysis.range(amount,start,stop);
                analysis.leastAmountTransfered(amount);
                analysis.maximumAmount(amount);
                System.out.println("Enter the beneficiary name");
                String beneficiaryName = scanner.next();
                analysis.transactionBeneficiary(amount,beneficiaryName);
                System.out.println("Enter the remarks name");
                String remarks = scanner.next();
                analysis.transactionBeneficiary(amount,remarks);
                break;
             //Sorting
            case 2: System.out.println("The Beneficiary name in descending is: ");
                analysis.BeneficiaryDescending(amount);
                System.out.println();
                System.out.println("The Amount  in ascending is: ");
                analysis.amountAscending(amount);
                break;
            default:
                System.out.println("Invalid Identifier");
        }

    }
    
    public void range(TransactionClass[] amount,int start,int stop){
        for(TransactionClass each: amount){
            if(each.getDate().getDate()>=start && each.getDate().getDate()<=stop){
                System.out.println(each.getDate().getDate()+" "+each.getTransactionAmount()+" "+each.getReceipt()+" "+each.getRemarks());
            }
        }

    }
    public void leastAmountTransfered(TransactionClass[] amount){
        Double min = amount[0].getTransactionAmount();
        for(int select=1;select<amount.length;select++){
            if(amount[select].getTransactionAmount()<=min){
                min = amount[select].getTransactionAmount();
            }
        }
        System.out.println("The least amount is "+min);
    }

    public void maximumAmount(TransactionClass[] amount){
        Double max = amount[0].getTransactionAmount();
        for(int select=1;select<amount.length;select++){
            if(amount[select].getTransactionAmount()>=max){
                max = amount[select].getTransactionAmount();
            }
        }
        System.out.println("The least amount is "+max);
    }
    public void transactionBeneficiary(TransactionClass[] amount,String beneficiaryName){
        for(TransactionClass each:amount){
            if(each.getReceipt().equalsIgnoreCase(beneficiaryName)){
                System.out.println("The amount returned is "+ each.getTransactionAmount());
            }
        }
    }
    public  void marks(TransactionClass[] amount,String remarks){
        for(TransactionClass each:amount){
            if(each.getReceipt().equalsIgnoreCase(remarks)){
                System.out.println("The remarks returned is "+ each.getRemarks());
            }
        }
    }

    public void BeneficiaryDescending(TransactionClass[] amount){
        TransactionClass backup = null;
        for(int select=0;select<amount.length-1;select++){
            for(int next = select+1;next<amount.length;next++){
                if(amount[select].getReceipt().compareTo(amount[next].getReceipt())<0){
                    backup = amount[select];
                    amount[select] = amount[next];
                    amount[next] = backup;
                }
            }
        }
        for(TransactionClass each:amount){
            System.out.println(each);
        }
    }

    public void amountAscending(TransactionClass[] amount){

        TransactionClass backup = null;
        for(int select=0;select<amount.length-1;select++){
            for(int next = select+1;next<amount.length;next++){
                if(amount[select].getTransactionAmount().compareTo(amount[next].getTransactionAmount())>0){
                    backup = amount[select];
                    amount[select] = amount[next];
                    amount[next] = backup;
                }
            }
        }
        for(TransactionClass each:amount){
            System.out.println(each);
        }
    }
}
