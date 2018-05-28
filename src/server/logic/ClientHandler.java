package server.logic;

import game.character.Player;

import javax.swing.*;
import javax.xml.crypto.Data;
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
    private Map<String, Player> players;

    public ClientHandler(Socket socket, int clientNumber, JTextArea textArea, Map<String, Player> players) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        this.textArea = textArea;
        this.players = players;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputFromClientObject = new ObjectInputStream(socket.getInputStream());
            Player player = (Player) inputFromClientObject.readObject();
            players.put(player.getName(), player);
            DataTransmit dataTransmit = new DataTransmit(this.socket,this.players);
            new Thread(dataTransmit).start();

            while (true) {
                Point currentPos = ((Player) inputFromClientObject.readObject()).getLocation();
                player.setLocation(
                        (int)currentPos.getX(),
                        (int)currentPos.getY());

                System.out.println("Player object: " + player.toString());
                System.out.println("Size of map: " + players.size());
                Thread.sleep(100);
            }



        } catch (SocketException e)
        {
            System.out.println("User disconnected from the server");
        } catch (EOFException e) {
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
    Map<String, Player> players;

    DataTransmit(Socket socket,Map<String, Player> players)
    {
        this.players = players;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
        ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.flush();
            while(true) {
                for(Map.Entry<String, Player> entry : players.entrySet())
                {
                    outputToClientObject.writeObject(entry.getValue());
                    outputToClientObject.flush();
                    outputToClientObject.reset();
                }
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}