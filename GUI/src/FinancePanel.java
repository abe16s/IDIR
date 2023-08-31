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

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class FinancePanel extends JPanel implements ParentPanel {

    private JPanel generalData;
    private BasePanel displayPanel;
    private CustomTable expendingsTable;
    private IndividualReceiptPanel receiptPanel;

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
            {"03/05/2021", "003539", "3000", "Death of Sister"}
        }; //Should be sorted by date when retrieved

        String[][] monthlyIncomeExpendings = {
            {"Income","10000","12000","10020","10000","12000","10020","10000","12000","10020","10000","12000","10020"},
            {"Expendings", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000", "5000","2000","3000"}
        };
        
        String[][] yearlyIncomeExpendings = {
            {"Income", "100000", "20000", "300000"},
            {"Expendings", "20000","15000", "11100"}
        };

        LocalDate currentDate = LocalDate.now();
        
        JPanel Contents = new JPanel();
        Contents.setLayout(new BoxLayout(Contents, BoxLayout.Y_AXIS));
        Contents.setBackground(getBackground());

        generalData = new JPanel();
        generalData.setLayout(new BoxLayout(generalData, BoxLayout.Y_AXIS));
        generalData.setPreferredSize(new Dimension(500, 300));
        generalData.setBackground(getBackground());
        generalData.setBorder(new EmptyBorder(new Insets(10, 20, 0, 0)));

        queryPanel officialNamePanel = new queryPanel("Official Name", officialName, Color.LIGHT_GRAY);
        queryPanel accountNoPanel = new queryPanel("Bank Account No", accountNo, Color.LIGHT_GRAY);
        queryPanel totalAmountPanel = new queryPanel("Total Amount", Double.toString(totalAmount) + " Birr", Color.LIGHT_GRAY);
        queryPanel curMonthIncomePanel = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total income till date", Double.toString(curMonthIncome) + " Birr", Color.LIGHT_GRAY);
        queryPanel curMonthExpenditurePanle = new queryPanel(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "'s total expenditure till date", Double.toString(curMonthExpenditure) + " Birr", Color.LIGHT_GRAY);
        queryPanel curYearIncomePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total income till date", Double.toString(curYearIncome) + " Birr", Color.LIGHT_GRAY);
        queryPanel curYearExpenditurePanel = new queryPanel(Integer.toString(currentDate.getYear()) + "'s total expenditure till date", Double.toString(curYearExpenditure) + " Birr", Color.LIGHT_GRAY);

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
        propertiesPanel.add(propertiesTable.getTableHeader(), BorderLayout.NORTH);
        propertiesPanel.add(propertiesTable);
        CollapsablePanel propertiesCollapse = new CollapsablePanel("Property Assests", propertiesPanel);

        JPanel expendingsPanel = new JPanel(new BorderLayout());
        expendingsPanel.setBackground(getBackground());
        String[] columnNames2 = {"Date", "Receipt No", "Amount", "Reason"};
        expendingsTable = new CustomTable(this, expendings, columnNames2);
        expendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        expendingsPanel.add(expendingsTable.getTableHeader(), BorderLayout.NORTH);
        expendingsPanel.add(expendingsTable);
        CollapsablePanel expendingsCollapse = new CollapsablePanel("Expendings", expendingsPanel);

        JPanel monthlyIncomeExpendingsPanel = new JPanel(new BorderLayout());
        monthlyIncomeExpendingsPanel.setBackground(getBackground());
        String[] columnNames3 = {"", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"};
        CustomTable monthlyIncomeExpendingsTable = new CustomTable(this, monthlyIncomeExpendings, columnNames3);
        monthlyIncomeExpendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        monthlyIncomeExpendingsPanel.add(monthlyIncomeExpendingsTable.getTableHeader(), BorderLayout.NORTH);
        monthlyIncomeExpendingsPanel.add(monthlyIncomeExpendingsTable);
        CollapsablePanel monthlyIncomeExpendingsCollapse = new CollapsablePanel("This year monthly reports", monthlyIncomeExpendingsPanel);
        
        JPanel yearlyIncomeExpendingsPanel = new JPanel(new BorderLayout());
        yearlyIncomeExpendingsPanel.setBackground(getBackground());
        String[] columnNames4 = {"", "2021", "2022", "2023"};
        CustomTable yearlyIncomeExpendingsTable = new CustomTable(this, yearlyIncomeExpendings, columnNames4);
        yearlyIncomeExpendingsTable.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);
        yearlyIncomeExpendingsPanel.add(yearlyIncomeExpendingsTable.getTableHeader(), BorderLayout.NORTH);
        yearlyIncomeExpendingsPanel.add(yearlyIncomeExpendingsTable);
        CollapsablePanel yearlyIncomeExpendingsCollapse = new CollapsablePanel("Yearly reports", yearlyIncomeExpendingsPanel);


        JPanel footer = new JPanel();
        
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

        footer.add(paymentHistory.getWhole());
        footer.add(writeReceipt.getWhole());
        
        Contents.add(generalData);
        Contents.add(propertiesCollapse);
        Contents.add(expendingsCollapse);
        Contents.add(monthlyIncomeExpendingsCollapse);
        Contents.add(yearlyIncomeExpendingsCollapse);

        Component[] contentsComponents = Contents.getComponents();
        for (Component c : contentsComponents) {
            ((JPanel)c).setAlignmentX(LEFT_ALIGNMENT);
        }

        JScrollPane ScrollContents = new JScrollPane(Contents);
        ScrollContents.setBorder(null);

        this.add(ScrollContents);
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