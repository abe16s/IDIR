package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ReadAndDisplay;

public class Help extends JPanel {

    public Help(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(228, 228, 228));
        setBorder(new EmptyBorder(new Insets(5, 10, 0, 0)));

        ReadAndDisplay readAndDisplay = new ReadAndDisplay();
        readAndDisplay.readFile("GUI\\src\\utilities\\Sample Help.txt");
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);
    }
}
