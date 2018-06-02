package game;

import game.character.Player;
import template.Colors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class HUD
{
    private Shape background;
    private BasicStroke strokeBackground;
    private int height;
    private int width;
    private BufferedImage hitpoints;
    private BufferedImage attack;
    private BufferedImage strength;
    public HUD(Player player)
    {
        this.height = 150;
        this.width = 250;
        this.strokeBackground = new BasicStroke(2);

        try
        {
            this.hitpoints = ImageIO.read(new FileInputStream("res/charachter/hitpoints.png"));
            this.attack = ImageIO.read(new FileInputStream("res/charachter/attack.png"));
            this.strength = ImageIO.read(new FileInputStream("res/charachter/strength.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Player player, Graphics2D g2d, JPanel panel)
    {
        AffineTransform af = new AffineTransform();
        this.background = new Rectangle2D.Double(panel.getWidth() - width, panel.getHeight() - height, width, height);
        g2d.setStroke(this.strokeBackground);
        g2d.setColor(Colors.hudBackground());
        g2d.fill(background);
        g2d.setColor(Colors.hudBackgroundBorder());
        g2d.draw(background);
        g2d.setColor(Colors.hudTextColor());
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        g2d.setColor(Color.BLACK);

        g2d.drawString("Skills" , panel.getWidth() - (width - 25),panel.getHeight() - (height - 25));
        g2d.drawString("Hitpoints", panel.getWidth() - (width - 75), panel.getHeight() - (height - 50));
        g2d.drawString("Attack:", panel.getWidth() -  (width - 75), panel.getHeight() - (height - 80));
        g2d.drawString("Strength:", panel.getWidth() - (width - 75), panel.getHeight() - (height - 110));


        g2d.drawString(String.valueOf(player.getSkill().getHitpoints().getHealth()) + "/" +
                            String.valueOf(player.getSkill().getHitpoints().getLevel()),
                        panel.getWidth() - (width - 175), panel.getHeight() - (height - 50));
        g2d.drawString(String.valueOf(player.getSkill().getAttack().getLevel()),
                        panel.getWidth() - (width - 175), panel.getHeight() - (height - 80));
        g2d.drawString(String.valueOf(player.getSkill().getAttack().getLevel()),
                        panel.getWidth() - (width - 175), panel.getHeight() - (height - 110));


        af.translate(panel.getWidth() - (width - (this.hitpoints.getWidth() / 2)), panel.getHeight() - (height + 5 - (this.hitpoints.getWidth() / 2)));
        af.scale(0.5,0.5);
        g2d.drawImage(this.hitpoints, af, null);

        af.scale(0.9,0.9);
        af.translate(0, 70);
        g2d.drawImage(this.attack, af, null);

        af.scale(0.9,0.9);
        af.translate(0, 75);
        g2d.drawImage(this.strength, af, null);
    }

}
