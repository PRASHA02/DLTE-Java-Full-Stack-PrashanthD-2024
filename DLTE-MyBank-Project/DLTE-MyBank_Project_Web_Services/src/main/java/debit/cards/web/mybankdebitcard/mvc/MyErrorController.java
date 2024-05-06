package debit.cards.web.mybankdebitcard.mvc;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) throws UnsupportedEncodingException {
        Integer errorCode;
        String errorMessage = null;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            errorCode = Integer.valueOf(status.toString());

            if (errorCode == HttpStatus.NOT_FOUND.value()) {
                errorMessage="Requested page is not available, Page Under Construction";
                return "redirect:../error?code=" + errorCode + "&message=" + URLEncoder.encode(errorMessage, "UTF-8");
            } else if (errorCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMessage="Internal Server Error !! Reload the Page";
                return "redirect:../error?code=" + errorCode + "&message=" + URLEncoder.encode(errorMessage, "UTF-8");
            }
        }
        return "error";
    }
}
