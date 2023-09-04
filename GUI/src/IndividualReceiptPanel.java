package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private JTextField quantity;
    private JPanel type;
    private JPanel reasonsPanel;
    private JPanel buttons;
    private ColoredButton delete;
    private IndividualReceiptPanel savedReceipt;
    private ArrayList<queryPanel> inputPanels =  new ArrayList<>();

    public IndividualReceiptPanel(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(new Insets(60, 60, 60, 60)));

        
        String nextAvailableReceipt = "001234";
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

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
        amount.adjustSize();

        reasonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reasonsPanel.setOpaque(false);
        String[] reasons = {"Monthly Payment of","Celebration dues of","Death of", "Utitlity payment for", "Buying of", "Other"};
        reason = new JComboBox<String>(reasons);
        reason.setFocusable(false);
        reason.addActionListener(new comboListener());
        String[] months = {"Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"};
        extraReason = new JComboBox<String>(months);
        extraReason.setFocusable(false);

        other = new JTextField("Reason", 15);
        other.setVisible(false);
        other.setForeground(Color.GRAY);
        other.addFocusListener(new TextFieldPlaceHolder(other.getText()));

        quantity = new JTextField("Qty",3);
        quantity.setVisible(false);
        quantity.setForeground(Color.GRAY);
        quantity.addFocusListener(new TextFieldPlaceHolder(quantity.getText()));

        reasonsPanel.add(reason);
        reasonsPanel.add(extraReason);
        reasonsPanel.add(other);
        reasonsPanel.add(quantity);

        reasonInfo = new queryPanel("Reason", "", Contents.getBackground());
        reasonInfo.setVisible(false);

        type = new JPanel();
        type.setBackground(Contents.getBackground());
        income = new JRadioButton("Income");
        expenditure = new JRadioButton("Expenditure");
        ButtonGroup moneyFor = new ButtonGroup();

        income.addActionListener(new RadioButtonListener());
        income.setFocusable(false);
        expenditure.addActionListener(new RadioButtonListener());
        expenditure.setFocusable(false);

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

        signature.add(signerName);
        signature.add(signerID);

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
                JOptionPane.showMessageDialog(displayPanel,"Saved Successfully!", "Save Receipt", JOptionPane.INFORMATION_MESSAGE);
                if (savedReceipt != null){displayPanel.remove(savedReceipt);

                }else{savedReceipt = new IndividualReceiptPanel(displayPanel);}
    
                savedReceipt.updateData(receiptNo.getInfoLabel().getText());
                addTab(save, savedReceipt);
                showMyTab(save.getName());
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

        footer.add(buttons);
        footer.add(delete.getWhole());

        JScrollPane ScrollContents = new JScrollPane(Contents);
        ScrollContents.setBorder(null);
        
        this.add(ScrollContents);
        this.add(footer, BorderLayout.SOUTH);
    } 

    public void updateData(String ReceiptNo) {
        String[] exampleReceipt = {"051222", "27/3/2023", "123", "102", "Monthly payment for Sep", "210", "Income"};//numbers payer, signer
        String issuerName = "Abebe";
        String signer = "Kebede";
        date.getInfoLabel().setText(exampleReceipt[1]);
        receiptNo.getInfoLabel().setText(ReceiptNo);
        issuedFor.getTextField().setText(issuerName);
        issuedFor.getTextField().setEditable(false);
        issuedForID.getTextField().setText(exampleReceipt[2]);
        issuedForID.getTextField().setEditable(false);
        amount.getTextField().setText(exampleReceipt[5]);
        amount.getTextField().setEditable(false);
        reasonInfo.getInfoLabel().setText(exampleReceipt[4] + " - " + exampleReceipt[6]);
        signerName.getTextField().setText(signer);
        signerName.getTextField().setEditable(false);
        signerID.getTextField().setText(exampleReceipt[3]);
        signerID.getTextField().setEditable(false);

        for (queryPanel panel : inputPanels) {
            panel.getTextField().setColumns(panel.getTextField().getText().length());
            panel.adjustSize();
        }
        reasonInfo.setVisible(true);
        delete.setVisible(true);
        type.setVisible(false);
        reasonsPanel.setVisible(false);
        buttons.setVisible(false);

        if (exampleReceipt[6].equals("Income")) {
            income.doClick();
        } else {
            expenditure.doClick();
        }
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
            quantity.setVisible(false);
            extraReason.setVisible(true);
            if (selected.equals("Monthly Payment of")) {
                String[] months = {"Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"};
                for (String mon : months) {
                    extraReason.addItem(mon);
                }
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
            } else if (selected.equals("Utitlity payment for")) {
                String[] utilities = {"Electricity", "Store rent"};
                for (String utility : utilities) {
                    extraReason.addItem(utility);
                }   
            } else if (selected.equals("Buying of")) {
                String[] properties = {"Chair", "Table","Tent", "Casserole"};
                for (String property : properties) {
                    extraReason.addItem(property);
                }
                quantity.setVisible(true);   
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
    public void showMyTab(CustomTable table, Object[] values, int source) {
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel, button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
    }
    
}