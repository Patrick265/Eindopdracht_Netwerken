package presentation;

import game.GameDrawer;
import presentation.views.IntroView;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements Runnable
{
    private static JFrame frame;
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
        frame = new JFrame(this.gameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new GameDrawer(), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setLocation(  (int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                            (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));
        frame.setSize(this.screensize.width, this.screensize.height);
        frame.setLocation(  (int) (this.screensize.getWidth() / 2) - (frame.getWidth() / 2),
                            (int) (this.screensize.getHeight() / 2) - (frame.getHeight() / 2));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();

    }

    public static JFrame getFrame()
    {
        return frame;
    }
}
