package GUI.src.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class ColoredButton extends HoverableButton {
    private RoundedPanel background = new RoundedPanel();
    private Color normalColor;
    private Color selectedColor;
    

    public ColoredButton(String text, JPanel parent) {
        super(text, parent);
        
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        background.setMaximumSize(new Dimension(getMaximumSize().width,getMaximumSize().height+6));
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
        this.background.setBackground(selectedColor);

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor ();

    Runnable task = new Runnable () {
    @Override
    public void run () {            
        try {((ParentPanel) parent).showMyTab(getName());} catch (Exception e) {}
        removeEffect();
    }};


    executor.schedule (task, 150, TimeUnit.MILLISECONDS);
    executor.shutdown ();

    }

    @Override
    public void removeEffect() {
        super.setSelected(false);
        this.background.setBackground(normalColor);
    }
}