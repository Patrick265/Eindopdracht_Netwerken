package game;

import game.NPC.Enemy;
import game.NPC.EnemyHealthComparator;
import game.character.Player;
import game.map.TiledMap;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDrawer extends JPanel implements KeyListener, ActionListener
{
    private int counter;
    private Player player;
    private Enemy enemy;
    private ObjectOutputStream toServer;
    private DataReceiver dataReceiver;
    private Socket socket;
    private HUD hud;


    public GameDrawer()
    {
        super.setFocusable(true);
        this.counter = 0;
        this.hud = new HUD(this.player);

        this.player = new Player(new Point(200,200), LoginView.getUsername(),true);
        this.enemy = new Enemy("Dummy", 10,1,new Point2D.Double(200,200), true, 0);
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
        synchronized (this)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;


            TiledMap map = new TiledMap("res/map/map.json");
            g2d.setFont(new Font("Arial", Font.PLAIN, 16));
            map.debugDraw(g2d);
            player.draw(g2d, this.dataReceiver.getPlayers());
            List<Enemy> enemies = new ArrayList<>(this.dataReceiver.getEnemies());
            EnemyHealthComparator enemyHealthComparator = new EnemyHealthComparator();
            Collections.sort(enemies,enemyHealthComparator);
            int i = 0;
            for(Enemy enemie : enemies)
            {
                g2d.drawString("Enemy health list",1000,15);
                if(enemie.getSkills().getHitpoints().getHealth() > 0) {
                    i++;
                    g2d.drawString("Name: " + enemie.getName() + " ID: " + enemie.getId() + " health: " + enemie.getSkills().getHitpoints().getHealth() + "", 1000, 30 + (i * 15));
                }
            }
            if (this.dataReceiver.getEnemies().size() > 0)
            {
                enemy.draw(g2d, this.dataReceiver);
            }

            hud.draw(player, g2d, this);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

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
                case KeyEvent.VK_SPACE:
                this.player.setAttacking(true);
        }
        try {
            writeEntities();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    @Override
    public void keyReleased(KeyEvent e) {
        this.player.setAttacking(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        player.update(this.dataReceiver, this);
        if(player.getAttackedEnemy() != null)
        {
            System.out.println("got enemy");
        }
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
    public void writeEntities() throws IOException
    {
        toServer.writeObject(new ClientPKG(this.player));
        toServer.reset();

    }
}