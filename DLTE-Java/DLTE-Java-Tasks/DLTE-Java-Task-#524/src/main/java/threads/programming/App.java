package threads.programming;


public class App
{
    public static void main( String[] args ) throws InterruptedException {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        //method reference
        Thread thread1 = new Thread(transactionAnalysis::displayReceipt);
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(transactionAnalysis::displayAmount);
        thread2.start();
        Thread thread3 = new Thread(transactionAnalysis::displayRemarks);
        thread3.start();
        Thread thread4 = new Thread(transactionAnalysis,"prash");
        thread4.start();
        thread4.join();
        Thread thread5 = new Thread(transactionAnalysis,"vignesh");
        thread5.start();
        thread5.join();
        Thread thread6 = new Thread(transactionAnalysis,"prashanth");
        thread6.start();
        thread6.join();
        Thread thread7 = new Thread(transactionAnalysis,"vineeth");
        thread7.start();
        thread7.join();
        Thread thread8 = new Thread(transactionAnalysis,"ajay");
        thread8.start();
        thread8.join();
        Thread thread9 = new Thread(transactionAnalysis,"vignesh");
        thread9.start();
        thread9.join();
        Thread thread10 = new Thread(transactionAnalysis,"shreyas");
        thread10.start();
        thread10.join();
    }
}
