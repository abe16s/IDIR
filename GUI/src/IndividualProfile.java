package GUI.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.src.utilities.*;

public class IndividualProfile extends JPanel {
    private JLabel title;
    private JPanel photoPanel;
    private JPanel infoPanel;
    private ArrayList<queryPanel> fields = new ArrayList<queryPanel>();

    public IndividualProfile(String MemberID, BasePanel displayPanel) {

        String[] exampleSelected = {"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox", "GUI\\Icons\\dark\\Example-Photo.jpg"};
        String[][] familiesExample = {
            {"Kassaye W/Medhin Kidane", "0911111111", "Mother"},
            {"Seifu Dula Kara", "0911111111", "Father"}, 
            {"Ermiyas Seifu Dula", "0911111111", "Brother"}
        };
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(228, 228, 228));

        JPanel Contents = new JPanel();
        BoxLayout contentBox = new BoxLayout(Contents, BoxLayout.Y_AXIS);
        Contents.setLayout(contentBox);
        Contents.setBackground(getBackground());

        photoPanel = new JPanel();
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());

        title = new JLabel("Profile of " + MemberID);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel photo = new JLabel(ImageIcons.reSize(new ImageIcon(exampleSelected[7]),200,200));
        
        photoPanel.add(title);
        photoPanel.add(photo);

        infoPanel = new JPanel(new BorderLayout(20,0));
        infoPanel.setBorder(new EmptyBorder(new Insets(0, 200, 0, 70)));
        infoPanel.setBackground(this.getBackground());
        
        JPanel generalInfo = new JPanel();
        BoxLayout infoBox = new BoxLayout(generalInfo, BoxLayout.Y_AXIS);
        generalInfo.setLayout(infoBox);
        generalInfo.setBackground(this.getBackground());
        generalInfo.setPreferredSize(new Dimension(300, 300));
        generalInfo.setMaximumSize(generalInfo.getPreferredSize());

        queryPanel ID = new queryPanel("ID", MemberID, Color.LIGHT_GRAY);
        queryPanel fullName = new queryPanel("Full Name", exampleSelected[1], Color.LIGHT_GRAY);
        queryPanel address = new queryPanel("Address", exampleSelected[2], Color.LIGHT_GRAY);
        queryPanel phone = new queryPanel("Phone", exampleSelected[3], Color.LIGHT_GRAY);
        queryPanel Age = new queryPanel("Age", exampleSelected[4], Color.LIGHT_GRAY);
        queryPanel occupation = new queryPanel("Occupation", exampleSelected[5], Color.LIGHT_GRAY);
        queryPanel religion = new queryPanel("Religion", exampleSelected[6], Color.LIGHT_GRAY);
        
        generalInfo.add(ID);        
        fields.add(ID);
        generalInfo.add(fullName);        
        fields.add(fullName);
        generalInfo.add(address);
        fields.add(address);
        generalInfo.add(phone);
        fields.add(phone);
        generalInfo.add(Age);
        fields.add(Age);
        generalInfo.add(occupation);
        fields.add(occupation);
        generalInfo.add(religion);
        fields.add(religion);

        Component[] components = generalInfo.getComponents();
        for (Component c : components) {
            ((queryPanel)c).setAlignmentX(LEFT_ALIGNMENT);
        }

        JPanel familyInfo = new JPanel();
        BoxLayout bcx = new BoxLayout(familyInfo, BoxLayout.Y_AXIS);
        familyInfo.setLayout(bcx);
        familyInfo.setBackground(this.getBackground());
        familyInfo.setBorder(new EmptyBorder(new Insets(40, 0, 0, 0)));

        JLabel familyTitle = new JLabel("Family Members");
        familyTitle.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel familyHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10)); 
        familyHeader.add(new JLabel("Full Name"));
        familyHeader.add(new JLabel("Phone No"));
        familyHeader.add(new JLabel("Relation"));
        familyHeader.setBackground(Color.LIGHT_GRAY);
        familyHeader.setMaximumSize(new Dimension(500, 30));

        String[] columnNames = {"Full Name", "Phone", "Relation"};
        JTable familiesList = new CustomTable(familiesExample, columnNames, Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        
        familyInfo.add(familyTitle);
        familyTitle.setAlignmentX(CENTER_ALIGNMENT);
        familyInfo.add(familyHeader);
        familyInfo.add(familiesList);

        infoPanel.add(generalInfo, BorderLayout.WEST);
        infoPanel.add(familyInfo, BorderLayout.EAST);
        
        JPanel footer = new JPanel();
        footer.setBackground(getBackground());

        ColoredButton edit = new ColoredButton("Edit", infoPanel);
        edit.setNormalColor(new Color(147, 175, 207));

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.addEditMember(new AddMemberPanel(displayPanel, MemberID));
            }
        });
        
        footer.add(edit.getWhole());

        Contents.add(photoPanel);
        Contents.add(infoPanel);
        Contents.add(footer);

        JScrollPane ScrollContent = new JScrollPane(Contents);
        ScrollContent.setBorder(null);

        this.add(ScrollContent);
    }
}
