package casics.services;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Services.UserInfoServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyUserInfoServer extends UnicastRemoteObject implements MyUserInfoFunctions, Serializable {

    private static Context context;
    private Registry registry;
    private UserInfoServices userInfoServices ;
    @Override
    public List findAll() throws RuntimeException {
        List<List> customerArrayList = userInfoServices.callFindAll();
        return customerArrayList;
    }

     public MyUserInfoServer() throws RemoteException, SQLException {
           userInfoServices = new UserInfoServices(new DatabaseTarget());
           try{
               registry = LocateRegistry.createRegistry(3000);
               Hashtable hashtable = new Hashtable();
               hashtable.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
               hashtable.put(Context.PROVIDER_URL,"rmi://localhost:3000");

               context=new InitialContext(hashtable);
           } catch (RemoteException e) {
               throw new RuntimeException(e);
           } catch (NamingException e) {
               throw new RuntimeException(e);
           }
     }

    public static void main(String[] args) throws NamingException, RemoteException, SQLException {
       MyUserInfoServer myUserInfoServer = new MyUserInfoServer();
       context.bind("java:/userfilter",myUserInfoServer);
     }
}
