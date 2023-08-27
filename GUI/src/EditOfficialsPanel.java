package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;

public class EditOfficialsPanel extends JPanel implements ParentPanel{
    private BasePanel displayPanel;
    private ImageIcon change = ImageIcons.reSize(ImageIcons.CHANGE,20,20);
    private Object[][] data = {
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0001","Alice frank", "Chairman",change},
                                {"","","","","",""},
                                {ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0002","Bob frank", "Vise chairman",change},
                                {"","","","","",""},
                                { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Auditor",change},
                                {"","","","","",""},
                                { ImageIcons.reSize(ImageIcons.UNKNOWN, 100, 100),"0003","Charlie frank","Secretary",change}};

    private Object[] columnNames = {"", "ID","Full Name","Title",""};

    public EditOfficialsPanel(BasePanel displayPanel){
        this.displayPanel = displayPanel;
        setLayout(new BorderLayout());

        CustomTable officialTable = new CustomTable(this, data, columnNames);
        officialTable.setAlternatingColor( new Color(0,0,0,0), new Color(241,241,241), Color.white,15);
        officialTable.updateRowHeights();

        JScrollPane ScrollList = new JScrollPane(officialTable );
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        JPanel gap = new JPanel();
        gap.setPreferredSize(new Dimension(100,20));
        add(gap,BorderLayout.NORTH);
        add( ScrollList,BorderLayout.CENTER);


        JPanel footer = new JPanel();
        footer.setBackground(new Color(241,241,241));

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
            officialsPanel.updateData();
            showMyTab(save.getName());
        }
    }
        );

        ColoredButton discard = new ColoredButton("Discard",this);
        discard.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        discard.setNormalColor(new Color(147, 175, 207));
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    displayPanel.showMyTab("Officials");
                }
            } 
        });

        footer.add(save.getWhole());
        footer.add(discard.getWhole());


        this.add(footer, BorderLayout.SOUTH);
    }
    public void updateData(String memberID){

    }
    @Override
    public void showMyTab(String buttonName) {
        displayPanel.showMyTab("Officials");
    }


    @Override
    public void showMyTab(String[] values, int source) {
        if (source == 4) new PopUpMembers(this,values[3]);
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {

    }

    private class PopUpMembers extends JPanel implements ParentPanel{

        private EditOfficialsPanel parent;
        private Choose dialog;

        public PopUpMembers(EditOfficialsPanel parent, String position) {
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
        public void showMyTab(String[] values, int source) {
            parent.updateData(values[1]);
            dialog.setVisible(false);;
            
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
    }

}