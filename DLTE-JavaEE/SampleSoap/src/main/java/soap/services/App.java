package soap.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Hello world!
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class App 
{
    @WebMethod
    public String handleRestRequest() {
        // Simulating processing of the REST request
        return "SOAP service processed the REST request successfully!";
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
