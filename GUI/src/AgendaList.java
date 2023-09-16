package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ColoredButton;
import GUI.src.utilities.ColoredFileChooser;
import GUI.src.utilities.CustomTable;
import GUI.src.utilities.ImageIcons;
import GUI.src.utilities.ParentPanel;
import GUI.src.utilities.queryPanel;

public class AgendaList extends JPanel implements ParentPanel {

    private BasePanel displayPanel;
    private IndividualAgenda individualAgenda;
    private CustomTable agendaList;
    private ColoredButton addAgenda;
    String[] agendaColumns = { "No", "Date", "Title", "Meeting chair" };

    // example Data
    Object[][] agendas = {
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
            { 1, "20/7/2023", "General Meeting", "Abenezer" },
            { 2, "20/7/2023", "General Meeting", "Abenezer" },
            { 3, "20/7/2023", "General Meeting", "Abenezer" },
    };

    public AgendaList(BasePanel displayPanel) {
        this.displayPanel = displayPanel;
        individualAgenda = new IndividualAgenda(displayPanel);

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 228));

        agendaList = new CustomTable(this, agendas, agendaColumns);
        agendaList.setAlternatingColor(Color.LIGHT_GRAY, new Color(228, 228, 228), Color.WHITE);

        JScrollPane ScrollList = new JScrollPane(agendaList);
        ScrollList.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));

        JPanel addBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addBar.setBackground(new Color(241, 241, 241));

        addAgenda = new ColoredButton("Add Agenda", this);
        addAgenda.setIcon(ImageIcons.reSize(ImageIcons.NOTEdark, 20, 20));
        addAgenda.setNormalColor(new Color(147, 175, 207));
        addAgenda.setSelectedColor(new Color(79, 170, 255));

        addBar.add(addAgenda.getWhole());

        this.add(addBar, BorderLayout.SOUTH);
        this.add(ScrollList, BorderLayout.CENTER);

        refresh();
    }

    public class AddAgenda extends JDialog {

        private AgendaList parent;

        public AddAgenda(AgendaList agendaList, ImageIcon icon) {

            super(null, "Add Agenda", JDialog.DEFAULT_MODALITY_TYPE);

            setIconImage(icon.getImage());
            setLayout(new BorderLayout());

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = (int) ((int) screenSize.getHeight() * 0.35);
            int width = (int) ((int) screenSize.getWidth() * 0.40);
            setPreferredSize(new Dimension(width, height));

            add(new inputPanel(this));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();

            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void report(File fileChosen) {
            setVisible(false);
            if (fileChosen != null)
                parent.workWithFileChosen(fileChosen);
        }

        private class inputPanel extends JPanel implements ParentPanel {
            private AddAgenda parent;
            private File fileChosen;
            private ColoredFileChooser chooser;
            private queryPanel agendaNo;
            private queryPanel title;
            private queryPanel writer;
            private queryPanel writerID;
            private String nextAvailableNo = "0001";
            private ArrayList<queryPanel> required = new ArrayList<>();
            private boolean insertionError = false;

            public inputPanel(AddAgenda parent) {
                this.parent = parent;

                setLayout(new BorderLayout());
                JPanel textInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
                textInput.add(Box.createRigidArea(new Dimension(10, 0)));

                JPanel generalInput = new JPanel();
                generalInput.setLayout(new BoxLayout(generalInput, BoxLayout.Y_AXIS));

                LocalDate currentDate = LocalDate.now();
                String formattedDate = currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

                setNextAvailableAgendaNo();
                agendaNo = new queryPanel("No", nextAvailableNo, Color.LIGHT_GRAY);
                agendaNo.setAlignmentX(LEFT_ALIGNMENT);

                queryPanel date = new queryPanel("Date", formattedDate, Color.LIGHT_GRAY);
                date.setAlignmentX(LEFT_ALIGNMENT);

                title = new queryPanel("Title", 20, Color.LIGHT_GRAY);
                title.setAlignmentX(LEFT_ALIGNMENT);
                title.adjustSize();
                required.add(title);

                writer = new queryPanel("Writer", 20, Color.LIGHT_GRAY);
                writer.setAlignmentX(LEFT_ALIGNMENT);
                writer.adjustSize();

                writerID = new queryPanel("Writer ID", 5, Color.LIGHT_GRAY);
                writerID.setAlignmentX(LEFT_ALIGNMENT);
                writerID.adjustSize();
                writerID.setNumeric();
                required.add(writerID);

                generalInput.add(agendaNo);
                generalInput.add(date);
                generalInput.add(title);
                generalInput.add(writer);
                generalInput.add(writerID);

                textInput.add(generalInput);
                add(textInput, BorderLayout.WEST);

                JPanel fileInput = new JPanel();
                fileInput.setLayout(new BoxLayout(fileInput, BoxLayout.Y_AXIS));
                fileInput.add(Box.createRigidArea(new Dimension(0, 40)));

                chooser = new ColoredFileChooser("Add File", this, "Choose File", "txt");
                chooser.setIcon(ImageIcons.reSize(ImageIcons.FILE, 40, 40));
                fileInput.add(chooser);

                add(fileInput, BorderLayout.EAST);
                JPanel footer = new JPanel();
                footer.setBackground(new Color(241, 241, 241));

                ColoredButton save = new ColoredButton("Save", this);
                save.setIcon(ImageIcons.reSize(ImageIcons.SAVE, 18, 18));
                save.setNormalColor(new Color(147, 175, 207));
                save.setSelectedColor(new Color(79, 170, 255));
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (checkBeforeInsert()) {
                            insertAgenda();
                            if (insertionError) {
                                JOptionPane.showMessageDialog(displayPanel,
                                        "Check if the specified ID correctly represent a member!", "Check Inputs",
                                        JOptionPane.WARNING_MESSAGE);
                                insertionError = false;
                            } else {
                                AgendaList.this.refresh();
                            }
                        }
                    }
                });

                ColoredButton discard = new ColoredButton("Discard", this);
                discard.setIcon(ImageIcons.reSize(ImageIcons.Discard_FILE, 18, 18));
                discard.setNormalColor(new Color(147, 175, 207));
                discard.setSelectedColor(new Color(79, 170, 255));

                footer.add(save.getWhole());
                footer.add(discard.getWhole());

                add(footer, BorderLayout.SOUTH);
            }

            @Override
            public void showMyTab(String buttonName) {
                if (buttonName.equals("Save")) {
                    JOptionPane.showMessageDialog(displayPanel, "Added Successfully!", "Add Agenda",
                            JOptionPane.WARNING_MESSAGE);
                    parent.report(this.fileChosen);

                } else {
                    int result = JOptionPane.showConfirmDialog(displayPanel,
                            "This will discard every unsaved changes. Do you wish to continue?", "Discard Changes",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        parent.report(null);
                    }
                }
            }

            @Override
            public void addTab(JButton button, JPanel clickedPanel) {
            }

            @Override
            public void workWithFileChosen(File selectedFile) {
                this.fileChosen = selectedFile;
                this.chooser.setText(selectedFile.getName());
                chooser.setSelected(true);
            }

            private void setNextAvailableAgendaNo() {
                try {
                    Statement nextAvailableAgendaStmt = App.DATABASE_CONNECTION.createStatement();
                    ResultSet nextAvailable = nextAvailableAgendaStmt.executeQuery(
                            "select LPAD(max(Agenda_No) + 1, 4, '0') AS nextAvailableAgendaNo FROM AGENDA;");
                    while (nextAvailable.next()) {
                        if (nextAvailable.getString(1) != null)
                            nextAvailableNo = nextAvailable.getString(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void insertAgenda() {
                String[] values = { "curdate()", "DEFAULT", "DEFAULT", "DEFAULT" };
                values[1] = "'" + title.getTextField().getText() + "'";
                values[2] = "'" + writerID.getTextField().getText() + "'";
                values[3] = "'" + fileChosen.getAbsolutePath().replace("\\", "\\\\") + "'";
                String query = "INSERT INTO AGENDA (Written_Date, Title, Writer, Written_Text) VALUES (";
                for (int i = 0; i < values.length; i++) {
                    if (i > 0)
                        query += ", ";
                    query += values[i];
                }
                query += ");";
                try (Statement insertStmt = App.DATABASE_CONNECTION.createStatement()) {
                    insertStmt.executeUpdate(query);
                } catch (Exception e) {
                    insertionError = true;
                    e.printStackTrace();
                }
            }

            private boolean checkBeforeInsert() {
                for (queryPanel qPanel : required) {
                    if (qPanel.getTextField().getText().equals("")) {
                        JOptionPane.showMessageDialog(displayPanel,
                                "Please Enter " + qPanel.getCaption().getText() + "!", "Required",
                                JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                }

                if (fileChosen == null) {
                    JOptionPane.showMessageDialog(displayPanel, "Please Choose File!", "Required",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

                return true;
            }

            @Override
            public void refresh() {

            }

            @Override
            public void showMyTab(CustomTable table, int selectedRow, int selectedColumn) {

            }
        }
    }

    @Override
    public void showMyTab(String buttonName) {
        new AddAgenda(this, ImageIcons.NOTE);
    }

    @Override
    public void showMyTab(CustomTable table, int selectedRow, int selectedColumn) {
        displayPanel.remove(individualAgenda);
        individualAgenda.updateData(Integer.parseInt((String) table.getValueAt(selectedRow, 0)));
        displayPanel.addMyTab(individualAgenda, table.getValueAt(selectedRow, 0).toString());
        displayPanel.showMyTab(table.getValueAt(selectedRow, 0).toString());
    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        displayPanel.addMyTab(clickedPanel, button.getName());
    }

    @Override
    public void workWithFileChosen(File selectedFile) {

    }

    @Override
    public void refresh() {
        try (Statement allAgendasStmt = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet retrieveAgendas = allAgendasStmt.executeQuery(
                    "SELECT LPAD(Agenda_No, 4, '0'), Written_Date, Title, CONCAT(M.First_Name, ' ', M.Father_Name, ' ', M.Grandfather_Name) AS Writter_Name\n"
                            + //
                            "FROM AGENDA AS A JOIN MEMBER_TABLE AS M ON A.Writer = M.ID ORDER BY Agenda_No DESC;");
            ArrayList<Object[]> curAgendas = new ArrayList<Object[]>();
            int ctr = 0;
            while (retrieveAgendas.next()) {
                ctr++;
                Object[] cur = { retrieveAgendas.getString(1), retrieveAgendas.getDate(2), retrieveAgendas.getString(3),
                        retrieveAgendas.getString(4) };
                curAgendas.add(cur);
            }
            if (ctr > 0)
                agendas = curAgendas.toArray(Object[][]::new);
            DefaultTableModel agendasModel = new DefaultTableModel();
            agendasModel.setColumnIdentifiers(agendaColumns);
            for (Object[] rowData : agendas) {
                agendasModel.addRow(rowData);
            }
            agendaList.setModel(agendasModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
