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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private CustomTable officialTable;
    private JPanel saveDiscardPanel;
    private TableColumnModel columnModel;
    private TableColumn column;
    private JPanel footer;
    private ArrayList<TransparentButton> affectedButtons = new ArrayList<>();
    private MenuBar menuBar;


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


        ColoredButton discard = new ColoredButton("Discard",this);
        discard.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        discard.setNormalColor(new Color(147, 175, 207));

        saveDiscardPanel.add(save.getWhole());
        saveDiscardPanel.add(discard.getWhole());

        edit = new ColoredButton("Edit", this);
        edit.setIcon(ImageIcons.reSize(ImageIcons.EDIT, 20,20));
        edit.setNormalColor(new Color(147, 175, 207));
        edit.setSelectedColor(new Color(79,170,255));
        
        footer.add(saveDiscardPanel);
        footer.add(edit.getWhole());

        this.add(footer, BorderLayout.SOUTH);
        viewOnlyPage();
        refreshOfficialsData();
    }

    public void updateData(String memberID,String position){
    }

    public void refreshOfficialsData(){
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[] rowData;
         try (Statement generalsStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveIdirInfo = generalsStmt.executeQuery("call retrieveOfficials");
            while (retrieveIdirInfo.next()) {
                rowData = new Object[5];
                rowData[0] = ImageIcons.reSize(new ImageIcon(retrieveIdirInfo.getString(1)), 100, 100);
                for(int i = 1; i < 4; i++){
                    rowData[i] = retrieveIdirInfo.getString(i+1);
                }
                data.add(rowData);
                if (!retrieveIdirInfo.isAfterLast()){data.add(new Object[]{"","","","",""});}
            }
        } 
            catch (SQLException e) {
                e.printStackTrace();
            }
            if (data.size() > 0){
                officialTable.updateTableData(data);
                officialTable.updateRowHeights();
            }
            
    }

    private void viewOnlyPage() {
        columnModel.removeColumn(column);
        saveDiscardPanel.setVisible(false);
        edit.setVisible(true);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    public void giveAffectedButtons(TransparentButton btn, MenuBar menuBar) {
        this.affectedButtons.add(btn);
        this.menuBar = menuBar;
    }
    
    @Override
    public void showMyTab(String buttonName) {
        if (buttonName.equals("Save")) { 
            refreshOfficialsData();
            JOptionPane.showMessageDialog(displayPanel,"Edited Successfully!", "Edit Officials", JOptionPane.INFORMATION_MESSAGE);
            viewOnlyPage();

        } else if (buttonName.equals("Edit")) {
                saveDiscardPanel.setVisible(true);
                edit.setVisible(false);
                footer.setLayout(new FlowLayout());
                columnModel.addColumn(column);

        } else if (buttonName.equals("Discard")) { // check if what we are discarding is editing or not and customize
                            // the info as displayed and determine which page to show
            int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes",JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) viewOnlyPage();

        }

        }


    @Override
    public void showMyTab(CustomTable table, Object[] values, int source) {
        if (source == 4 & !values[0].equals(""))new PopUpMembers(this,(String)values[3]);
        
        if(!values[0].equals("") && edit.isVisible()){
            App.INDIVIDUAL_PROFILE.prepareToShowProfile(Integer.parseInt((String) values[1]));
            displayPanel.showMyTab("individualProfile");

            for (TransparentButton transparentButton: affectedButtons) {
                if (transparentButton.getName().equalsIgnoreCase("Members")) {
                    this.menuBar.prepare("Members");
                    transparentButton.setIcon(transparentButton.getSelectedIcon());
                    transparentButton.setSelected(true);
                }
            }
        }
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {

    }

    private class PopUpMembers extends JPanel implements ParentPanel{

        private OfficialsPanel parent;
        private Choose dialog;
        private String position;
        public PopUpMembers(OfficialsPanel parent, String position) {
            this.parent= parent;
            this.position = position;
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(228, 228, 228));
            
            String[] columnNames = {"ID", "Full Name", "Address", "Phone No", "Age", "Occupation", "Religion"};
            String[][] exampleData = {{"1", "Abenezer Seifu Dula", "Shenkor, 10, 551", "0936120470", "19", "Student", "Orthodox"},};
        
            CustomTable MembersList = new CustomTable(this,exampleData, columnNames);
            MembersList.setAlternatingColor( Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
            MembersList.setModel(App.membersPanel.MembersList.getModel());
            
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
            parent.updateData((String)values[1],position);
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
        
        }}

    @Override
    public void workWithFileChosen(File selectedFile) {
        
    }}