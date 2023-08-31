package GUI.src;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class MembersPanel extends JPanel implements ParentPanel {
    private BasePanel displayPanel;
    private IndividualProfile individualProfile;
    private CustomTable MembersList;
    private ColoredButton AddMember;

    public MembersPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        individualProfile = new IndividualProfile(displayPanel);
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));
        
        String[] columnNames = {"ID", "Full Name", "Address", "Phone No", "Age", "Occupation", "Religion"};
        String[][] exampleData = {
            {"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka Birhanu Atomsa", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"},
            {"1", "Abenezer", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},
            {"2", "Beka", "Hakim, 17, 4423", "0919131212", "20", "Student", "Orthodox"}
        };
    
        MembersList = new CustomTable(this,exampleData, columnNames);
        MembersList.setAlternatingColor( Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);

        JScrollPane ScrollList = new JScrollPane(MembersList);
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        JPanel addBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addBar.setBackground(new Color(241,241,241));

        AddMember = new ColoredButton("Add Member", this);
        AddMember.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        AddMember.setNormalColor(new Color(147, 175, 207));
        AddMember.setSelectedColor(new Color(79,170,255));

        addBar.add(AddMember.getWhole());
        addTab(AddMember, new AddMemberPanel(this.displayPanel));

        this.add(addBar, BorderLayout.SOUTH);
        this.add(ScrollList, BorderLayout.CENTER);
    }

    
    @Override
    public void showMyTab(String buttonName){
                displayPanel.showMyTab(buttonName);
    }


    @Override
    public void showMyTab(CustomTable table, Object[] values, int source) {
        displayPanel.remove(individualProfile);
        
        individualProfile.updateData((String)values[0]);
        displayPanel.addMyTab(individualProfile,(String)values[0]);
        displayPanel.showMyTab((String)values[0]);
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
