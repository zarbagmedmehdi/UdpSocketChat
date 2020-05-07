package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EchoClient {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        String url="rmi://localhost/irisi";
        Echo od=(Echo) Naming.lookup(url);
        System.out.println(od.echo(("message Client")));
    }
}
