package presentation.views;

import game.GameDrawer;
import game.GameFrame;
import presentation.IntroFrame;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel
{
    private JButton connect;
    private JTextField ipField;
    private JTextField nameField;
    private static String address;
    private static String username;

    public LoginView()
    {
        super();
        this.ipField = new JTextField("");
        this.ipField.setPreferredSize(new Dimension(275,35));
        this.nameField = new JTextField();

        this.connect = new JButton("Connect");
        this.connect.setPreferredSize(new Dimension(100,35));
        this.connect.addActionListener(e -> {

            address = this.ipField.getText();
            username = this.nameField.getText();
            GameFrame launch = new GameFrame();
            IntroFrame.getFrame().dispose();
        });
        this.nameField.setPreferredSize(new Dimension(100,35));

        super.add(this.ipField);
        super.add(this.connect);
        super.add(this.nameField);

    }

    public static String getAddress()
    {
        return address;
    }

    public static String getUsername()
    {
        return username;
    }
}
