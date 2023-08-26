package GUI.src;

import java.awt.*;

import javax.swing.*;

import GUI.src.utilities.*;

public class MembersPanel extends JPanel implements ParentPanel {
    private CustomTable MembersList;
    private BasePanel displayPanel;
    private ColoredButton AddMember;

    public MembersPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        
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
    
        MembersList = new CustomTable(displayPanel,exampleData, columnNames,"Members");
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

        this.add(addBar, BorderLayout.SOUTH);
        this.add(ScrollList, BorderLayout.CENTER);
    }

    public void addTab(ColoredButton button, JPanel clickedPanel){
        displayPanel.addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void showMyTab(String buttonName){
        AddMember.unselect();
        AddMember.removeEffect();
        addTab(AddMember, new AddMemberPanel(this.displayPanel));
        displayPanel.showMyTab(buttonName);
    }
}
