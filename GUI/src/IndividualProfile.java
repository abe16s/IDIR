package GUI.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class IndividualProfile extends JPanel implements ParentPanel{
    private BasePanel displayPanel;

    private JLabel photo;
    private ColoredFileChooser choosePhoto;
    
    private JLabel fullName;
    
    private ArrayList<queryPanel> personalInfoInputFields;

    private JPanel familyList;
    private ColoredButton addFamily;

    private ArrayList<queryPanel> familyInfoInputFields;


    public IndividualProfile(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        personalInfoInputFields = new ArrayList<queryPanel>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(228, 228, 228));       

        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel,BoxLayout.Y_AXIS));
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(250, 300));
        photoPanel.setMaximumSize(photoPanel.getPreferredSize());

        photo = new JLabel();

        fullName = new JLabel();
        fullName.setFont(new Font("Arial", Font.BOLD, 15));
        fullName.setOpaque(false);
        fullName.setVisible(false);

        choosePhoto = new ColoredFileChooser("choosePhoto", this, "Choose Member Photo", "jpg", "img");
        choosePhoto.setVisible(false);

        photoPanel.add(photo);
        photoPanel.add(fullName);
        photoPanel.add(choosePhoto);

        queryPanel ID = new queryPanel("ID", " ", getBackground());
        ID.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel name = new queryPanel("Full Name", " ", getBackground());
        name.setAlignmentX(LEFT_ALIGNMENT);
        name.setVisible(false);

        queryPanel gender = new queryPanel("Gender", " ", getBackground());
        gender.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel age = new queryPanel("Age", " ", getBackground());
        age.setAlignmentX(LEFT_ALIGNMENT); 

        queryPanel religion = new queryPanel("Religion", " ", getBackground());
        religion.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel address = new queryPanel("Home address", " ", getBackground());
        address.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel phone = new queryPanel("Phone", " ", getBackground());
        phone.setAlignmentX(LEFT_ALIGNMENT);

        queryPanel occupation = new queryPanel("Occupation", " ", getBackground());
        occupation.setAlignmentX(LEFT_ALIGNMENT);


        JPanel personalInfo = new JPanel();
        BoxLayout infoBox = new BoxLayout(personalInfo, BoxLayout.Y_AXIS);
        personalInfo.setLayout(infoBox);
        personalInfo.setBackground(this.getBackground());
        personalInfo.setPreferredSize(new Dimension(300, 300));
        personalInfo.setMaximumSize(personalInfo.getPreferredSize());

        
        personalInfo.add(ID);        
        personalInfoInputFields.add(ID);
        personalInfo.add(name);        
        personalInfoInputFields.add(name);
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
        
        
        JLabel title = new JLabel("Family Members");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JPanel familyHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10)); 
        familyHeader.add(new JLabel("Full Name"));
        familyHeader.add(new JLabel("Phone No"));
        familyHeader.add(new JLabel("Relation"));
        familyHeader.setOpaque(false);
        familyHeader.setAlignmentX(CENTER_ALIGNMENT);
        familyHeader.setMaximumSize(new Dimension(400, 30));

        familyList = new JPanel(new BoxLayout(familyList, BoxLayout.Y_AXIS));
        familyList.setOpaque(false);
        familyList.setAlignmentX(CENTER_ALIGNMENT);
        familyList.setPreferredSize(new Dimension(500,100));
        familyList.setMaximumSize(familyList.getPreferredSize());
        
        addFamily = new ColoredButton("Add Family", this);
        addFamily.setIcon(ImageIcons.reSize(ImageIcons., WIDTH, HEIGHT));

        JPanel familyInfo = new JPanel();
        familyInfo.setLayout(new BoxLayout(familyInfo, BoxLayout.Y_AXIS));
        familyInfo.setOpaque(false);
        familyInfo.setBorder(new EmptyBorder(new Insets(40, 0, 0, 0)));

        familyInfo.add(title);
        familyInfo.add(familyHeader);




        JPanel infoPanel;
        infoPanel = new JPanel(new BorderLayout(20,0));
        infoPanel.setBorder(new EmptyBorder(new Insets(0, 200, 0, 70)));
        infoPanel.setBackground(this.getBackground());


        JPanel Contents = new JPanel();
        BoxLayout contentBox = new BoxLayout(Contents, BoxLayout.Y_AXIS);
        Contents.setLayout(contentBox);
        Contents.setBackground(getBackground());

    }
    public void updateData(String memberID){
        removeAll();

        String[] exampleSelected = {"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox", "GUI\\Icons\\dark\\Example-Photo.jpg"};
        String[][] familiesExample = {
            {"Kassaye W/Medhin Kidane", "0911111111", "Mother"},
            {"Seifu Dula Kara", "0911111111", "Father"}, 
            {"Ermiyas Seifu Dula", "0911111111", "Brother"}
        };
        

        


        
        
        CustomTable familiesList = new CustomTable(this,familiesExample, columnNames);
        familiesList.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        
        familyInfo.add(familyTitle);
        familyTitle.setAlignmentX(CENTER_ALIGNMENT);
        familyInfo.add(familyHeader);
        familyInfo.add(familiesList);

        infoPanel.add(generalInfo, BorderLayout.WEST);
        infoPanel.add(familyInfo, BorderLayout.EAST);
        
        JPanel footer = new JPanel();
        footer.setBackground(getBackground());

        ColoredButton edit = new ColoredButton("Edit", infoPanel);
        edit.setIcon(ImageIcons.reSize(ImageIcons.EDIT, 20, 20));
        edit.setNormalColor(new Color(147, 175, 207));
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.remove(editable);
                editable = new AddMemberPanel(displayPanel, memberID);
                addTab(edit, editable);

                showMyTab(edit.getName());
            }
        });
        
        footer.add(edit.getWhole());

        Contents.add(photoPanel);
        Contents.add(infoPanel);
        Contents.add(footer);

        JScrollPane ScrollContent = new JScrollPane(Contents);
        ScrollContent.setBorder(null);

        this.add(ScrollContent);

        repaint();
    }
    public void addFamilyInputPanel(String name, String )
    public void prepareToAddMember(){

    }

    public void prepareToEditMember(int memberID){

    }
    public void prepareToShowProfile(int memberID){

    }

    public void saveProfile(){

    }

    public void updateProfile(int memberID){

    }



    @Override
    public void showMyTab(String buttonName) {
        displayPanel.showMyTab(buttonName);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
    }
}
