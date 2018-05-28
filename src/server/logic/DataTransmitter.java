package server.logic;

import game.character.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class DataTransmitter implements Runnable, Serializable
{
    private Map<String, Player> players;
    private ObjectOutputStream transmitter;
    private Socket socket;
    private Server server;

    public DataTransmitter(Map<String, Player> players, Socket socket, Server server)
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
                    for (Map.Entry<String, Player> entry : this.players.entrySet())
                    {

                        System.out.println("Player van client " + entry.getValue());
                        this.transmitter.writeObject(entry.getValue());
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
