package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioControl implements Runnable
{
    private static final int BUFFER_SIZE = 4096;
    private final String path;
    private Clip clip;
    private DataLine.Info info;
    public boolean muted;

    public AudioControl(String path)
    {
        this.path = path;
    }


    @Override
    public void run()
    {
        File audioFile = new File(path);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            this.info = new DataLine.Info(SourceDataLine.class, format);

            this.clip = AudioSystem.getClip();

            this.clip.open(audioStream);

            this.clip.start();

            System.out.println("Playback started.");

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead;
            this.muted = false;
            while (this.clip.isActive())
            {
                if(muted)
                {
                    break;
                }
            }

            clip.drain();
            clip.close();
            audioStream.close();

            System.out.println("Playback completed.");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }


    public boolean isMuted()
    {
        return muted;
    }

    public void setMuted(boolean muted)
    {
        this.muted = muted;
    }
}