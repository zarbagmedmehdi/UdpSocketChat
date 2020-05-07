package Test;

import sample.Client;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;

public class ClientModel {
    private InetAddress clientAddresse;
    private  Integer clientPort;
    private String id;
    private String name;
    private ArrayList<ClientModel> friendList=new ArrayList<>();

    public ClientModel(InetAddress clientAddresse, Integer clientPort, String id, String name) {
        this.clientAddresse= clientAddresse;
        this.clientPort = clientPort;
        this.id = id;
        this.name = name;
    }

    public InetAddress getClientAddresse() {
        return clientAddresse;
    }

    public void setClientAddresses(InetAddress clientAddresses) {
        this.clientAddresse = clientAddresses;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPorts) {
        this.clientPort = clientPorts;
    }

    public String getId() {
        return id;
    }

    public void setExistingClients(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ClientModel> getFriendList() {
        return friendList;
    }

    public void addToFriendList(ClientModel friend) {
        this.friendList.add(friend);
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "" + clientAddresse+
                "," + clientPort +
                //", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", friendList=" + friendList +
                '}';
    }
}
