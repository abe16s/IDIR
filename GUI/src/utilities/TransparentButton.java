package GUI.src.utilities;

import javax.swing.Icon;
import javax.swing.JPanel;


public class TransparentButton extends HoverableButton {
    private Icon selectedIcon;

    public TransparentButton(String text,Icon icon, JPanel parent) {
        super(text,icon,parent);
    }

    public void setSelectedIcon(Icon icon){
        this.selectedIcon = icon;
    }
    @Override
    public void showEffect() {
        if (this.selectedIcon != null){
            this.setIcon(selectedIcon);            
        }

        try {
            ((ParentPanel) super.parent).showMyTab(this.getName()); //Changed the cast for MenuBar to ParentPanel to include the MembersPanel
        } catch (Exception e) {}
    }

    @Override
    public void removeEffect() {
        this.setIcon(super.normalIcon);
    }
    
}
