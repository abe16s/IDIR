package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.SkeletalWindow.MenuBar;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;
import GUI.src.utilities.TransparentButton;

public class OfficialsPanel extends JPanel implements ParentPanel{
    private BasePanel displayPanel;
    private ImageIcon change = ImageIcons.reSize(ImageIcons.CHANGE,20,20);
    private Object[][] data = {
        {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0001","Alice frank", "Chairman",change},
        {"","","","","",""},
        {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0002","Bob frank", "Vise chairman",change},
        {"","","","","",""},
        { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Auditor",change},
        {"","","","","",""},
        { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Secretary",change}
    };

    private Object[] columnNames = {"", "ID","Full Name","Title",""};
    private ColoredButton edit;
    private IndividualProfile official;
    private CustomTable officialTable;
    private JPanel saveDiscardPanel;
    private TableColumnModel columnModel;
    private TableColumn column;
    private JPanel footer;
    private ArrayList<TransparentButton> affectedButtons = new ArrayList<>();
    private MenuBar menuBar;

    private Object[][] data = {
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0001","Alice frank", "Chairman"},
                                {"","","","","",},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0002","Bob frank", "Vise chairman"},
                                {"","","","","",},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Auditor"},
                                {"","","","","",},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Secretary"}};


    public OfficialsPanel(BasePanel displayPanel){
        this.displayPanel = displayPanel;
        setLayout(new BorderLayout());

        officialTable = new CustomTable(this, data, columnNames);
        officialTable.setAlternatingColor( new Color(0,0,0,0), new Color(241,241,241), Color.white,15);
        officialTable.updateRowHeights();

        JScrollPane ScrollList = new JScrollPane(officialTable );
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        columnModel = officialTable.getColumnModel();
        column = columnModel.getColumn(4);

        JPanel gap = new JPanel();
        gap.setPreferredSize(new Dimension(100,20));
        add(gap,BorderLayout.NORTH);
        add( ScrollList,BorderLayout.CENTER);

        footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(new Color(241,241,241));

        saveDiscardPanel = new JPanel();
        saveDiscardPanel.setBackground(new Color(241,241,241));

        ColoredButton save = new ColoredButton("Save", this);
        save.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 20, 20));
        save.setNormalColor(new Color(147, 175, 207));
        save.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OfficialsPanel officialsPanel = null;
                JOptionPane.showMessageDialog(displayPanel,"Edited Successfully!", "Edit Officials", JOptionPane.INFORMATION_MESSAGE);
                for (Component panel : displayPanel.getComponents()){
                    if (panel instanceof OfficialsPanel){
                        officialsPanel = ((OfficialsPanel)panel);
                    }
                }
                officialsPanel.updateData("");
                viewOnlyPage();
            }
        });

        ColoredButton discard = new ColoredButton("Discard",this);
        discard.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        discard.setNormalColor(new Color(147, 175, 207));
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    viewOnlyPage();
                }
            } 
        });

        saveDiscardPanel.add(save.getWhole());
        saveDiscardPanel.add(discard.getWhole());

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
    }


    @Override
    public void showMyTab(CustomTable table, String[] values, int source) {
        if(!values[0].equals("")){
        displayPanel.remove(official);
        official.updateData(values[1]);

        displayPanel.addMyTab(official,values[1]);
        displayPanel.showMyTab(values[1]);
    }}

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {

    }

    private class PopUpMembers extends JPanel implements ParentPanel{

        private OfficialsPanel parent;
        private Choose dialog;

        public PopUpMembers(OfficialsPanel parent, String position) {
            this.parent= parent;
    
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
        
            CustomTable MembersList = new CustomTable(this,exampleData, columnNames);
            MembersList.setAlternatingColor( Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
    
            JScrollPane ScrollList = new JScrollPane(MembersList);
            ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
            
    
            add(ScrollList, BorderLayout.CENTER);
            dialog = new Choose(this, ImageIcons.reSize(ImageIcons.CHANGE,20,20),position);
            dialog.setVisible(true);
        }
    
    
        @Override
        public void showMyTab(String buttonName){
            throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
        }
    
    
        @Override
        public void showMyTab(CustomTable table, Object[] values, int source) {
            parent.updateData((String)values[1]);
            dialog.setVisible(false);
            
        }
    
        @Override
        public void addTab(JButton button, JPanel clickedPanel) {
            throw new UnsupportedOperationException("Unimplemented method 'addTab'");
        }


        public class Choose extends JDialog {

            public Choose(JPanel component, ImageIcon icon, String position) {

                super(null, "Choose " + position,JDialog.DEFAULT_MODALITY_TYPE);

                setIconImage(icon.getImage());
                setLayout(new BorderLayout());

                Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
                int height = (int) ((int)screenSize.getHeight () * 0.60);
                int width = (int) ((int)screenSize.getWidth () * 0.60);
                setPreferredSize(new Dimension(width, height));

                add(component);

                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                pack();

                setLocationRelativeTo(null);

    }}


        @Override
        public void workWithFileChosen(File selectedFile) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
        }}

    @Override
    public void workWithFileChosen(File selectedFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
    }}