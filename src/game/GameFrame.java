package game;

import presentation.views.LoginView;

import javax.swing.*;

public class GameFrame
{
    public GameFrame()
    {
        JFrame frame = new JFrame("TreacherousMUD");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new GameDrawer());
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
