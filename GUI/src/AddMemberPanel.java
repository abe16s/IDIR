package GUI.src;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class AddMemberPanel extends JPanel implements ParentPanel {
    private BasePanel displayPanel;
    private JLabel title;
    private JLabel unknownPhoto;
    private JPanel photoPanel;
    private JPanel inputPanel;
    private ArrayList<queryPanel> fields = new ArrayList<queryPanel>();
    private ColoredButton save;
    private ActionListener addMemberListener;
    private JPanel familyQn;
    private IndividualProfile savedProfile;
    
    public AddMemberPanel(BasePanel displayPanel) {
        /*This constructor is used when adding new members */
        this.setBackground(new Color(228, 228, 228));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.displayPanel = displayPanel;

        JPanel Contents = new JPanel();
        BoxLayout contentBox = new BoxLayout(Contents, BoxLayout.Y_AXIS);
        Contents.setLayout(contentBox);
        Contents.setBackground(getBackground());

        photoPanel = new JPanel(); //An upper panel with 3 components title, unkownPhoto & choosefile button
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());
        
        title = new JLabel("Add Member");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        
        unknownPhoto = new JLabel(ImageIcons.reSize(ImageIcons.UNKNOWN,200,200));

        ColoredFileChooser choosePhoto = new ColoredFileChooser("Choose Photo", this, "Choose Member Photo", "jpg", "img");
        choosePhoto.setNormalColor(new Color(147, 175, 207));
        RoundedPanel choosePanel = choosePhoto.getWhole();
        choosePanel.setPreferredSize(new Dimension(choosePanel.getPreferredSize().width, 30));
        
        photoPanel.add(title);
        photoPanel.add(unknownPhoto);
        photoPanel.add(choosePanel);

        inputPanel = new JPanel(new BorderLayout()); //The lower panel for receiving information / input panel divided into two general & family input
        inputPanel.setBorder(new EmptyBorder(new Insets(0, 200, 0, 50)));
        inputPanel.setBackground(this.getBackground());

        JPanel generalInput = new JPanel(); // The left part of the input panel for receiving general information
        BoxLayout inputBox = new BoxLayout(generalInput, BoxLayout.Y_AXIS);
        generalInput.setLayout(inputBox);
        generalInput.setBackground(this.getBackground());
        generalInput.setPreferredSize(new Dimension(250, 300));
        generalInput.setMaximumSize(generalInput.getPreferredSize());

        String nextAvailableIDExample = "122"; 
        queryPanel ID = new queryPanel("ID", nextAvailableIDExample, Color.LIGHT_GRAY);
        queryPanel fullName = new queryPanel("Full Name", 20, Color.LIGHT_GRAY);
        queryPanel address = new queryPanel("Address", 15, Color.LIGHT_GRAY);
        queryPanel phone = new queryPanel("Phone", 10, Color.LIGHT_GRAY);
        queryPanel Age = new queryPanel("Age", 2, Color.LIGHT_GRAY);
        queryPanel occupation = new queryPanel("Occupation", 10, Color.LIGHT_GRAY);
        queryPanel religion = new queryPanel("Religion", 10, Color.LIGHT_GRAY);
        
        generalInput.add(ID);        
        fields.add(ID);
        generalInput.add(fullName);        
        fields.add(fullName);
        generalInput.add(address);
        fields.add(address);
        generalInput.add(phone);
        fields.add(phone);
        generalInput.add(Age);
        fields.add(Age);
        generalInput.add(occupation);
        fields.add(occupation);
        generalInput.add(religion);
        fields.add(religion);
        
        Component[] components = generalInput.getComponents();
        for (Component c : components) {
            ((queryPanel)c).setAlignmentX(LEFT_ALIGNMENT);
        }
        
        JPanel familyInput = new JPanel(); // The right part of the input panel for receiving family information
        BoxLayout familyBox = new BoxLayout(familyInput, BoxLayout.Y_AXIS);
        familyInput.setLayout(familyBox);
        familyInput.setBackground(this.getBackground());
        familyInput.setBorder(new EmptyBorder(new Insets(40, 0, 0, 0)));
        
        JLabel familyTitle = new JLabel("Family Members");
        familyTitle.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel familyHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10)); 
        familyHeader.add(new JLabel("Full Name"));
        familyHeader.add(new JLabel("Phone No"));
        familyHeader.add(new JLabel("Relation"));
        familyHeader.setBackground(Color.LIGHT_GRAY);
        familyHeader.setMaximumSize(new Dimension(400, 30));

        familyQn = new JPanel(); //This panel is used for the organization of the query of the family info
        familyQn.setBackground(this.getBackground());
        familyQn.setPreferredSize(new Dimension(500,100));
        familyQn.setMaximumSize(familyQn.getPreferredSize());

        familyQuery();

        ColoredButton addFamily = new ColoredButton("Add Family", familyInput);
        addFamily.setNormalColor(new Color(147, 175, 207));
        RoundedPanel addFamilyPanel = addFamily.getWhole();
        addFamilyPanel.setPreferredSize(new Dimension(addFamilyPanel.getPreferredSize().width, 30));
        addFamily.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                familyQuery();
                ((ColoredButton) e.getSource()).removeEffect();
                ((ColoredButton) e.getSource()).unselect();
            }
        });
        
        familyInput.add(familyTitle);
        familyTitle.setAlignmentX(CENTER_ALIGNMENT);
        familyInput.add(familyHeader);
        familyInput.add(familyQn);
        familyInput.add(addFamilyPanel);
        
        inputPanel.add(generalInput, BorderLayout.WEST);
        inputPanel.add(familyInput, BorderLayout.EAST);
        
        JPanel footer = new JPanel();
        footer.setBackground(getBackground());

        save = new ColoredButton("Save", inputPanel);
        save.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 20, 20));
        save.setNormalColor(new Color(147, 175, 207));

        addMemberListener = new saveLitsener("add", displayPanel, nextAvailableIDExample);
        save.addActionListener(addMemberListener);

        ColoredButton discard = new ColoredButton("Discard", inputPanel);
        discard.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        discard.setNormalColor(new Color(147, 175, 207));

        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    displayPanel.showMyTab("Members");
                }
            } 
        });
        
        footer.add(save.getWhole());
        footer.add(discard.getWhole());

        Contents.add(photoPanel);
        Contents.add(inputPanel);
        Contents.add(footer);

        JScrollPane ScrollContents = new JScrollPane(Contents);
        ScrollContents.setBorder(null);

        this.add(ScrollContents);
    }


    public AddMemberPanel(BasePanel displayPanel, String MemberID) {
        /*This constructor is used when editing existing members*/

        this(displayPanel);
        this.title.setText("Edit Member");

        String[] exampleToBeEdited = {"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox", "GUI\\Icons\\dark\\Example-Photo.jpg"};
        String[][] familiesExample = {
            {"Kassaye W/Medhin Kidane", "0911111111", "Mother"},
            {"Seifu Dula Kara", "0911111111", "Father"}, 
            {"Ermiyas Seifu Dula", "0911111111", "Brother"}
        };

        //including the existing data in the photo and textfields
        fields.get(0).getInfoLabel().setText(MemberID);
        for (int i = 1; i < fields.size(); i++) {
            fields.get(i).getTextField().setText(exampleToBeEdited[i]); 
        }
        unknownPhoto.setIcon(ImageIcons.reSize(new ImageIcon(exampleToBeEdited[7]),200,200));
        save.removeActionListener(addMemberListener);
        save.addActionListener(new saveLitsener("edit", displayPanel, MemberID));

        for (int i = 1; i < familiesExample.length; i++) {
            familyQuery();
        }
        Component[] family = familyQn.getComponents();
        int j = 0;
        for (int i = 0; i < familiesExample.length; i++) {
            try {
                ((JTextField) family[j++]).setText(familiesExample[i][0]);
                ((JTextField) family[j++]).setText(familiesExample[i][1]);
                ((JComboBox<String>) family[j++]).setSelectedItem(familiesExample[i][2]);
            } catch (Exception e) {}
        }
        
    }

    private class saveLitsener implements ActionListener {
        private String todo;
        private BasePanel displayPanel;
        private String MemberID;
        public saveLitsener(String todo, BasePanel displayPanel, String MemberID) {
            this.todo = todo;
            this.displayPanel = displayPanel;
            this.MemberID = MemberID;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (todo.equalsIgnoreCase("edit")) {
                JOptionPane.showMessageDialog(this.displayPanel,"Edited Successfully!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
            } else if (todo.equalsIgnoreCase("add")) {
                JOptionPane.showMessageDialog(this.displayPanel,"Added New Member Successfully!", "Add Member", JOptionPane.INFORMATION_MESSAGE);

            }
            if (savedProfile != null){displayPanel.remove(savedProfile);

            }else{savedProfile = new IndividualProfile(displayPanel);}

            savedProfile.updateData(MemberID);
            addTab(save, savedProfile);
            showMyTab(save.getName());
        }
    }

    private void familyQuery() {
        /*A function for organized adding the two text fields and JCombobox in the familyQn panel above for the family input*/

        String[] Relations = {"Mother", "Father", "Spouse","Son","Daughter","Brother", "Sister", "Mother-in-law", "Father-in-Law","Brother-in-law","Sister-in-law"};
        JTextField name = new JTextField(20);
        JTextField phone = new JTextField(12);
        JComboBox<String> relation = new JComboBox<String>(Relations);

        name.setBorder(null); 
        name.setPreferredSize(new Dimension(name.getPreferredSize().width, name.getPreferredSize().height+5));
        phone.setBorder(null);
        phone.setPreferredSize(new Dimension(phone.getPreferredSize().width, phone.getPreferredSize().height+5));
        relation.setBorder(null);

        familyQn.add(name);
        familyQn.add(phone);
        familyQn.add(relation);
        int size = familyQn.getComponentCount();
        if (size/3*30 > 100) {
            familyQn.setPreferredSize(new Dimension(500,size/3*30));
            familyQn.setMaximumSize(familyQn.getPreferredSize());
        }
    }


    @Override
    public void showMyTab(String buttonName) {
        displayPanel.showMyTab(buttonName);
    }


    @Override
    public void showMyTab(String[] values, int source) {

    }


    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel, button.getName());
    }


    @Override
    public void workWithFileChosen(File selectedFile) {
        unknownPhoto.setIcon(ImageIcons.reSize(new ImageIcon(selectedFile.getAbsolutePath()),200,200));
    }
}
