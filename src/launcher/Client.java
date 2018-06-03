package launcher;

import presentation.IntroFrame;
import javax.swing.*;

public class Client
{

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        IntroFrame frame = new IntroFrame("TreacherousMUD");
        Thread frameThread = new Thread(frame);
        frameThread.start();
    }

    public Client()
    {

    }
}
