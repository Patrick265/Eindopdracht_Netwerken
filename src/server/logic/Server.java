package server.logic;

import game.character.Player;
import server.presentation.ServerFrame;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server implements Runnable
{
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ArrayList<Player> players;

    @Override
    public void run()
    {
        ServerFrame frame = new ServerFrame("TreacherousMUD - Server");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        try
        {
            frame.getTextArea().append("Server started on " + formatter.format(new Date()));
            ServerSocket serverSocket = new ServerSocket(8000);
            frame.getTextArea().append("\nThe current server IP: " + Inet4Address.getLocalHost().getHostAddress()+ " with port " + serverSocket.getLocalPort());
            int clientNR = 1;
            while (true)
            {

                Socket socket = serverSocket.accept();
                frame.getTextArea().append(frame.standardClientText(socket.getInetAddress()));
                ClientHandler clientHandler = new ClientHandler(socket, clientNR, frame.getTextArea());

                new Thread(clientHandler).start();
                for(ClientHandler client : clients)
                {
                    System.out.println(client.getPlayer().toString());
                    this.players.add(client.getPlayer());
                }

                clients.add(clientHandler);
                clientNR++;
                DataTransmitter dataTransmitter = new DataTransmitter(this.players, socket, this);
                new Thread(dataTransmitter).start();

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

    public ArrayList<Player> getPlayers()
    {
        return players;
    }
}
