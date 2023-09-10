package GUI.src;

import java.awt.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class MembersPanel extends JPanel implements ParentPanel {
    private BasePanel displayPanel;
    private CustomTable MembersList;
    private ColoredButton AddMember;
    private JPanel panelNull;
    private JScrollPane ScrollList;
    public MembersPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));
        
        String[] columnNames = {"ID", "Full Name", "Address", "Phone No", "Age", "Occupation", "Religion"};
        String[][] data = {{"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"}};

        MembersList = new CustomTable(this,data, columnNames);
        MembersList.setAlternatingColor( Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);

        JLabel Null = new JLabel("NULL");
        Null.setFont(new Font("Arial", Font.BOLD, 100));
        
        panelNull = new JPanel();
        panelNull.add(Null);
        panelNull.setVisible(false);
        add(panelNull);

        ScrollList = new JScrollPane(MembersList);
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
        updateData();
    }
    public void updateData(){
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        String[] rowData;
         try (Statement generalsStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveIdirInfo = generalsStmt.executeQuery("call retrieveListOfMembers");
            while (retrieveIdirInfo.next()) {
                rowData = new String[7];
                for(int i = 0; i < 7; i++){
                    rowData[i] = retrieveIdirInfo.getString(i+1);
                }
                data.add(rowData);
            }
        } 
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (data.size() > 0){
            MembersList.updateTableData(data);
            panelNull.setVisible(false);
        }else{
            ScrollList.setVisible(false);
            panelNull.setVisible(true);
        }
        
    }
    
    @Override
    public void showMyTab(String buttonName){
        App.INDIVIDUAL_PROFILE.prepareToAddMember();
        displayPanel.showMyTab("individualProfile");

    }


    @Override
    public void showMyTab(CustomTable table, Object[] values, int source) {        
        App.INDIVIDUAL_PROFILE.prepareToShowProfile(Integer.parseInt((String)values[0]));

        displayPanel.showMyTab("individualProfile");
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel,button.getName());
    }


    @Override
    public void workWithFileChosen(File selectedFile) {
        
    }
    
}
