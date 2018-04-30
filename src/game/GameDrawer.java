package game;

import game.map.TiledMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameDrawer extends JPanel
{

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tx = new AffineTransform();
        TiledMap map = new TiledMap("res/map/map.json");
        map.debugDraw(g2d, tx);
    }
}