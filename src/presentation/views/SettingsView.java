package presentation.views;

import datamanager.SettingsManager;
import presentation.IntroFrame;
import template.Colors;
import template.Fonts;

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
    private SettingsManager settingsManager;

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
    }

    private void initialise()
    {
        this.settingsManager = SettingsManager.getInstance();
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
                this.resolutionPullDown.setSelectedItem(this.resolutionPullDown.getSelectedItem());
                height = Integer.parseInt(this.resolutionPullDown.getSelectedItem().toString().substring(22, 26));
            } else
            {
                height = Integer.parseInt(this.resolutionPullDown.getSelectedItem().toString().substring(22, 25));
            }
            IntroFrame.getFrame().setSize(width, height);
            this.settingsManager.getSettings().setClientWidth(width);
            this.settingsManager.getSettings().setClientHeight(height);
            try
            {
                this.settingsManager.write();
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
            IntroFrame.getFrame().getContentPane().removeAll();
            IntroFrame.getFrame().setContentPane(new IntroView());
            IntroFrame.getFrame().revalidate();
        });
    }

    private void buildResolutions()
    {
        this.resolutions = new ArrayList<>();
        this.resolutions.add(new Dimension(1980,1080));
        this.resolutions.add(new Dimension(1600,900));
        this.resolutions.add(new Dimension(1280,720));
        this.resolutions.add(new Dimension(1024,576));
    }
}
