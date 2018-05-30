package server.logic;

import game.character.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class ClientHandler implements Runnable
{
    private Socket socket;
    private int clientNumber;
    private Player player;
    private JTextArea textArea;
    private Server server;
    public ClientHandler(Socket socket, int clientNumber, JTextArea textArea, Server server) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        this.textArea = textArea;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputFromClientObject = new ObjectInputStream(socket.getInputStream());
            this.player = (Player) inputFromClientObject.readObject();
            server.getPlayers().put(player.getName(), player);
            DataTransmit dataTransmit = new DataTransmit(this.socket,this.server);
            new Thread(dataTransmit).start();

            while (true) {
                Point currentPos = (Point)
                        inputFromClientObject.readObject();
                player.setLocation(
                        (int)currentPos.getX(),
                        (int)currentPos.getY());

                Thread.sleep(10);
            }



        } catch (SocketException e)
        {
            this.server.getPlayers().remove(this.player.getName());
            this.server.getPlayers().remove(this.player.getName(), this.player);
            System.out.println("User disconnected from the server");

        }
        catch (EOFException e) {
            System.out.println("User disconnected");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public Player getPlayer() {
        return player;
    }

}
class DataTransmit implements Runnable
{
    Socket socket;
    Server server;

    DataTransmit(Socket socket,Server server)
    {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
        ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.flush();
            while(true) {
                for(Map.Entry<String, Player> entry : server.getPlayers().entrySet())
                {
                    outputToClientObject.writeObject(entry.getValue());
                    outputToClientObject.reset();
                }
                Thread.sleep(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}