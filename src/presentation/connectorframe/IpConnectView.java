package presentation.connectorframe;

import launcher.Main;
import presentation.GameFrame;
import sun.misc.Launcher;

import javax.swing.*;
import java.awt.*;

public class IpConnectView implements Runnable
{

    public static void main(String[] args)
    {
        Thread thread = new Thread(new IpConnectView());
        thread.start();
    }
    private JFrame frame;
    private JPanel panel;
    private JButton connect;
    private JTextField field;
    private static String address;

    public static String getAddress()
    {
        return address;
    }

    @Override
    public void run()
    {
        this.frame = new JFrame("Connect to IP-Adress");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel();

        this.field = new JTextField("145.49.48.60");
        this.field.setPreferredSize(new Dimension(275,35));

        this.connect = new JButton("Connect");
        this.connect.setPreferredSize(new Dimension(100,35));
        this.connect.addActionListener(e -> {
            address = this.field.getText();
            Main launcher = new Main();
            this.frame.dispose();
        });


        this.panel.add(this.field);
        this.panel.add(this.connect);

        this.frame.setContentPane(this.panel);
        this.frame.setSize(400,400);
        this.frame.setVisible(true);
    }


}
