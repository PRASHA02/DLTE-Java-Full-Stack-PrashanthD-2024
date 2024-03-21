package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/transaction/*")
public class MainTransaction extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet is initialized");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Servlet destroyed");
    }

    //array list using streams
    List<Transaction> transactionList = Stream.of(new Transaction(new Date(2024,11,9),10000.0,"vineeth","Family"),
            new Transaction(new Date(2025,02,19),50000.0,"Elroy","Education"),
            new Transaction(new Date(2022,12,18),10000.0,"Varun","Bills"),
            new Transaction(new Date(2025,06,5),10000.0,"Vignesh","Emergency"),
            new Transaction(new Date(2024,2,20),20000.0,"Shreyas","Friends")).collect(Collectors.toList());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //initializing the parameters in url
        String minimumAmount = req.getParameter("min");
        String maxAmount = req.getParameter("max");

        if (minimumAmount!= null && maxAmount != null) {
            double minimum = Double.parseDouble(minimumAmount);
            double maximum = Double.parseDouble(maxAmount);

            List<Transaction> transactions = transactionList.stream()
                    .filter(each -> each.getAmountInTransaction() >= minimum && each.getAmountInTransaction() <= maximum)
                    .collect(Collectors.toList());

            Gson gson = new Gson();
            resp.setContentType("application/json");
            String transactionJson = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(transactionJson);
        } else {

            Gson gson = new Gson();
            resp.setContentType("application/json");
            String transJson = gson.toJson(transactionList);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(transJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creating Transaction
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionList.add(transaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(transaction.getTo()+" has added to the records");
    }
}
