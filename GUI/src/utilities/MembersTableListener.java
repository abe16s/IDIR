package GUI.src.utilities;

import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.TableModel;

import GUI.src.BasePanel;
import GUI.src.IndividualProfile;

public class MembersTableListener implements ListSelectionListener{
    private JTable table;
    private BasePanel showPanel;
    private TransparentButton MembersButton;
    
    public MembersTableListener(JTable table, BasePanel membersPanel, TransparentButton MembersButton) {
        this.table = table;   
        this.showPanel = membersPanel;
        this.MembersButton = MembersButton;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();            
            if (selectedRow != -1) { 
                TableModel model = table.getModel();
                String MemberID = (String) model.getValueAt(selectedRow, 0);
                this.showPanel.createIndividualProfile(new IndividualProfile(MemberID, this.showPanel), MemberID);
                this.table.clearSelection();
                this.MembersButton.removeEffect();
                this.MembersButton.unselect();
            }
        }
    }
    
}
