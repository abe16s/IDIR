package GUI.src;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import GUI.src.utilities.*;

public class MembersPanel extends JPanel implements ParentPanel {
    private JLabel title; 
    private JTable MembersList;
    private JPanel titleBar;
    private ArrayList<HoverableButton> buttons = new ArrayList<HoverableButton>();
    private BasePanel displayPanel;
    private TransparentButton MembersButton;


    public MembersPanel(BasePanel displayPanel, TransparentButton MembersButton) {
        this.displayPanel = displayPanel;
        this.MembersButton = MembersButton;
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));
        
        titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(217, 217, 217));
        
        title = new JLabel("   Members");
        title.setFont(new Font("Arial", Font.BOLD, 15));

        TransparentButton AddMemeber = new TransparentButton("Add member", ImageIcons.reSize(ImageIcons.ADD_MEMBER, 25, 25), this);
        AddMemeber.setBorder(BorderFactory.createEmptyBorder(5,0,5,40));
        
        titleBar.add(title, BorderLayout.WEST);
        titleBar.add(AddMemeber, BorderLayout.EAST);
        buttons.add(AddMemeber);

        this.add(titleBar, BorderLayout.NORTH);
        
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
    
        MembersList = new JTable(exampleData, columnNames);
        CustomTableCellRenderer renderer = new CustomTableCellRenderer(Color.WHITE, new Color(228, 228, 228));
        MembersList.setDefaultRenderer(Object.class, renderer);
        MembersList.setShowGrid(false);
        MembersList.setDefaultEditor(Object.class, null);
        MembersList.getTableHeader().setDefaultRenderer(new CustomTableCellRenderer(Color.LIGHT_GRAY, Color.LIGHT_GRAY));
        MembersList.getSelectionModel().addListSelectionListener(new MembersTableListener(MembersList, this.displayPanel, this.MembersButton));

        JScrollPane ScrollList = new JScrollPane(MembersList);
        this.add(ScrollList, BorderLayout.CENTER);
    }

    @Override
    public void addTab(HoverableButton button, JPanel clickedPanel){
        displayPanel.addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void showMyTab(String buttonName){
        prepare(buttonName);
        MembersButton.removeEffect();
        MembersButton.unselect();
        this.addTab(buttons.get(0), new AddMemberPanel(this.displayPanel));
        displayPanel.showMyTab(buttonName);
    }

    private void prepare(String buttonName){
        for(HoverableButton x : buttons){
            if (x.getName().equalsIgnoreCase(buttonName)){
                x.unselect();
                x.removeEffect();
            }
        }
    }
}
