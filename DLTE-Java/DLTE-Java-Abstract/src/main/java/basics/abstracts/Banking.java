package basics.abstracts;

public abstract class Banking {
    Banking(){
        System.out.println("Payment through Banking");
    }
    public abstract void payment();
//    public void upi(){
//        System.out.println("Base class Upi");
//    }

}

class NetBanking extends Banking{
//    public void upi(){
//        System.out.println("NetBanking upi");
//    }
    public void payment(){
        System.out.println("Payment through Net banking");
    }
}

class MobileBanking extends Banking{
    public void payment(){
        System.out.println("Payment through Mobile banking");
    }
}

class Test {
    public static void main(String[] args) {
        NetBanking bank = new NetBanking();
        bank.payment();
        MobileBanking banking = new MobileBanking();
        banking.payment();
    }
}