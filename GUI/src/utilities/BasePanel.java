package GUI.src.utilities;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class BasePanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();

    public BasePanel(){
        setLayout(cardLayout);
    }

    public void showMyTab(String buttonName) {
        cardLayout.show(this, buttonName);
    }


    public void addMyTab(JPanel clickedPanel, String buttonName){
        add(clickedPanel, buttonName);
    }

}
