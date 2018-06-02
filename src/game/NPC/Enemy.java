package game.NPC;

import game.DataReceiver;
import game.skills.Skills;

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
    private Point2D location;
    private transient BufferedImage npcSkin;
    private boolean isAlive;
    private Skills skills;
    private final int id;

    public Enemy(String name, int hitpoints, int strength, Point2D location, boolean isAlive, int id)
    {
        this.name = name;
        this.id = id;
        skills = new Skills();
        skills.getHitpoints().setLevel(hitpoints);
        skills.getHitpoints().setHealth(hitpoints);
        skills.getStrength().setLevel(strength);
        this.location = location;
        this.isAlive = isAlive;
        try {
            npcSkin = ImageIO.read(new FileInputStream("res/enemies/skeleton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d, DataReceiver dataReceiver)
    {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList = dataReceiver.getEnemies();
        for(Enemy enemy : enemyArrayList)
        {
            if(enemy.isAlive())
            {
                AffineTransform af = new AffineTransform();
                af.translate((int) enemy.getLocation().getX() + 16 - this.getNpcSkin().getWidth() / 2, (int) enemy.getLocation().getY() + 12 - this.getNpcSkin().getHeight() / 2);
                g2d.drawImage(this.getNpcSkin(), af, null);
                g2d.setColor(Color.WHITE);
                g2d.drawString(enemy.getName(),
                        (int) enemy.getLocation().getX(),
                        (int) enemy.getLocation().getY() - 20);
                g2d.drawString(enemy.getSkills().getHitpoints().getHealth() + "/" + enemy.getSkills().getHitpoints().getLevel(),
                        (int)enemy.getLocation().getX(),
                        (int)enemy.getLocation().getY()-35);
            }
       }
    }

    public String getName()
    {
        return name;
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

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", npcSkin=" + npcSkin +
                ", isAlive=" + isAlive +
                ", skills=" + skills.toString() +
                '}';
    }

    public int getId()
    {
        return id;
    }

}
