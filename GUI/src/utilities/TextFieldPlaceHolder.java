package GUI.src.utilities;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextFieldPlaceHolder implements FocusListener {
    private String text;
    
    public TextFieldPlaceHolder(String text) {
        this.text = text;
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        if (source.getText().equals(text)) {
            source.setText("");
            source.setForeground(Color.BLACK);
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        if (source.getText().isEmpty()) {
            source.setForeground(Color.GRAY);
            source.setText(text);
        }
    }
}