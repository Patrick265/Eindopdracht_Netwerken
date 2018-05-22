package game;

import game.charachter.Player;
import game.map.TiledMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameDrawer extends JPanel
{
    Player player;

    public GameDrawer()
    {
        player = new Player(new Point(200,200),"Frankie");
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        TiledMap map = new TiledMap("res/map/map.json");
        map.debugDraw(g2d);
        player.drawPlayer(g2d);
    }
}