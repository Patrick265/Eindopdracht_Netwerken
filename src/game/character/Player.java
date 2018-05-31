package game.character;

import game.NPC.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Player implements Serializable
{
    private Point location;
    private String name;
    private boolean isConnected;
    private transient BufferedImage playerSkin;
    private int hitpoints;
    private int strenght;
    private boolean attacking;

    public Player(Point location, String username,boolean isConnected) {
        this.location = location;
        this.name = username;
        this.isConnected = isConnected;
        try {
            playerSkin = ImageIO.read(new FileInputStream("res/charachter/charachter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.hitpoints = 10;
        this.strenght = 1;
        this.attacking = false;
    }

    public void draw(Graphics2D g2d, Map<String, Player> players)
    {
        for(Map.Entry<String, Player> entry : players.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().isConnected()) {
                    AffineTransform af = new AffineTransform();
                    af.translate(
                            (int) entry.getValue().getLocation().getX() + 16 - this.getPlayerSkin().getWidth() / 2,
                            (int) entry.getValue().getLocation().getY() + 12 - this.getPlayerSkin().getHeight() / 2);
                    g2d.drawImage(this.getPlayerSkin(), af, null);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(entry.getKey(), (int) entry.getValue().getLocation().getX(), (int) entry.getValue().getLocation().getY() - 20);
                }
            }
        }
    }

    public void update(ArrayList<Enemy> enemies)
    {
        for (Enemy enemy : enemies)
        {

            if(getLocation().distance(enemy.getLocation()) < 75 && attacking)
            {
                System.out.println("BEFORE: " + enemy);
                enemy.setHitpoints(enemy.getHitpoints()-1);
                System.out.println("AFTER: " + enemy);
            }
        }
    }

    public int[] dealDamage()
    {
        int[] hitsPossible = {0, strenght};
        return hitsPossible;
    }


    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }

    public int getHitpoints()
    {
        return hitpoints;
    }

    public Point getLocation()
    {
        return location;
    }

    public void setLocation(int x , int y)
    {
        location.setLocation(x,y);
    }

    @Override
    public String toString()
    {
        return "Player{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", isConnected=" + isConnected +
                ", playerSkin=" + playerSkin +
                ", hitpoints=" + hitpoints +
                ", strenght=" + strenght +
                '}';
    }

    public String getName()
    {
        return name;
    }

    public BufferedImage getPlayerSkin()
    {
        return playerSkin;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public int getStrenght()
    {
        return strenght;
    }

    public boolean isAttacking()
    {
        return attacking;
    }

    public void setStrenght(int strenght)
    {
        this.strenght = strenght;
    }

    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }
}
