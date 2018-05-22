package datamanager;

import presentation.GameFrame;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Patrick de Jong
 * @since 30 April 2018
 */
public class ClientSettings
{

    private static ClientSettings instance = null;

    //This works only for Linux and Windows 10
    private final String systemOS = System.getProperty("os.name");
    private final String userNameOS = System.getProperty("user.name");;

    //Settings File
    private File settingsFile;

    private Map<String, String> paths;
    private Map<String, String> settings;

    //Storing settings.
    private Properties clientProperties;

    //Need more like OS X and Windows 7/8
    private String[] ostypes;

    private ClientSettings()
    {
        paths = new HashMap<>();
        createpaths();
        settings = new HashMap<>();
        clientProperties = new Properties();
        try
        {
            check();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    public static ClientSettings getInstance()
    {
        if(instance == null)
        {
            instance = new ClientSettings();
        }

        return instance;
    }


    /**
     * This class check if the clientsettings file exists.
     * @throws IOException If the file cannot be found
     */
    public void check() throws IOException
    {

        //Check for linux
        if(systemOS.toLowerCase().equals(ostypes[0].toLowerCase()))
        {
            Path dataPath = Paths.get(paths.get(ostypes[0].toLowerCase()));
            if(Files.exists(dataPath))
            {
                System.out.println("Looking for settingsFile file");
            } else {
                //Creating launcher.Main Folder
                new File(String.valueOf(dataPath)).mkdir();

            }
            Path clientPath = Paths.get(dataPath + "/client/");
            if(Files.exists(clientPath))
            {
                System.out.println("Searching for client settingsFile");
                boolean settingsFileCheck = new File(clientPath + "/config.properties").exists();
                if(settingsFileCheck)
                {
                    System.out.println("Found settings file");
                    settingsFile = new File(clientPath + "/config.properties");
                }
                else {
                    System.out.println("Creating a new settings file");
                    settingsFile = new File(clientPath + "/config.properties");
                    settingsFile.createNewFile();
                }
            } else {
                System.out.println("Creating folder for client settingsFile");
                new File(String.valueOf(dataPath + "/client/")).mkdir();
                this.settingsFile = new File(clientPath + "/config.properties");
            }
        }

        //Check for Windows 10
        if(systemOS.toLowerCase().equals(ostypes[1].toLowerCase()))
        {
            Path dataPath = Paths.get(paths.get(ostypes[1].toLowerCase()));
            if(Files.exists(dataPath))
            {
                System.out.println("Looking for settingsFile file");
            } else {
                //Creating launcher.Main Folder
                new File(String.valueOf(dataPath)).mkdir();

            }
            Path clientPath = Paths.get(dataPath + "/client/");
            if(Files.exists(clientPath))
            {
                System.out.println("Searching for client settingsFile");
                boolean settingsFileCheck = new File(clientPath + "/config.properties").exists();
                if(settingsFileCheck)
                {
                    System.out.println("Found settings file");
                    settingsFile = new File(clientPath + "/config.properties");
                }
                else {
                    System.out.println("Creating a new settings file");
                    settingsFile = new File(clientPath + "/config.properties");
                    settingsFile.createNewFile();
                }
            } else {
                System.out.println("Creating folder for client settingsFile");
                new File(String.valueOf(dataPath + "/client/")).mkdir();
            }
        }
    }


    /**
     * Writing all of the settings to the file
     * @throws IOException When the file is not found
     */
    public void write() throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(settingsFile);
        clientProperties.store(outputStream, null);

    }

    /**
     * Builds a standard setting scenario for first time launch
     */
    public void setup() throws IOException
    {
        GameFrame gameFrame = new GameFrame();
        clientProperties.put("width", String.valueOf(gameFrame.getScreenSize().width));
        clientProperties.put("height", String.valueOf(gameFrame.getScreenSize().height));
        clientProperties.put("audio", String.valueOf(true));
        System.out.println("writing");
        write();
    }


    /**
     * Reads the properties file
     * @throws IOException if file cannot be found.
     */
    public void read() throws IOException
    {
        FileInputStream inputStream = new FileInputStream(settingsFile);
        clientProperties.load(inputStream);
    }

    /**
     * Retrieves the user name used in computer.
     * @return returns the username.
     */
    public String getUserNameOS()
    {
        return userNameOS;
    }

    /**
     * Retrieves the system you are using.
     * @return returns the OS the user is using.
     */
    public String getSystemOS()
    {
        return systemOS;
    }

    /**
     * Creates the paths needed for every type of OS we support.
     */
    public void createpaths()
    {
        ostypes = new String[]{"Linux", "Windows 10"};
        //linux
        paths.put(ostypes[0].toLowerCase(), "/home/" + userNameOS + "/Documents/TreacherousMUD/");
        //Windows10
        paths.put(ostypes[1].toLowerCase(), "C:/Users/" + userNameOS + "/Documents/TreacherousMUD/");
    }

    public Properties getClientProperties()
    {
        return clientProperties;
    }

    public File getSettingsFile()
    {
        return settingsFile;
    }
}
