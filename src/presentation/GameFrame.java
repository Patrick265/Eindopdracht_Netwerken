package presentation;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements Runnable
{
    private JFrame frame;
    private JPanel mainPanel;
    private Dimension screensize;
    private String gameName;

    public GameFrame(String gameName)
    {
        this.screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameName = gameName;
    }

    @Override
    public void run()
    {
        this.frame = new JFrame(this.gameName);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(new BorderLayout());

        this.mainPanel.add(new IntroView(), BorderLayout.CENTER);
        this.frame.setContentPane(mainPanel);
        this.frame.setSize(this.screensize.width, this.screensize.height);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
}
