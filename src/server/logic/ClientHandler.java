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
            ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.flush();

            ObjectInputStream inputFromClientObject = new ObjectInputStream(socket.getInputStream());
            Player player = (Player) inputFromClientObject.readObject();
            players.put(player.getName(), player);

            while (true) {
                Point currentPos = ((Player) inputFromClientObject.readObject()).getLocation();
                player.setLocation(
                        (int)currentPos.getX(),
                        (int)currentPos.getY());

                System.out.println("Player object: " + player.toString());
                System.out.println("Size of map: " + players.size());

                for(Map.Entry<String, Player> entry : players.entrySet())
                {
                    outputToClientObject.writeObject(entry.getValue());
                    outputToClientObject.flush();
                    outputToClientObject.reset();
                }
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
        }
    }
    public Player getPlayer() {
        return player;
    }


}
