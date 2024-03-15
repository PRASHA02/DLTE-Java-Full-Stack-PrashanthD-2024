package casics.services;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class MyUserClient {
    Context context;
    public static void main(String[] args) throws NamingException, RemoteException {
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:3000");
        Context context=new InitialContext(properties);
        MyUserInfoFunctions myCardServer = (MyUserInfoFunctions) context.lookup("java:/userfilter");
        myCardServer.findAll().forEach(System.out::println);
    }
}
