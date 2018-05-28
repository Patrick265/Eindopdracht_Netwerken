package game;

import game.character.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DataReceiver implements Runnable
{
    private Socket socket;
    private Map<String, Player> players;
    private JPanel jpanel;

    public DataReceiver(Socket socket, JPanel jpanel)
    {
        this.socket = socket;
        this.players = new HashMap<>();
        this.jpanel = jpanel;
    }

    @Override
    public void run()
    {
        try
        {

            System.out.println("Entered in DataReciever" + "\n" + "-------------------------------");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while(true)
            {

                Player player = (Player) objectInputStream.readObject();
                players.put(player.getName(), player);
                jpanel.repaint();


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
