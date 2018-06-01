package game;

import game.NPC.Enemy;
import game.character.Player;
import server.logic.ServerPKG;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReceiver implements Runnable
{
    private Socket socket;
    private Map<String, Player> players;
    private ArrayList<Enemy> enemies;
    private JPanel jpanel;

    public DataReceiver(Socket socket, JPanel jpanel)
    {
        this.socket = socket;
        this.players = new HashMap<>();
        this.jpanel = jpanel;
    }

    @Override
    public void run() {
        try {

            System.out.println("Entered in DataReciever" + "\n" + "-------------------------------");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true)
            {
                ServerPKG pkg = (ServerPKG) objectInputStream.readObject();
                this.players = pkg.getPlayers();
                this.enemies = pkg.getEnemies();
                jpanel.repaint();


            }
        } catch (SocketException e) {
            System.out.println("User disconnected");
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

    public Map<String, Player> getPlayers()
    {
        return players;
    }
}
