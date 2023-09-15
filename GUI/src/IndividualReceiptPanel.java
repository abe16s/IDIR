package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.*;

public class IndividualReceiptPanel extends JPanel implements ParentPanel {
    private BasePanel displayPanel;
    private queryPanel date; 
    private queryPanel receiptNo; 
    private JRadioButton income;
    private JRadioButton expenditure;
    private queryPanel issuedFor;
    private queryPanel issuedForID;
    private queryPanel amount;
    private queryPanel signerName; 
    private queryPanel signerID;
    private JPanel body;
    private JComboBox<String> reason;
    private JComboBox<String> extraReason;
    private queryPanel reasonInfo;
    private JTextField other;
    private queryPanel quantity;
    private JComboBox<String> chooseYear;
    private JPanel type;
    private JPanel reasonsPanel;
    private JPanel buttons;
    private ColoredButton delete;
    private ArrayList<queryPanel> inputPanels =  new ArrayList<>();
    private String nextAvailableReceipt;
    private String formattedDate;
    private ButtonGroup moneyFor;
    private String deleteReason;
    private ArrayList<queryPanel> required = new ArrayList<>();
    private boolean insertionError = false;

    public IndividualReceiptPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(new Insets(60, 60, 60, 60)));

        
        nextAvailableReceipt = "000001";
        LocalDate currentDate = LocalDate.now();
        formattedDate = currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

        RoundedPanel Contents = new RoundedPanel();
        Contents.setLayout(new BorderLayout());
        Contents.setBackground(new Color(217, 217, 217));
        Contents.setBorder(new EmptyBorder(new Insets(20, 20, 20, 50)));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Contents.getBackground());

        date = new queryPanel("Date", formattedDate, Contents.getBackground());
        receiptNo = new queryPanel("Receipt No", nextAvailableReceipt, Contents.getBackground());

        header.add(date);
        header.add(receiptNo);

        Component[] components = header.getComponents();
        for (Component c : components) {
            ((queryPanel)c).setAlignmentX(RIGHT_ALIGNMENT);
        }

        body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(Contents.getBackground());
        body.setBorder(new EmptyBorder(new Insets(30, 50, 0, 0)));

        issuedFor = new queryPanel("Payer Name", 20, Contents.getBackground());
        issuedForID = new queryPanel("Payer ID", 6, Contents.getBackground());
        amount = new queryPanel("Total Amount", 10, Contents.getBackground());

        issuedFor.adjustSize();
        issuedForID.adjustSize();
        issuedForID.setNumeric();
        required.add(issuedForID);
        amount.adjustSize();
        amount.setNumeric();
        required.add(amount);

        reasonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reasonsPanel.setOpaque(false);
        String[] reasons = {"Monthly Payment of","Celebration dues of","Death of", "Utitlity Payment for", "Buying of", "Other"};
        reason = new JComboBox<String>(reasons);
        reason.setFocusable(false);
        reason.addActionListener(new comboListener());
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        extraReason = new JComboBox<String>(months);
        extraReason.setSelectedIndex(LocalDate.now().getMonthValue()-1);
        extraReason.setFocusable(false);

        other = new JTextField("Reason", 15);
        other.setVisible(false);
        other.setForeground(Color.GRAY);
        other.addFocusListener(new TextFieldPlaceHolder(other.getText()));

        quantity = new queryPanel("Quantity", 3, getBackground());
        quantity.adjustSize();       
        quantity.setNumeric(); 
        quantity.getTextField().setVisible(false);
        quantity.getTextField().setText("Qty");
        quantity.getTextField().setForeground(Color.GRAY);
        quantity.getTextField().addFocusListener(new TextFieldPlaceHolder(quantity.getTextField().getText()));

        Integer curYear = currentDate.getYear();
        chooseYear =  new JComboBox<>();    
        chooseYear.addItem(Integer.toString(curYear-1));
        chooseYear.addItem(Integer.toString(curYear));
        chooseYear.addItem(Integer.toString(curYear+1));   
        chooseYear.setSelectedIndex(1);
        chooseYear.setFocusable(false);

        reasonsPanel.add(reason);
        reasonsPanel.add(extraReason);
        reasonsPanel.add(other);
        reasonsPanel.add(quantity.getTextField());
        reasonsPanel.add(chooseYear);

        reasonInfo = new queryPanel("Reason", "", Contents.getBackground());
        reasonInfo.setVisible(false);

        type = new JPanel();
        type.setBackground(Contents.getBackground());
        income = new JRadioButton("Income");
        expenditure = new JRadioButton("Expenditure");
        moneyFor = new ButtonGroup();

        income.addActionListener(new RadioButtonListener());
        income.setFocusable(false);
        income.setActionCommand("Income");
        expenditure.addActionListener(new RadioButtonListener());
        expenditure.setFocusable(false);
        expenditure.setActionCommand("Expenditure");

        income.setSelected(true);

        moneyFor.add(income);
        moneyFor.add(expenditure);
        type.add(income);
        type.add(expenditure);

        JPanel signature = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signature.setBackground(Contents.getBackground());

        signerName = new queryPanel("Issued By", 20, Contents.getBackground());
        signerID = new queryPanel("ID", 5, Contents.getBackground());

        signerName.adjustSize();
        signerID.adjustSize();
        required.add(signerID);

        signature.add(signerName);
        signature.add(signerID);
        
        inputPanels.add(issuedFor);
        inputPanels.add(issuedForID);
        inputPanels.add(amount);
        inputPanels.add(signerName);
        inputPanels.add(signerID);

        body.add(issuedFor);
        body.add(issuedForID);
        body.add(amount);
        body.add(reasonsPanel);
        body.add(reasonInfo);
        body.add(type);
        body.add(Box.createVerticalGlue());
        body.add(signature);

        Component[] bodyComponents = body.getComponents();
        for (Component c : bodyComponents) {
            try{
                ((JPanel)c).setAlignmentX(LEFT_ALIGNMENT);
            } catch(Exception e) {};
         }        

        Contents.add(header, BorderLayout.NORTH);
        Contents.add(body, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttons = new JPanel();

        ColoredButton save = new ColoredButton("Save", buttons);
        save.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 20, 20));
        save.setNormalColor(new Color(147, 175, 207));
        save.setSelectedColor(new Color(79,170,255));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBeforeInsert()) {
                    insertReceipt();
                    if (insertionError) {
                        JOptionPane.showMessageDialog(displayPanel,"Check if the specified IDs correctly represent members and also enter other values correctly!", "Check Inputs", JOptionPane.INFORMATION_MESSAGE);
                        insertionError = false;
                    } else {
                        customTrigger();
                        JOptionPane.showMessageDialog(displayPanel,"Saved Successfully!", "Save Receipt", JOptionPane.INFORMATION_MESSAGE);
                        App.INDIVIDUAL_RECEIPT.prepareToShow(receiptNo.getInfoLabel().getText());
                    }
                }     
            }
        });

        ColoredButton discard = new ColoredButton("Discard", buttons);
        discard.setIcon(ImageIcons.reSize(ImageIcons.DISCARD_RECEIPT, 20, 20));
        discard.setNormalColor(new Color(147, 175, 207));
        discard.setSelectedColor(new Color(79,170,255));
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(displayPanel,"This will discard every unsaved changes. Do you wish to continue?", "Discard Changes", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    displayPanel.showMyTab("Financial data");
                }
            } 
        });

        buttons.add(save.getWhole());
        buttons.add(discard.getWhole());

        delete = new ColoredButton("Delete", footer);
        delete.setIcon(ImageIcons.reSize(ImageIcons.DISCARD_RECEIPT, 20, 20));
        delete.setNormalColor(new Color(147, 175, 207));
        delete.setSelectedColor(new Color(79,170,255));
        delete.setVisible(false);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new reasonForDelete();
            }
        });

        footer.add(buttons);
        footer.add(delete.getWhole());

        JScrollPane ScrollContents = new JScrollPane(Contents);
        ScrollContents.setBorder(null);
        
        this.add(ScrollContents);
        this.add(footer, BorderLayout.SOUTH);
    } 

    public void prepareToShow(String ReceiptNo) {
        Object[] receiptInfo = {LocalDate.of(2023, 9, 9), ReceiptNo, "0102", "Abebe", 5000.0, "Monthly payment for Sep 2023", "Income", "0321", "Kebede"};
        try (Statement retrieveReceiptStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveReceipt = retrieveReceiptStmt.executeQuery("SELECT r.Issued_Date, LPAD(r.Receipt_No, 6, '0'), LPAD(r.Issued_For, 4, '0') AS Formatted_Issued_For, CONCAT(fi.First_Name, ' ', fi.Father_Name, ' ', fi.Grandfather_Name) AS Issuer_Name,  " + //
                    "r.Amount, r.Reason_for_Payment, r.Money_Type, LPAD(r.Issued_By, 4, '0') AS Formatted_Issued_By, CONCAT(fb.First_Name, ' ', fb.Father_Name, ' ', fb.Grandfather_Name) AS Signer_Name " + //
                    "FROM RECEIPT AS r JOIN MEMBER_TABLE AS fi ON r.Issued_For = fi.ID JOIN MEMBER_TABLE AS fb ON r.Issued_By = fb.ID " + //
                    "WHERE r.Receipt_No = " + ReceiptNo + ";");
            while (retrieveReceipt.next()) {
                for(int i = 1; i <= receiptInfo.length; i++) {
                    if (i == 1) {receiptInfo[i-1] = retrieveReceipt.getDate(i).toLocalDate(); continue;}                    
                    if (i == 5) {receiptInfo[i-1] = retrieveReceipt.getDouble(i); continue;}
                    receiptInfo[i-1] = retrieveReceipt.getString(i);
                }
            }     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        date.getInfoLabel().setText(((LocalDate)receiptInfo[0]).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        receiptNo.getInfoLabel().setText((String) receiptInfo[1]);
        issuedFor.getTextField().setText((String) receiptInfo[3]);
        issuedForID.getTextField().setText((String) receiptInfo[2]);
        amount.getTextField().setText(Double.toString((double) receiptInfo[4]));
        reasonInfo.getInfoLabel().setText(receiptInfo[5] + " - " + receiptInfo[6]);
        signerName.getTextField().setText((String) receiptInfo[8]);
        signerID.getTextField().setText((String) receiptInfo[7]);

        for (queryPanel panel : inputPanels) {
            panel.getTextField().setColumns(panel.getTextField().getText().length());
            panel.getTextField().setBackground(Color.WHITE);
            panel.getTextField().setPreferredSize(new Dimension(panel.getTextField().getPreferredSize().width, 20));
            panel.getTextField().setMaximumSize(panel.getTextField().getPreferredSize());
            panel.getTextField().setEditable(false);
        }
        reasonInfo.setVisible(true);
        delete.setVisible(true);
        type.setVisible(false);
        reasonsPanel.setVisible(false);
        buttons.setVisible(false);

        if (receiptInfo[6].equals("Income")) {
            income.doClick();
        } else {
            expenditure.doClick();
        }
    }

    public void prepareToAddReceipt() {
        date.getInfoLabel().setText(formattedDate);
        setNextAvailableReceipt();
        receiptNo.getInfoLabel().setText(nextAvailableReceipt);

        for (queryPanel panel : inputPanels) {
            panel.getTextField().setText("");
            panel.getTextField().setEditable(true);
            panel.adjustSize();
        }

        reasonInfo.setVisible(false);
        delete.setVisible(false);
        type.setVisible(true);
        reasonsPanel.setVisible(true);
        buttons.setVisible(true);

        income.doClick();
    }

    private class RadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JRadioButton)e.getSource()).equals(income)) {
                issuedFor.getCaption().setText("Payer Name:");
                issuedForID.getCaption().setText("Payer ID:");
            } else if (((JRadioButton)e.getSource()).equals(expenditure)) {
                issuedFor.getCaption().setText("Receiver Name:");
                issuedForID.getCaption().setText("Receiver ID:");
            }
        }

    } 

    private class comboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = (String)((JComboBox<String>)e.getSource()).getSelectedItem();
            extraReason.removeAllItems();
            other.setVisible(false);
            quantity.getTextField().setVisible(false);
            extraReason.setVisible(true);
            chooseYear.setVisible(false);
            if (selected.equals("Monthly Payment of")) {
                String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                for (String mon : months) {
                    extraReason.addItem(mon);
                }
                chooseYear.setVisible(true);
                extraReason.setSelectedIndex(LocalDate.now().getMonthValue()-1);
            } else if (selected.equals("Celebration dues of")) {
                String[] exReason = {"Wedding", "Graduation"};
                for (String reason : exReason) {
                    extraReason.addItem(reason);
                }
            } else if (selected.equals("Death of")) {
                String[] families = {"Mother", "Father", "Spouse","Son","Daughter","Brother", "Sister", "Mother-in-law", "Father-in-Law","Brother-in-law","Sister-in-law"};
                for (String family : families) {
                    extraReason.addItem(family);
                }
            } else if (selected.equals("Utitlity Payment for")) {
                String[] utilities = {"Electricity", "Store rent"};
                for (String utility : utilities) {
                    extraReason.addItem(utility);
                }   
            } else if (selected.equals("Buying of")) {
                String[] properties = {"Chair", "Table","Tent", "Casserole", "Desktop"};
                for (String property : properties) {
                    extraReason.addItem(property);
                }
                quantity.getTextField().setVisible(true);   
            } else if (selected.equals("Other")){
                other.setVisible(true);
                extraReason.setVisible(false);
            }
        }

    } 
    @Override
    public void showMyTab(String buttonName) {
        displayPanel.showMyTab(buttonName);
    }

    @Override
    public void showMyTab(CustomTable table,int selectedRow, int selectedColumn) {
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel, button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
    }

    private class reasonForDelete extends JDialog {
        public reasonForDelete() {
            super(null, "Reason for Deletion",JDialog.DEFAULT_MODALITY_TYPE);
            setIconImage(ImageIcons.NOTE.getImage());
            setLayout(new FlowLayout());

            queryPanel reasonDel = new queryPanel("Reason", 30, getBackground());
            reasonDel.adjustSize();

            ColoredButton ok = new ColoredButton("Ok", null);
            ok.setNormalColor(new Color(147, 175, 207));
            ok.setSelectedColor(new Color(79,170,255));
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteReason = reasonDel.getTextField().getText();
                    deleteReceipt();
                    dispose();
                }
            });
            
            add(reasonDel);
            add(ok.getWhole());

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();

            setLocationRelativeTo(displayPanel);
            setVisible(true);
        }
    }

    private void setNextAvailableReceipt() {
        try {
            Statement nextAvailableReceiptStmt = App.DATABASE_CONNECTION.createStatement();
            ResultSet startingDate = nextAvailableReceiptStmt.executeQuery("select LPAD(max(Receipt_No) + 1, 6, '0') AS nextAvailableReceiptNo FROM RECEIPT;");
            while (startingDate.next()) {
                if (startingDate.getString(1) != null)
                    nextAvailableReceipt = startingDate.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertReceipt() {
        String[] values = {"curdate()", "DEFAULT", "DEFAULT", "DEFAULT", "DEFAULT", "DEFAULT"};
        String rsn = "";
        for (Component cmpt : reasonsPanel.getComponents()) {
            String temp;
            if (!cmpt.isVisible()) continue;
            try {
                temp = (String) ((JComboBox)cmpt).getSelectedItem();
            } catch (Exception e) {
                temp = "- " + ((JTextField)cmpt).getText();
            }
            if (rsn != "") rsn += " ";
            rsn += temp;
        }
        values[1] = issuedForID.getTextField().getText();
        values[2] = amount.getTextField().getText();
        values[3] = "'" + rsn + "'";
        values[4] = "'" + moneyFor.getSelection().getActionCommand() + "'";
        values[5] = signerID.getTextField().getText();
        String query = "INSERT INTO RECEIPT (Issued_Date, Issued_For, Amount, Reason_For_Payment, Money_Type, Issued_By) VALUES (";
        for (int i = 0; i < values.length; i++) {
            if (i > 0) query += ", ";
            query += values[i];
        }
        query += ");";
        try (Statement insertStmt = App.DATABASE_CONNECTION.createStatement()) {
            insertStmt.executeUpdate(query);
        } catch (SQLException e) {
            insertionError = true;
            e.printStackTrace();
        }
    }

    private void customTrigger() {
        String query = "";
        if (reason.getSelectedItem().equals("Monthly Payment of")) {
            query += "UPDATE MONTHLY_PAYMENT_HISTORY SET ";
            String mon = (String) extraReason.getSelectedItem();
            if (mon.equals("Dec")) 
                mon = '`' + mon + '`';
            query += (mon + " = " + amount.getTextField().getText() + " WHERE ID = " + issuedForID.getTextField().getText() + " AND yr = " + chooseYear.getSelectedItem() + ";");
            try (Statement updateHistory = App.DATABASE_CONNECTION.createStatement()) {
                updateHistory.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (reason.getSelectedItem().equals("Buying of")) {
            try (Statement updateProperties = App.DATABASE_CONNECTION.createStatement()) {
                // check if property already exists
                ResultSet retrieveProperties = updateProperties.executeQuery("SELECT count(*)  " + //
                        "FROM PROPERTY " + //
                        "WHERE Property_Type = '" + extraReason.getSelectedItem() + "';");
                int ctr = 0;
                if (retrieveProperties.next()) {
                    ctr = retrieveProperties.getInt(1);}
                String individualPrice = String.format("%.2f", Double.parseDouble(amount.getTextField().getText()) / Double.parseDouble(quantity.getTextField().getText()));
                if (ctr == 0) {
                    updateProperties.executeUpdate("INSERT INTO PROPERTY(Property_Type, Number_Of_Items, Individual_Price) " + //
                            "VALUES ('" + extraReason.getSelectedItem()+ "', " + quantity.getTextField().getText() + ", " + individualPrice + ");");
                } else if (ctr > 0) {
                    updateProperties.executeUpdate("UPDATE PROPERTY " + //
                            "SET Number_Of_Items = Number_Of_Items + " + quantity.getTextField().getText() + ", Individual_Price = " + individualPrice + //
                            " WHERE Property_Type = '" + extraReason.getSelectedItem() + "';");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    private void deleteReceipt() {
        try (Statement deleteStmt = App.DATABASE_CONNECTION.createStatement()) {
            deleteStmt.executeUpdate("UPDATE RECEIPT " + //
                    "SET Deleted = '" + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + " - " + deleteReason + "' " + //
                    "WHERE Receipt_No = " + receiptNo.getInfoLabel().getText() + ";");

            String[] rsn = reasonInfo.getInfoLabel().getText().split(" ");
            if (rsn[0].equals("Monthly")) {
                String query = "";
                query += "UPDATE MONTHLY_PAYMENT_HISTORY SET ";
                String mon = rsn[3];
                if (mon.equals("Dec")) 
                    mon = '`' + mon + '`';
                query += (mon + " = " + "0" + " WHERE ID = " + issuedForID.getTextField().getText() + " AND yr = " + rsn[4] + ";");
                deleteStmt.executeUpdate(query);
            } else if (rsn[0].equals("Buying")) {
                deleteStmt.executeUpdate("UPDATE PROPERTY " + //
                            "SET Number_Of_Items = Number_Of_Items - " + rsn[3] +  //
                            " WHERE Property_Type = '" + rsn[2] + "';");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkBeforeInsert() {
        for (queryPanel queryPanel : required) {
            if (queryPanel.getTextField().getText().equals("")) {
                JOptionPane.showMessageDialog(displayPanel,"Please Enter " + queryPanel.getCaption().getText() + " Value!", "Required", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }

        if ((other.isVisible() && other.getText().equals("Reason")) || (quantity.getTextField().isVisible() && quantity.getTextField().getText().equals("Qty"))) {
            JOptionPane.showMessageDialog(displayPanel,"Please Enter All Reason Fields!", "Required", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void refresh() {
        
    }
}