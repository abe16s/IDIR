package GUI.src.utilities;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;


public abstract class HoverableButton extends JButton {
    /*A Invisible Button that transparent through the background it is in and extends the JButton 
     * it has features like
     * i. invisible background
     * ii. has an enlarged text when hovered over
     * iii. it changes the main panel on the skeletalFrame according to its assigned name in the button 
     * iv. it has customizable select effect.
    */
    protected JPanel parent;
    protected Icon normalIcon;
    private int size;

    private MouseAdapter listener = new MouseAdapter() {
        
            @Override
            public void mouseEntered(MouseEvent e) {
                if (! isSelected()){
                    enlargeText();
                }}


            @Override
            public void mouseExited(MouseEvent e) { 
                
                if (! isSelected()){
                    ReduceText();
                }}


            @Override
            public void  mouseClicked(MouseEvent e){
                    
                if (! isSelected()){
                    setSelected(true);
                    ReduceText();
                    showPanel();
                }     
    }};

   
    public HoverableButton(String text, Icon icon, JPanel parent) {
        super(text, icon);
        super.setName(text);
        this.parent = parent;
        this.normalIcon = icon;

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.addMouseListener(this.listener); 
    }


    public HoverableButton(String text, JPanel parent) {
        super(text);

        this.parent = parent;
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.addMouseListener(this.listener);

    }


    public void unselect(){
        setSelected(false);;
    }


    private void enlargeText(){
        size = this.getFont().getSize();
        Font newFont = this.getFont().deriveFont((float)(size+2));;
        this.setFont(newFont);
    }


    private void ReduceText() {
        Font newFont = this.getFont().deriveFont((float)(size));;
        this.setFont(newFont);
    }
    

    private void showPanel() {
        this.showEffect();
    }


    public abstract void showEffect();
    public abstract void removeEffect();
}
