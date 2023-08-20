package GUI.src.utilities;

import java.awt.Color;

import javax.swing.JPanel;

import GUI.src.BasePanel;

public class ColoredButton extends HoverableButton {
    private RoundedPanel background = new RoundedPanel();
    private Color normalColor;
    private Color selectedColor;
    

    public ColoredButton(String text, JPanel parent) {
        super(text, parent);

        background.add(this);
        setNormalColor(Color.black);
        setSelectedColor(Color.white);
    }


    public void setNormalColor(Color color){
        this.normalColor = color;
        this.background.setBackground(normalColor);
    }


    public void setSelectedColor(Color color){
        this.selectedColor = color;
    }

    public RoundedPanel getWhole(){
        return background;
    }

    @Override
    public void showEffect() {
        super.setSelected(true);
        this.background.setBackground(selectedColor);
        try {
            ((BasePanel) super.parent).showMyTab(this.getName());
        } catch (Exception e) {
           
        }
    }

    @Override
    public void removeEffect() {
        super.setSelected(false);
        this.background.setBackground(normalColor);
    }
}
