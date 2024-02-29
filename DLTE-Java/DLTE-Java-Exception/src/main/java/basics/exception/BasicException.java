package basics.exception;

import jdk.nashorn.internal.ir.Node;

public class BasicException {
    public static void main(String[] args) throws Exception {

        try {
            String name = null;
            int result = 1/0;

        }catch(ArithmeticException e){

            System.out.println(name.length());

    }catch(NullPointerException exception){
            throw new NullPointerException();
        }
}
}
