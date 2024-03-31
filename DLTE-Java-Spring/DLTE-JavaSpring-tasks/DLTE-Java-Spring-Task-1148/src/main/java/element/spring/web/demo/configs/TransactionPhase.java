package element.spring.web.demo.configs;

import element.spring.web.demo.dao.TransactionServices;
import element.spring.web.demo.dao.Transactions;
import links.transactions.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

//final endpoint:-http://localhost:8082/transRepo/transactions.wsdl

@Endpoint
public class TransactionPhase {
    private final String url = "http://transactions.links";

    @Autowired
    private TransactionServices transactionServices;

    //insertion
    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url,localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse = new NewTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        links.transactions.Transactions actual = newTransactionRequest.getTransactions();
        //converted gregorian date to util date
        Date date = newTransactionRequest.getTransactions().getTransactionDate().toGregorianCalendar().getTime();
        Transactions daoTransaction = new Transactions();
        daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actual,daoTransaction);
        daoTransaction = transactionServices.newTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction,actual);
            newTransactionResponse.setTransactions(actual);
            serviceStatus.setMessage(actual.getTransactionId()+" has inserted");
        }else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" has not inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        return newTransactionResponse;
    }
    //filter by sender
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "filterBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterBySenderResponse(@RequestPayload FilterBySenderRequest filterBySenderRequest){
        FilterBySenderResponse filterBySenderResponse =new  FilterBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<links.transactions.Transactions> filterBySender = new ArrayList<>();

        List<Transactions> received = transactionServices.findBySender(filterBySenderRequest.getSender());

        Iterator<Transactions> iterator= received.iterator();

        while(iterator.hasNext()){
            links.transactions.Transactions currentTransactions=new links.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            filterBySender.add(currentTransactions);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions were fetched");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransactions().addAll(filterBySender);

        return  filterBySenderResponse;
    }
    //filter by receiver
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "filterByReceiverRequest")
    @ResponsePayload
    public FilterByReceiverResponse filterByReceiverResponse(@RequestPayload FilterByReceiverRequest filterByReceiverRequest){
        FilterByReceiverResponse filterByReceiverResponse =new  FilterByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<links.transactions.Transactions> filterByReceiver = new ArrayList<>();

        List<Transactions> received = transactionServices.findByReceiver(filterByReceiverRequest.getReceiver());

        Iterator<Transactions> iterator= received.iterator();

        while(iterator.hasNext()){
            links.transactions.Transactions currentTransactions=new links.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            filterByReceiver.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions were fetched");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransactions().addAll(filterByReceiver);

        return  filterByReceiverResponse;
    }
    //filter by Amount
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url,localPart = "filterByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterByAmountResponse(@RequestPayload FilterByAmountRequest filterByAmountRequest){
        FilterByAmountResponse filterByAmountResponse =new  FilterByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<links.transactions.Transactions> filterByAmount = new ArrayList<>();

        List<Transactions> received = transactionServices.findByAmount(filterByAmountRequest.getAmount());

        Iterator<Transactions> iterator= received.iterator();

        while(iterator.hasNext()){
            links.transactions.Transactions currentTransactions=new links.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            filterByAmount.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions were fetched");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransactions().addAll(filterByAmount);

        return  filterByAmountResponse;
    }
    //update based on remarks
    @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PayloadRoot(namespace = url, localPart = "updateRemarksTransactionRequest")
    @ResponsePayload
    public UpdateRemarksTransactionResponse updateRemarksTransactionResponse(@RequestPayload UpdateRemarksTransactionRequest updateRemarksTransactionRequest){
        UpdateRemarksTransactionResponse updateRemarksTransactionResponse=new UpdateRemarksTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        links.transactions.Transactions  updateTransactionRemarks=new links.transactions.Transactions();

        Transactions daoTransaction=new Transactions();
        BeanUtils.copyProperties(updateRemarksTransactionRequest.getTransactions(),daoTransaction);

        daoTransaction = transactionServices.findByRemarks(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+" has updated loan details");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+" hasn't updated loan details");
        }

        BeanUtils.copyProperties(daoTransaction,updateTransactionRemarks);

        updateRemarksTransactionResponse.setServiceStatus(serviceStatus);
        updateRemarksTransactionResponse.setTransactions(updateTransactionRemarks);

        return updateRemarksTransactionResponse;
    }

    //remove based on dates
    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "removeByDateRequest")
    @ResponsePayload
    public RemoveByDateResponse removeByDateResponse(@RequestPayload RemoveByDateRequest removeByDateRequest) throws DatatypeConfigurationException {
        RemoveByDateResponse removeByDateResponse = new RemoveByDateResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Date startDate=removeByDateRequest.getStart().toGregorianCalendar().getTime();
        Date endDate=removeByDateRequest.getEnd().toGregorianCalendar().getTime();
        String result = transactionServices.removeByDate(startDate,endDate);
        if (result.equals("removed")) {
            serviceStatus.setStatus("removed the transactions");
        } else {
            serviceStatus.setStatus("not able to remove");
        }
        serviceStatus.setMessage(result);
        removeByDateResponse.setServiceStatus(serviceStatus);

        return removeByDateResponse;
    }


}
