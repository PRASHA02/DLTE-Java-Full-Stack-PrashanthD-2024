//package first;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.Path;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//@WebServlet("/rest")
//public class SimpleRest extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            // URL of the SOAP service
//            URL url = new URL("http://localhost:8080/soap/SoapService");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            // Reading the response from the SOAP service
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder responseData = new StringBuilder();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                responseData.append(inputLine);
//            }
//            in.close();
//
//            // Set response content type
//            response.setContentType(MediaType.TEXT_PLAIN);
//
//            // Write the response data from SOAP service to the REST response
//            response.getWriter().write(responseData.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle error response
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("Error occurred: " + e.getMessage());
//        }
//    }
//}
//
//
//
