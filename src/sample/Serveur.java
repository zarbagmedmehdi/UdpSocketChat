package sample;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Serveur  extends Thread{
    public ArrayList<ClientServer> clientServerList=new ArrayList<>();
    private int number;
  //  private DatagramSocket socket;



    public Serveur() throws SocketException {
        number=0;
       // socket=new DatagramSocket(7000);
        this.start();
    }

    public int getNumber() {
        number++;
        return this.number;
    }



    public ArrayList<ClientServer> getClientServerList() {
        return clientServerList;
    }

    @Override
    public void run() {
        super.run();
        byte[] buf = new byte[1024];
       while(true){

         receptionServeur(buf);
        }

    }

    public static void main(String[] args) throws SocketException {
        Serveur s=new Serveur();


    }

    public  void receptionServeur(byte[] buf) {

        try {
            DatagramSocket ds=new DatagramSocket(7000);
            Arrays.fill(buf, (byte)0);
            System.out.println("////////////////////////////receptServeur");
            Arrays.fill(buf, (byte)0);
            DatagramPacket packetIn = new DatagramPacket(buf, buf.length);
            ds.receive(packetIn);
ds.close();
            TraitementServeur tr=new TraitementServeur(packetIn,this);

            System.out.println("///////////////////////////End receptServeur");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
