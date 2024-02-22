package oops.concept;

import java.util.Scanner;

public class DebitCard extends Account{

    private Long cardNumber;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getmPin() {
        return mPin;
    }

    public void setmPin(Integer mPin) {
        this.mPin = mPin;
    }

    private Integer mPin;

    public DebitCard(Long accountNumber, Double accountBalance, String accountHolder,Long cardNumber,Integer mPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardNumber = cardNumber;
        this.mPin = mPin;
    }

    public static void main(String[] args){
        DebitCard db = new DebitCard(123456789123L,200000.0,"Prashanth",9980969636L,2002);
        db.withdraw(db);
    }

   public void withdraw(DebitCard db){
        Scanner scanner = new Scanner(System.in);
        Double amount;
       System.out.println("Enter the money to be withdraw");
       amount = scanner.nextDouble();
           if(db.getAccountBalance().compareTo(amount)>=0){
               System.out.println("Enter the mPin");
               int mpin = scanner.nextInt();
               if(db.getmPin().equals(mpin)){
                   System.out.println("Your withdraw amount is "+(db.getAccountBalance()-amount));

           }else{
                   System.out.println("Your mPin is wrong Please provide the correct mPin!");
               }
       }else{
               System.out.println("Insufficient balance");
           }

   }
}
