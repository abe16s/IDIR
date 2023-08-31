package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ReadAndDisplay;
import GUI.src.utilities.queryPanel;

public class IndividualAgenda extends JPanel {

    public IndividualAgenda(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(228, 228, 228));
        
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT,40,0));
        
        queryPanel agendaNo = new queryPanel("No", "1", getBackground());
        queryPanel date = new queryPanel("Date", "20/7/2023", getBackground());
        queryPanel title = new queryPanel("Title", "General Meeting", getBackground());
        queryPanel writer = new queryPanel("Writer","Abenezer", getBackground());

        header.setBackground(getBackground());
        header.add(agendaNo);
        header.add(date);
        header.add(title);
        header.add(writer);
        add(header,BorderLayout.NORTH);

        ReadAndDisplay readAndDisplay = new ReadAndDisplay("GUI\\src\\utilities\\Sample Agenda.txt");
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);
    }

    public void updateData(String string) {
    }
}



