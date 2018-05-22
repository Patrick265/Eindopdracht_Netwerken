package presentation.loginframe;

import launcher.Main;

import javax.swing.*;
import java.awt.*;

public class LoginView implements Runnable
{

    public static void main(String[] args)
    {
        Thread thread = new Thread(new LoginView());
        thread.start();
    }
    private JFrame frame;
    private JPanel panel;
    private JButton connect;
    private JTextField ipField;
    private JTextField nameField;
    private static String address;
    private static String username;

    @Override
    public void run()
    {
        this.frame = new JFrame("Connect to IP-Adress");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel();

        this.ipField = new JTextField("145.49.48.60");
        this.ipField.setPreferredSize(new Dimension(275,35));
        this.nameField = new JTextField();


        this.connect = new JButton("Connect");
        this.connect.setPreferredSize(new Dimension(100,35));
        this.connect.addActionListener(e -> {
            address = this.ipField.getText();
            username = this.nameField.getText();
            Main launcher = new Main();
            this.frame.dispose();
        });

        this.nameField.setPreferredSize(new Dimension(100,35));

        this.panel.add(this.ipField);
        this.panel.add(this.connect);
        this.panel.add(this.nameField);

        this.frame.setContentPane(this.panel);
        this.frame.setSize(400,400);
        this.frame.setVisible(true);
    }

    public static String getUsername()
    {
        return username;
    }

    public static String getAddress()
    {
        return address;
    }
}
