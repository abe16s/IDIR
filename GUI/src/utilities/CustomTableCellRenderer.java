package GUI.src.utilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    public CustomTableCellRenderer() {};

    @Override
    public Component getTableCellRendererComponent(JTable table, Object Value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, Value, isSelected, hasFocus, row, column);
        if (row%2 == 0) {
            cellComponent.setBackground(Color.WHITE);
        } else {
            cellComponent.setBackground(new Color(228, 228, 228));
        }

        return cellComponent;
    }
}
