package element.spring.boot.springboot;

public class TransactionException extends RuntimeException {

    public TransactionException(){
        super("Transaction failed due to some errors");
    }

    public TransactionException(String message){
        super(message);
    }
}
