package Test;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer extends Thread {
    public final static int RPORT = 5000;
    public final static int SPORT = 7000;

    private final static int BUFFER = 1024;

    private DatagramSocket socket;
    private DatagramSocket envoiSocket;

    private ArrayList<ClientModel > clientModels;
    public ChatServer() throws IOException {
        socket = new DatagramSocket(RPORT);
        envoiSocket = new DatagramSocket(SPORT);
        clientModels = new ArrayList();

    }

    public ArrayList<ClientModel> getClientModels() {
        return clientModels;
    }

    public void addClientModels(ClientModel clientModels) {
        this.clientModels.add(clientModels);
    }

    public void run() {
        byte[] buf = new byte[BUFFER];

        while (true) {
            System.out.println(clientModels);
            try {
                System.out.println(Util.t()+"recept Serveur");
                Arrays.fill(buf, (byte)0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String content = new String(buf, buf.length);
                System.out.println(Util.t()+"Receiverd "+content);
ServeurService ss=new ServeurService(envoiSocket,packet.getAddress(),packet.getPort(), content, this);
ss.start();
               /* InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String id = clientAddress.toString() + "," + clientPort;


                if (!existingClients.contains(id)) {
                    existingClients.add( id );
                    clientPorts.add( clientPort );
                    clientAddresses.add(clientAddress);
                }

                System.out.println(id + " : " + content);
                byte[] data = (id + " : " +  content).getBytes();
                for (int i=0; i < clientAddresses.size(); i++) {
                    InetAddress cl = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, cl, cp);
                    socket.send(packet);
                }

                */
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        ChatServer s = new ChatServer();
        s.start();
    }
}