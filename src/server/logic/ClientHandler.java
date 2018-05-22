package server.logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber)
    {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
