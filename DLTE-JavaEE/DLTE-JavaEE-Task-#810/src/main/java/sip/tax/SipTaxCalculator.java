package sip.tax;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sip/*")
public class SipTaxCalculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //        super.doGet(req, resp);
//        int principle = Integer.parseInt(req.getParameter("principle"));
//        Double interestRate = Double.parseDouble(req.getParameter("interestRate"));
//        int period = Integer.parseInt(req.getParameter("period"));
//        Double estimatedAmount = 0.0,totalAmount=0.0;
//        interestRate = interestRate / (12*100);
//        period *=12;
//        totalAmount = (principle*((Math.pow(1+interestRate,period)-1)/interestRate)*(1+interestRate));
//        estimatedAmount = totalAmount - principle;
//        resp.setContentType("application/json");
//        Gson gson = new Gson();
//        String totalAmountMessage = gson.toJson(totalAmount);
//        resp.getWriter().println("The total Amount = "+totalAmountMessage);
//        String estimatedAmountMessage = gson.toJson(estimatedAmount);
//        resp.getWriter().println("Estimated Amount = "+estimatedAmountMessage);
          resp.getWriter().println("hello");
    }


}
