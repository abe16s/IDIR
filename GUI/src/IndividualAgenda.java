package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.queryPanel;

public class IndividualAgenda extends JPanel {
    private JTextArea textArea;

    public IndividualAgenda(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT,40,0));

        queryPanel agendaNo = new queryPanel("No", "1", getBackground());

        queryPanel date = new queryPanel("Date", "20/7/2023", getBackground());

        queryPanel title = new queryPanel("Title", "General Meeting", getBackground());

        queryPanel writer = new queryPanel("Writer","Abenezer", getBackground());

        p.setBackground(Color.WHITE);
        p.add(agendaNo);
        p.add(date);
        p.add(title);
        p.add(writer);
        add(p,BorderLayout.NORTH);

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

    public void updateData(String string) {
    }
}



