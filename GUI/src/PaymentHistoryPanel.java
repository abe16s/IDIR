package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
    private String[] columnNames = {"ID", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private BasePanel displayPanel;
    private String[][] exampleData = {
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

    public PaymentHistoryPanel(BasePanel displayPanel) {
        this.setLayout(new BorderLayout());
        this.displayPanel = displayPanel;

        currentDate = LocalDate.now();
        
        years = new JComboBox<Integer>();
        years.setPreferredSize(new Dimension(100, years.getPreferredSize().height));
        years.setFocusable(false);

        int yearInterval = 1;

        try {
            Statement startingDateStmt = App.DATABASE_CONNECTION.createStatement();
            ResultSet startingDate = startingDateStmt.executeQuery("SELECT YEAR(Starting_Date) FROM IDIR_TABLE;");
            if (startingDate.next()) {
                yearInterval = currentDate.getYear() - startingDate.getInt(1) + 1 ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int yr = currentDate.getYear(); yr > currentDate.getYear() - yearInterval; yr--) {
            years.addItem(yr);
        }
        years.addActionListener(new changeYear());

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(new Insets(0, 50, 10, 70)));
        
        JLabel title = new JLabel("Monthly Payment History");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        
        header.add(title, BorderLayout.WEST);
        header.add(years, BorderLayout.EAST);

        historyTable = new CustomTable(this, getYearHistoryTable(currentDate.getYear()), columnNames);
        historyTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        
        JScrollPane scrollHistory = new JScrollPane(historyTable);
        scrollHistory.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));

        this.add(header, BorderLayout.NORTH);
        this.add(scrollHistory, BorderLayout.CENTER);
        years.setSelectedItem(currentDate.getYear());
    }

    private class changeYear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<Integer> source = (JComboBox<Integer>) e.getSource();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            Object[][] newData = getYearHistoryTable((int)source.getSelectedItem());
            for (Object[] rowData:newData) {
                model.addRow(rowData);
            }
            historyTable.setModel(model);
        }
    }

    @Override
    public void showMyTab(String buttonName) {
        
    }

    @Override
    public void showMyTab(CustomTable table,int selectedRow, int selectedColumn) {
        if (!table.getValueAt(selectedRow, selectedColumn).equals("-") && Integer.parseInt((String)table.getValueAt(selectedRow, selectedColumn)) != 0 && selectedColumn>0) {
            //get the receipt for the source column month years combobox year and values[0] id
            String rNo = "110011";
            try (Statement statement = App.DATABASE_CONNECTION.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT Receipt_No " + //
                        "FROM RECEIPT " + //
                        "WHERE Reason_for_Payment = 'Monthly Payment of " + columnNames[selectedColumn] + " " + //
                        years.getSelectedItem() + "' AND Issued_For = " + table.getValueAt(selectedRow, 0) + " AND Deleted = 'NO';");
                if (resultSet.next()) {
                    rNo = resultSet.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            App.INDIVIDUAL_RECEIPT.prepareToShow(rNo);
            displayPanel.showMyTab("individualReceipt");
        }
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
        
    }

    private Object[][] getYearHistoryTable(int year) {
        try (Statement statement = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveTable = statement.executeQuery("SELECT LPAD(ID, 4, '0'), Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, `Dec` " + //
                    "FROM MONTHLY_PAYMENT_HISTORY " + //
                    "WHERE yr = " + year + " ORDER BY ID;");
            
            ArrayList<Object[]> curTable = new ArrayList<Object[]>();
            int ctr = 0;
            while (retrieveTable.next()) {
                ctr++;
                Object[] cur = new Object[13];
                for (int i = 0; i < cur.length; i++) {
                    if (i == 0) cur[i] = retrieveTable.getString(i+1);
                    else cur[i] = retrieveTable.getInt(i+1);
                }
                curTable.add(cur);
            }
            if (ctr > 0)
                return curTable.toArray(Object[][]::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exampleData;
    }

    @Override
    public void refresh() {
       years.setSelectedItem(years.getSelectedItem());
    } 
}