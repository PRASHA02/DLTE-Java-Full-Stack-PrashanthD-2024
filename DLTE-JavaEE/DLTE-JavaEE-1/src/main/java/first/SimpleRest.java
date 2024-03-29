//package first;
////
//import com.google.gson.Gson;
//import soap.services.GroupTransaction;
//import soap.services.TransactionService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@WebServlet("/simple")
//public class SimpleRest extends HttpServlet {
//    List<Transaction> transactionList = Stream.of(
//            new Transaction(new Date(2024, 11, 9), 10000.0, "vineeth", "Family"),
//            new Transaction(new Date(2025, 02, 19), 50000.0, "Elroy", "Education"),
//            new Transaction(new Date(2022, 12, 18), 10000.0, "Varun", "Bills"),
//            new Transaction(new Date(2025, 06, 5), 10000.0, "Vignesh", "Emergency"),
//            new Transaction(new Date(2024, 2, 20), 20000.0, "Shreyas", "Friends")
//    ).collect(Collectors.toList());
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        // Serialize transactionList to JSON
//        Gson gson = new Gson();
//        String transactionListJson = gson.toJson(transactionList);
//
//        // SOAP endpoint URL
//        String soapEndpointUrl = "http://localhost:8083/soap/transactions";
//
//        // Make SOAP request
//        URL url = new URL(soapEndpointUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "text/xml");
//        connection.setDoOutput(true);
//
//        // Write transactionList JSON to SOAP request body
//        OutputStream outputStream = connection.getOutputStream();
//        outputStream.write(transactionListJson.getBytes());
//        outputStream.flush();
//
//        TransactionService transactionService = new TransactionService();
//        soap.services.Transaction transaction = transactionService.getTransactionPort();
//        //GroupTransaction groupTransaction = transaction.receiveTransactions(transactionListJson);
//        List<soap.services.Transaction_Type> groupTransaction = transaction.receiveTransactions(transactionListJson).getTransactionList();
//
//
//        // Handle SOAP response
//        resp.setContentType("application/json");
//        String transactionJson = gson.toJson(groupTransaction);
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println(transactionJson);
//
//}
//}
//
//
