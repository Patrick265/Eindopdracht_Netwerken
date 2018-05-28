package game;

import game.character.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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

            System.out.println("Entered in DataReciever" + "\n" + "-------------------------------");
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("SNKOALNKSDLNALNSAKNDA");

            while(true)
            {

                System.out.println(this.objectInputStream.readObject());
                Player player = (Player) this.objectInputStream.readObject();
                System.out.println("TEST");
                players.put(player.getName(), player);
//                this.objectInputStream.reset();
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
