package GUI.src.utilities;

import javax.swing.*;

import java.awt.*;


public class queryPanel extends JPanel{
    private JLabel caption;
    private JTextField captionField;
    private JLabel info;

    public queryPanel(String captionText, int columns, Color color, JPanel parent) {
        this.prepare(captionText, color, parent);;
        this.captionField = new JTextField(columns); 
        this.captionField.setBorder(null);
        this.add(this.captionField, BorderLayout.EAST);
    }

    public queryPanel(String captionText, String info, Color color, JPanel parent) {
        this.prepare(captionText, color, parent);;
        this.info = new JLabel(info);
        this.add(this.info, BorderLayout.EAST);
    }

    private void prepare(String captionText, Color color, JPanel parent) {    
        this.caption = new JLabel(captionText+":");
        this.caption.setOpaque(true);
        this.caption.setBackground(color);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.add(this.caption, BorderLayout.WEST);
        this.setPreferredSize(new Dimension(parent.getPreferredSize().width, this.getPreferredSize().height+5));
    }
}
