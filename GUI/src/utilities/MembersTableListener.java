package GUI.src.utilities;

import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.TableModel;

import GUI.src.IndividualProfile;
import GUI.src.MembersPanel;

public class MembersTableListener implements ListSelectionListener{
    private JTable table;
    private MembersPanel showPanel;
    
    public MembersTableListener(JTable table, MembersPanel membersPanel) {
        this.table = table;   
        this.showPanel = membersPanel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();            
            if (selectedRow != -1) { 
                TableModel model = table.getModel();
                String MemberID = (String) model.getValueAt(selectedRow, 0);
                this.showPanel.createIndividualProfile(new IndividualProfile(MemberID), MemberID);
            }
        }
    }
    
}
