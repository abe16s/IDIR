package GUI.src;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.src.utilities.*;

public class AddMemberPanel extends JPanel {
    private JLabel title;
    private JLabel unknownPhoto;
    private JPanel photoPanel;
    private JPanel inputPanel;
    private ArrayList<queryPanel> fields = new ArrayList<queryPanel>();
    private ColoredButton save;
    private ActionListener addListener;
    private JPanel familyQn;
    
    public AddMemberPanel(BasePanel displayPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(228, 228, 228));
        title = new JLabel("Add Member");
        title.setFont(new Font("Arial", Font.BOLD, 15));

        photoPanel = new JPanel();
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());

        
        unknownPhoto = new JLabel(ImageIcons.reSize(ImageIcons.UNKNOWN,200,200));

        ColoredButton choosePhoto = new ColoredButton("Choose Photo", photoPanel);
        choosePhoto.setNormalColor(Color.CYAN);
        RoundedPanel choosePanel = choosePhoto.getWhole();
        choosePanel.setPreferredSize(new Dimension(100, 40));
        choosePanel.setMaximumSize(choosePanel.getPreferredSize()); 

        choosePhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & IMG Images", "jpg", "img");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Member Photo");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    unknownPhoto.setIcon(ImageIcons.reSize(new ImageIcon(selectedFile.getAbsolutePath()),200,200));
                }
            }
        });

        photoPanel.add(title);
        photoPanel.add(unknownPhoto);
        photoPanel.add(choosePanel);

        this.add(photoPanel);

        inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(new Insets(30, 100, 0, 20)));
        inputPanel.setBackground(this.getBackground());

        JPanel generalInput = new JPanel();
        generalInput.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));
        generalInput.setBackground(this.getBackground());
        generalInput.setPreferredSize(new Dimension(250, 300));
        generalInput.setMaximumSize(generalInput.getPreferredSize());

        String nextAvailableIDExample = "122";
        queryPanel ID = new queryPanel("ID", nextAvailableIDExample, Color.LIGHT_GRAY, generalInput);
        queryPanel fullName = new queryPanel("Full Name", 20, Color.LIGHT_GRAY, generalInput);
        queryPanel address = new queryPanel("Address", 20, Color.LIGHT_GRAY, generalInput);
        queryPanel phone = new queryPanel("Phone", 20, Color.LIGHT_GRAY, generalInput);
        queryPanel Age = new queryPanel("Age", 20, Color.LIGHT_GRAY, generalInput);
        queryPanel occupation = new queryPanel("Occupation", 20, Color.LIGHT_GRAY, generalInput);
        queryPanel religion = new queryPanel("Religion", 20, Color.LIGHT_GRAY, generalInput);

        save = new ColoredButton("Save", inputPanel);
        save.setNormalColor(Color.CYAN);

        addListener = new saveLitsener("add", displayPanel, nextAvailableIDExample);
        save.addActionListener(addListener);

        ColoredButton discard = new ColoredButton("Discard", inputPanel);
        discard.setNormalColor(Color.CYAN);

        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.showMyTab("Members");
            }
            
        });
        
        
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
        generalInput.add(save.getWhole());
        generalInput.add(discard.getWhole());


        
        JPanel familyInput = new JPanel();
        BoxLayout bcx = new BoxLayout(familyInput, BoxLayout.Y_AXIS);
        familyInput.setLayout(bcx);
        familyInput.setBackground(this.getBackground());

        familyInput.setBorder(new EmptyBorder(new Insets(40, 0, 0, 0)));
        
        ColoredButton addFamily = new ColoredButton("Add Family", familyInput);
        addFamily.setNormalColor(Color.CYAN);
        
        RoundedPanel addFamilyPanel = addFamily.getWhole();
        addFamilyPanel.setPreferredSize(new Dimension(100, 40));
        addFamilyPanel.setMaximumSize(addFamilyPanel.getPreferredSize());

        familyQn = new JPanel();
        familyQn.setBackground(this.getBackground());
        familyQn.setPreferredSize(new Dimension(500,100));
        familyQn.setMaximumSize(familyQn.getPreferredSize());

        familyQuery();
        addFamily.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                familyQuery();
                ((ColoredButton) e.getSource()).removeEffect();
                ((ColoredButton) e.getSource()).unselect();
            }
        });

        JPanel familyHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10)); 
        familyHeader.add(new JLabel("Full Name"));
        familyHeader.add(new JLabel("Phone No"));
        familyHeader.add(new JLabel("Relation"));
        familyHeader.setBackground(Color.LIGHT_GRAY);
        familyHeader.setMaximumSize(new Dimension(400, 30));

        JLabel familyTitle = new JLabel("Family Members");
        familyTitle.setFont(new Font("Arial", Font.BOLD, 15));
        
        familyInput.add(familyTitle);
        familyInput.add(familyHeader);
        familyInput.add(familyQn);
        familyInput.add(addFamilyPanel);
        
        inputPanel.add(generalInput, BorderLayout.WEST);
        inputPanel.add(familyInput, BorderLayout.EAST);

        JScrollPane ScrollInput = new JScrollPane(inputPanel);
        ScrollInput.setBorder(null);
        
        this.add(ScrollInput);
    }


    public AddMemberPanel(BasePanel displayPanel, String MemberID) {
        this(displayPanel);
        String[] exampleToBeEdited = {"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox", "GUI\\Icons\\dark\\Example-Photo.jpg"};
        String[][] familiesExample = {
            {"Kassaye W/Medhin Kidane", "0911111111", "Mother"},
            {"Seifu Dula Kara", "0911111111", "Father"}, 
            {"Ermiyas Seifu Dula", "0911111111", "Brother"}
        };

        fields.get(0).getInfoLabel().setText(MemberID);
        for (int i = 1; i < fields.size(); i++) {
            fields.get(i).getTextField().setText(exampleToBeEdited[i]); 
        }
        unknownPhoto.setIcon(ImageIcons.reSize(new ImageIcon(exampleToBeEdited[7]),200,200));
        save.removeActionListener(addListener);
        save.addActionListener(new saveLitsener("edit", displayPanel, MemberID));

        for (int i = 1; i < familiesExample.length; i++) {
            familyQuery();
        }
        Component[] family = familyQn.getComponents();
        int j = 0;
        for (int i = 0; i < familiesExample.length; i++) {
            ((JTextField) family[j++]).setText(familiesExample[i][0]);
            ((JTextField) family[j++]).setText(familiesExample[i][1]);
            ((JComboBox<String>) family[j++]).setSelectedItem(familiesExample[i][2]);
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
                System.out.println("Edited");
            } else if (todo.equalsIgnoreCase("add")) {
                System.out.println("Added");
            }
            this.displayPanel.createIndividualProfile(new IndividualProfile(MemberID, this.displayPanel), MemberID);
        }
    }

    private void familyQuery() {
        String[] Relations = {"Mother", "Father", "Spouse","Brother", "Sister", "Mother-in-law", "Father-in-Law","Brother-in-law","Sister-in-law"};
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

}
