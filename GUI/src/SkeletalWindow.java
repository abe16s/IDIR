package GUI.src;

import java.awt.*;

import javax.swing.*;

public class SkeletalWindow extends JFrame{

    private MenuBar menu;
    private BasePanel contentPanel;

    public SkeletalWindow(String title){
        setTitle(this.getIndentedTitle(title));

        setMinimumSize(this.getMinSize());
        setLocation(this.getMinSize().width/5,this.getMinSize().height/6);

        setLayout(new BorderLayout());
        
        contentPanel = new BasePanel();
        add(contentPanel,BorderLayout.CENTER);

        menu = new MenuBar(contentPanel);
        menu.setPreferredSize(new Dimension(140,1080));
        add(this.menu,BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    private Dimension getMinSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();

        int height = (int) ((int)screenSize.getHeight () * 0.75);
        int width = (int) ((int)screenSize.getWidth () * 0.75);

        Dimension minSize = new Dimension(width, height);

        return minSize ;
    }

    private String getIndentedTitle(String title){

        int width = this.getMinSize().width;
        String whiteSpace = " ";

        for (int i = 0; i < width/10; i++){
            whiteSpace += " ";
        }

        return whiteSpace + title;
    }


    public MenuBar getMenu(){
        return this.menu;   
    }

    public BasePanel getBasePanel(){
        return this.contentPanel;
    }

}