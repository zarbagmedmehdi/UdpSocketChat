package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Messaging {

    public  static int getMessageType(byte[] data){
        String msg=new String(data);
        return Integer.parseInt(msg.substring(0,1));
    }
    public  static String getStringFrom(byte[] data){
        return (new String(data)).substring(2);
    }



}
