package GUI.src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Utilities {
    /*This file contains classes that are useful for the Idir project*/
}

class RoundedPanel extends JPanel {
    /*A Rounded panel that has curved edges and extends the JPanel class */

    /** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(20, 20);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;

    public RoundedPanel() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
        height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
        height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
} 


class InvisibleButton extends JButton {
    /*A Invisible Button that transparents through the background it is in and extends the JButton 
     * it has features like
     * i. invisibile background
     * ii. has an enlarged text when hovered over
     * iii. it changes the main panel in the skeletal according to its assigned text in the button 
    */

    public InvisibleButton(String text, Icon icon) {
        super(text, icon);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.setFont(new Font("Times New Roman", Font.ITALIC|Font.BOLD, 15));
        this.addMouseListener(new EnlargeText(this));
        this.addActionListener(new MainPanelChanger(text)); // This action listener only works when the constraint
        //of the panel to be shown in the main panel has exactly the same string as the text assigned to this button
        //so be sure to use the same text for the button and to the constraint of the panel it forwards to.  
    }

    public InvisibleButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.setFont(new Font("Times New Roman", Font.ITALIC|Font.BOLD, 15));
        this.addMouseListener(new EnlargeText(this));
        this.addActionListener(new MainPanelChanger(text));

    }

    public InvisibleButton(Icon icon) {
        super(icon);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
    }
}


class EnlargeText extends MouseAdapter {
    /*A mouse adapter that listens to buttons when a mouse hovers overs them & enlarges their text */
    
    private int size;
    private JButton btn;
    private Font btnFont;
    public EnlargeText(JButton btn) {
        this.size = btn.getFont().getSize();
        this.btn = btn;
        this.btnFont = btn.getFont();
    }

    public void mouseEntered(MouseEvent me) {
        Font newFont = btnFont.deriveFont((float)(size+2));
        this.btn.setFont(newFont);
    }
    public void mouseExited(MouseEvent me) {
        this.btn.setFont(btnFont);
    }
}


class MainPanelChanger implements ActionListener {
    /*An action listener that changes the content in the static panel 
    of the skeletal "mainPanel" according to the button function*/

    private String btn;

    public MainPanelChanger(String s) {
        this.btn = s;
    }

    public void actionPerformed(ActionEvent e) {
        SkeletalPage.cardlayout.show(SkeletalPage.MainPanel, this.btn);
    }
}
