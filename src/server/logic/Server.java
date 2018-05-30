package server.logic;

import game.character.Player;
import server.presentation.ServerFrame;

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
//                DataTransmitter dataTransmitter = new DataTransmitter(this.players, socket, this);
//                new Thread(dataTransmitter).start();

                frame.getTextArea().append("Amount of players online: " + (players.size() + 1));

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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
