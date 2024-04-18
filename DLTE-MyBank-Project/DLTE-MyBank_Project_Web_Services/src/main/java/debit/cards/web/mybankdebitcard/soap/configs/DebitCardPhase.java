package debit.cards.web.mybankdebitcard.soap.configs;


import debit.cards.dao.exceptions.DebitCardException;
import debit.cards.dao.exceptions.DebitCardNullException;
import debit.cards.dao.remotes.DebitCardRepository;

import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;



// Spring will search for classes annotated within the specified package and register them as Spring beans.
@ComponentScan("debit.cards.dao")
@Endpoint
public class DebitCardPhase {
   //Services for fetching the Debit card Details from Database
    private static final String url = "http://debitcard.links";
    @Autowired
    private DebitCardRepository debitCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(DebitCardPhase.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    //This specifies the Debit Card list to be viewed
    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException {
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try{
           List<links.debitcard.DebitCard> debitCardList = new ArrayList<>();

            List<debit.cards.dao.entities.DebitCard> debitCardsDao = debitCardRepository.getDebitCard();
            //lambda function for performing bean utils
            debitCardsDao.forEach(debitCard -> {
                links.debitcard.DebitCard currentDebitCard =new links.debitcard.DebitCard();
                BeanUtils.copyProperties(debitCard,currentDebitCard);
                debitCardList.add(currentDebitCard);
            });

            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("card.fetch.success"));
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);

        }catch (DebitCardException syntaxError) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(resourceBundle.getString("soap.sql.error") +  syntaxError + HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            serviceStatus.setMessage(resourceBundle.getString("sql.syntax.invalid"));

        }catch (DebitCardNullException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            logger.error(resourceBundle.getString("card.list.null")+ e + HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("card.null.available"));

        }
        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return  viewDebitCardResponse;
    }





//
}


