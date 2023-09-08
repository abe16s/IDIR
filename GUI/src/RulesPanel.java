package GUI.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.src.SkeletalWindow.BasePanel;
import GUI.src.utilities.ReadAndDisplay;

public class RulesPanel extends JPanel{
    private String rules = "GUI\\src\\utilities\\Sample Rule.txt";

    public RulesPanel(BasePanel displayPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(228, 228, 228));
        setBorder(new EmptyBorder(new Insets(5, 10, 0, 0)));

        ReadAndDisplay readAndDisplay = new ReadAndDisplay();
        readAndDisplay.readFile(getRules());
        readAndDisplay.setBackground(getBackground());
        add(readAndDisplay.getWhole(), BorderLayout.CENTER);
    }

    private String getRules() {
        try (Statement statement = App.DATABASE_CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT Rules_And_Regulations FROM IDIR_TABLE");
            while (resultSet.next()) {
                rules = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rules;
    }
}
