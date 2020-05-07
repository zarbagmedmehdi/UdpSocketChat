package Test;

import java.io.*;
import java.net.*;


public class Client1 {

    public static void main(String args[]) throws Exception {

        DatagramSocket socket = new DatagramSocket(8000);


        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket,"0/a");
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start(); st.start();
    }
}