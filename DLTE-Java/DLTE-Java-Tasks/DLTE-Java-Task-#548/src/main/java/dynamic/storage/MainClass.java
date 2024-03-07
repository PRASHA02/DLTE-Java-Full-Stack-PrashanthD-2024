package dynamic.storage;

import sun.applet.Main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass  {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();
    public static void main(String[] args){

            MainClass mainClass = new MainClass();
            Transaction transaction1 = new Transaction(new Date(2023, 04, 4), 2100.0, "vineeth", "water");
            Transaction transaction2 = new Transaction(new Date(2024, 04, 4), 1000.25, "ajay", "payment");
            Transaction transaction3 = new Transaction(new Date(2025, 04, 4), 1000000.0, "vignesh", "grocery");

            mainClass.transactions = (ArrayList<Transaction>) Stream.of(transaction1, transaction2, transaction3).collect(Collectors.toList());
            System.out.println("Welcome to My Transaction Analysis");
            System.out.println("1->filter date based on range\n" + "2->Display least amount \n" + "3-> Display maximum amount\n" + "4-> Property and order \n");
            int choice = scanner.nextInt();
            //CreditCardAnalysis analysis = new CreditCardAnalysis();
            switch (choice) {
                case 1:
                    System.out.println("The filtered range of date  is " + rangeDate(new Date(2021, 02, 2), new Date(2025, 04, 4)));
                    break;
                case 2:
                    System.out.println("The least amount is " + leastAmount());
                    break;
                case 3:
                    System.out.println("The maximum amount is " + maxAmount());
                    break;
                case 4:
                    System.out.println("property->date,amount,to,remark\norder-ascending,descending");
                    TransactionComparator compare = new TransactionComparator("amount:ascending");
                    Collections.sort(mainClass.transactions, compare);
                    mainClass.transactions.forEach(System.out::println);
                    break;
            }
    }
    public  static List<Transaction> rangeDate(Date start, Date end){
        List<Transaction> transactionList = transactions.stream().filter(each -> each.getTransactionDate().after(start) && each.getTransactionDate().before(end)).collect(Collectors.toList());
        return transactionList;
    }

    public  static Double leastAmount(){
        Transaction transactionLeastAmount = transactions.stream().min(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
        return transactionLeastAmount.getAmountInTransaction();
    }

    public static Double maxAmount(){
        Transaction transactionMaxAmount = transactions.stream().max(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
        return transactionMaxAmount.getAmountInTransaction();
    }
}
