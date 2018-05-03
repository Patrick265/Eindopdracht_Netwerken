package presentation.views;

import datamanager.ClientSettings;
import javafx.scene.input.KeyCode;
import presentation.GameFrame;
import presentation.template.Colors;
import presentation.template.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Patrick de Jong
 */
public class SettingsView extends JPanel
{
    private JComboBox<Dimension> resolutionPullDown;
    private List<Dimension> resolutionsformats;
    private JLabel resolutionLabel;
    private JButton returnView;


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

    public void initialise()
    {
        this.resolutionLabel = new JLabel("Screen Resolution");
        this.resolutionLabel.setFont(Fonts.settings());
        buildResolutions();
        String[] resolutions = new String[this.resolutionsformats.size()];
        for (int i = 0; i < this.resolutionsformats.size(); i++)
        {
            resolutions[i] = "Width: " + this.resolutionsformats.get(i).getWidth() +
                    " Height: " + this.resolutionsformats.get(i).getHeight();
        }


        this.resolutionPullDown = new JComboBox(resolutions);
        this.resolutionPullDown.setFont(Fonts.settings());
        String widthProperty = ClientSettings.getClientProperties().getProperty("width");
        System.out.println(widthProperty);
        System.out.println(this.resolutionPullDown.getSelectedItem());

        for(Object entry : this.resolutionPullDown.getComponents())
        {
            System.out.println("Entry: " + entry.toString());
            //if()
        }

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
            ClientSettings.getClientProperties().put("width", String.valueOf(width));
            ClientSettings.getClientProperties().put("height", String.valueOf(height));
            try
            {
                ClientSettings.write();
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
