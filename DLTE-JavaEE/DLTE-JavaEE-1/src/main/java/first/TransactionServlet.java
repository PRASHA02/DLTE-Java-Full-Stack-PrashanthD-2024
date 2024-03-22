package first;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//http://localhost:7001/DLTE-JavaEE-1/
@WebServlet("/test")
public class TransactionServlet extends HttpServlet {
    // Sample transaction data
    List<Transaction> transactionList = Stream.of(
            new Transaction(new Date(2024, 11, 9), 10000.0, "vineeth", "Family"),
            new Transaction(new Date(2025, 02, 19), 50000.0, "Elroy", "Education"),
            new Transaction(new Date(2022, 12, 18), 10000.0, "Varun", "Bills"),
            new Transaction(new Date(2025, 06, 5), 10000.0, "Vignesh", "Emergency"),
            new Transaction(new Date(2024, 2, 20), 20000.0, "Shreyas", "Friends")
    ).collect(Collectors.toList());


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");


        for (Transaction transaction : transactionList) {
            writer.println("<div>");
            writer.println("<p>Date: " + transaction.getDateOfTransaction() + "</p>");
            writer.println("<p>Amount: " + transaction.getAmountInTransaction() + "</p>");
            writer.println("<p>Name: " + transaction.getTo() + "</p>");
            writer.println("<p>Type: " + transaction.getRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionList.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getTo() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
