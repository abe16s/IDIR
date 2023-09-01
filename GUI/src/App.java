package GUI.src;

import java.awt.Dimension;
import javax.swing.UIManager;
import GUI.src.utilities.*;

public class App {
    private SkeletalWindow window;
    public static IndividualProfile INDIVIDUAL_PROFILE;
    App(String title){
        //This block of code is to make the whole GUI look and feel like the operating system. Can be seen in the ScrollPane and the file choosers
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        window = new SkeletalWindow(title);
        window.setIconImage(ImageIcons.LOGO.getImage());



        HomePage homePage = new HomePage(window);

        TransparentButton HOME = new TransparentButton("Home", ImageIcons.reSize(ImageIcons.HOMEdark,25,25), window.getMenu());
        HOME.setSelectedIcon(ImageIcons.reSize(ImageIcons.HOME,30,30));
        window.getMenu().addTab(HOME, homePage);

        TransparentButton MEMBERS = new TransparentButton("Members", ImageIcons.reSize(ImageIcons.CONTACTSdark,25,25), window.getMenu());
        MEMBERS.setSelectedIcon(ImageIcons.reSize(ImageIcons.CONTACTS,30,30));
        window.getMenu().addTab(MEMBERS, new MembersPanel(window.getBasePanel()));

        INDIVIDUAL_PROFILE = new IndividualProfile(window.getBasePanel());
        window.getBasePanel().addMyTab(INDIVIDUAL_PROFILE,"individualProfile");

        TransparentButton OFFICIAL = new TransparentButton("Officials", ImageIcons.reSize(ImageIcons.OFFICIALdark,25,25), window.getMenu());
        OFFICIAL.setSelectedIcon(ImageIcons.reSize(ImageIcons.OFFICIAL,30,30));
        window.getMenu().addTab (OFFICIAL, new OfficialsPanel(window.getBasePanel()));

        TransparentButton FINANCIALdata = new TransparentButton("Financial data", ImageIcons.reSize(ImageIcons.COINSdark,20,20), window.getMenu());
        FINANCIALdata.setSelectedIcon(ImageIcons.reSize(ImageIcons.COINS,25,25));
        window.getMenu().addTab(FINANCIALdata, new FinancePanel(window.getBasePanel()));

        TransparentButton AGENDA = new TransparentButton("Agendas", ImageIcons.reSize(ImageIcons.NOTEdark,25,25), window.getMenu());
        AGENDA.setSelectedIcon(ImageIcons.reSize(ImageIcons.NOTE,30,30));
        window.getMenu().addTab(AGENDA, new AgendaList(window.getBasePanel()));

        TransparentButton RULE = new TransparentButton("Rules", ImageIcons.reSize(ImageIcons.RULESdark,25,25), window.getMenu());
        RULE.setSelectedIcon(ImageIcons.reSize(ImageIcons.RULES,30,30));
        window.getMenu().addTab(RULE, new RulesPanel(window.getBasePanel()));

        TransparentButton HELP = new TransparentButton("Help", ImageIcons.reSize(ImageIcons.HELPdark,25,25), window.getMenu());
        HELP.setSelectedIcon(ImageIcons.reSize(ImageIcons.HELP,30,30));
        window.getMenu().addTab(HELP, new Help(window.getBasePanel()));

        window.getMenu().setPreferredSize(new Dimension(140, window.getMenu().getPreferredSize().height)); //set the size of the MenuBar so as to not increase when financial data is hovered over
        homePage.giveAffectedButtons(MEMBERS);
        homePage.giveAffectedButtons(FINANCIALdata);
        HOME.showEffect();
        HOME.setSelected(true);
    }

    public static void main(String[] args) {
        new App("Idir");
    }
    
}
