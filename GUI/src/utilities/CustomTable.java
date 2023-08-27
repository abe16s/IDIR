package GUI.src.utilities;

import javax.swing.*;
import java.awt.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class CustomTable extends JTable {
    /*
     * This custom table is a Jtable that accepts an array of arrays and column header
     * Colors itself with its private cell renderer with a palette (mixed style) of two types of colors
     * It Colors the odd rows with color1 and even rows with color2
     * Colors it header with accepted color
     * And this render works to change the color of JTable header with desired color in color1 variable
     * It also have features like not editable cells, invisible grid lines
    */
    public ParentPanel parent;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.WHITE;

    public CustomTable(ParentPanel parent,Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        this.parent = parent;

        setShowGrid(false);
        setDefaultEditor(Object.class, null);
        setRowSelectionAllowed(false);

        getSelectionModel().addListSelectionListener(new TableListener());
    }


    public void setAlternatingColor(Color headerColor, Color color1, Color color2){
        getTableHeader().setDefaultRenderer(new AlternatingColorRenderer(headerColor, headerColor));
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    public void setAlternatingColor(Color headerColor, Color color1, Color color2, int headersize){
        getTableHeader().setDefaultRenderer(new BoldHeaderRenderer(headerColor,headersize));
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }


    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        // Override this method to return different renderers based on value
        Object value = getValueAt(row, column);

        if ( value instanceof ImageIcon) {
            return new ImageIconRenderer(color1, color2);
        } else {
            return new AlternatingColorRenderer(color1, color2);
        }
    }

    private class AlternatingColorRenderer extends DefaultTableCellRenderer {
        private Color color1;
        private Color color2;

        public AlternatingColorRenderer(Color color1, Color color2) {
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

    private class ImageIconRenderer extends DefaultTableCellRenderer {
        private Color color1;
        private Color color2;

        public ImageIconRenderer(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
            setHorizontalAlignment(SwingConstants.CENTER); 
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object Value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, Value, isSelected, hasFocus, row, column);
            if (Value instanceof ImageIcon) {
                setIcon((Icon)Value);
                setText("");
            }
            // Set the background color to match the alternating color scheme
            if (row%2 != 0) {
                cellComponent.setBackground(color1);
            } else {
                cellComponent.setBackground(color2);
            }
            return cellComponent;
        }
    }

    class BoldHeaderRenderer extends DefaultTableCellRenderer {
        private Color color;
        private int textSize;
        public BoldHeaderRenderer(Color color,int textSize) {
            this.color= color;
            this.textSize = textSize;
            setHorizontalAlignment(SwingConstants.CENTER); 
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            component.setFont(new Font("Arial", Font.BOLD, textSize));
            component.setBackground(color);
            return component;
        }
    }

    public void updateRowHeights() {
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < getRowCount(); i++) {
            int rowHeight = getRowHeight();
            for (int j = 0; j < getColumnCount(); j++) {
                Component comp = prepareRenderer(getCellRenderer(i, j), i, j);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            setRowHeight(i, rowHeight);
        }
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }


    public String[] getRowValue(int rowIndex) {

        int columnCount = getColumnCount();
        String[] rowValue = new String[columnCount];
        for (int i = 0; i < columnCount; i++) { 
          rowValue[i] = getValueAt(rowIndex, i).toString(); 
        }
        return rowValue;
      }

    public class TableListener implements ListSelectionListener{
    /*
     * This table listener creates and shows an individual custom page based on the row touched in and table id
     */


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = getSelectedRow();   
            int selectedColumn = getSelectedColumn();        

            if (selectedRow != -1) { 
                parent.showMyTab(getRowValue(selectedRow),selectedColumn);
                clearSelection();
            }
        }
    }}

}