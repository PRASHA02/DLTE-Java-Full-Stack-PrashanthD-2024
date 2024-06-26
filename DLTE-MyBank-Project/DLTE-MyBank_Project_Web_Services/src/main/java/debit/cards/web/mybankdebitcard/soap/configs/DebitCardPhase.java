package debit.cards.web.mybankdebitcard.soap.configs;


import debit.cards.dao.exceptions.DebitCardException;
import debit.cards.dao.remotes.DebitCardRepository;

import debit.cards.dao.security.CustomerServices;
import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;


// Spring will search for classes annotated within the specified package and register them as Spring beans.
@ComponentScan("debit.cards.dao")
@Endpoint
public class DebitCardPhase {
    //Services for fetching the Debit card Details from Database
    private static final String url = "http://debitcard.links";
    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private CustomerServices customerServices;



    private static final Logger logger = LoggerFactory.getLogger(DebitCardPhase.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("webservice");

    //This specifies the Debit Card list to be viewed
    @PayloadRoot(namespace = url, localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {

            List<links.debitcard.DebitCard> debitCardList = new ArrayList<>();

            List<debit.cards.dao.entities.DebitCard> debitCardsDao = debitCardRepository.getDebitCard(username);
            //lambda function for performing bean utils
            debitCardsDao.forEach(debitCard -> {
                links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
                Date date = debitCard.getDebitCardExpiry();
                XMLGregorianCalendar xmlCalendar = null;
                try {
                    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
                    currentDebitCard.setDebitCardExpiry(xmlCalendar);
                } catch (DatatypeConfigurationException | IllegalArgumentException e) {
                    e.printStackTrace();
                }

                BeanUtils.copyProperties(debitCard, currentDebitCard);
                debitCardList.add(currentDebitCard);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("card.fetch.success"));
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);
            logger.info(resourceBundle.getString("card.fetch.success"));
        } catch (SQLException syntaxError) {
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
//            logger.error(resourceBundle.getString("soap.sql.error") + syntaxError.toString() + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(resourceBundle.getString("sql.syntax.invalid"));

        }catch (DebitCardException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("error.one")+resourceBundle.getString("card.null.available"));
        }
        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return viewDebitCardResponse;
    }

}


