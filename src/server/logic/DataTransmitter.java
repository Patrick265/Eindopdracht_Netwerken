package server.logic;

import game.character.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

public class DataTransmitter implements Runnable, Serializable
{
    private List<Player> players;
    private ObjectOutputStream transmitter;
    private Socket socket;
    private Server server;

    public DataTransmitter(List<Player> players, Socket socket, Server server)
    {
        this.players = players;
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
                System.out.println(this.server.getPlayers());
                    this.players = this.server.getPlayers();
                    this.transmitter.flush();
                    for (Player player : players)
                    {

                        System.out.println("Player van client " + player);
                        this.transmitter.writeObject(player);
                        this.transmitter.flush();
                        this.transmitter.reset();
                    }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
