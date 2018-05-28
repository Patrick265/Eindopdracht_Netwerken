package server.logic;

import game.character.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
    private Socket socket;
    private int clientNumber;
    private Player player;
    private JTextArea textArea;

    public ClientHandler(Socket socket, int clientNumber, JTextArea textArea) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.flush();
            ObjectInputStream inputFromClientObject = new ObjectInputStream(socket.getInputStream());
            player = (Player) inputFromClientObject.readObject();
            while (true) {
                Point currentPos = ((Player) inputFromClientObject.readObject()).getLocation();
                player.setLocation(
                        (int)currentPos.getX(),
                        (int)currentPos.getY());
                outputToClientObject.flush();

                System.out.println("TOM ZIJN CODE: " + player.toString());

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
