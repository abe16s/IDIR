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
    private JPanel photoPanel;
    private JPanel inputPanel;
    private ArrayList<queryPanel> fields = new ArrayList<queryPanel>();

    
    public AddMemberPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(228, 228, 228));
        title = new JLabel("Add Member");
        title.setFont(new Font("Arial", Font.BOLD, 15));

        photoPanel = new JPanel();
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(300, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());

        
        JLabel unknownPhoto = new JLabel(ImageIcons.reSize(ImageIcons.UNKNOWN,200,200));

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

        ColoredButton save = new ColoredButton("Save", inputPanel);
        save.setNormalColor(Color.cyan);

        //RoundedPanel savePanel = save.getWhole();
        //savePanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        
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

        JPanel familyQn = new JPanel();
        familyQn.setBackground(this.getBackground());

        String[] Relations = {"Mother", "Father", "Spouse","Brother", "Sister", "Mother-in-law", "Father-in-Law","Brother-in-law","Sister-in-law"};
        familyQn.add(new JTextField(20));
        familyQn.add(new JTextField(12));
        familyQn.add(new JComboBox<String>(Relations));
        familyQn.setPreferredSize(new Dimension(500,100));
        familyQn.setMaximumSize(familyQn.getPreferredSize());
        addFamily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFamily.removeEffect();
                familyQn.add(new JTextField(20));
                familyQn.add(new JTextField(12));
                familyQn.add(new JComboBox<String>(Relations));
                int size = familyQn.getComponentCount();
                if (size/3*30 > 100) {
                    familyQn.setPreferredSize(new Dimension(500,size/3*30));
                    familyQn.setMaximumSize(familyQn.getPreferredSize());
                }
                
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
}
