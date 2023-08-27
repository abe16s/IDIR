package GUI.src.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ColoredFileChooser extends ColoredButton{
    private FileNameExtensionFilter filter;
    private String dialogTitle;
    private ParentPanel parent;

    public ColoredFileChooser(String text, JPanel parent, String dialogTitle, String... filterFor){
        super(text, parent);
        this.dialogTitle = dialogTitle;
        this.parent = (ParentPanel) parent;
        String description = "";
        for (String string : filterFor) {
            description += (string + ",");
        }
        filter = new FileNameExtensionFilter("Filter for " + description, filterFor);
        this.addActionListener(new choosefile());
    }

    private class choosefile implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(dialogTitle);
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                parent.workWithFileChosen(selectedFile);
            }
        }
    }
}

