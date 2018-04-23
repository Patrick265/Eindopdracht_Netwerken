package presentation;

import presentation.views.IntroView;
import presentation.views.SettingsView;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements Runnable
{
    private static JFrame frame;
    private JPanel mainPanel;
    private Dimension screensize;
    private String gameName;

    public GameFrame(String gameName)
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = gameName;
    }

    public GameFrame()
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = "";
    }

    @Override
    public void run()
    {
        frame = new JFrame(this.gameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new SettingsView(), BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.setSize(this.screensize.width, this.screensize.height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static JFrame getFrame()
    {
        return frame;
    }

    public Dimension getScreensize()
    {
        return screensize;
    }

    public void setScreensize(int width, int height)
    {
        this.screensize.setSize(width, height);
    }
}
