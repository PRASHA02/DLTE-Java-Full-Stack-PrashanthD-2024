package debit.cards.application.cardspringboot.dao.services;
//
//import list.cards.elementspringboot.dao.entities.DebitCard;
//import list.cards.elementspringboot.dao.exceptions.DebitCardException;
//import list.cards.elementspringboot.dao.services.DebitCardServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLException;
//import java.util.List;
//
//
//import list.cards.elementspringboot.dao.entities.DebitCard;
//import list.cards.elementspringboot.dao.exceptions.DebitCardException;
//import list.cards.elementspringboot.dao.services.DebitCardServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/debit-cards")
//public class DebitCardController {
//
//        private final DebitCardServices debitCardServices;
//
//        @Autowired
//        public DebitCardController(DebitCardServices debitCardServices) {
//                this.debitCardServices = debitCardServices;
//        }
//
//        @GetMapping
//        public ResponseEntity<List<DebitCard>> getAllDebitCards() {
//                try {
//                        List<DebitCard> debitCards = debitCardServices.getDebitCard();
//                        System.out.println(debitCards.toString());
//                        return ResponseEntity.ok(debitCards);
//
//                } catch (DebitCardException e) {
//                        e.printStackTrace(); // Log the exception
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//                }
//        }
//}
//
