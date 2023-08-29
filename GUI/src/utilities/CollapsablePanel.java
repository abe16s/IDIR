package GUI.src.utilities;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CollapsablePanel extends JPanel {
 
    private boolean selected;
    JPanel contentPanel_;
    HeaderPanel headerPanel_;
 
    private class HeaderPanel extends JPanel implements MouseListener {
        String text_;
        Font font;
        BufferedImage open, closed;
        final int OFFSET = 30, PAD = 5;
 
        public HeaderPanel(String text) {
            addMouseListener(this);
            text_ = text;
            font = new Font("sans-serif", Font.PLAIN, 12);
            // setRequestFocusEnabled(true);
            setPreferredSize(new Dimension(200, 20));
            
            try {
                open = ImageIO.read(new File("GUI\\Icons\\dark\\down-arrow.png"));
                closed = ImageIO.read(new File("GUI\\Icons\\dark\\right-arrow.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
 
        }
 
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            int h = getHeight();
            if (selected)
                g2.drawImage(open, PAD, 0, h, h, this);
            else
                g2.drawImage(closed, PAD, 0, h, h, this);
            
            g2.setFont(font);
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = font.getLineMetrics(text_, frc);
            float height = lm.getAscent() + lm.getDescent();
            float x = OFFSET;
            float y = (h + height) / 2 - lm.getDescent();
            g2.drawString(text_, x, y);
        }
 
        public void mouseClicked(MouseEvent e) {
            toggleSelection();
        }
 
        public void mouseEntered(MouseEvent e) {
        }
 
        public void mouseExited(MouseEvent e) {
        }
 
        public void mousePressed(MouseEvent e) {
        }
 
        public void mouseReleased(MouseEvent e) {
        }
 
    }
 
    public CollapsablePanel(String text, JPanel panel) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 3, 0, 3);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
 
        selected = false;
        headerPanel_ = new HeaderPanel(text);
        setOpaque(false);
        contentPanel_ = panel;
 
        add(headerPanel_, gbc);
        add(contentPanel_, gbc);
        contentPanel_.setVisible(false);
 
        JLabel padding = new JLabel();
        gbc.weighty = 1.0;
        add(padding, gbc);
 
    }
 
    public void toggleSelection() {
        selected = !selected;
 
        if (contentPanel_.isShowing()) {
            contentPanel_.setVisible(false);
            setSize(headerPanel_.getPreferredSize());
        } else
            contentPanel_.setVisible(true);
            setSize(new Dimension(getPreferredSize().width ,headerPanel_.getPreferredSize().height + contentPanel_.getPreferredSize().height));
 
        validate();
 
        headerPanel_.repaint();
    }
 
}


