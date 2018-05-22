package launcher;

import audio.AudioControl;
import datamanager.ClientSettings;
import presentation.GameFrame;
import presentation.template.Colors;
import presentation.views.SettingsView;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Tom Martens, Patrick de Jong
 * @since 23 April 2018
 */
public class Main
{
    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        ClientSettings clientSettings = ClientSettings.getInstance();
        try
        {
            if(clientSettings.getSettingsFile().exists() && clientSettings.getSettingsFile().length() != 0)
            {

                clientSettings.read();
            }
            else{

                clientSettings.setup();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        GameFrame frame = new GameFrame("TreacherousMUD");
        AudioControl audio = new AudioControl("res/audio/background.wav");


        Thread frameThread = new Thread(frame);
        Thread audioThread = new Thread(audio);

        frameThread.start();
        System.out.println(clientSettings.getClientProperties());

        if(clientSettings.getClientProperties().getProperty("audio").equals("true"))
        {
            audioThread.start();
        }
    }
}
