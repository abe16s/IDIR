package GUI.src.utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;


public class queryPanel extends JPanel{
    /*
     * This custom panel is to be used in the adding members and showing individual members
     * It has two constructors / two versions for receiving and showing information
     * i. for creating query panel with caption label and a text field
     * ii. for showing information with caption and info to be showed labels
     */
    
    private JLabel caption;
    private JTextField captionField;
    private JLabel info;

    public queryPanel(String captionText, Color color) {    
        this.caption = new JLabel(captionText+":");
        this.caption.setOpaque(true);
        this.caption.setBackground(color);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);
        this.add(this.caption);
        this.add(new JLabel("   "));
        this.setBorder(new EmptyBorder(8, 0, 8, 0));
    }

    public queryPanel(String captionText, int columns, Color color) {
        this(captionText, color);
        this.captionField = new JTextField(); 
        this.captionField.setBorder(null);
        this.add(this.captionField);
        // this.captionField.setPreferredSize(new Dimension(this.captionField.getPreferredSize().width, this.captionField.getPreferredSize().height+5));
        // this.captionField.setMaximumSize(this.captionField.getPreferredSize());
    }

    public queryPanel(String captionText, String info, Color color) {
        this(captionText, color);
        this.info = new JLabel(info);
        this.info.setOpaque(true);
        this.info.setBackground(Color.WHITE);
        this.add(this.info);
    }

    public JTextField getTextField() {
        return this.captionField;
    }

    public JLabel getInfoLabel() {
        return this.info;
    }

    public JLabel getCaption() {
        return this.caption;
    }
}
