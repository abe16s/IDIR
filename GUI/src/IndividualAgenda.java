package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 0));

        queryPanel agendaNo = new queryPanel("No", "", getBackground());
        queryPanel date = new queryPanel("Date", "", getBackground());
        queryPanel title = new queryPanel("Title", "", getBackground());
        queryPanel writer = new queryPanel("Writer", "", getBackground());

        panels.add(agendaNo);
        panels.add(date);
        panels.add(title);
        panels.add(writer);

        header.setBackground(getBackground());
        header.add(agendaNo);
        header.add(date);
        header.add(title);
        header.add(writer);
        add(header, BorderLayout.NORTH);

        readAndDisplay = new ReadAndDisplay();
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);
    }

    public void updateData(Integer agendaNo) {
        Object[] agendaInfo = { agendaNo, LocalDate.of(2023, 9, 9), "General Meeting", "Abenezer",
                "GUI\\src\\utilities\\Sample Agenda.txt" };
        try (Statement retrieveAgendaStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveReceipt = retrieveAgendaStmt.executeQuery(
                    "SELECT LPAD(max(Agenda_No) + 1, 4, '0'), Written_Date, Title, CONCAT(M.First_Name, ' ', M.Father_Name, ' ', M.Grandfather_Name) AS Writter_Name, Written_Text "
                            + //
                            "FROM AGENDA AS A JOIN MEMBER_TABLE AS M ON A.Writer = M.ID " + //
                            "WHERE Agenda_No = " + agendaNo + ";");
            while (retrieveReceipt.next()) {
                for (int i = 1; i <= agendaInfo.length; i++) {
                    if (i == 2) {
                        agendaInfo[i - 1] = retrieveReceipt.getDate(i).toLocalDate();
                        continue;
                    }
                    agendaInfo[i - 1] = retrieveReceipt.getString(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        panels.get(0).getInfoLabel().setText(agendaNo.toString());
        panels.get(1).getInfoLabel()
                .setText(((LocalDate) agendaInfo[1]).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        for (int i = 2; i < panels.size(); i++) {
            panels.get(i).getInfoLabel().setText((String) agendaInfo[i]);
        }
        readAndDisplay.setText("");
        readAndDisplay.readFile((String) agendaInfo[4]);
    }
}
