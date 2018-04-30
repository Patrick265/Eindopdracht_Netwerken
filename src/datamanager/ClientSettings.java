package datamanager;

import jdk.nashorn.internal.ir.WhileNode;
import presentation.views.SettingsView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private String systemOS;
    private String userNameOS;
    private Map<String, String> paths;
    private File settingsFile;
    private List<String> settings;

    //Need more like OS X and Windows 7/8
    private String[] ostypes = {"Linux", "Windows 10"};

    public ClientSettings()
    {
        //This is for getting the os type
        this.systemOS = System.getProperty("os.name");

        //For saving path
        this.userNameOS = System.getProperty("user.name");

        this.paths = new HashMap<>();
        createpaths();

        this.settings = new ArrayList<>();
    }


    //Check's if the folder exits
    public void check() throws IOException
    {
        //Check for linux
        if(this.systemOS.toLowerCase().equals(this.ostypes[0].toLowerCase()))
        {
            Path dataPath = Paths.get(this.paths.get(this.ostypes[0].toLowerCase()));
            if(Files.exists(dataPath))
            {
                System.out.println("Looking for settingsFile file");
            } else {
                //Creating Main Folder
                new File(String.valueOf(dataPath)).mkdir();

            }
            Path clientPath = Paths.get(dataPath + "/client/");
            if(Files.exists(clientPath))
            {
                System.out.println("Searching for client settingsFile");
                boolean settingsFileCheck = new File(clientPath + "/settings.txt").exists();
                if(settingsFileCheck)
                {
                    System.out.println("Found settings file");
                    this.settingsFile = new File(clientPath + "/settings.txt");
                }
                else {
                    System.out.println("Creating a new settings file");
                    this.settingsFile = new File(clientPath + "/settings.txt");
                    this.settingsFile.createNewFile();
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

        check();
        if(this.settings.size() > 0)
        {
            FileWriter writer = new FileWriter(this.settingsFile);
            {
                writer.write("");
                for(int i = 0; i < this.settings.size(); i++)
                {
                    System.out.println(this.settings.get(i));
                    writer.write(this.settings.get(i));
                }
            }
            //Mandatory!!!
            writer.close();
        }
    }

    public void read() throws IOException
    {
        check();
        Scanner scanner = new Scanner(this.settingsFile);
        while (scanner.hasNext())
        {
            scanner.nextLine();

        }
        scanner.close();

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
     * @return
     */
    public String getSystemOS()
    {
        return systemOS;
    }

    public void createpaths()
    {
        //linux
        this.paths.put(this.ostypes[0].toLowerCase(), "/home/" + this.userNameOS + "/Documents/TreacherousMUD/");
        //Windows10
        this.paths.put(this.ostypes[1].toLowerCase(), "C:/Users/" + this.userNameOS + "/Documents/TreacherousMUD/");
    }
}
