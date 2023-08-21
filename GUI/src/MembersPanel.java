package GUI.src;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.table.*;


import GUI.src.utilities.*;

public class MembersPanel extends JPanel implements ParentPanel {
    private JLabel title; 
    private JTable MembersList;
    private JPanel titleBar;
    private ArrayList<HoverableButton> buttons = new ArrayList<HoverableButton>();
    private BasePanel displayPanel;


    public MembersPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));
        
        titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(217, 217, 217));
        
        title = new JLabel("   Members");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        titleBar.add(title, BorderLayout.WEST);

        TransparentButton AddMemeber = new TransparentButton("Add member", ImageIcons.reSize(ImageIcons.ADD_MEMBER, 25, 25), this);
        AddMemeber.setBorder(BorderFactory.createEmptyBorder(5,0,5,40));
        
        
        this.addTab(AddMemeber, new AddMemberPanel());

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
        CustomTableCellRenderer renderer = new CustomTableCellRenderer();
        
        MembersList.setDefaultRenderer(Object.class, renderer);
        MembersList.setShowGrid(false);
        MembersList.setDefaultEditor(Object.class, null);

        /* 
        MembersList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel columnModel = MembersList.getColumnModel();
        int totalWidth = MembersList.getWidth();

        for (int column = 0; column < MembersList.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int minimumWidth = tableColumn.getWidth();

            for (int row = 0; row < MembersList.getRowCount(); row++) {
                TableCellRenderer cellRenderer = MembersList.getCellRenderer(row, column);
                Component component = MembersList.prepareRenderer(cellRenderer, row, column);
                minimumWidth = Math.max(minimumWidth, component.getPreferredSize().width);
            }

            tableColumn.setMinWidth(minimumWidth);
            totalWidth -= minimumWidth;
        }

        int averageWidth = totalWidth / MembersList.getColumnCount();
        for (int columnIndex = 0; columnIndex < MembersList.getColumnCount(); columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            int preferredWidth = column.getPreferredWidth();
        
            if (preferredWidth < averageWidth) {
                column.setPreferredWidth(averageWidth);
            }
        }*/


        JScrollPane ScrollList = new JScrollPane(MembersList);
        this.add(ScrollList, BorderLayout.CENTER);
    }

    @Override
    public void addTab(HoverableButton button, JPanel clickedPanel){
        titleBar.add(button, BorderLayout.EAST);
        buttons.add(button);
        displayPanel.addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void showMyTab(String buttonName){
        prepare(buttonName);
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
