package launcher;

import audio.AudioControl;
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
        Runnable audio = new AudioControl("res/audio/background.wav");


        Thread frameThread = new Thread(frame);
        Thread audioThread = new Thread(audio);

        frameThread.start();
        audioThread.start();
    }
}
