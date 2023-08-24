package GUI.src.utilities;

import javax.swing.JPanel;

public interface ParentPanel{
    /* 
     * This interface is created for the generalization of showEffect method in the TransparentButton
     * so that the cast in the method can be used by TransparentButtons that are added both on MenuBar and MembersPanel
    */
    public void addTab(HoverableButton button, JPanel clickedPanel);
    public void showMyTab(String buttonName);
}
