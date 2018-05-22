package game.character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player implements KeyListener
{
    private Point location;
    private String name;
    private BufferedImage playerSkin;

    public Player(Point location, String name) {
        this.location = location;
        this.name = name;
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
        g2d.drawImage(playerSkin,af,null);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            this.location.setLocation(this.location.getX(), this.location.getY() + 5);
        }

        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            this.location.setLocation(this.location.getX(), this.location.getY() - 5);
        }

        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            this.location.setLocation(this.location.getX() + 5, this.location.getY());
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            this.location.setLocation(this.location.getX() - 5, this.location.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
