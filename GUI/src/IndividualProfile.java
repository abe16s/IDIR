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

        photoPanel = new JPanel();
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());

        title = new JLabel("Profile of " + MemberID);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel photo = new JLabel(ImageIcons.reSize(new ImageIcon(exampleSelected[7]),200,200));
        
        photoPanel.add(title);
        photoPanel.add(photo);

        this.add(photoPanel);

        infoPanel = new JPanel(new BorderLayout(20,0));
        infoPanel.setBorder(new EmptyBorder(new Insets(30, 100, 0, 20)));
        infoPanel.setBackground(this.getBackground());
        
        JPanel generalInfo = new JPanel();
        generalInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));
        generalInfo.setBackground(this.getBackground());
        generalInfo.setPreferredSize(new Dimension(300, 300));
        generalInfo.setMaximumSize(generalInfo.getPreferredSize());

        queryPanel ID = new queryPanel("ID", MemberID, Color.LIGHT_GRAY, generalInfo);
        queryPanel fullName = new queryPanel("Full Name", exampleSelected[1], Color.LIGHT_GRAY, generalInfo);
        queryPanel address = new queryPanel("Address", exampleSelected[2], Color.LIGHT_GRAY, generalInfo);
        queryPanel phone = new queryPanel("Phone", exampleSelected[3], Color.LIGHT_GRAY, generalInfo);
        queryPanel Age = new queryPanel("Age", exampleSelected[4], Color.LIGHT_GRAY, generalInfo);
        queryPanel occupation = new queryPanel("Occupation", exampleSelected[5], Color.LIGHT_GRAY, generalInfo);
        queryPanel religion = new queryPanel("Religion", exampleSelected[6], Color.LIGHT_GRAY, generalInfo);

        ColoredButton edit = new ColoredButton("Edit", infoPanel);
        edit.setNormalColor(Color.cyan);

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.addEditMember(new AddMemberPanel(displayPanel, MemberID), MemberID);
            }
            
        });
        
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
        generalInfo.add(edit.getWhole());

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
        JTable familiesList = new JTable(familiesExample, columnNames);
        CustomTableCellRenderer renderer = new CustomTableCellRenderer(Color.WHITE, new Color(228, 228, 228));
        familiesList.setDefaultRenderer(Object.class, renderer);
        familiesList.setShowGrid(false);
        familiesList.setDefaultEditor(Object.class, null);
        
        familyInfo.add(familyTitle);
        familyInfo.add(familyHeader);
        familyInfo.add(familiesList);

        infoPanel.add(generalInfo, BorderLayout.WEST);
        infoPanel.add(familyInfo, BorderLayout.EAST);
        
        JScrollPane ScrollInfo = new JScrollPane(infoPanel);
        ScrollInfo.setBorder(null);
        
        this.add(ScrollInfo);
    }
}
