package generics.credit;

import java.util.Date;

public class MainClass {
    public static void main(String[] args) {
        MyBankDatabase<CreditCard> myBankDatabaseCreditCard = new MyBankDatabase<>();
        MyBankDatabase<Transaction> myBankDatabaseTransaction = new MyBankDatabase<>();

        myBankDatabaseCreditCard.myObjects = new CreditCard[3];
        CreditCard creditCard1 = new CreditCard(123456244L,123,1234,new Date(2023,04,4));
        CreditCard creditCard2 = new CreditCard(123457323L,586,3213,new Date(2025,04,9));
        CreditCard creditCard3 = new CreditCard(123456213L,604,4321,new Date(2028,04,21));
        CreditCard creditCard4 = new CreditCard(123456213L,705,4321,new Date(2028,04,21));
        System.out.println(myBankDatabaseCreditCard.insertNewRecord(creditCard1));
        System.out.println(myBankDatabaseCreditCard.insertNewRecord(creditCard2));
        System.out.println(myBankDatabaseCreditCard.insertNewRecord(creditCard3));

        myBankDatabaseCreditCard.viewAll();

        System.out.println(myBankDatabaseCreditCard.read(0));

        myBankDatabaseCreditCard.delete(1);

        myBankDatabaseCreditCard.update(1,creditCard4);

        myBankDatabaseTransaction.myObjects = new Transaction[2];
        Transaction transaction1 = new Transaction(new Date(2023,04,4),2100.0,"vineeth","water");
        Transaction transaction2 = new Transaction(new Date(2024,04,4),5055.5,"ajay","payment");
        Transaction transaction3 = new Transaction(new Date(2025,04,4),1000000.0,"vignesh","grocery");
        System.out.println(myBankDatabaseTransaction.insertNewRecord(transaction1));
        System.out.println(myBankDatabaseTransaction.insertNewRecord(transaction2));
        System.out.println(myBankDatabaseTransaction.insertNewRecord(transaction3));

        myBankDatabaseTransaction.viewAll();

        System.out.println(myBankDatabaseTransaction.read(0));

        myBankDatabaseTransaction.delete(1);

        myBankDatabaseTransaction.update(0,transaction3);


    }
}
