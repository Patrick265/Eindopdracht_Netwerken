package presentation;

import datamanager.ClientSettings;
import game.GameDrawer;
import presentation.connectorframe.IpConnectView;
import presentation.views.IntroView;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
        this.mainPanel.add(new GameDrawer(), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        setup();
        try
        {
            System.out.println(IpConnectView.getAddress());
            connectionToServer(IpConnectView.getAddress(), 420);
            setup();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.setLocation(  (int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                            (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setup()
    {
        frame.setSize(800,600);
    }

    private void connectionToServer(String adress, int port) throws IOException
    {
        Socket socket = new Socket(adress, port);
        DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
        DataInputStream fromServer = new DataInputStream(socket.getInputStream());

        toServer.flush();
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