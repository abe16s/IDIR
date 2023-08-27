package GUI.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.ImageIcons;

public class HomePage extends JPanel{
    private JLabel background = new JLabel(ImageIcons.reSize(ImageIcons.LOGObackground, 200, 200));
    private  JLabel welcome = new JLabel("Welcome");
    private ColoredButton paymentHistory = new ColoredButton("Payment history",this);
    private ColoredButton collectPayment = new ColoredButton("Collect monthly payment ",this);
    
    
    private BasePanel displayPanel;
    
    public HomePage (BasePanel basePanel){
        this.displayPanel = basePanel;
        setBackground(new Color(228,228,228));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(50));

        background.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(background);
        add(Box.createVerticalStrut(50));

        welcome.setFont(new Font("Serif", Font.BOLD, 30));
        add(welcome);
        add(Box.createVerticalStrut(40));

        JPanel p = new JPanel();
        p.setBackground(new Color(228,228,228));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);

        collectPayment.setAlignmentX(Component.LEFT_ALIGNMENT);
        collectPayment.setSelectedColor(new Color(79,170,255));
        collectPayment.setNormalColor(new Color(147,175,207));
        add(collectPayment.getWhole());

        paymentHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        paymentHistory.setSelectedColor(new Color(79,170,255));
        paymentHistory.setNormalColor(new Color(147,175,207));
        add(paymentHistory.getWhole());
        
        add(p);
        setVisible(true);
    }  
}