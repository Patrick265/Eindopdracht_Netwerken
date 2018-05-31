package game.character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable
{
    private Point location;
    private String name;
    private boolean isConnected;
    private transient BufferedImage playerSkin;

    public Player(Point location, String username,boolean isConnected) {
        this.location = location;
        this.name = username;
        this.isConnected = isConnected;
        try {
            playerSkin = ImageIO.read(new FileInputStream("res/charachter/charachter.png"));
        } catch (IOException e) {
            e.printStackTrace();
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
    public String toString() {
        return "Player{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", playerSkin=" + playerSkin +
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
}
