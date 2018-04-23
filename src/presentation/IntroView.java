package presentation;

import presentation.template.Fonts;

import javax.swing.*;
import java.awt.*;

public class IntroView extends JPanel
{
    private JButton play;
    private JButton settings;
    private JButton exit;

    public IntroView()
    {
        super();
        super.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.play = new JButton("Play");
        this.play.setFont(Fonts.introScreenButtons());

        this.settings = new JButton("Settings");
        this.settings.setFont(Fonts.introScreenButtons());

        this.exit = new JButton("Exit");
        this.exit.setFont(Fonts.introScreenButtons());

        super.add(this.play, BorderLayout.NORTH);
        super.add(this.settings, BorderLayout.CENTER);
        super.add(this.exit, BorderLayout.SOUTH);
    }
}
