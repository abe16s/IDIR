package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ReadAndDisplay;
import GUI.src.utilities.queryPanel;

public class IndividualAgenda extends JPanel {
    private ReadAndDisplay readAndDisplay;
    ArrayList<queryPanel> panels = new ArrayList<>();

    public IndividualAgenda(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(228, 228, 228));
        setBorder(new EmptyBorder(new Insets(5, 10, 0, 0)));
        
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT,40,0));
        
        queryPanel agendaNo = new queryPanel("No", "", getBackground());
        queryPanel date = new queryPanel("Date", "", getBackground());
        queryPanel title = new queryPanel("Title", "", getBackground());
        queryPanel writer = new queryPanel("Writer","", getBackground());

        panels.add(agendaNo);
        panels.add(date);
        panels.add(title);
        panels.add(writer);

        header.setBackground(getBackground());
        header.add(agendaNo);
        header.add(date);
        header.add(title);
        header.add(writer);
        add(header,BorderLayout.NORTH);

        readAndDisplay = new ReadAndDisplay();
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);
    }

    public void updateData(Integer agendaNo) {
        Object[] exampleAgenda = {agendaNo, "20/7/2023", "General Meeting", "Abenezer", "GUI\\src\\utilities\\Sample Agenda.txt"};
        panels.get(0).getInfoLabel().setText(agendaNo.toString());
        for (int i = 1; i < panels.size(); i++) {
            panels.get(i).getInfoLabel().setText((String)exampleAgenda[i]);
        }
        readAndDisplay.setText("");
        readAndDisplay.readFile((String)exampleAgenda[4]);
    }
}


