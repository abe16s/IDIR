package GUI.src;

import java.awt.*;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.src.utilities.*;

public class AddMemberPanel extends JPanel {
    private JLabel title;
    private JPanel photoPanel;
    private JPanel inputPanel;
    
    public AddMemberPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        title = new JLabel("Add Member");
        title.setFont(new Font("Arial", Font.BOLD, 15));

        photoPanel = new JPanel();
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
        photoPanel.add(choosePhoto.getWhole());

        this.add(photoPanel);

        inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(new Insets(30, 100, 0, 20)));

        JPanel generalInput = new JPanel();

        generalInput.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));
        generalInput.setBorder(new EmptyBorder(new Insets(70, 0, 0, 0)));
        generalInput.setPreferredSize(new Dimension(300, 300));
        generalInput.setMaximumSize(generalInput.getPreferredSize());

        JLabel fullName = new JLabel("Full Name:    ");
        fullName.setBackground(Color.LIGHT_GRAY);
        fullName.setOpaque(true);
        JTextField nameField = new JTextField(20);
        
        JLabel Address = new JLabel("Address:       ");
        JTextField AddressField = new JTextField(20);

        JLabel phone = new JLabel("Phone:          ");
        JTextField phoneField = new JTextField(20);

        JLabel occupation = new JLabel("Occupation:  ");
        JTextField occupationField = new JTextField(20);  
        
        JLabel religion = new JLabel("Religion:       ");
        JTextField religionField = new JTextField(20);

        ColoredButton save = new ColoredButton("Save", inputPanel);
        save.setNormalColor(Color.cyan);

        //RoundedPanel savePanel = save.getWhole();
        //savePanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        
        generalInput.add(fullName);
        generalInput.add(nameField);
        generalInput.add(Address);
        generalInput.add(AddressField);
        generalInput.add(phone);
        generalInput.add(phoneField);
        generalInput.add(occupation);
        generalInput.add(occupationField);
        generalInput.add(religion);
        generalInput.add(religionField);
        generalInput.add(save.getWhole());

        
        JPanel familyInput = new JPanel();
        BoxLayout bcx = new BoxLayout(familyInput, BoxLayout.Y_AXIS);
        familyInput.setLayout(bcx);
        familyInput.setBorder(new EmptyBorder(new Insets(40, 0, 0, 0)));
        
        ColoredButton addFamily = new ColoredButton("Add Family", familyInput);
        addFamily.setNormalColor(Color.CYAN);
        
        RoundedPanel addFamilyPanel = addFamily.getWhole();
        addFamilyPanel.setPreferredSize(new Dimension(100, 40));
        addFamilyPanel.setMaximumSize(addFamilyPanel.getPreferredSize());

        JPanel familyQn = new JPanel();

        String[] Relations = {"Mother", "Father", "Spouse","Brother", "Sister"};
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

        familyInput.add(familyHeader, BorderLayout.NORTH);
        familyInput.add(familyQn, BorderLayout.CENTER);
        familyInput.add(addFamilyPanel, BorderLayout.SOUTH);
        
        inputPanel.add(generalInput, BorderLayout.WEST);
        inputPanel.add(familyInput, BorderLayout.EAST);

        JScrollPane ScrollInput = new JScrollPane(inputPanel);
        ScrollInput.setBorder(null);
        
        this.add(ScrollInput);
    }
}
