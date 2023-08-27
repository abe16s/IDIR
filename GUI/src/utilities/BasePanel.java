package GUI.src.utilities;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class BasePanel extends JPanel implements ParentPanel{
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


    @Override
    public void showMyTab(String[] values, int source) {

    }

    @Override
    public void addTab(JButton button, JPanel clickedPanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTab'");
    }

}
