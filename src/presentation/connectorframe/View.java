package presentation.connectorframe;

import javax.swing.*;
import java.awt.*;

public class View
{
    private JFrame frame;
    private JPanel panel;
    private JButton connect;
    private JTextField field;
    private String address;

    public static void main(String[] args)
    {
        new View();
    }
    public View()
    {
        this.frame = new JFrame("Connect to IP-Adress");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel();


        this.connect = new JButton("Connect");
        this.connect.setPreferredSize(new Dimension(100,35));
        this.connect.addActionListener(e -> {
            this.address = this.field.getText();
        });
        this.field = new JTextField();
        this.field.setPreferredSize(new Dimension(275,35));

        this.panel.add(this.field);
        this.panel.add(this.connect);

        this.frame.setContentPane(this.panel);
        this.frame.setSize(400,400);
        this.frame.setVisible(true);
    }

    public String getAddress()
    {
        return address;
    }
}
