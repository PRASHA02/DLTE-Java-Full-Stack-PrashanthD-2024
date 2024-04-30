package debit.cards.web.mybankdebitcard.rest;

import debit.cards.dao.entities.Account;
import debit.cards.dao.exceptions.AccountException;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.dao.security.CardSecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/account")
public class AccountDisplay {
    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private CardSecurityServices cardSecurityServices;

    private static final Logger logger = LoggerFactory.getLogger(UpdateCardLimit.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("card");

    @GetMapping("/list")
    public ResponseEntity<?> getAccountList() {
        List<Account> accountList = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            accountList = debitCardRepository.accountList(username);
            logger.info(resourceBundle.getString("account.fetch.success"));
        } catch (AccountException e) {
            logger.error(resourceBundle.getString("account.list.null"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("account.list.null"));
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("internal.error"));
        } catch (SQLSyntaxErrorException exception) {
            logger.warn(resourceBundle.getString("sql.syntax.invalid"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("sql.syntax.invalid"));
        }
        return ResponseEntity.ok(accountList);
    }
}
