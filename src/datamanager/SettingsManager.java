package datamanager;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;

public class SettingsManager
{
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static SettingsManager instance = null;
    private Settings settings;
    private File file = new File("res/settings/client.json");

    private SettingsManager() throws IOException
    {
        System.out.println(file.length());
        if(file.length()  != 0)
        {
            read();
        }
        else
        {
            this.settings = new Settings(Toolkit.getDefaultToolkit().getScreenSize().width,
                                            Toolkit.getDefaultToolkit().getScreenSize().height);
            write();
        }
    }

    public static SettingsManager getInstance()
    {
        if(instance == null)
        {
            try
            {
                instance = new SettingsManager();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return instance;
    }


    public void write() throws IOException
    {
        objectMapper.writeValue(this.file, this.settings);
    }

    public Settings read() throws IOException
    {
        return this.settings = objectMapper.readValue(this.file, Settings.class);
    }

    public Settings getSettings()
    {
        return settings;
    }
}