package GUI.src.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReadAndDisplay extends JTextArea {
    private JScrollPane scrollPane;

    public ReadAndDisplay() {
        setEditable(false);
        setLineWrap(true); 
        setWrapStyleWord(true);
        scrollPane = new JScrollPane(this);
        scrollPane.setBorder(null);
    }

    public JScrollPane getWhole() {
        return scrollPane;
    }

    public void readFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCaretPosition(0);
    }
        
}
