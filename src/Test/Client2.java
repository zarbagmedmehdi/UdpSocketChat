package Test;

import java.net.DatagramSocket;


public class Client2 {

    public static void main(String args[]) throws Exception {


        DatagramSocket socket = new DatagramSocket(8001);

        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket,"3/a/bbb");
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start(); st.start();
    }
}