package server.presentation;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

public class ServerFrame
{
    private final JFrame frame;
    private final JPanel panel;
    private final JTextArea textArea;
    private final String name;
    private final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    public ServerFrame(String name)
    {
        this.name = name;
        this.frame = new JFrame(name);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.frame.setVisible(true);

        this.panel.add(this.textArea);
        this.frame.setContentPane(this.panel);
        this.frame.setSize(400,400);
        this.frame.setLocation((this.screensize.width / 2) - (this.frame.getWidth() / 2),
                                (this.screensize.height / 2) - (this.frame.getHeight() / 2));
    }

    public String standardClientText(InetAddress inetAddress)
    {
        return "\n" + "Client " + inetAddress.getHostName() + " is connected. " + "\n" + "IP-adress is:" + inetAddress.getHostAddress() + "\n";
    }
    public JTextArea getTextArea()
    {
        return textArea;
    }
}