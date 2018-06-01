package server.logic;

import game.ClientPKG;
import game.NPC.Enemy;
import game.character.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
            DataTransmit dataTransmit = new DataTransmit(this.socket,this.server,this.player);
            new Thread(dataTransmit).start();

            while (true) {
                ClientPKG pkg = (ClientPKG) inputFromClientObject.readObject();
                Point currentPos = pkg.getPlayer().getLocation();
                player.setLocation(
                        (int)currentPos.getX(),
                        (int)currentPos.getY());
                Enemy enemy = pkg.getPlayer().getAttackedEnemy();
                player.setAttackedEnemy(enemy);
                int damage = pkg.getPlayer().getDealtDamage();
                System.out.println(damage);
                player.setDealtDamage(damage);
                this.server.update(this.player);
                Thread.sleep(10);
            }

        } catch (SocketException e)
        {
            this.player.setConnected(false);
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
    Player player;

    DataTransmit(Socket socket,Server server,Player player)
    {
        this.server = server;
        this.socket = socket;
        this.player = player;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputToClientObject = new ObjectOutputStream(socket.getOutputStream());
            outputToClientObject.reset();
            while (true) {
                outputToClientObject.writeObject(new ServerPKG(this.server.getPlayers(), this.server.getMonsters()));
                outputToClientObject.reset();
                Thread.sleep(10);
            }
        }
        catch(SocketException e) {
            this.player.setConnected(false);
            System.out.println("Set connected to false");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}