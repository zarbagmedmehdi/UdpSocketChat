package sample;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Client extends Thread {
String nom;
ArrayList<String> NomAmis=new ArrayList<>();

    int port;

    @Override
    public void run() {
        super.run();
        try {
            DatagramSocket dataSockClient = new DatagramSocket(5000);

           this.demandeInscription(  dataSockClient);
             this.attendreConfirmation( dataSockClient);
            System.out.println();
            dataSockClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public Client(String nom) {
        this.nom = nom;
        this.start();
    }
    public  void demandeInscription( DatagramSocket dataSockClient) throws IOException {
        System.out.println("Demande D'inscription");
       envoiMsgASCII(  dataSockClient,"0/"+nom);

    }
    public void attendreConfirmation( DatagramSocket dataSockClient){
        System.out.println("attendreConfirmation");
       receptASCII(  dataSockClient);
    }


    public static void main(String[] args) {
        Client client=new Client("zarbag");



    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                '}';
    }

    public  void receptASCII(DatagramSocket dataSockClient) {

        byte[] tampon = new byte[1024];
        try {
            DatagramPacket packetIn = new DatagramPacket(tampon, tampon.length);
            dataSockClient.receive(packetIn);
            System.out.println("Un packet vient d'etre recu depuis le serveru : " + packetIn.getSocketAddress());
            System.out.println("Le Message recu est : "+new String(packetIn.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public   void envoiMsgASCII( DatagramSocket dataSockClient,String msg) throws IOException {
        byte[] tampon = msg.getBytes();
         InetAddress   adresse = InetAddress.getByName("127.0.0.1");
            DatagramPacket packetOut = new DatagramPacket(tampon, tampon.length, adresse, 7000);
            dataSockClient.send(packetOut);

    }

}

