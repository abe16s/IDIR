package GUI.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;
import GUI.src.utilities.TransparentButton;

public class HomePage extends JPanel implements ParentPanel{
    private JLabel background = new JLabel(ImageIcons.reSize(ImageIcons.LOGObackground, 200, 200));
    private  JLabel welcome = new JLabel("Welcome");
    private ColoredButton paymentHistory = new ColoredButton("Payment history",this);
    private ColoredButton writeReceipt = new ColoredButton("Write Receipt",this);
    private ColoredButton AddMember = new ColoredButton("Add Member",this);
    private ArrayList<TransparentButton> affectedButtons = new ArrayList<>();
    
    private SkeletalWindow windowPanel;
    
    public HomePage (SkeletalWindow windowPanel){
        this.windowPanel = windowPanel;
        setBackground(new Color(228,228,228));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(50));

        background.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(background);
        add(Box.createVerticalStrut(50));

        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setFont(new Font("Serif", Font.BOLD, 30));
        add(welcome);
        add(Box.createVerticalStrut(40));
    
        paymentHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        paymentHistory.setSelectedColor(new Color(79,170,255));
        paymentHistory.setNormalColor(new Color(147,175,207));
        add(paymentHistory.getWhole());
        addTab(paymentHistory, new PaymentHistoryPanel(this.windowPanel.getBasePanel()));
        paymentHistory.addActionListener(new changeButtonEffect("Financial Data"));

        writeReceipt.setAlignmentX(Component.LEFT_ALIGNMENT);
        writeReceipt.setSelectedColor(new Color(79,170,255));
        writeReceipt.setNormalColor(new Color(147,175,207));
        add(writeReceipt.getWhole());
        addTab(writeReceipt, new IndividualReceiptPanel(this.windowPanel.getBasePanel()));
        writeReceipt.addActionListener(new changeButtonEffect("Financial Data"));

        AddMember.setAlignmentX(Component.LEFT_ALIGNMENT);
        AddMember.setSelectedColor(new Color(79,170,255));
        AddMember.setNormalColor(new Color(147,175,207));
        add(AddMember.getWhole());
        addTab(AddMember, new AddMemberPanel(this.windowPanel.getBasePanel()));
        AddMember.addActionListener(new changeButtonEffect("Members"));
        
        setVisible(true);
    }  

    public void giveAffectedButtons(TransparentButton btn) {
        this.affectedButtons.add(btn);
    }

    private class changeButtonEffect implements ActionListener{
        private String btn;

        public changeButtonEffect(String btn) {
            this.btn = btn;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            for (TransparentButton transparentButton: affectedButtons) {
                if (transparentButton.getName().equalsIgnoreCase(this.btn)) {
                    windowPanel.getMenu().prepare(btn);
                    transparentButton.setIcon(transparentButton.getSelectedIcon());
                    transparentButton.setSelected(true);
                }
            }
        }

    }

    @Override
    public void showMyTab(String buttonName) {
        windowPanel.getBasePanel().showMyTab(buttonName);
    }

    @Override
    public void showMyTab(CustomTable table, String[] values, int source) {

    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        windowPanel.getBasePanel().addMyTab(clickedPanel,button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
    }
}