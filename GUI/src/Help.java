package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ReadAndDisplay;

public class Help extends JPanel {

    public Help(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(228, 228, 228));
        
        ReadAndDisplay readAndDisplay = new ReadAndDisplay("GUI\\src\\utilities\\Sample Help.txt");
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);        
    }
}
