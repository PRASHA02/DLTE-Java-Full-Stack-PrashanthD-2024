package basics.abstracts;


public class ArithmeticException {
    static void display(int a,int b)  {
        if(b==0){
            throw new java.lang.ArithmeticException("Divide by zero is not possible");
        }else{
            int result = a/b;
            System.out.println("The result is "+result);
        }
    }

    public static void main(String[] args) {
        int a =10,b=0;
        display(a,b);

    }
}
