
package soap.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Transaction", targetNamespace = "http://services.soap/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Transaction {


    /**
     * 
     * @param transactionListJson
     * @return
     *     returns soap.services.GroupTransaction
     */
    @WebMethod
    @WebResult(name = "transactionListJson", partName = "transactionListJson")
    @Action(input = "http://services.soap/Transaction/receiveTransactionsRequest", output = "http://services.soap/Transaction/receiveTransactionsResponse")
    public GroupTransaction receiveTransactions(
        @WebParam(name = "transactionListJson", partName = "transactionListJson")
        String transactionListJson);

}