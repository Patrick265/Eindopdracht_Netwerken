package game.skills;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Strength implements Serializable{

    private int level;
    private int experience;
    private transient BufferedImage icon;

    public Strength()
    {
        this.level = 1;
        this.experience = 0;
        try {
            this.icon = ImageIO.read(new FileInputStream("res/charachter/strength.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int addExperience(int experience)
    {
        return this.experience += experience;
    }

    public int calculateLevel(int experience)
    {
        return ((experience / 100)+1);
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
