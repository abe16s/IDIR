package GUI.src;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import GUI.src.utilities.CustomTable;
import GUI.src.utilities.HoverableButton;
import GUI.src.utilities.ParentPanel;

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
        public void showMyTab(CustomTable table, String[] values, int source) {

        }

        @Override
        public void addTab(JButton button, JPanel clickedPanel) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addTab'");
        }

        @Override
        public void workWithFileChosen(File selectedFile) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
        }

    }


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


        public void addTab(JButton button, JPanel clickedPanel){

            add((HoverableButton)button);
            buttons.add((HoverableButton)button);
            displayPanel.addMyTab(clickedPanel,button.getName());
        }


        public void showMyTab(String buttonName){
            prepare(buttonName);
            displayPanel.showMyTab(buttonName);
        }


        void prepare(String buttonName){

            for(HoverableButton x : buttons){
                if (x.getName() != buttonName){
                    x.unselect();
                    x.removeEffect();
                }
            }
        }


        @Override
        public void showMyTab(CustomTable table, String[] values, int source) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'showMyTab'");
        }


        @Override
        public void workWithFileChosen(File selectedFile) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'workWithFileChosen'");
        }
    }
}