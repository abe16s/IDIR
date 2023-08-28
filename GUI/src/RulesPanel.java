package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ParentPanel;

public class RulesPanel extends JPanel implements ParentPanel{
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
    @Override
    public void showMyTab(String buttonName) {
        throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
    }

    @Override
    public void showMyTab(String[] values, int source) {
        throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        throw new UnsupportedOperationException("Unimplemented method 'addTab'");
    }

    @Override
    public void workWithFileChosen(File selectedFile) {
        throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
    }
    
}
