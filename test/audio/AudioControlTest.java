package audio;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AudioControlTest
{

    private AudioControl audioControl;

    public AudioControlTest()
    {
        this.audioControl = new AudioControl();
    }

    @Test
    void checkdevices()
    {
        audioControl.checkdevices();
    }

    @Test
    void play()
    {

        try
        {
            audioControl.playAudio("res/audio/test_audio.wav");
        } catch (LineUnavailableException | IOException | InterruptedException | UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }

    }

}