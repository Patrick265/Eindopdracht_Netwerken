package server.logic;

import game.NPC.Enemy;
import game.character.Player;
import server.presentation.ServerFrame;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server implements Runnable, Serializable
{
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private Map<String, Player> players = new HashMap<>();
    private ArrayList<Enemy> monsters = new ArrayList<>();

    public Server()
    {
        createMonster();
    }

    @Override
    public void run()
    {
        ServerFrame frame = new ServerFrame("TreacherousMUD - BootServer");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        try
        {
            frame.getTextArea().append("BootServer started on " + formatter.format(new Date()));
            ServerSocket serverSocket = new ServerSocket(8000);
            frame.getTextArea().append("\nThe current server IP: " + Inet4Address.getLocalHost().getHostAddress()+ " with port " + serverSocket.getLocalPort());
            int clientNR = 1;
            while (true)
            {

                Socket socket = serverSocket.accept();
                frame.getTextArea().append(frame.standardClientText(socket.getInetAddress()));
                ClientHandler clientHandler = new ClientHandler(socket, clientNR, frame.getTextArea(), this);

                new Thread(clientHandler).start();
                clientNR++;

                frame.getTextArea().append("Amount of players online: " + (players.size() + 1));

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update(Player player)
    {
        if(player.getAttackedEnemy() != null)
        {
            player.getAttackedEnemy().getSkills().getHitpoints().setHealth(player.getAttackedEnemy().getSkills().getHitpoints().getHealth() - player.getDealtDamage());
            for(Enemy enemy : monsters)
            {
                if(player.getAttackedEnemy().getId() == enemy.getId())
                {
                    enemy.getSkills().getHitpoints().setHealth(player.getAttackedEnemy().getSkills().getHitpoints().getHealth());
                }
            }
            if (player.getAttackedEnemy().getSkills().getHitpoints().getHealth() <= 0)
            {
                for(Enemy enemy : monsters)
                {
                    if(player.getAttackedEnemy().getId() == enemy.getId())
                    {
                        enemy.setAlive(false);
                    }
                }
            }
        }
    }

    private void createMonster()
    {
        for(int i = 1; i < 10; i++)
        {
            Random random = new Random();
            this.monsters.add(new Enemy("Skeleton",random.nextInt(50000 + 1 - 10) + 10, 1, new Point2D.Double(Math.random() * 600, Math.random() * 600), true, i));
        }
    }

    public ArrayList<Enemy> getMonsters()
    {
        return monsters;
    }

    public ArrayList<ClientHandler> getClients()
    {
        return clients;
    }

    public Map<String, Player> getPlayers()
    {
        return players;
    }
}
