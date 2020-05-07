package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoImpl extends UnicastRemoteObject implements  Echo {
    private static final  long serialVersionUID=1L;
    public EchoImpl() throws RemoteException {
        super();
    }

    @Override
    public String echo(String str) throws RemoteException {
        return "le serveur repond au "+str;
    }
}
