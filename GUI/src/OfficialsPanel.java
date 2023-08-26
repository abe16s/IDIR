package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;


public class OfficialsPanel extends JPanel implements ParentPanel{
    private BasePanel displayPanel;
    private ColoredButton edit;

    private Object[][] data = {
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0001","Alice frank", "Chairman"},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0002","Bob frank", "Vise chairman"},
                                { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Auditor"},
                                { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Secretary"}};

    private Object[] columnNames = {"", "ID","Full Name","Title"};

    public OfficialsPanel(BasePanel displayPanel){
        this.displayPanel = displayPanel;
        setLayout(new BorderLayout());

        CustomTable officialTable = new CustomTable(displayPanel, data, columnNames,"Officials");
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
        edit.setIcon(ImageIcons.reSize(ImageIcons.EDIT, 17,17));
        edit.setNormalColor(new Color(147, 175, 207));
        edit.setSelectedColor(new Color(79,170,255));

        editBar.add(edit.getWhole());

        this.add(editBar, BorderLayout.SOUTH);
    }

    @Override
    public void showMyTab(String buttonName) {

    }

}