package server.logic;

import game.character.Player;

import java.io.*;
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
            ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.flush();
            ObjectInputStream inputFromClientObject = new ObjectInputStream(socket.getInputStream());

            while (true) {
                //DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                //DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

                System.out.println(inputFromClientObject.readObject().toString());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
