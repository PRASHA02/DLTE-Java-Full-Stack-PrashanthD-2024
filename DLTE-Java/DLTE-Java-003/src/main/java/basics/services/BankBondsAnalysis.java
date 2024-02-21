package basics.services;

public class BankBondsAnalysis {
    public static void main(String[] args) {
        BankBonds[] bonds = {
                new BankBonds(100000.0,8.0,true,"prashanth",4),
                new BankBonds(50000.0,3.0,true,"vignesh",2),
                new BankBonds(800000.0,10.0,false,"elroy",5),
                new BankBonds(10000.0,3.0,true,"varun",1),
                new BankBonds(40000.0,4.5,true,"shreyas",1),

        };
        BankBondsAnalysis bondsAnalysis = new BankBondsAnalysis();
        bondsAnalysis.sort(bonds);
    }
    //sorting and viewing the intersest amount
    public void sort(BankBonds[] bonds){
        System.out.println("The sorting is given below: ");
        BankBonds temp=null;
        for(int select=0;select<bonds.length-1;select++){
            for(int next = select +1;next<bonds.length;next++){
               if(bonds[select].getInterestRate().compareTo(bonds[next].getInterestRate())<0){
                   temp = bonds[select];
                   bonds[select] = bonds[next];
                   bonds[next] = temp;
               }
            }
        }
        for(BankBonds each:bonds){
            System.out.println(each);
        }
    }
}
