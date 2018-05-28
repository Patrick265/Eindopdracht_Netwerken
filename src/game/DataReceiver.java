package game;

import game.character.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataReceiver implements Runnable
{
    private Socket socket;
    private Map<String, Player> players;
    private ObjectInputStream objectInputStream = null;

    public DataReceiver(Socket socket)
    {
        this.socket = socket;
        this.players = new HashMap<>();
    }

    @Override
    public void run()
    {
        try
        {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

            while(true)
            {
                Player player = (Player) this.objectInputStream.readObject();
                System.out.println("RECIEVER: "  + player);
                players.put(player.getName(), player);
                this.objectInputStream.reset();
            }
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public Map<String, Player> getPlayers()
    {
        return players;
    }
}
