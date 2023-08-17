package GUI.src;

import java.awt.Image;

import javax.swing.ImageIcon;

public final class ImageIcons {
    public static ImageIcon LOGO = new ImageIcon("Java-Version/src/GUI/images/game logo.png");
    public static ImageIcon SETTING = new ImageIcon("Java-Version/src/GUI/images/setting.png");
    public static ImageIcon GEAR = new ImageIcon("C:/Users/user/Downloads/icons8-settings-32 (1).png");
    public static ImageIcon PLAY = new ImageIcon("C:/Users/user/Downloads/icons8-play-64.png");
    public static ImageIcon MENU = new ImageIcon("C:/Users/user/Downloads/icons8-menu-32.png");


    public static ImageIcon reSizeImageIcon(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
