package soap.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Transaction {
    @WebMethod
    @WebResult(name = "transactionListJson")
    public GroupTransaction receiveTransactions(@WebParam(name = "transactionListJson") String transactionListJson) {
        // Deserialize the JSON string to get the list of transactions
        Gson gson = new Gson();
        List<Transaction> transactions = gson.fromJson(transactionListJson, new TypeToken<List<Transaction>>(){}.getType());
        GroupTransaction groupTransaction = new GroupTransaction();
        groupTransaction.setTransactionList(transactions);
        return groupTransaction;
    }
}
