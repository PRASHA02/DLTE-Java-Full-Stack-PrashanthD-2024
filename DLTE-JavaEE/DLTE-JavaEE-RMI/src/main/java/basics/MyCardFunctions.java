package basics;

import java.rmi.Remote;
import java.rmi.server.RemoteServer;
import java.util.List;

public interface MyCardFunctions extends Remote {
    List<String> fetchOverLimit();
}
