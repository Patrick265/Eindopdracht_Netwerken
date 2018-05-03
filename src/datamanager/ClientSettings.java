package datamanager;

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

    //This works only for Linux and Windows 10
    private final static String systemOS = System.getProperty("os.name");
    private final static String userNameOS = System.getProperty("user.name");;

    //Settings File
    private static File settingsFile;

    private static Map<String, String> paths;
    private static Map<String, String> settings;

    //Storing settings.
    private static Properties clientProperties;

    //Need more like OS X and Windows 7/8
    private static String[] ostypes;

    public ClientSettings()
    {
        paths = new HashMap<>();
        createpaths();
        settings = new HashMap<>();
        clientProperties = new Properties();
    }


    /**
     * This class check if the clientsettings file exists.
     * @throws IOException If the file cannot be found
     */
    public static void check() throws IOException
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
    public static void write() throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(settingsFile);
        clientProperties.store(outputStream, null);

    }

    public static void read() throws IOException
    {
        FileInputStream inputStream = new FileInputStream(settingsFile);
        clientProperties.load(inputStream);

        Enumeration<?> e = clientProperties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = clientProperties.getProperty(key);
        }
    }


    public void readList(Map<String, String> settings)
    {
        for(Map.Entry<String, String> entry : settings.entrySet())
        {
            clientProperties.setProperty(entry.getKey(), entry.getValue().toLowerCase());
        }
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
    public static void createpaths()
    {
        ostypes = new String[]{"Linux", "Windows 10"};
        //linux
        paths.put(ostypes[0].toLowerCase(), "/home/" + userNameOS + "/Documents/TreacherousMUD/");
        //Windows10
        paths.put(ostypes[1].toLowerCase(), "C:/Users/" + userNameOS + "/Documents/TreacherousMUD/");
    }

    public static Properties getClientProperties()
    {
        return clientProperties;
    }

    public static File getSettingsFile()
    {
        return settingsFile;
    }
}
