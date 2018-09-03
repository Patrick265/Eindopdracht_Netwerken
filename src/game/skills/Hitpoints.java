package game.skills;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Hitpoints implements Serializable {

    private int level;
    private int experience;
    private transient BufferedImage icon;
    private int health;

    public Hitpoints()
    {
        this.level = 10;
        this.health = this.level;
        this.experience = 0;
        try {
            this.icon = ImageIO.read(new FileInputStream("res/charachter/hitpoints.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String  toString()
    {
        return "Hitpoints{" +
                "level=" + level +
                ", experience=" + experience +
                ", icon=" + icon +
                ", health=" + health +
                '}';
    }


    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int addExperience(int experience)
    {
        return this.experience += experience;
    }

    public int calculateLevel(int experience)
    {
        return ((experience / 100) + 10);
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
