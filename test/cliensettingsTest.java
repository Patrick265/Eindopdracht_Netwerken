import datamanager.ClientSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class cliensettingsTest
{
    private ClientSettings clientSettings;

    public cliensettingsTest()
    {
        this.clientSettings = new ClientSettings();
    }

    @Test
    public void other()
    {
        System.out.println(clientSettings.getUserNameOS());
        System.out.println(clientSettings.getSystemOS());
    }

    @Test
    public void check()
    {
        try
        {
            this.clientSettings.check();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void write()
    {
        clientSettings.addSetting();
        try
        {
            clientSettings.write();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}