package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class FinancePanel extends JPanel implements ParentPanel {

    private JPanel generalData;
    private BasePanel displayPanel;
    private CustomTable expendingsTable;
    private Icon onIcon = ImageIcons.reSize(ImageIcons.ON,15,15);

    public FinancePanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        this.setBackground(new Color(228, 228, 228));
        this.setLayout(new BorderLayout());
        
        //example data
        String officialName = "Ye Shenkor Wereda Kebele 10 Newariwoch Meredaja Idir";
        String accountNo = "10001234567 (CBE)";
        double totalAmount = 250000;
        double curMonthIncome = 10000;
        double curMonthExpenditure = 3000;
        double curYearIncome = 100000;
        double curYearExpenditure = 30000;

        Object[] basicInfo = {officialName, accountNo, totalAmount};
        Double[] curMonthData = {curMonthIncome, curMonthExpenditure};
        Double[] curYearData = {curYearIncome, curYearExpenditure};
 
        try (Statement generalsStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveIdirInfo = generalsStmt.executeQuery("SELECT Official_Name, Bank_Acc_No, Total_Amount From IDIR_TABLE;");
            while (retrieveIdirInfo.next()) {
                basicInfo[0] = retrieveIdirInfo.getString(1);
                basicInfo[1] = retrieveIdirInfo.getString(2);
                basicInfo[2] = retrieveIdirInfo.getDouble(3);
            }

            ResultSet retrieveCurMonthData = generalsStmt.executeQuery("SELECT Type, Sum(Amount) " + //
                    "FROM RECEIPT " + //
                    "WHERE MONTH(Issued_Date) = MONTH(curdate()) AND YEAR(Issued_Date) = YEAR(curdate()) AND Deleted = 'NO' " + //
                    "GROUP BY Type " + //
                    "ORDER BY Type DESC;");

            int type = 0;
            while (retrieveCurMonthData.next()) {
                curMonthData[type] = retrieveCurMonthData.getDouble(2);
                type++;
            } 

            ResultSet retrieveCurYearData = generalsStmt.executeQuery("SELECT Type, Sum(Amount) " + //
                    "FROM RECEIPT " + //
                    "WHERE YEAR(Issued_Date) = YEAR(curdate()) AND Deleted = 'NO' " + //
                    "GROUP BY Type " + //
                    "ORDER BY Type DESC;");

            type = 0;
            while (retrieveCurYearData.next()) {
                curYearData[type] = retrieveCurYearData.getDouble(2);
                type++;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] properties = {
            {"Chair", 10, 5500},
            {"Table", 5, 7700},
            {"Tent", 3, 15000},
            {"Casserole", 15, 1000},
            {"Chair", 10, 5500},
            {"Table", 5, 7700},
            {"Tent", 3, 15000},
            {"Casserole", 15, 1000}
        };
        int ctr = 0;
        try (Statement propertiesStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveProperties = propertiesStmt.executeQuery("SELECT Property_Type, Number_Of_Items, Individual_Price " + //
                    "FROM PROPERTY; ");
            ArrayList<Object[]> curProperties = new ArrayList<Object[]>();
            ctr = 0;
            while (retrieveProperties.next()) {
                ctr++;
                Object[] cur = {retrieveProperties.getString(1), retrieveProperties.getInt(2), retrieveProperties.getDouble(3)};
                curProperties.add(cur);
            }
            if (ctr > 0)
                properties = curProperties.toArray(Object[][]::new);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] expendings = {
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"},
            {"03/05/2021", "000039",  "3000", "Death of Brother"},
            {"23/05/2021", "000111", "5500", "Buying of chair"},
            {"03/05/2021", "000139", "3000", "Electrict utility"},
            {"03/05/2021", "003539", "3000", "Death of Sister"}
        };

        try (Statement expendingsStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveExpendings = expendingsStmt.executeQuery("SELECT Issued_Date, LPAD(Receipt_No, 6, '0'), Amount, Reason_for_Payment " + //
                    "FROM Receipt " + //
                    "WHERE Type = 'Expenditure' AND Deleted = 'NO' " + //
                    "ORDER BY Issued_Date DESC;");
            ArrayList<Object[]> curExpendings = new ArrayList<Object[]>();
            ctr = 0;
            while (retrieveExpendings.next()) {
                ctr++;
                Object[] cur = {retrieveExpendings.getDate(1), retrieveExpendings.getString(2), retrieveExpendings.getDouble(3), retrieveExpendings.getString(4)};
                curExpendings.add(cur);
            }
            if (ctr > 0) 
                expendings = curExpendings.toArray(Object[][]::new);
        } catch (SQLException e) {
            e.printStackTrace();
        }        

        LocalDate currentDate = LocalDate.now();
        int yearInterval = 1;

        try {
            Statement startingDateStmt = App.DATABASE_CONNECTION.createStatement();
            ResultSet startingDate = startingDateStmt.executeQuery("SELECT YEAR(Starting_Date) FROM IDIR_TABLE;");
            while (startingDate.next()) {
                yearInterval = currentDate.getYear() - startingDate.getInt(1) + 1 ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] yearlyIncomeExpendings = new Object[yearInterval*2][];
        int j = 0;
        for (int i = currentDate.getYear(); i >= currentDate.getYear()-yearInterval+1; ) {
            Object[] cur = new Object[14];
            if (j % 2 == 0) {
                cur[0] = Integer.toString(i) + " (Income)";
            } else {
                cur[0] = Integer.toString(i) + " (Expendings)";
                i--;
            }
            for (int k = 1; k < 14; k++) {
                cur[k] = 0;
            }
            yearlyIncomeExpendings[j++] = cur;
        }
        

        try (Statement reportStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveReport = reportStmt.executeQuery("Select YEAR(Issued_Date), MONTH(Issued_Date), Type, SUM(Amount) " + //
                    "From RECEIPT " + //
                    "Where Deleted = 'NO' " + //
                    "GROUP BY MONTH(Issued_Date), YEAR(Issued_Date), Type");
            
            while (retrieveReport.next()) {
                int yr = currentDate.getYear() - retrieveReport.getInt(1);
                yr *= 2;
                if (retrieveReport.getString(3).equalsIgnoreCase("Expenditure")) {
                    yr++;
                }
                int mnt = retrieveReport.getInt(2);
                if (yr < 0 || yr >= yearInterval*2) continue;
                Double amt = retrieveReport.getDouble(4);
                yearlyIncomeExpendings[yr][mnt] = amt;
                yearlyIncomeExpendings[yr][13] = Double.valueOf(yearlyIncomeExpendings[yr][13].toString()) + amt;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        queryPanel officialNamePanel = new queryPanel("Official Name", basicInfo[0].toString(), getBackground());
        queryPanel accountNoPanel = new queryPanel("Bank Account No", basicInfo[1].toString(), getBackground());
        queryPanel totalAmountPanel = new queryPanel("Total Amount", Double.toString((Double)basicInfo[2]) + " Birr", getBackground());
        queryPanel curMonthIncomePanel = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total income till date", Double.toString(curMonthData[0]) + " Birr", getBackground());
        queryPanel curMonthExpenditurePanle = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total expenditure till date", Double.toString(curMonthData[1]) + " Birr", getBackground());
        queryPanel curYearIncomePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total income till date", Double.toString(curYearData[0]) + " Birr", getBackground());
        queryPanel curYearExpenditurePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total expenditure till date", Double.toString(curYearData[1]) + " Birr", getBackground());
        
        generalData = new JPanel();
        generalData.setLayout(new BoxLayout(generalData, BoxLayout.Y_AXIS));
        generalData.setPreferredSize(new Dimension(500, 300));
        generalData.setBackground(getBackground());
        generalData.setBorder(new EmptyBorder(new Insets(10, 20, 0, 0)));

        generalData.add(officialNamePanel);
        generalData.add(accountNoPanel);
        generalData.add(totalAmountPanel);
        generalData.add(curMonthIncomePanel);
        generalData.add(curMonthExpenditurePanle);
        generalData.add(curYearIncomePanel);
        generalData.add(curYearExpenditurePanel);

        Component[] components = generalData.getComponents();
        for (Component c : components) {
            ((queryPanel)c).setAlignmentX(LEFT_ALIGNMENT);
        }

        JPanel propertiesPanel = new JPanel(new BorderLayout());
        propertiesPanel.setBackground(getBackground());
        String[] columnNames = {"Type", "Number of items", "Single Price"};
        CustomTable propertiesTable = new CustomTable(this, properties, columnNames);
        propertiesTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        JScrollPane scrollProperties = new JScrollPane(propertiesTable);
        scrollProperties.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        propertiesPanel.add(scrollProperties);


        JPanel expendingsPanel = new JPanel(new BorderLayout());
        expendingsPanel.setBackground(getBackground());
        String[] columnNames2 = {"Date", "Receipt No", "Amount", "Reason"};
        expendingsTable = new CustomTable(this, expendings, columnNames2);
        expendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        JScrollPane scrollExpendings = new JScrollPane(expendingsTable);
        scrollExpendings.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        expendingsPanel.add(scrollExpendings);
        
        JPanel yearlyReportPanel = new JPanel(new BorderLayout());
        yearlyReportPanel.setBackground(getBackground());
        String[] columnNames3 = {"Year", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Total"};
        CustomTable yearlyIncomeExpendingsTable = new CustomTable(this, yearlyIncomeExpendings, columnNames3);
        yearlyIncomeExpendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        JScrollPane scrollYearly = new JScrollPane(yearlyIncomeExpendingsTable);
        scrollYearly.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        yearlyReportPanel.add(scrollYearly);
        TableColumn column = yearlyIncomeExpendingsTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(120);
        column.setMinWidth(column.getPreferredWidth());
        
        ColoredButton paymentHistory = new ColoredButton("Members Payment History", this);
        paymentHistory.setNormalColor(new Color(147, 175, 207));
        paymentHistory.setSelectedColor(new Color(79,170,255));
        paymentHistory.setIcon(ImageIcons.reSize(ImageIcons.PAYMENT_HISTORY, 20, 20));
        this.addTab(paymentHistory, new PaymentHistoryPanel(displayPanel));

        ColoredButton writeReceipt = new ColoredButton("Write Receipt", this);
        writeReceipt.setNormalColor(new Color(147, 175, 207));
        writeReceipt.setSelectedColor(new Color(79,170,255));
        writeReceipt.setIcon(ImageIcons.reSize(ImageIcons.RECEIPT, 20, 20));
        
        JPanel footer = new JPanel();
        footer.add(paymentHistory.getWhole());
        footer.add(writeReceipt.getWhole());

        JTabbedPane tab = new JTabbedPane();
        tab.setOpaque(false);
        tab.setBackground(getBackground());
        tab.setFocusable(false);
        tab.setBorder(null);
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tab.addTab("General", generalData);
        tab.insertTab("General", onIcon, generalData, "General", 0);
        tab.insertTab("Material Assets", null, propertiesPanel, "Materials", 1);
        tab.insertTab("Expenditures", null, expendingsPanel, "Expenditures", 2);
        tab.insertTab("Yearly Report", null, yearlyReportPanel, "Yearly Report", 3);

        tab.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selected = tab.getSelectedIndex();
                for (int i = 0; i < tab.getTabCount(); i++) {
                    if (i == selected) {
                        tab.setIconAt(i, onIcon);
                    } else {
                        tab.setIconAt(i, null);
                    }
               }
            } 
        });

        this.add(tab, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }

    
    @Override
    public void showMyTab(String buttonName) {
        if (buttonName.equals("Write Receipt")) {
            App.INDIVIDUAL_RECEIPT.prepareToAddReceipt();
            displayPanel.showMyTab("individualReceipt");
        }
        displayPanel.showMyTab(buttonName);
    }

    @Override
    public void showMyTab(CustomTable table, Object[] values, int source) {
        if (table.equals(expendingsTable)) {
            App.INDIVIDUAL_RECEIPT.prepareToShow((String)values[1]);
            displayPanel.showMyTab("individualReceipt");
        }   
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
    }
    
}