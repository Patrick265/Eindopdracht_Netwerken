import presentation.GameFrame;
import presentation.template.Colors;

import javax.swing.*;

/**
 * @author Tom Martens, Patrick de Jong
 * @since 23 April 2018
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        Runnable frame = new GameFrame("TreacherousMUD");
        Thread frameThread = new Thread(frame);
        frameThread.start();
    }
}
