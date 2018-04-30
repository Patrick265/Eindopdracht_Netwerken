package game.charachter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Charachter {
    private Point location;
    private String name;
    private BufferedImage charachterSkin;

    public Charachter(Point location, String name, BufferedImage charachterSkin) {
        this.location = location;
        this.name = name;
        this.charachterSkin = charachterSkin;
    }

    public void drawCharachter(Graphics2D g2d)
    {
        AffineTransform af = new AffineTransform();
        af.translate(location.getX() + 16 - charachterSkin.getWidth()/2, location.getY() + 12 - charachterSkin.getHeight()/2);
        g2d.drawImage(charachterSkin,af,null);
    }
}
