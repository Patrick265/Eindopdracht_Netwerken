package game.character;

import game.GameFrame;
import presentation.views.LoginView;

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
    private transient BufferedImage playerSkin;

    public Player(Point location, String username) {
        this.location = location;
        this.name = username;
        try {
            playerSkin = ImageIO.read(new FileInputStream("res/charachter/charachter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPlayer(Graphics2D g2d)
    {
        AffineTransform af = new AffineTransform();
        af.translate(location.getX() + 16 - playerSkin.getWidth()/2, location.getY() + 12 - playerSkin.getHeight()/2);
        //g2d.drawImage(playerSkin,af,null);
       // g2d.drawString(name,(int)location.getX(),(int)location.getY() - 30);
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
}
