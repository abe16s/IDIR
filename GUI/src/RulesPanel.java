package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUI.src.SkeletalWindow.BasePanel;

public class RulesPanel extends JPanel{
    private JTextArea textArea;

    public RulesPanel(BasePanel displayPanel) {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        add(scrollPane, BorderLayout.CENTER);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("GUI\\src\\Sample Agenda.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
