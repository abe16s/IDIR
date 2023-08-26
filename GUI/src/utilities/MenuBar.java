package GUI.src.utilities;

import java.awt.*;

import java.util.ArrayList;

import javax.swing.*;


public class MenuBar extends JPanel implements ParentPanel{

    private ArrayList<HoverableButton> buttons = new ArrayList<HoverableButton>();
    
    private BasePanel displayPanel;

    public MenuBar(BasePanel displayPanel){
        this.displayPanel = displayPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));     
        setBackground(new Color(244,244,244));
    }
    

    public void setSize(int Width){
        setSize(Width, 1080);
    }


    public void addTab(HoverableButton button, JPanel clickedPanel){
        add(button);
        buttons.add(button);
        displayPanel.addMyTab(clickedPanel,button.getName());
    }


    public void showMyTab(String buttonName){
        prepare(buttonName);
        displayPanel.showMyTab(buttonName);
    }


    private void prepare(String buttonName){

        for(HoverableButton x : buttons){
            if (x.getName() != buttonName){
                x.unselect();
                x.removeEffect();
            }
        }
    }
}
