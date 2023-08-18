package GUI.src;


import java.awt.Color;

import javax.swing.JPanel;

import GUI.src.utilities.*;

public class App {
    private SkeletalWindow window;
    
    App(String title){
        window = new SkeletalWindow(title);
        window.setIconImage(ImageIcons.LOGO.getImage());
        JPanel temp = new JPanel();
        temp.setBackground(new Color(228,228,228));

        TransparentButton HOME = new TransparentButton("Home", ImageIcons.reSize(ImageIcons.HOMEdark,25,25), window.getMenu());
        HOME.setSelectedIcon(ImageIcons.reSize(ImageIcons.HOME,25,25));
        window.getMenu().addTab(HOME, temp);

        TransparentButton MEMBERS = new TransparentButton("Members", ImageIcons.reSize(ImageIcons.CONTACTSdark,25,25), window.getMenu());
        MEMBERS.setSelectedIcon(ImageIcons.reSize(ImageIcons.CONTACTS,25,25));
        window.getMenu().addTab(MEMBERS, temp);

        TransparentButton OFFICIAL = new TransparentButton("Official", ImageIcons.reSize(ImageIcons.OFFICIALdark,25,25), window.getMenu());
        OFFICIAL.setSelectedIcon(ImageIcons.reSize(ImageIcons.OFFICIAL,25,25));
        window.getMenu().addTab (OFFICIAL, temp);

        TransparentButton FINANCIALdata = new TransparentButton("Financial data", ImageIcons.reSize(ImageIcons.COINSdark,20,20), window.getMenu());
        FINANCIALdata.setSelectedIcon(ImageIcons.reSize(ImageIcons.COINS,25,25));
        window.getMenu().addTab(FINANCIALdata, temp);

        TransparentButton AGENDA = new TransparentButton("Agenda", ImageIcons.reSize(ImageIcons.NOTEdark,25,25), window.getMenu());
        AGENDA.setSelectedIcon(ImageIcons.reSize(ImageIcons.NOTE,25,25));
        window.getMenu().addTab(AGENDA, temp);

        TransparentButton RULE = new TransparentButton("Rule", ImageIcons.reSize(ImageIcons.RULESdark,25,25), window.getMenu());
        RULE.setSelectedIcon(ImageIcons.reSize(ImageIcons.RULES,25,25));
        window.getMenu().addTab(RULE, temp);

        TransparentButton HELP = new TransparentButton("Help", ImageIcons.reSize(ImageIcons.HELPdark,25,25), window.getMenu());
        HELP.setSelectedIcon(ImageIcons.reSize(ImageIcons.HELP,25,25));
        window.getMenu().addTab(HELP, temp);

    }

    public static void main(String[] args) {
        new App("Idir");
    }
    
}
