package presentation.views;

import audio.AudioControl;
import datamanager.ClientSettings;
import presentation.GameFrame;
import presentation.template.Colors;
import presentation.template.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick de Jong
 */
public class SettingsView extends JPanel
{
    private JComboBox<Dimension> resolutionPullDown;
    private List<Dimension> resolutions;
    private JLabel resolutionLabel;
    private JButton returnView;
    private JLabel soundMute;
    private JCheckBox soundMuteCheckBox;
    private ClientSettings clientSettings;

    public SettingsView()
    {
        super(new GridBagLayout());
        super.requestFocusInWindow();
        super.setFocusable(true);
        initialise();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy++;
        super.add(this.resolutionLabel, gbc);
        gbc.gridx++;
        super.add(this.resolutionPullDown, gbc);
        gbc.gridy++;
        super.add(this.returnView, gbc);
        gbc.gridy++;
        super.add(this.soundMute);
        gbc.gridx++;
        super.add(this.soundMuteCheckBox);

    }

    /**
     * A method to initialise all of variables used, besides they are also styled and added listeners for it.
     */
    public void initialise()
    {
        this.clientSettings = ClientSettings.getInstance();
        buildResolutions();

        this.resolutionLabel = new JLabel("Screen Resolution");
        this.resolutionLabel.setFont(Fonts.settings());

        String[] resolutions = new String[this.resolutions.size()];
        for (int i = 0; i < this.resolutions.size(); i++)
        {
            resolutions[i] = "Width: " + this.resolutions.get(i).getWidth() +
                    " Height: " + this.resolutions.get(i).getHeight();
        }


        this.resolutionPullDown = new JComboBox(resolutions);
        this.resolutionPullDown.setFont(Fonts.settings());
        this.resolutionPullDown.addActionListener(e ->
        {
            int width = Integer.parseInt(this.resolutionPullDown.getSelectedItem().toString().substring(7, 11));
            int height;
            if (this.resolutionPullDown.getSelectedItem().toString().substring(22).length() > 5)
            {
                height = Integer.parseInt(this.resolutionPullDown.getSelectedItem().toString().substring(22, 26));
            } else
            {
                height = Integer.parseInt(this.resolutionPullDown.getSelectedItem().toString().substring(22, 25));
            }
            GameFrame.getFrame().setSize(width, height);
            this.clientSettings.getClientProperties().put("width", String.valueOf(width));
            this.clientSettings.getClientProperties().put("height", String.valueOf(height));
            try
            {
                this.clientSettings.write();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        });

        this.returnView = new JButton("Return");
        this.returnView.setFont(Fonts.settings());
        this.returnView.setPreferredSize(new Dimension(335, 50));
        this.returnView.setOpaque(true);
        this.returnView.setFocusPainted(false);
        this.returnView.setBackground(Colors.buttonBackground());
        this.returnView.setForeground(Colors.buttonFontColor());
        this.returnView.addActionListener(e ->
        {
            GameFrame.getFrame().getContentPane().removeAll();
            GameFrame.getFrame().setContentPane(new IntroView());
            GameFrame.getFrame().revalidate();
        });


        this.soundMute = new JLabel("Music");
        this.soundMute.setFont(Fonts.settings());

        this.soundMuteCheckBox = new JCheckBox("");
        this.soundMuteCheckBox.setFont(Fonts.settings());

        if(clientSettings.getClientProperties().getProperty("audio").equals("true"))
        {
            this.soundMuteCheckBox.setSelected(true);
        }
        else {
            this.soundMuteCheckBox.setSelected(false);
        }

        this.soundMuteCheckBox.addActionListener(e ->
        {
            this.clientSettings.getClientProperties().put("audio", String.valueOf(this.soundMuteCheckBox.isSelected()));
            try
            {
                this.clientSettings.write();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        });
    }

    /**
     * This method makes a list of resolutions we can use.
     * <p>The type of these resolutions are dimensions so you can easily store with and height</p>
     */
    public void buildResolutions()
    {
        this.resolutions = new ArrayList<>();
        this.resolutions.add(new Dimension(1980,1080));
        this.resolutions.add(new Dimension(1600,900));
        this.resolutions.add(new Dimension(1280,720));
        this.resolutions.add(new Dimension(1024,576));
    }
}
