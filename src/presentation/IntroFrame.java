package presentation;

import datamanager.ClientSettings;
import game.GameDrawer;
import presentation.views.IntroView;

import javax.swing.*;
import java.awt.*;

public class IntroFrame implements Runnable
{
    private static JFrame frame;
    private JPanel mainPanel;
    private Dimension screensize;
    private String gameName;
    private ClientSettings clientSettings;

    public IntroFrame(String gameName)
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = gameName;
        this.clientSettings= ClientSettings.getInstance();
    }

    public IntroFrame()
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = "";
        this.clientSettings= ClientSettings.getInstance();
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
        frame.setSize(800,600);
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