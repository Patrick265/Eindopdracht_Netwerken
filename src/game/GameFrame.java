package game;

import datamanager.SettingsManager;

import javax.swing.*;
import java.io.IOException;

public class GameFrame
{
    public GameFrame() throws IOException
    {
        SettingsManager settingsManager = null;
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            settingsManager = SettingsManager.getInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("TreacherousMUD");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new GameDrawer());
        frame.setSize(settingsManager.read().getClientWidth(), settingsManager.read().getClientHeight());
        frame.setVisible(true);
    }
}
