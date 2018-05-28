package server.logic;

import game.character.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

public class DataTransmitter implements Runnable, Serializable
{
    private List<ClientHandler> clients;
    private ObjectOutputStream transmitter;
    private Socket socket;
    private Server server;

    public DataTransmitter(List<ClientHandler> clients, Socket socket, Server server)
    {
        this.clients = clients;
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        try
        {
            this.transmitter = new ObjectOutputStream(this.socket.getOutputStream());
            while(true)
            {
                this.clients.forEach(a -> System.out.println(a.getPlayer()));
                this.clients = this.server.getClients();
                this.clients.forEach(a -> System.out.println(a.getPlayer()));
                this.transmitter.flush();
                for(ClientHandler client : clients)
                {
                    System.out.println("Fucking kut code" + client.getPlayer());
                    this.transmitter.writeObject(client.getPlayer());
                    this.transmitter.flush();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
