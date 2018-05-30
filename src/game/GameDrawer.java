package game;

import game.character.Player;
import game.map.TiledMap;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class GameDrawer extends JPanel implements KeyListener, ActionListener
{
    private int counter;
    private Player player;
    private ObjectOutputStream toServer;
    private DataReceiver dataReceiver;
    private Socket socket;


    public GameDrawer()
    {
        super.setFocusable(true);
        this.counter = 0;
        player = new Player(new Point(200,200), LoginView.getUsername());
        addKeyListener(this);

        try
        {
            connectionToServer(LoginView.getAddress(), 8000);

        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(this,"Cannot connect to server");
            System.exit(1);
        }

        this.dataReceiver = new DataReceiver(this.socket,this);
        new Thread(this.dataReceiver).start();

        Timer timer = new Timer(1000/60,this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        TiledMap map = new TiledMap("res/map/map.json");
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        map.debugDraw(g2d);

        for(Map.Entry<String, Player> entry : this.dataReceiver.getPlayers().entrySet()) {
            if (entry.getValue() != null) {
                AffineTransform af = new AffineTransform();
                af.translate(
                        (int) entry.getValue().getLocation().getX() + 16 - player.getPlayerSkin().getWidth()/2,
                        (int) entry.getValue().getLocation().getY() + 12 - player.getPlayerSkin().getHeight()/2);
                g2d.drawImage(player.getPlayerSkin(), af, null);
                g2d.setColor(Color.WHITE);
                g2d.drawString(entry.getKey(), (int) entry.getValue().getLocation().getX(), (int) entry.getValue().getLocation().getY() - 20);
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.player.setLocation((int) this.player.getLocation().getX(), (int) this.player.getLocation().getY() - 4);
                break;
            case KeyEvent.VK_S:
                this.player.setLocation((int) this.player.getLocation().getX(), (int) this.player.getLocation().getY() + 4);
                break;
            case KeyEvent.VK_D:
                this.player.setLocation((int) this.player.getLocation().getX() + 4, (int) this.player.getLocation().getY());
                break;
            case KeyEvent.VK_A:
                this.player.setLocation((int) this.player.getLocation().getX() - 4, (int) this.player.getLocation().getY());
                break;
        }
        try {
            writeObject();
            } catch (IOException e1) {
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
        repaint();
    }

    public Player getPlayer()
    {
        return player;
    }

    private void connectionToServer(String adress, int port) throws IOException
    {
        this.socket = new Socket(adress, port);
        toServer = new ObjectOutputStream(socket.getOutputStream());
        toServer.reset();

        toServer.writeObject(this.player);
        toServer.reset();
    }

    private void writeObject() throws IOException
    {
        toServer.writeObject(this.player.getLocation());
        toServer.reset();
    }

}