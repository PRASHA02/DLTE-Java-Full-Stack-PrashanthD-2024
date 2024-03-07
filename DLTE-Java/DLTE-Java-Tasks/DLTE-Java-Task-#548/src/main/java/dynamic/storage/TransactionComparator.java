package dynamic.storage;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {
    private String byAttribute;
    private String orderBy;

    TransactionComparator(String userOrder){
        String[] splitting = userOrder.split(":");
        byAttribute = splitting[0];
        orderBy = splitting[1];
    }

    @Override
    public int compare(Transaction o1, Transaction o2) {
        int returnedOrder=0;
        switch(byAttribute){
            case "remarks": case "Remarks": case "REMARKS":returnedOrder = o1.getRemarks().compareTo(o2.getRemarks());break;

            case "transactiondate": case "TransactionDate": case "transactionDate": case "TRANSACTIONDATE":returnedOrder = o1.getTransactionDate().compareTo(o2.getTransactionDate());break;

            case "amount": case "Amount": case "AMOUNT":returnedOrder = o1.getAmountInTransaction().compareTo(o2.getAmountInTransaction());break;

            case "to": case "TO": case "To":returnedOrder = o1.getSentTo().compareTo(o2.getSentTo());break;
        }
        return orderBy.equals("ascending")?returnedOrder:-returnedOrder;
    }
}
