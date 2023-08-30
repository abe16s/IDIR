package GUI.src.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReadAndDisplay extends JTextArea {
    private JScrollPane scrollPane;

    public ReadAndDisplay(String file) {
        setEditable(false);
        scrollPane = new JScrollPane(this);
        scrollPane.setBorder(null);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                this.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JScrollPane getWhole() {
        return scrollPane;
    }
        
}
