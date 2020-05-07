package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TraitementServeur extends Thread {
    DatagramSocket ds; DatagramPacket dt;Serveur s;

    public TraitementServeur( DatagramPacket datagramPacket,Serveur s) throws SocketException {
        this.ds =new DatagramSocket();
        this.dt = datagramPacket;
        this.s=s;
        this.start();
    }

    @Override
    public void run() {
        this.TraiterRequeteVersServeur();
       // ds.close();
    }

    public   void envoiMsgoTClient(DatagramSocket ds,DatagramPacket dt, String msg) {
        System.out.println("////////// envoiMsgoTClient");
        byte[] tampon = msg.getBytes();
        try {
            System.out.println(dt.getPort());
            DatagramPacket packetOut = new DatagramPacket(tampon, tampon.length, dt.getAddress(), dt.getPort());
            ds.send(packetOut);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void TraiterRequeteVersServeur(){
        System.out.println("////////////////////TraiterRequeteVersServeur");
        int type=Messaging.getMessageType(dt.getData());
        switch(type) {
            case 0:
                Inscription();
                break;
            case 1:
                // code block
                break;
            default:
                // code block
        }


    }
    public void Inscription(){
          System.out.println("inscription");
        ClientServer cs=new
              ClientServer( s.getNumber(), new Client(Messaging.getStringFrom(dt.getData())),  dt.getAddress(),dt.getPort());
        s.clientServerList.add(cs);
         // System.out.println(cs.getId());
        this.envoiMsgoTClient(ds,dt,"1/Confirmation");


    }
}
