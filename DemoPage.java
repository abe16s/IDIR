import java.awt.*;
import javax.swing.*;


public class DemoPage extends JFrame {
    private RoundedPanel menuPanel;
    private RoundedPanel mainPanel;
    Image img = new ImageIcon("C:\\Users\\HP\\Downloads\\icons8-family-64.png").getImage();

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public DemoPage() {
        Container cp = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(255,255,255,26));
        setTitle("IDIR");
        setBounds(0, 0, screenWidth, screenHeight);
        setLayout(null);
        setIconImage(img);

        //Panels
        menuPanel = new RoundedPanel();
        menuPanel.setBackground(new Color(0,0,0,50));
        menuPanel.setBounds(30, 20, screenWidth/7, screenHeight*85/100);
        mainPanel = new RoundedPanel();
        mainPanel.setBackground(new Color(0,0,0,50));
        mainPanel.setBounds(240, 20, screenWidth*78/100, screenHeight*85/100);
        cp.add(menuPanel);
        cp.add(mainPanel);

    
    }

    public static void main(String[] args) {
        DemoPage trial = new DemoPage();
        //trial.setUndecorated(true);
        trial.setVisible(true);
    }

} 