package GUI.src.utilities;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTable extends JTable {
    /*
     * This custom table is a Jtable that accepts an array of arrays and column header
     * Colors itself with its private cell renderer with a palette (mixed style) of two types of colors
     * It Colors the odd rows with color1 and even rows with color2
     * Colors it header with accepted color
     * And this render works to change the color of JTable header with desired color in color1 variable
     * It also have features like not editable cells, invisible grid lines
    */
    public CustomTable(Object[][] rowData, Object[] columnNames, Color headerColor, Color color1, Color color2) {
        super(rowData, columnNames);
        getTableHeader().setDefaultRenderer(new CustomTableCellRenderer(headerColor, headerColor));
        setDefaultRenderer(Object.class, new CustomTableCellRenderer(color1, color2));
        setShowGrid(false);
        setDefaultEditor(Object.class, null);
        setRowSelectionAllowed(false);
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
}
