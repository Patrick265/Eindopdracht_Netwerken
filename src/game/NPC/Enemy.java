package game.NPC;

import game.DataReceiver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Enemy implements Serializable
{
    private final String name;
    private int hitpoints;
    private final double strength;
    private Point2D location;
    private transient BufferedImage npcSkin;
    private boolean isAlive;

    public Enemy(String name, int hitpoints, double strength, Point2D location, boolean isAlive)
    {
        this.name = name;
        this.hitpoints = hitpoints;
        this.strength = strength;
        this.location = location;
        this.isAlive = isAlive;
        try {
            npcSkin = ImageIO.read(new FileInputStream("res/enemies/skeleton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {

    }


    public void draw(Graphics2D g2d, ArrayList<Enemy> enemies)
    {
        for(int i = 0; i < enemies.size(); i++)
        {
            AffineTransform af = new AffineTransform();
            af.translate(
                    (int) enemies.get(i).getLocation().getX() + 16 - this.getNpcSkin().getWidth() / 2,
                    (int) enemies.get(i).getLocation().getY() + 12 - this.getNpcSkin().getHeight() / 2);
            g2d.drawImage(this.getNpcSkin(), af, null);
            g2d.setColor(Color.WHITE);
            g2d.drawString( enemies.get(i).getName(),
                          (int) enemies.get(i).getLocation().getX(),
                        (int) enemies.get(i).getLocation().getY() - 20);
        }
    }

    public String getName()
    {
        return name;
    }
    public int getHitpoints()
    {
        return hitpoints;
    }
    public void setHitpoints(int hitpoints)
    {
        this.hitpoints = hitpoints;
    }
    public double getStrength()
    {
        return strength;
    }
    public Point2D getLocation()
    {
        return location;
    }
    public void setLocation(Point2D location)
    {
        this.location = location;
    }
    public BufferedImage getNpcSkin()
    {
        return npcSkin;
    }
    public void setNpcSkin(BufferedImage npcSkin)
    {
        this.npcSkin = npcSkin;
    }
    public boolean isAlive()
    {
        return isAlive;
    }
    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }
    @Override
    public String toString()
    {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", hitpoints=" + hitpoints +
                ", strength=" + strength +
                ", location=" + location +
                ", npcSkin=" + npcSkin +
                ", isAlive=" + isAlive +
                '}';
    }
}
