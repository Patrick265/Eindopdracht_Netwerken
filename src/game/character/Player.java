package game.character;

import game.DataReceiver;
import game.GameDrawer;
import game.NPC.Enemy;
import game.skills.Skills;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Player implements Serializable, Comparable<Player>
{
    private Point location;
    private String name;
    private boolean isConnected;
    private transient BufferedImage playerSkin;
    private boolean attacking;
    private Skills skill;
    private Enemy attackedEnemy;
    private int dealtDamage;

    public Player(Point location, String username,boolean isConnected) {
        this.location = location;
        this.name = username;
        this.isConnected = isConnected;
        try {
            playerSkin = ImageIO.read(new FileInputStream("res/charachter/charachter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.attacking = false;
        skill = new Skills();

        this.attackedEnemy = null;
        this.dealtDamage = 0;
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

    public void update(DataReceiver dataReceiver, GameDrawer gameDrawer)
    {
        this.dealtDamage = 0;

        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList = dataReceiver.getEnemies();
        for (Enemy enemy : enemyArrayList)
        {
            if(getLocation().distance(enemy.getLocation()) < 75 && attacking)
            {
                this.attackedEnemy = enemy;
                this.dealtDamage = (getSkill().getStrength().getLevel() * 2) * (getSkill().getAttack().getLevel() * 2);
                skill.getStrength().addExperience(10);
                skill.getAttack().addExperience(10);
                System.out.println("ATTACK LEVEL: " + skill.getAttack().getLevel() + " EXP: " + skill.getAttack().getExperience());
                System.out.println("STRENGTH LEVEL: " + skill.getStrength().getLevel() + " EXP: " + skill.getStrength().getExperience() );
                try
                {
                    gameDrawer.writeEntities();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else {
                this.attackedEnemy = null;
                this.dealtDamage = 0;
            }
        }


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
                ", name='" + name +
                ", attacking=" + attacking +
                ", dealtDamage=" + dealtDamage +
                ", attackedEnemy=" + attackedEnemy +
                '}';
    }

    public boolean isAttacking()
    {
        return attacking;
    }

    public void setDealtDamage(int dealtDamage)
    {
        this.dealtDamage = dealtDamage;
    }

    public Enemy getAttackedEnemy()
    {
        return attackedEnemy;
    }

    public int getDealtDamage()
    {
        return dealtDamage;
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

    public Skills getSkill() {
        return skill;
    }

    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }

    public void setAttackedEnemy(Enemy attackedEnemy)
    {
        this.attackedEnemy = attackedEnemy;
    }

    @Override
    public int compareTo(Player o) {
        int compare = getName().compareTo(o.getName());
        return compare;
        }
    }
