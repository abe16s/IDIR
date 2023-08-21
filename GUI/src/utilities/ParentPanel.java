package GUI.src.utilities;

import javax.swing.JPanel;

public interface ParentPanel{

    public void addTab(HoverableButton button, JPanel clickedPanel);
    public void showMyTab(String buttonName);
}
