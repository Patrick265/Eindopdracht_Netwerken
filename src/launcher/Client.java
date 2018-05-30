package launcher;

import audio.AudioControl;
import presentation.IntroFrame;

import javax.swing.*;

/**
 * @author Tom Martens, Patrick de Jong
 * @since 23 April 2018
 */
public class Client
{

    public static void main(String[] args)
    {
        new Client();
    }

    public Client()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        IntroFrame frame = new IntroFrame("TreacherousMUD");
        AudioControl audio = new AudioControl("res/audio/background.wav");
        Thread frameThread = new Thread(frame);
        Thread audioThread = new Thread(audio);

        frameThread.start();
    }
}
