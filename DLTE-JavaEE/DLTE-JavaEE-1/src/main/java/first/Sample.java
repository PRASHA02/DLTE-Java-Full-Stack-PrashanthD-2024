package first;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/sample/*")
public class Sample extends GenericServlet {
    //array list using streams
    List<Transaction> transactionList = Stream.of(new Transaction(new Date(2024,11,9),10000.0,"vineeth","Family"),
            new Transaction(new Date(2025,02,19),50000.0,"Elroy","Education"),
            new Transaction(new Date(2022,12,18),10000.0,"Varun","Bills"),
            new Transaction(new Date(2025,06,5),10000.0,"Vignesh","Emergency"),
            new Transaction(new Date(2024,2,20),20000.0,"Shreyas","Friends")).collect(Collectors.toList());



    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        List<Transaction> transactions = transactionList.stream()
                .filter(each -> each.getAmountInTransaction() >= 100 && each.getAmountInTransaction() <= 10000)
                .collect(Collectors.toList());
        Gson gson = new Gson();
        String choice = servletRequest.getParameter("get");

        if (choice != null && choice.equals("get")){
            servletResponse.setContentType("application/html");
            String transactionJson = gson.toJson(transactions);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_OK);
            servletResponse.getWriter().println(transactionJson);
        }
        if (choice != null && choice.equals("post")){

            Transaction transaction = gson.fromJson(servletRequest.getReader(),Transaction.class);
            transactionList.add(transaction);

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_OK);
            servletResponse.getWriter().println(transaction.getTo()+" has added to the records");
        }
        if(!choice.equals("get") && !choice.equals("post") ){
            servletResponse.getWriter().println("Invalid method");
        }

//        Gson gson=new Gson();
//
//        Transaction transaction = gson.fromJson(servletRequest.getReader(),Transaction.class);
//        transactionList.add(transaction);
//
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setStatus(HttpServletResponse.SC_OK);
//        servletResponse.getWriter().println(transaction.getTo()+" has added to the records");
    }

}
