package launcher;

import audio.AudioControl;
import datamanager.SettingsManager;
import presentation.IntroFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Tom Martens, Patrick de Jong
 * @since 23 April 2018
 */
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
