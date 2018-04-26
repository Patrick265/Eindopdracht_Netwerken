package Game;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}