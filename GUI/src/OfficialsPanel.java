package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;


public class OfficialsPanel extends JPanel implements ParentPanel{
    private BasePanel displayPanel;
    private ColoredButton edit;
    private IndividualProfile official;

    private Object[][] data = {
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0001","Alice frank", "Chairman"},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0002","Bob frank", "Vise chairman"},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Auditor"},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Secretary"}};

    private Object[] columnNames = {"", "ID","Full Name","Title"};

    public OfficialsPanel(BasePanel displayPanel){
        this.displayPanel = displayPanel;
        setLayout(new BorderLayout());

        CustomTable officialTable = new CustomTable(this, data, columnNames);
        officialTable.setAlternatingColor( new Color(0,0,0,0), new Color(241,241,241), Color.white);
        officialTable.updateRowHeights();

        JScrollPane ScrollList = new JScrollPane(officialTable );
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        JPanel gap = new JPanel();
        gap.setPreferredSize(new Dimension(100,60));
        add(gap,BorderLayout.NORTH);
        add( ScrollList,BorderLayout.CENTER);

        JPanel editBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        editBar.setBackground(new Color(241,241,241));

        edit = new ColoredButton("Edit", this);
        edit.setIcon(ImageIcons.reSize(ImageIcons.EDIT, 20,20));
        edit.setNormalColor(new Color(147, 175, 207));
        edit.setSelectedColor(new Color(79,170,255));
        addTab(edit,new EditOfficialsPanel(displayPanel));
        editBar.add(edit.getWhole());

        this.add(editBar, BorderLayout.SOUTH);
        official = new IndividualProfile(displayPanel);
    }

    @Override
    public void showMyTab(String buttonName) {
        displayPanel.showMyTab(buttonName + "Officials");
    }


    @Override
    public void showMyTab(String[] values, int source) {

        displayPanel.remove(official);
        official.updateData(values[1]);

        displayPanel.addMyTab(official,values[1]);
        displayPanel.showMyTab(values[1]);
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
         displayPanel.addMyTab(clickedPanel, button.getName() + "Officials");
    }

    public void updateData() {
    }

}
