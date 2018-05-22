package server.logic;

import server.presentation.ServerFrame;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server implements Runnable
{
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    @Override
    public void run()
    {
        ServerFrame frame = new ServerFrame("TreacherousMUD - Server");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        try
        {
            frame.getTextArea().append("Server started on " + formatter.format(new Date()));
            ServerSocket serverSocket = new ServerSocket(420);
            frame.getTextArea().append("\nThe current server IP: " + Inet4Address.getLocalHost().getHostAddress()+ " with port " + serverSocket.getLocalPort());
            int clientNR = 1;
            while (true)
            {
                Socket socket = serverSocket.accept();
                frame.getTextArea().append(frame.standardClientText(socket.getInetAddress()));
                ClientHandler clientHandler = new ClientHandler(socket, clientNR);

                new Thread(clientHandler).start();
                clients.add(clientHandler);
                clientNR++;

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
