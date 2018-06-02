package presentation;

import datamanager.SettingsManager;
import presentation.views.IntroView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IntroFrame implements Runnable
{
    private static JFrame frame;
    private JPanel mainPanel;
    private Dimension screensize;
    private String gameName;
    private SettingsManager settingsManager;

    public IntroFrame(String gameName)
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = gameName;
        this.settingsManager = SettingsManager.getInstance();
    }

    @Override
    public void run()
    {
        frame = new JFrame(this.gameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.add(new IntroView(), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        setup();


        frame.setLocation(  (int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                            (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setup()
    {
        try
        {
            frame.setSize(  this.settingsManager.read().getClientWidth(),
                            this.settingsManager.read().getClientHeight());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public static JFrame getFrame()
    {
        return frame;
    }

    public Dimension getScreenSize()
    {
        return screensize;
    }
}