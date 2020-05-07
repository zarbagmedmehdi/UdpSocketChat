package sample;

import javax.swing.text.StyledEditorKit;
import java.net.InetAddress;

public class ClientServer {

    private int id;
    private Client client;
    private InetAddress inetAddress;
    private int port;
    private boolean connecté;

    public ClientServer(int id, Client client, InetAddress inetAddress, int port) {
        this.id = id;
        this.client = client;
        this.inetAddress = inetAddress;
        this.port = port;
        this.connecté=true;
    }

    public boolean isConnecté() {
        return connecté;
    }

    public void setConnecté(boolean connecté) {
        this.connecté = connecté;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ClientServer{" +
                "id=" + id +
                ", client=" + client +
                ", inetAddress=" + inetAddress +
                ", port=" + port +
                '}';
    }
}
