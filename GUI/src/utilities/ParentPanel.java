package GUI.src.utilities;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

public interface ParentPanel{
    /* 
     * This interface is created for the generalization of showEffect method in the TransparentButton/ColoredButton
     * so that the cast in the method can be used by TransparentButtons that are added both on MenuBar and MembersPanel
    */
    public void showMyTab(String buttonName);

    // if it owns table 
    public void showMyTab(String[] values,int source);

    public void addTab(JButton button, JPanel clickedPanel);

    public void workWithFileChosen(File selectedFile);

}
