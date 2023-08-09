import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class DemoPage extends JFrame {
    private RoundedPanel menuPanel;
    private RoundedPanel mainPanel;
    private InvisibleButton home;
    private InvisibleButton members;
    private InvisibleButton rules;
    private InvisibleButton agendas;
    private InvisibleButton officials;
    private InvisibleButton settings;
    private InvisibleButton info;

    Image familyIcon = new ImageIcon("icons8-family-64.png").getImage();
    Icon homeIcon = new ImageIcon(new ImageIcon("icons8-home-48.png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
    Icon membersIcon = new ImageIcon(new ImageIcon("icons8-contacts-50.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon rulesIcon = new ImageIcon(new ImageIcon("icons8-book-48.png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
    Icon agendasIcon = new ImageIcon(new ImageIcon("icons8-note-50.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon officialsIcon = new ImageIcon(new ImageIcon("icons8-administrator-male-50.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon settingsIcon = new ImageIcon(new ImageIcon("icons8-tools-100.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon infoIcon = new ImageIcon(new ImageIcon("icons8-info-100.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));

    Icon homeIconFilled = new ImageIcon(new ImageIcon("icons8-home-48 (1).png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
    Icon membersIconFilled = new ImageIcon(new ImageIcon("icons8-contacts-50 (1).png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon rulesIconFilled = new ImageIcon(new ImageIcon("icons8-book-64 (2).png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
    Icon agendasIconFilled = new ImageIcon(new ImageIcon("icons8-note-50 (1).png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon officialsIconFilled = new ImageIcon(new ImageIcon("icons8-administrator-male-50 (1).png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon settingsIconFilled = new ImageIcon(new ImageIcon("icons8-tools-50 (1).png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    Icon infoIconFilled = new ImageIcon(new ImageIcon("icons8-info-50.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public DemoPage() {
        Container cp = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(255,255,255,26));
        setTitle("IDIR");
        setBounds(0, 0, screenWidth, screenHeight);
        setLayout(null);
        setIconImage(familyIcon);

        //Panels
        menuPanel = new RoundedPanel();
        menuPanel.setBackground(new Color(217,217,217));
        menuPanel.setBounds(30, 20, screenWidth/7, screenHeight*85/100);
        mainPanel = new RoundedPanel();
        mainPanel.setBackground(new Color(217,217,217));
        mainPanel.setBounds(240, 20, screenWidth*78/100, screenHeight*85/100);
        cp.add(menuPanel);
        cp.add(mainPanel);
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 30));

        
        
        //Buttons
        home = new InvisibleButton(" Home", homeIcon);
        members = new InvisibleButton("  Members", membersIcon);
        rules = new InvisibleButton("<html> Rules &<br /> Regulations</html>", rulesIcon);
        agendas = new InvisibleButton("  Agendas", agendasIcon);
        officials = new InvisibleButton("  Officials", officialsIcon);
        settings = new InvisibleButton("   Settings", settingsIcon);
        info = new InvisibleButton("    Info", infoIcon);
        
        home.setBorder(new EmptyBorder(0, 12, 0, 0));   
        home.addActionListener(new IconChanger("home"));
        home.addMouseListener(new EnlargeText(home));

        members.addActionListener(new IconChanger("members"));
        members.addMouseListener(new EnlargeText(members));

        rules.setBorder(new EmptyBorder(0, 22, 0, 0));
        rules.addActionListener(new IconChanger("rules"));
        rules.addMouseListener(new EnlargeText(rules));

        agendas.addActionListener(new IconChanger("agendas"));
        agendas.addMouseListener(new EnlargeText(agendas));

        officials.addActionListener(new IconChanger("officials"));
        officials.addMouseListener(new EnlargeText(officials));

        settings.addActionListener(new IconChanger("settings"));
        settings.addMouseListener(new EnlargeText(settings));

        info.addActionListener(new IconChanger("info"));
        info.addMouseListener(new EnlargeText(info));


        menuPanel.add(home);
        menuPanel.add(members);
        menuPanel.add(rules);
        menuPanel.add(agendas);
        menuPanel.add(officials);
        menuPanel.add(settings);
        menuPanel.add(info);
    }

    private class IconChanger implements ActionListener {
        /*An action listener that listenes to buttons and changes their icon to filled one*/

        private String button;

        public IconChanger (String s) {
            button = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (button) {
                case "home":
                    home.setIcon(homeIconFilled);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
                case "members":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIconFilled);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
                case "rules":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIconFilled);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
                case "agendas":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIconFilled);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
                case "officials":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIconFilled);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
                case "settings":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIconFilled);
                    info.setIcon(infoIcon);
                    break;
                case "info":
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIconFilled);
                    break;
                default:
                    home.setIcon(homeIcon);
                    members.setIcon(membersIcon);
                    rules.setIcon(rulesIcon);
                    agendas.setIcon(agendasIcon);
                    officials.setIcon(officialsIcon);
                    settings.setIcon(settingsIcon);
                    info.setIcon(infoIcon);
                    break;
            }
        }      
    } 

    public static void main(String[] args) {
    DemoPage trial = new DemoPage();
    trial.setVisible(true);
    }

} 