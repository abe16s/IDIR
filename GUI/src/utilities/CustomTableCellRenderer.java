package GUI.src.utilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    /*
     * This a cell renderer that colors a JTable with a palette (mixed style) of two types of colors.
     * The constructor accepts two colors and
     * It Colors the odd rows with color1 and even rows with color2
     * And this render works to change the color of JTable header with desired color in color1 variable
     */

    private Color color1;
    private Color color2;

    public CustomTableCellRenderer(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
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
