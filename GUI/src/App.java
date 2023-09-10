package GUI.src;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.UIManager;
import GUI.src.utilities.*;

public class App {
    private SkeletalWindow window;
    public static IndividualProfile INDIVIDUAL_PROFILE;
    public static IndividualReceiptPanel INDIVIDUAL_RECEIPT;
    public static Connection DATABASE_CONNECTION;

    App(String title){
        //This block of code is to make the whole GUI look and feel like the operating system. Can be seen in the ScrollPane and the file choosers
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        window = new SkeletalWindow(title);
        window.setIconImage(ImageIcons.LOGO.getImage());

        String url = "jdbc:mysql://localhost:3306/idir";
        String username = "root";
        String password = "bekabirhanu";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DATABASE_CONNECTION = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the MySQL database.");
        } catch (Exception e) {
            System.out.println(e);
        } 


        HomePage homePage = new HomePage(window);
        OfficialsPanel offcialsPage = new OfficialsPanel(window.getBasePanel());

        TransparentButton HOME = new TransparentButton("Home", ImageIcons.reSize(ImageIcons.HOMEdark,25,25), window.getMenu());
        HOME.setSelectedIcon(ImageIcons.reSize(ImageIcons.HOME,30,30));
        window.getMenu().addTab(HOME, homePage);
        window.getMenu().add(Box.createVerticalStrut(5));

        TransparentButton MEMBERS = new TransparentButton("Members", ImageIcons.reSize(ImageIcons.CONTACTSdark,25,25), window.getMenu());
        MEMBERS.setSelectedIcon(ImageIcons.reSize(ImageIcons.CONTACTS,30,30));
        window.getMenu().addTab(MEMBERS, new MembersPanel(window.getBasePanel()));
        window.getMenu().add(Box.createVerticalStrut(5));

        INDIVIDUAL_PROFILE = new IndividualProfile(window.getBasePanel());
        window.getBasePanel().addMyTab(INDIVIDUAL_PROFILE,"individualProfile");

        TransparentButton OFFICIAL = new TransparentButton("Officials", ImageIcons.reSize(ImageIcons.OFFICIALdark,25,25), window.getMenu());
        OFFICIAL.setSelectedIcon(ImageIcons.reSize(ImageIcons.OFFICIAL,30,30));
        window.getMenu().addTab (OFFICIAL, offcialsPage);
        window.getMenu().add(Box.createVerticalStrut(5));

        TransparentButton FINANCIALdata = new TransparentButton("Financial data", ImageIcons.reSize(ImageIcons.COINSdark,23,23), window.getMenu());
        FINANCIALdata.setSelectedIcon(ImageIcons.reSize(ImageIcons.COINS,28,28));
        window.getMenu().addTab(FINANCIALdata, new FinancePanel(window.getBasePanel()));
        window.getMenu().add(Box.createVerticalStrut(5));

        INDIVIDUAL_RECEIPT = new IndividualReceiptPanel(window.getBasePanel());
        window.getBasePanel().addMyTab(INDIVIDUAL_RECEIPT,"individualReceipt");

        TransparentButton AGENDA = new TransparentButton("Agendas", ImageIcons.reSize(ImageIcons.NOTEdark,25,25), window.getMenu());
        AGENDA.setSelectedIcon(ImageIcons.reSize(ImageIcons.NOTE,30,30));
        window.getMenu().addTab(AGENDA, new AgendaList(window.getBasePanel()));
        window.getMenu().add(Box.createVerticalStrut(5));

        TransparentButton RULE = new TransparentButton("Rules", ImageIcons.reSize(ImageIcons.RULESdark,25,25), window.getMenu());
        RULE.setSelectedIcon(ImageIcons.reSize(ImageIcons.RULES,30,30));
        window.getMenu().addTab(RULE, new RulesPanel(window.getBasePanel()));
        window.getMenu().add(Box.createVerticalStrut(5));

        TransparentButton HELP = new TransparentButton("Help", ImageIcons.reSize(ImageIcons.HELPdark,27,27), window.getMenu());
        HELP.setSelectedIcon(ImageIcons.reSize(ImageIcons.HELP,32,32));
        window.getMenu().addTab(HELP, new Help(window.getBasePanel()));
        window.getMenu().add(Box.createVerticalStrut(5));

        window.getMenu().setPreferredSize(new Dimension(140, window.getMenu().getPreferredSize().height)); //set the size of the MenuBar so as to not increase when financial data is hovered over
        Component[] components = window.getMenu().getComponents();
            for (Component c : components) {
                try{((JButton)c).setMargin(new Insets(0, 6, 0, 0));}
                catch (Exception e) {} 
        }
        
        homePage.giveAffectedButtons(MEMBERS);
        homePage.giveAffectedButtons(FINANCIALdata);
        offcialsPage.giveAffectedButtons(MEMBERS, window.getMenu());

        HOME.showEffect();
        HOME.setSelected(true);
    }

    public static void main(String[] args) {
        new App("Idir");
    }
    
}
