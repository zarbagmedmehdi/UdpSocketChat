package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class EchoAppliServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        EchoImpl od=new EchoImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("irisi",od);
        System.out.println(
                "lobjet distant od est enregitsté dans RMI ... serveur Pret"
        );
    }
}
