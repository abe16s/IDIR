package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.ColoredFileChooser;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;
import GUI.src.utilities.queryPanel;

public class AgendaList extends JPanel implements ParentPanel {

    private BasePanel displayPanel;
    private IndividualAgenda individualAgenda;
    private CustomTable agendaList;
    private ColoredButton addAgenda;

    public AgendaList(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        individualAgenda = new IndividualAgenda(displayPanel);
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));
        
        String[] columnNames = {"No", "Date", "Title", "Meeting chair"};
        String[][] exampleData = {
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
            {"1", "20/7/2023", "General Meeting", "Abenezer"},
        };
    
        agendaList = new CustomTable(this,exampleData, columnNames);
        agendaList.setAlternatingColor( Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);

        JScrollPane ScrollList = new JScrollPane(agendaList);
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        JPanel addBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addBar.setBackground(new Color(241,241,241));

        addAgenda = new ColoredButton("Add Agenda", this);
        addAgenda.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 20, 20));
        addAgenda.setNormalColor(new Color(147, 175, 207));
        addAgenda.setSelectedColor(new Color(79,170,255));

        addBar.add(addAgenda.getWhole());
        

        this.add(addBar, BorderLayout.SOUTH);
        this.add(ScrollList, BorderLayout.CENTER);
    }
    public class AddAgenda extends JDialog{

        private AgendaList parent;

        public AddAgenda(AgendaList agendaList , ImageIcon icon) {
            
            super(null, "Add Agenda",JDialog.DEFAULT_MODALITY_TYPE);

            setIconImage(icon.getImage());
            setLayout(new BorderLayout());

            Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
            int height = (int) ((int)screenSize.getHeight () * 0.30);
            int width = (int) ((int)screenSize.getWidth () * 0.40);
            setPreferredSize(new Dimension(width, height));

            add(new inputPanel(this));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();

            setLocationRelativeTo(null);
            setVisible(true);
        }
        

        public void report(File fileChosen) {
            if (fileChosen != null)parent.workWithFileChosen(fileChosen);
            setVisible(false);
        }



        private class inputPanel extends JPanel implements ParentPanel{
            private AddAgenda parent;
            private File fileChosen;

            public inputPanel (AddAgenda parent){
            this.parent = parent;

            setLayout(new BorderLayout());
            JPanel textInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
            textInput.add(Box.createRigidArea(new Dimension(10, 0)));

            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
            String nextAvailableNoExample = "122"; 

            queryPanel agendaNo = new queryPanel("No", nextAvailableNoExample, Color.LIGHT_GRAY);
            agendaNo.setAlignmentX(LEFT_ALIGNMENT);

            queryPanel date = new queryPanel("Date", 10, Color.LIGHT_GRAY);
            date.setAlignmentX(LEFT_ALIGNMENT);

            queryPanel title = new queryPanel("Title", 20, Color.LIGHT_GRAY);
            title.setAlignmentX(LEFT_ALIGNMENT);

            queryPanel writer = new queryPanel("Writer", 20, Color.LIGHT_GRAY);
            writer.setAlignmentX(LEFT_ALIGNMENT);

            p.add(agendaNo);
            p.add(date);
            p.add(title);
            p.add(writer);

            textInput.add(p);
            add(textInput,BorderLayout.WEST);


            JPanel fileInput = new JPanel();
            fileInput.setLayout(new BoxLayout(fileInput,BoxLayout.Y_AXIS));
            fileInput.add(Box.createRigidArea(new Dimension(0, 40)));

            ColoredFileChooser chooser = new ColoredFileChooser("Add File", this, "Choose File", "txt");
            chooser.setIcon(ImageIcons.reSize(ImageIcons.FILE, 40,40));
            fileInput.add(chooser);
            
            add(fileInput,BorderLayout.EAST);
            JPanel footer = new JPanel();
            footer.setBackground(new Color(241,241,241));

            ColoredButton save = new ColoredButton("Save", this);
            save.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 18, 18));
            save.setNormalColor(new Color(147, 175, 207));

            ColoredButton discard = new ColoredButton("Discard",this);
            discard.setIcon(ImageIcons.reSize(ImageIcons.ADD_MEMBER, 18, 18));
            discard.setNormalColor(new Color(147, 175, 207));

            footer.add(save.getWhole());
            footer.add(discard.getWhole());

            add(footer, BorderLayout.SOUTH);

            }

        
            @Override
            public void showMyTab(String buttonName) {
                if (buttonName.equals("Save")){
                    JOptionPane.showMessageDialog(displayPanel,"Added Successfully!", "Add Agenda", JOptionPane.INFORMATION_MESSAGE);
                    parent.report(this.fileChosen);

                }else{
                    int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                       parent.report(null);
                    }
                }
            }


            @Override
            public void showMyTab(String[] values, int source) {
                throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
            }

            @Override
            public void addTab(JButton button, JPanel clickedPanel) {
                throw new UnsupportedOperationException("Unimplemented method 'addTab'");
            }


            @Override
            public void workWithFileChosen(File selectedFile) {
                this.fileChosen = selectedFile;
            }}
    }


    @Override
    public void showMyTab(String buttonName) {
        new AddAgenda(this, ImageIcons.NOTE);
    }


    @Override
    public void showMyTab(String[] values, int source) {
        displayPanel.remove(individualAgenda);
        
        individualAgenda.updateData(values[0]);
        displayPanel.addMyTab(individualAgenda,values[0]);
        displayPanel.showMyTab(values[0]);
    }
    

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {

    }
    
}
