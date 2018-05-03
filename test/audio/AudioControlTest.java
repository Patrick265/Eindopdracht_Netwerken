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
        this.audioControl = new AudioControl("res/audio/background.wav");
    }

    @Test
    void checkdevices()
    {
     //   audioControl.checkdevices();
    }

    @Test
    void play()
    {
        audioControl.run();
    }

}