package casics.services;

import application.db.Entities.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyUserInfoFunctions extends Remote {

    public List findAll() throws RuntimeException;
}
