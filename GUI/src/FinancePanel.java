package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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
    private IndividualReceiptPanel receiptPanel;
    private Icon onIcon = ImageIcons.reSize(ImageIcons.ON,15,15);

    public FinancePanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        this.receiptPanel = new IndividualReceiptPanel(displayPanel);
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

        String[][] properties = {
            {"Chair", "10", "5500"},
            {"Table", "5", "7700"},
            {"Tent", "3", "15000"},
            {"Casserole", "15", "1000"},
            {"Chair", "10", "5500"},
            {"Table", "5", "7700"},
            {"Tent", "3", "15000"},
            {"Casserole", "15", "1000"}
        };

        String[][] expendings = {
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
        }; //Should be sorted by date when retrieved

        String[][] yearlyIncomeExpendings = {
            {"2023 (Income)","10000","12000","10020","10000","12000","10020","10000","12000","10020","10000","12000","10020", "150,000"},
            {"2023 (Expendings)", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "30,000"},
            {"2022 (Income)","10000","12000","10020","10000","12000","10020","10000","12000","10020","10000","12000","10020", "150,000"},
            {"2022 (Expendings)", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "30,000"},
            {"2021 (Income)","10000","12000","10020","10000","12000","10020","10000","12000","10020","10000","12000","10020", "150,000"},
            {"2021 (Expendings)", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "30,000"},
            {"2020 (Income)","10000","12000","10020","10000","12000","10020","10000","12000","10020","10000","12000","10020", "150,000"},
            {"2020 (Expendings)", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "30,000"},
        };
        

        LocalDate currentDate = LocalDate.now();

        queryPanel officialNamePanel = new queryPanel("Official Name", officialName, getBackground());
        queryPanel accountNoPanel = new queryPanel("Bank Account No", accountNo, getBackground());
        queryPanel totalAmountPanel = new queryPanel("Total Amount", Double.toString(totalAmount) + " Birr", getBackground());
        queryPanel curMonthIncomePanel = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total income till date", Double.toString(curMonthIncome) + " Birr", getBackground());
        queryPanel curMonthExpenditurePanle = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total expenditure till date", Double.toString(curMonthExpenditure) + " Birr", getBackground());
        queryPanel curYearIncomePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total income till date", Double.toString(curYearIncome) + " Birr", getBackground());
        queryPanel curYearExpenditurePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total expenditure till date", Double.toString(curYearExpenditure) + " Birr", getBackground());
        
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
        String[] columnNames3 = {"Year", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Total"};
        CustomTable yearlyIncomeExpendingsTable = new CustomTable(this, yearlyIncomeExpendings, columnNames3);
        yearlyIncomeExpendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        JScrollPane scrollYearly = new JScrollPane(yearlyIncomeExpendingsTable);
        scrollYearly.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        yearlyReportPanel.add(scrollYearly);
        TableColumn column = yearlyIncomeExpendingsTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(120);
        column.setMinWidth(column.getPreferredWidth());
        
        ColoredButton paymentHistory = new ColoredButton("Members Payment History", this.displayPanel);
        paymentHistory.setNormalColor(new Color(147, 175, 207));
        paymentHistory.setSelectedColor(new Color(79,170,255));
        paymentHistory.setIcon(ImageIcons.reSize(ImageIcons.PAYMENT_HISTORY, 20, 20));
        this.addTab(paymentHistory, new PaymentHistoryPanel(displayPanel));

        ColoredButton writeReceipt = new ColoredButton("Write Receipt", this.displayPanel);
        writeReceipt.setNormalColor(new Color(147, 175, 207));
        writeReceipt.setSelectedColor(new Color(79,170,255));
        writeReceipt.setIcon(ImageIcons.reSize(ImageIcons.RECEIPT, 20, 20));
        this.addTab(writeReceipt, new IndividualReceiptPanel(displayPanel));
        
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
    }

    @Override
    public void showMyTab(CustomTable table, Object[] values, int source) {
        if (table.equals(expendingsTable)) {
            displayPanel.remove(receiptPanel);
            receiptPanel.updateData((String)values[1]);
            displayPanel.addMyTab(receiptPanel,(String)values[1]);
            displayPanel.showMyTab((String)values[1]);
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