package presentation;

import datamanager.ClientSettings;
import presentation.views.IntroView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        this.clientSettings = new ClientSettings();
    }

    public GameFrame()
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = "";
        this.clientSettings = new ClientSettings();
    }

    @Override
    public void run()
    {
        frame = new JFrame(this.gameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new IntroView(), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setLocation((int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                        (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));

        try
        {
            checkClientSettings();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.setLocation(  (int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                            (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();


    }


    public void checkClientSettings() throws IOException
    {
        ClientSettings.check();
        if(ClientSettings.getSettingsFile().length() == 0)
        {
            frame.setSize(this.screensize.width, this.screensize.height);
            ClientSettings.getClientProperties().put("width", String.valueOf(frame.getWidth()));
            ClientSettings.getClientProperties().put("height", String.valueOf(frame.getHeight()));
            ClientSettings.write();
        }
        else
        {
            ClientSettings.read();
            frame.setSize(new Dimension(Integer.parseInt(ClientSettings.getClientProperties().getProperty("width")),
                                        Integer.parseInt(ClientSettings.getClientProperties().getProperty("height"))));
        }
    }

    public static JFrame getFrame()
    {
        return frame;
    }
}