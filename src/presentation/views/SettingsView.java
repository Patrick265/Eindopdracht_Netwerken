package presentation.views;

import presentation.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsView extends JPanel
{
    private JComboBox<Dimension> resolutionPullDown;
    private List<Dimension> resolutionsformats;
    private JLabel resolutionLabel;

    public SettingsView()
    {
        super(new GridBagLayout());
        initialise();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0,0,10,10);
        //gbc.gridy++;
        super.add(this.resolutionLabel, gbc);
        gbc.gridx++;
        super.add(this.resolutionPullDown, gbc);
    }

    public void initialise()
    {
        this.resolutionLabel = new JLabel("Screen Resolution");
        buildResolutions();
        String[] resolutions = new String[this.resolutionsformats.size()];
        for(int i = 0; i < this.resolutionsformats.size(); i++)
        {
            resolutions[i] = "Width: " + this.resolutionsformats.get(i).getWidth() +
                            " Height: " + this.resolutionsformats.get(i).getHeight();
        }


        this.resolutionPullDown = new JComboBox(resolutions);

        this.resolutionPullDown.addActionListener(e ->
        {
            System.out.println(this.resolutionPullDown.getSelectedItem());
            for(int i = 0; i < resolutions.length; i++)
            {
                System.out.println(resolutions[i].substring(7,13));
                int width = Integer.parseInt(resolutions[i].substring(7,13));
                System.out.println(width);
                if(resolutions[i].equals(this.resolutionsformats.get(i).width))
                {
                    GameFrame.getFrame().setSize(
                                            this.resolutionsformats.get(i).width,
                                            this.resolutionsformats.get(i).height);
                }
            }
        });
    }

    public void buildResolutions()
    {
        this.resolutionsformats = new ArrayList<>();
        this.resolutionsformats.add(new Dimension(1980,1080));
        this.resolutionsformats.add(new Dimension(1600,900));
        this.resolutionsformats.add(new Dimension(1280,720));
        this.resolutionsformats.add(new Dimension(1024,576));
    }
}
