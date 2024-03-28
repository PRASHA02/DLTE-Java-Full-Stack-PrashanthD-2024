package web;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class SoapEndPoints {
    private static String url = "http://localhost:5001/prash";
    public static void main(String[] args) {
        SoapEmployee soapEmployee = new SoapEmployee();
        System.out.println("Webservice hosted @ "+ url);
        Endpoint.publish(url,soapEmployee);
    }
}
