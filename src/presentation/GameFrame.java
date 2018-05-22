package presentation;

import datamanager.ClientSettings;
import presentation.views.IntroView;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements Runnable
{
    private static JFrame frame;
    private JPanel mainPanel;
    private Dimension screensize;
    private String gameName;
    private ClientSettings clientSettings;

    public GameFrame(String gameName)
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = gameName;
        this.clientSettings= ClientSettings.getInstance();
    }

    public GameFrame()
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

        mainPanel.add(new IntroView(), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        setup();
        frame.setLocation((int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                        (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();
    }

    public void setup()
    {
        System.out.println(clientSettings.getClientProperties().getProperty("width"));
        System.out.println(clientSettings.getClientProperties().getProperty("height"));
        frame.setSize(  Integer.parseInt(clientSettings.getClientProperties().getProperty("width")),
                        Integer.parseInt(clientSettings.getClientProperties().getProperty("height")));
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