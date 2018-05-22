package game.charachter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player {
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
        g2d.drawString(name,(int)location.getX(),(int)location.getY() - 30);
    }
}
