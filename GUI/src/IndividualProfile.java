package GUI.src;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class IndividualProfile extends JPanel implements ParentPanel{
    private BasePanel displayPanel;

    private JLabel photo;
    private JPanel choosePhoto;
    
    private JLabel fullName;
    
    private ArrayList<queryPanel> personalInfoInputFields;

    private JPanel familyList;
    private RoundedPanel addFamily;

    private ArrayList<FamilyInfoInputPanel> familyInfoInputPanels;

    private JPanel save;
    private JPanel discard;
    private JPanel edit;

    private boolean editing;

    String[] exampleSelected = {"1","Male", "19", "Orthodox", "Shenkor, 10, 551", "0936120470", "Student", "Abenezer Seifu Dula"};

    ArrayList<String[]> familiesExample = new ArrayList<String[]>(
        Arrays.asList( 
            new String[]{"Kassaye W/Medhin Kidane", "0911111111", "Mother"},
            new String[]{"Seifu Dula Kara", "0911111111", "Father"}, 
            new String[]{"Ermiyas Seifu Dula", "0911111111", "Brother"} 
        )
    );

    private int familiesSize = 0;

    public IndividualProfile(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        personalInfoInputFields = new ArrayList<queryPanel>();
        familyInfoInputPanels = new ArrayList<FamilyInfoInputPanel>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(228, 228, 228));       

        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel,BoxLayout.Y_AXIS));
        photoPanel.setOpaque(false);;
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());
        photoPanel.setAlignmentX(CENTER_ALIGNMENT);

        photo = new JLabel();
        photo.setAlignmentX(CENTER_ALIGNMENT);

        fullName = new JLabel();
        fullName.setFont(new Font("Arial", Font.BOLD, 15));
        fullName.setOpaque(false);
        fullName.setVisible(false);
        fullName.setAlignmentX(CENTER_ALIGNMENT);

        ColoredFileChooser choosePhotoButton = new ColoredFileChooser("choosePhoto", this, "Choose Member Photo", "jpg", "img");
        choosePhotoButton.setNormalColor(new Color(147, 175, 207));
        choosePhotoButton.setSelectedColor(new Color(79,170,255));
        choosePhoto = choosePhotoButton.getWhole();
        choosePhoto.setAlignmentX(CENTER_ALIGNMENT);
        choosePhoto.setVisible(false);

        photoPanel.add(photo);
        photoPanel.add(fullName);
        photoPanel.add(choosePhoto);

        queryPanel ID = new queryPanel("ID", 4, getBackground());
        ID.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel name = new queryPanel("Full Name", 20, getBackground());
        name.setVisible(false);
        name.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel gender = new queryPanel("Gender", 6, getBackground());
        gender.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel age = new queryPanel("Age", 3, getBackground());
        age.setAlignmentX(LEFT_ALIGNMENT); 

        queryPanel religion = new queryPanel("Religion", 12, getBackground());
        religion.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel address = new queryPanel("Home address", 20, getBackground());
        address.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel phone = new queryPanel("Phone", 10, getBackground());
        phone.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel occupation = new queryPanel("Occupation", 20, getBackground());
        occupation.setAlignmentX(LEFT_ALIGNMENT);


        JPanel personalInfo = new JPanel();
        personalInfo.setLayout(new BoxLayout(personalInfo, BoxLayout.Y_AXIS));
        personalInfo.setBackground(this.getBackground());
        personalInfo.setPreferredSize(new Dimension(300, 300));
        personalInfo.setMaximumSize(personalInfo.getPreferredSize());

        
        personalInfo.add(ID);        
        personalInfoInputFields.add(ID);
        personalInfo.add(name);        
        personalInfo.add(gender);        
        personalInfoInputFields.add(gender);
        personalInfo.add(age);
        personalInfoInputFields.add(age);
        personalInfo.add(religion);
        personalInfoInputFields.add(religion);
        personalInfo.add(address);
        personalInfoInputFields.add(address);
        personalInfo.add(phone);
        personalInfoInputFields.add(phone);
        personalInfo.add(occupation);
        personalInfoInputFields.add(occupation);
        personalInfoInputFields.add(name);
        
        
        JLabel title = new JLabel("Family Members");
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JPanel familyHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10)); 
        familyHeader.add(new JLabel("Full Name"));
        familyHeader.add(new JLabel("Phone No"));
        familyHeader.add(new JLabel("Relation"));
        familyHeader.setOpaque(false);
        familyHeader.setAlignmentX(CENTER_ALIGNMENT);
        familyHeader.setMaximumSize(new Dimension(400, 30));

        familyList = new JPanel();
        familyList.setLayout(new BoxLayout(familyList, BoxLayout.Y_AXIS));
        familyList.setOpaque(false);
        familyList.setAlignmentX(CENTER_ALIGNMENT);
        
        ColoredButton addFamilyButton = new ColoredButton("Add Family", this);
        addFamilyButton.setIcon(ImageIcons.reSize(ImageIcons.FAMILY, 20,20));
        addFamilyButton.setNormalColor(new Color(147, 175, 207));
        addFamilyButton.setSelectedColor(new Color(79,170,255));
        addFamily = addFamilyButton.getWhole();
        addFamily.setVisible(false);

        JPanel familyInfo = new JPanel();
        familyInfo.setLayout(new BoxLayout(familyInfo, BoxLayout.Y_AXIS));
        familyInfo.setOpaque(false);
        familyInfo.setBorder(null);

        familyInfo.add(title);
        familyInfo.add(familyHeader);
        familyInfo.add(familyList);
        familyInfo.add(Box.createVerticalStrut(5));
        familyInfo.add(addFamily);

        JPanel infoPanel;
        infoPanel = new JPanel(new BorderLayout(20,0));
        infoPanel.setBorder(new EmptyBorder(new Insets(0, 200, 0, 70)));
        infoPanel.setBackground(getBackground());

        infoPanel.add(familyInfo,BorderLayout.EAST);
        infoPanel.add(personalInfo,BorderLayout.WEST);

        ColoredButton saveButton = new ColoredButton("Save", this);
        saveButton.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 20, 20));
        saveButton.setNormalColor(new Color(147, 175, 207));
        saveButton.setSelectedColor(new Color(79,170,255));
        save = saveButton.getWhole();
        save.setVisible(false);

        ColoredButton discardButton = new ColoredButton("Discard", this);
        discardButton.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        discardButton.setNormalColor(new Color(147, 175, 207));
        discardButton.setSelectedColor(new Color(79,170,255));
        discard = discardButton.getWhole();
        discard.setVisible(false);

        ColoredButton editButton = new ColoredButton("Edit", this);
        editButton.setIcon(ImageIcons.reSize(ImageIcons.EDIT, 20,20));
        editButton.setNormalColor(new Color(147, 175, 207));
        editButton.setSelectedColor(new Color(79,170,255));
        edit = editButton.getWhole();
        edit.setVisible(false);

        JPanel footer = new JPanel();
        footer.setBackground(getBackground());

        footer.add(save);
        footer.add(discard);
        footer.add(edit);

        JPanel contents = new JPanel();
        contents.setLayout( new BoxLayout(contents, BoxLayout.Y_AXIS));
        contents.setBackground(getBackground());

        contents.add(photoPanel);
        contents.add(infoPanel);
        contents.add(footer);
        contents.setBorder(new EmptyBorder(new Insets(40, 0, 40, 0)));

        JScrollPane ScrollContents = new JScrollPane(contents);
        ScrollContents.setBorder(null);

        add(ScrollContents);

    }

    public void addFamilyInputPanel(String name, String phone, String relation){
        FamilyInfoInputPanel newLine =  new FamilyInfoInputPanel(name,phone,relation);
        familyList.add(newLine);
        familyList.add(Box.createVerticalStrut(5));
        familyInfoInputPanels.add(newLine);
    }
    
    public void addFamilyInputPanel(String name, String phone){
        FamilyInfoInputPanel newLine =  new FamilyInfoInputPanel(name,phone);
        familyList.add(newLine);
        familyList.add(Box.createVerticalStrut(10));
        familyInfoInputPanels.add(newLine);
    }

    public void prepareToAddMember(){
        editing = false;
        photo.setIcon(ImageIcons.reSize(ImageIcons.UNKNOWN,200,200));
        fullName.setVisible(false);
        choosePhoto.setVisible(true);
        
        for (int i = 0; i < 8; i++ ){
            int[] size = {3,20,2,6,15,20,10,20};
            JTextField t = personalInfoInputFields.get(i).getTextField();
            t.setText("");
            t.setBackground(Color.white);
            t.setColumns(size[i]);
            t.setMaximumSize(new Dimension(t.getPreferredSize().width, 20));
            t.setEditable(true);
        }
        JTextField t = personalInfoInputFields.get(0).getTextField();
        t.setEditable(false);
        t.setText("2");
        t.setBackground(new Color(247, 247, 247));
        personalInfoInputFields.get(7).setVisible(true);

        int i = 0;
        if(i < familyInfoInputPanels.size()){
            familyInfoInputPanels.get(i).updateData(" "," ");
            i++;
        }else {
            addFamilyInputPanel(" ","");
        }


        while (i < familyInfoInputPanels.size()){
            familyInfoInputPanels.get(i).setVisible(false);
            i++;
        }

        addFamily.setVisible(true);
        discard.setVisible(true);
        save.setVisible(true);

        edit.setVisible(false);
    }

    public void prepareToEditMember(){
        editing = true;
        for (int i = 1; i < 7; i++ ){
            personalInfoInputFields.get(i).getTextField().setEditable(true);
        }
        personalInfoInputFields.get(0).getTextField().setEditable(false);
        personalInfoInputFields.get(0).getTextField().setBackground(new Color(247, 247, 247));

        for(FamilyInfoInputPanel I : familyInfoInputPanels){
            I.setEditable(true);
        }
        addFamily.setVisible(true);
        discard.setVisible(true);
        save.setVisible(true);

        edit.setVisible(false);
    }


    public void prepareToShowProfile(String memberID){
        photo.setIcon(ImageIcons.reSize(new ImageIcon("GUI\\Icons\\dark\\Example-Photo.jpg"),200,200));
        fullName.setText(exampleSelected[7]);
        fullName.setVisible(true);
        choosePhoto.setVisible(false);
        
        for (int i = 0; i < 7; i++ ){
            JTextField t = personalInfoInputFields.get(i).getTextField();
            t.setText(exampleSelected[i]);
            t.setEditable(false);
            t.setBackground(Color.white);
            t.setColumns(exampleSelected[i].length());
            t.setMaximumSize(new Dimension(t.getPreferredSize().width, 20));
            
        }
        personalInfoInputFields.get(7).setVisible(false);


        familiesSize = 0;
        while(familiesSize < familyInfoInputPanels.size() & familiesSize < familiesExample.size()){
            familyInfoInputPanels.get(familiesSize).updateData(familiesExample.get(familiesSize)[0],familiesExample.get(familiesSize)[1],familiesExample.get(familiesSize)[2]);
            familyInfoInputPanels.get(familiesSize).setVisible(true);
            familiesSize++;
        }

        while (familiesSize < familiesExample.size()){
            addFamilyInputPanel(familiesExample.get(familiesSize)[0], familiesExample.get(familiesSize)[1], familiesExample.get(familiesSize)[2]);
            familiesSize++;
        }

        int i = familiesSize;
        while (i < familyInfoInputPanels.size()){
            familyInfoInputPanels.get(i).setVisible(false);
            i++;
        }

        addFamily.setVisible(false);
        discard.setVisible(false);
        save.setVisible(false);

        edit.setVisible(true);
        
    }

    public void saveProfile(){
        familiesSize = 0;
    }


    @Override
    public void showMyTab(String buttonName) {
        if (buttonName.equals("Save")){
            if (editing)JOptionPane.showMessageDialog(this.displayPanel,"Edited Successfully!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(this.displayPanel,"Added New Member Successfully!", "Add Member", JOptionPane.INFORMATION_MESSAGE);

            saveProfile();
            prepareToShowProfile(exampleSelected[0]);

        } else if (buttonName.equals("Edit")){
            prepareToEditMember();

        }else if (buttonName.equals("Discard")){
            int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {familiesSize = 0 ;prepareToShowProfile(exampleSelected[0]); }

        }else{
            if (familyInfoInputPanels.size() > familiesSize){
                familyInfoInputPanels.get(familiesSize).updateData("","");
                
            }else{
                addFamilyInputPanel("","");
            }
            familiesSize++;
        }
    }

    @Override
    public void showMyTab(CustomTable table, String[] values, int source) {
    }
    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel,button.getName());
    }
    @Override
    public void workWithFileChosen(File selectedFile) {

    }

    private class FamilyInfoInputPanel extends JPanel{
        private static final String[] RELATIONS = {"Mother", "Father", "Spouse","Son","Daughter","Brother", "Sister", "Mother-in-law", "Father-in-Law","Brother-in-law","Sister-in-law"};
        private JTextField name = new JTextField(20);
        private JTextField phone = new JTextField(12);
        private JTextField relation = new JTextField(12);
        private final JComboBox<String> relationChoice = new JComboBox<String>(RELATIONS);
        
        public FamilyInfoInputPanel(String name , String phone, String relation){
            setLayout(new FlowLayout(FlowLayout.LEFT,22,2));
            setBackground(Color.white);
            setPreferredSize(new Dimension(430, this.name.getHeight()+30));
            setMaximumSize(getPreferredSize());

            this.name.setBorder(null);
            this.name.setPreferredSize(new Dimension(this.name.getPreferredSize().width,this.name.getPreferredSize().height+8));

            this.phone.setBorder(null);
            this.phone.setPreferredSize(new Dimension(this.phone.getPreferredSize().width,this.phone.getPreferredSize().height+8));

            this.relation.setBorder(null);
            this.relation.setEditable(false);
            this.relation.setOpaque(false);
            this.relation.setPreferredSize(new Dimension(this.relation.getPreferredSize().width,this.relation.getPreferredSize().height+8));

            this.relationChoice.setBorder(null);          

            updateData(name,phone,relation);

            add(this.name);
            add(this.phone);
            add(this.relation);
            add(this.relationChoice);
        }

        public FamilyInfoInputPanel(String name, String phone){
            setLayout(new FlowLayout(FlowLayout.LEFT,22,2));
            setBackground(Color.white);
            setPreferredSize(new Dimension(430,this.name.getHeight()+30));
            setMaximumSize(getPreferredSize());
            
            this.name.setBorder(null);
            this.name.setPreferredSize(new Dimension(this.name.getPreferredSize().width,this.name.getPreferredSize().height+8));

            this.phone.setBorder(null);
            this.phone.setPreferredSize(new Dimension(this.phone.getPreferredSize().width,this.phone.getPreferredSize().height+8));

            this.relation.setBorder(null);
            this.relation.setEditable(false);
            this.relation.setOpaque(false);
            this.relation.setPreferredSize(new Dimension(this.relation.getPreferredSize().width,this.relation.getPreferredSize().height+8));

            this.relationChoice.setBorder(null);  
            updateData(name,phone);

            add(this.name);
            add(this.phone);
            add(this.relationChoice);
        }

        public void updateData(String name, String phone, String relation) {
            this.name.setText(name);
            this.phone.setText(phone);
            this.relation.setText(relation);
            setEditable(false);
            setVisible(true);
        }

        public void updateData(String name,String phone){
            this.name.setText(name);
            this.phone.setText(phone);
            setEditable(true);
            setVisible(true);
        }


        public void setEditable(boolean edit){
            setOpaque(!edit);

            name.setEditable(edit);
            name.setOpaque(edit);

            phone.setEditable(edit);
            phone.setOpaque(edit);

            relation.setVisible(!edit);
            relationChoice.setVisible(edit);
        }

        public String[] getData(){
            String[] values = {name.getText(),phone.getText(),(String) relationChoice.getSelectedItem()};
            return  values;
        }
    }
}
