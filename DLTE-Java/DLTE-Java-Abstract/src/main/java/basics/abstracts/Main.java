package basics.abstracts;
class ThreadExtends extends Thread{
    public void run(){
        int counter=0;
        counter = counter+1;
        System.out.println("The counter value is "+counter);
    }

}

class ThreadRunnable implements Runnable{
    int counter=0;
    public void run(){
        counter = counter+1;
        System.out.println(counter);
    }

}

public class Main {


    public static void main(String[] args) throws Exception{
            ThreadExtends thread1 = new ThreadExtends();
            ThreadExtends thread2 = new ThreadExtends();
            thread1.start();
            thread2.start();

            Thread.sleep(1000);


            ThreadRunnable run = new ThreadRunnable();
            Thread run1 = new Thread(run);
            Thread run2 = new Thread(run);
            run1.start();
            run2.start();
    }
}
