package presentation.views;

import Game.GameDrawer;
import presentation.GameFrame;
import presentation.template.Colors;
import presentation.template.Fonts;

import javax.swing.*;
import java.awt.*;

public class IntroView extends JPanel
{
    private JButton play;
    private JButton settings;
    private JButton exit;
    private Dimension buttonsize;

    public IntroView()
    {
        super(new GridBagLayout());
        initialise();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;


        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0,0,10,10);
        gbc.gridy++;
        super.add(this.play, gbc);
        gbc.gridy++;
        super.add(this.settings, gbc);
        gbc.gridy++;
        super.add(this.exit, gbc);


    }

    private void initialise()
    {
        this.buttonsize = new Dimension(400,50);
        this.play = new JButton("Play");
        this.play.setOpaque(true);
        this.play.setBackground(Colors.buttonBackground());
        this.play.setFont(Fonts.introScreenButtons());
        this.play.setForeground(Colors.buttonFontColor());
        this.play.setPreferredSize(this.buttonsize);
        this.play.setFocusPainted(false);
        this.play.addActionListener(e ->
        {
            GameFrame.getFrame().setContentPane(new GameDrawer());
            GameFrame.getFrame().revalidate();
        });

        this.settings = new JButton("Settings");
        this.settings.setOpaque(true);
        this.settings.setBackground(Colors.buttonBackground());
        this.settings.setFont(Fonts.introScreenButtons());
        this.settings.setPreferredSize(this.buttonsize);
        this.settings.setFocusPainted(false);
        this.settings.setForeground(Colors.buttonFontColor());
        this.settings.addActionListener(e ->
        {
            GameFrame.getFrame().setContentPane(new SettingsView());
            GameFrame.getFrame().revalidate();
        });

        this.exit = new JButton("Exit");
        this.exit.setOpaque(true);
        this.exit.setBackground(Colors.buttonBackground());
        this.exit.setFont(Fonts.introScreenButtons());
        this.exit.setPreferredSize(this.buttonsize);
        this.exit.setFocusPainted(false);
        this.exit.setForeground(Colors.buttonFontColor());
        this.exit.addActionListener(e -> GameFrame.getFrame().dispose());
    }
}