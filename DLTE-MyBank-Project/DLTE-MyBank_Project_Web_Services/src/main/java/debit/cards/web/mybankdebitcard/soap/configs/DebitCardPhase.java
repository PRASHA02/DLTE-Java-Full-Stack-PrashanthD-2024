package debit.cards.web.mybankdebitcard.soap.configs;


import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.dao.services.DebitCardServices;
import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ComponentScan("debit.cards.dao.services")
@Endpoint
public class DebitCardPhase {

    private final String url = "http://debitcard.links";
    @Autowired
    private DebitCardServices debitCardServices;

    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException {
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<links.debitcard.DebitCard> debitCardList = new ArrayList<>();

        List<debit.cards.dao.entities.DebitCard> debitCardsDao = debitCardServices.getDebitCard();

        debitCardsDao.forEach(debitCard -> {
            links.debitcard.DebitCard currentDebitCard =new links.debitcard.DebitCard();
            BeanUtils.copyProperties(debitCard,currentDebitCard);
            debitCardList.add(currentDebitCard);
        });

//        Iterator<debit.cards.dao.entities.DebitCard> iterator = debitCardsDao.iterator();
//
//        while(iterator.hasNext()){
//            links.debitcard.DebitCard currentDebitCard =new links.debitcard.DebitCard();
//            BeanUtils.copyProperties(iterator.next(),currentDebitCard);
//            debitCardList.add(currentDebitCard);
//        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Debit Cards were fetched");

        viewDebitCardResponse.setServiceStatus(serviceStatus);
        viewDebitCardResponse.getDebitCard().addAll(debitCardList);

        return  viewDebitCardResponse;
    }

//
}
