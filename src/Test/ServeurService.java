package Test;

import sample.Client;
import sample.Messaging;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServeurService extends  Thread {
private DatagramSocket envoiSocket;
private InetAddress clientAddress ;
private  int clientPort ;
private String message;
private ChatServer chatServer;

    public ServeurService(DatagramSocket envoiSocket,InetAddress clientAddress, int clientPort, String message, ChatServer chatServer) {
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        this.message = message;
        this.chatServer = chatServer;
        this.envoiSocket=envoiSocket;
    }

    @Override
    public void run() {
        int type= Util.getMessageType(message);
        switch(type) {
            case 0:
                try { inscription(); } catch (Exception e) { e.printStackTrace(); }
                break;
            case 1:
                try { ajoutAmis(); } catch (Exception e) { e.printStackTrace(); }
                break;
            case 2:
                try { getListFriends(); } catch (Exception e) { e.printStackTrace(); }
                break;
            case 3:
                try { sendMessageToAFriend(); } catch (Exception e) { e.printStackTrace(); }
                break;
            default:
                // code block
    }
        System.out.println(Util.t()+""+chatServer.getClientModels());
    }


    public void inscription() throws Exception {
        String id=clientAddress.toString() + "," + clientPort;
if(!Util.containsId(chatServer.getClientModels(),id)){
ClientModel clientModel=new ClientModel(clientAddress,clientPort,id,Util.getStringFrom(message));
chatServer.addClientModels(clientModel);
sendMessage("confirmation");
    }
else sendMessage("vous etes deja inscrit");
    }
    public void getListFriends() throws Exception {
        String id=clientAddress.toString() + "," + clientPort;
        if(Util.containsId(chatServer.getClientModels(),id)){
         ArrayList<ClientModel> fs= chatServer.getClientModels().get(Util.getIndexOfList(chatServer.getClientModels(),id)).getFriendList();
            sendMessage(fs.toString());
        }
        else sendMessage("vous n'etes pas inscrit");
    }


    public void ajoutAmis() throws Exception {
        String id=clientAddress.toString() + "," + clientPort;
        System.out.println("1"+id);
        if(Util.containsId(chatServer.getClientModels(),id)){
            String friendName=Util.getStringFrom(message);
            System.out.println("2"+friendName);
            if(Util.getClientModelByName(chatServer.getClientModels(),friendName).isPresent()){
             ClientModel cl=
                     Util.getClientModelByName(chatServer.getClientModels(),friendName).get();
             int indexOfClient=Util.getIndexOfList(chatServer.getClientModels(),id);
             chatServer.getClientModels().get(indexOfClient).addToFriendList(cl);
                System.out.println(chatServer.getClientModels().get(indexOfClient));
                sendMessage("ajouté ");
            }
            else sendMessage("ce nom n'existe pas ");

    } }


        public void sendMessageToAFriend() throws Exception {
            String id=clientAddress.toString() + "," + clientPort;
            System.out.println("1"+id);
            if(Util.containsId(chatServer.getClientModels(),id)){
                String friendName=Util.getStringFrom(message);
                System.out.println("2"+friendName);
                ClientModel cl=chatServer.getClientModels().get(Util.getIndexOfList(chatServer.getClientModels(),id));
               ArrayList<ClientModel> friendList =cl.getFriendList();
                System.out.println("hahya list "+friendList);
                System.out.println(friendList.size());
                sendMessageToAnother(message.split("/")[2],
                        new ClientModel(InetAddress.getByName("127.0.0.1"),8000,"cc","cc"));
               if((Integer)Util.getObjectOfList(friendList,friendName)[0]!=0){
                   ClientModel fr=(ClientModel)Util.getObjectOfList(friendList,friendName)[1];
                //    ClientModel fr=Util.getClientModelByName(cl.getFriendList(),friendName).get();

                    sendMessageToAnother(message.split("/")[2],fr);
                }
                else sendMessage("ce nom n'existe pas ");

            }
else sendMessage("vous n'etes pas inscrit");
    }


    private void sendMessage(String s) throws Exception {
        byte buf[] = s.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, clientAddress, clientPort);
        envoiSocket.send(packet);
    }
        private void sendMessageToAnother(String s,ClientModel c) throws Exception {
            byte buf[] = s.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, c.getClientAddresse(), c.getClientPort());
            envoiSocket.send(packet);
        }



}
