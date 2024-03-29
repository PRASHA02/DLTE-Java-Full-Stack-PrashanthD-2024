package soap.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.List;


public class EndPoint {
   private static String url = "http://localhost:8083/soap/transactions";
    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        System.out.println("url hosted "+url);
        Endpoint.publish(url,transaction);

    }

}
