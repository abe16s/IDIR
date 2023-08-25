package GUI.src.utilities;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import GUI.src.BasePanel;
import GUI.src.IndividualProfile;

public class CustomTable extends JTable {
    /*
     * This custom table is a Jtable that accepts an array of arrays and column header
     * Colors itself with its private cell renderer with a palette (mixed style) of two types of colors
     * It Colors the odd rows with color1 and even rows with color2
     * Colors it header with accepted color
     * And this render works to change the color of JTable header with desired color in color1 variable
     * It also have features like not editable cells, invisible grid lines
    */
    public CustomTable(BasePanel displayPanel,Object[][] rowData, Object[] columnNames, Color headerColor, Color color1, Color color2) {
        super(rowData, columnNames);
        getTableHeader().setDefaultRenderer(new CustomTableCellRenderer(headerColor, headerColor));
        setDefaultRenderer(Object.class, new CustomTableCellRenderer(color1, color2));
        setShowGrid(false);
        setDefaultEditor(Object.class, null);
        setRowSelectionAllowed(false);
        getSelectionModel().addListSelectionListener(new TableListener(this, displayPanel));
    }

    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        private Color color1;
        private Color color2;

        public CustomTableCellRenderer(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
            setHorizontalAlignment(SwingConstants.CENTER);
        };

        @Override
        public Component getTableCellRendererComponent(JTable table, Object Value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, Value, isSelected, hasFocus, row, column);
            if (row%2 != 0) {
                cellComponent.setBackground(this.color1);
            } else {
                cellComponent.setBackground(this.color2);
            }
            return cellComponent;
        }
    }
    public class TableListener implements ListSelectionListener{
    /*
     * This table litsener creates and shows an individual profile page based on the row touched in
     * the MembersList JTable in MembersPanel which is the list of all the members.
     * this listener add the individualProfile page in the basePanel and removes the effect of the 
     * members button in the menu bar to be in effect again.
     */
    private JTable table;
    private BasePanel displayPanel;
    
    public TableListener(JTable table, BasePanel displayPanel) {
        this.table = table;   
        this.displayPanel = displayPanel;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();            
            if (selectedRow != -1) { 
                TableModel model = table.getModel();
                String MemberID = (String) model.getValueAt(selectedRow, 0);
                this.displayPanel.createIndividualProfile(new IndividualProfile(MemberID, this.displayPanel));
                this.table.clearSelection();
            }
        }
    } 
}

}
