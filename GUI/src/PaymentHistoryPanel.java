package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ParentPanel;

public class PaymentHistoryPanel extends JPanel implements ParentPanel{
    private CustomTable historyTable;
    private JComboBox<Integer> years; 
    private LocalDate currentDate;
    private String[] columnNames = {"ID","Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"};
    private IndividualReceiptPanel receiptPanel;
    private BasePanel displayPanel;

    public PaymentHistoryPanel(BasePanel displayPanel) {
        this.setLayout(new BorderLayout());
        this.displayPanel = displayPanel;
        this.receiptPanel = new IndividualReceiptPanel(displayPanel);

        currentDate = LocalDate.now();
        
        years = new JComboBox<Integer>();
        years.setPreferredSize(new Dimension(100, years.getPreferredSize().height));
        years.setFocusable(false);
        for (int yr = 2020; yr <= currentDate.getYear(); yr++) {
            years.addItem(yr);
        }
        years.addActionListener(new changeYear());

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(new Insets(0, 50, 10, 70)));
        
        JLabel title = new JLabel("Monthly Payment History");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        
        header.add(title, BorderLayout.WEST);
        header.add(years, BorderLayout.EAST);

        historyTable = new CustomTable(this, getYearData(currentDate.getYear()), columnNames);
        historyTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);

        this.add(header, BorderLayout.NORTH);
        this.add(new JScrollPane(historyTable), BorderLayout.CENTER);
        years.setSelectedItem(currentDate.getYear());
    }

    private class changeYear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<Integer> source = (JComboBox<Integer>) e.getSource();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            String[][] newData = getYearData((int)source.getSelectedItem());
            for (String[] rowData:newData) {
                model.addRow(rowData);
            }
            historyTable.setModel(model);
        }

    }

    private String[][] getYearData(int year) {
        String[][] exampleData = {
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
            {"001", "100","100", "100","100", "100","-", "210","100", "100","100", "100","100"},
            {"002", "100","100", "-","-", "320","100", "100","100", "100","100", "100","-"},
            {"003", "100","100", "100","100", "100","100", "100","100", "100","100", "100","100"},
        };

        String[][] exampleData2 = {
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
            {"001", "200","200", "200","200", "200","-", "420","200", "200","200", "200","200"},
        };

        if (year % 2 == 0) {
            return exampleData;
        } else {
            return exampleData2;
        }
    }




    @Override
    public void showMyTab(String buttonName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
    }

    @Override
    public void showMyTab(CustomTable table, String[] values, int source) {
        if (!values[source].equals("-") && source>0) {
            //get the receipt for the source column month years combobox year and values[0] id
            displayPanel.remove(receiptPanel);
            receiptPanel.updateData("110011");
            displayPanel.addMyTab(receiptPanel,"110011");
            displayPanel.showMyTab("110011");
        }
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTab'");
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
    }
}