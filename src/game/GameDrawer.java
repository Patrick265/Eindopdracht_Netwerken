package game;

import game.character.Player;
import game.map.TiledMap;
import presentation.loginframe.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class GameDrawer extends JPanel implements KeyListener, ActionListener
{
    private int counter;
    private Player player;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private DataReceiver dataReceiver;
    private Socket socket;


    public GameDrawer()
    {
        super.setFocusable(true);
        this.counter = 0;
        player = new Player(new Point(200,200),"Frankie");
        addKeyListener(this);

        try
        {
            connectionToServer(LoginView.getAddress(), 8000);

        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(this,"Cannot connect to server");
            System.exit(1);
        }

        this.dataReceiver = new DataReceiver(this.socket);
        new Thread(this.dataReceiver).start();

        Timer timer = new Timer(1000/250,this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        TiledMap map = new TiledMap("res/map/map.json");
        map.debugDraw(g2d);
        player.drawPlayer(g2d);

        System.out.println(this.dataReceiver.getPlayers().size());
        for(Map.Entry<String, Player> entry : this.dataReceiver.getPlayers().entrySet())
            {
                System.out.print(entry.getValue().getLocation() + "    ");
                System.out.println();
                g2d.fill(new Rectangle((int)entry.getValue().getLocation().getX(),
                        (int) entry.getValue().getLocation().getY(),
                        10,10));

            }
    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            this.player.setLocation((int) this.player.getLocation().getX(), (int) this.player.getLocation().getY() - 25);
        }

        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            this.player.setLocation((int) this.player.getLocation().getX(), (int) this.player.getLocation().getY() + 25);
        }

        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            this.player.setLocation((int) this.player.getLocation().getX() + 25, (int) this.player.getLocation().getY());
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            this.player.setLocation((int) this.player.getLocation().getX() - 25, (int) this.player.getLocation().getY());
        }

        try
        {
            writeObject();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(counter >= 1)
        {
            this.dataReceiver.getPlayers();
        }
        repaint();
        this.counter++;
    }

    public Player getPlayer()
    {
        return player;
    }

    private void connectionToServer(String adress, int port) throws IOException
    {
        this.socket = new Socket(adress, port);
        toServer = new ObjectOutputStream(socket.getOutputStream());
        toServer.flush();
        toServer.reset();
        fromServer = new ObjectInputStream(socket.getInputStream());

        toServer.writeObject(this.player);
        toServer.reset();
        toServer.flush();
    }

    private void writeObject() throws IOException
    {
        toServer.writeObject(this.player);
        toServer.flush();
        toServer.reset();
    }

}