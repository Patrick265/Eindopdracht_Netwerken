package audio;

import launcher.Main;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioControl
{
    private Mixer mixer;
    private Mixer.Info[] devices;
    private Clip clip;
    private DataLine.Info dataInfo;

    public AudioControl()
    {
        this.devices = AudioSystem.getMixerInfo();
        this.mixer = AudioSystem.getMixer(this.devices[0]);
        this.dataInfo = new DataLine.Info(Clip.class, null);
    }

    public void checkdevices()
    {
        System.out.println("Audio devices found:");
        for(Mixer.Info info : devices)
        {
            System.out.println(info.getName() + " - " +info.getDescription());
        }
    }

    public void playAudio(String path) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException
    {
        this.clip = (Clip) this.mixer.getLine(dataInfo);

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
        System.out.println("HET IS NULL KUT");
        if(audioStream != null)
        {
            clip.open(audioStream);
            clip.start();
            do
            {
                Thread.sleep(50);
            } while(clip.isActive());
        }
    }

}
