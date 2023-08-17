package GUI.src;

import java.awt.Image;

import javax.swing.ImageIcon;

public final class ImageIcons {
    public static final ImageIcon OFFICIAL = new ImageIcon("GUI\\Icons\\colored\\administrator-male.png");
    public static final ImageIcon OFFICIALdark = new ImageIcon("GUI\\Icons\\dark\\administrator-male.png");

    public static final ImageIcon COINS = new ImageIcon("GUI\\Icons\\colored\\coins.png");
    public static final ImageIcon COINSdark = new ImageIcon("GUI\\Icons\\dark\\coins.png");

    public static final ImageIcon CONTACTS = new ImageIcon("GUI\\Icons\\colored\\contacts.png");
    public static final ImageIcon CONTACTSdark = new ImageIcon("GUI\\Icons\\dark\\contacts.png");

    public static final ImageIcon HELP = new ImageIcon("GUI\\Icons\\colored\\help.png");
    public static final ImageIcon HELPdark = new ImageIcon("GUI\\Icons\\dark\\help.png");

    public static final ImageIcon NOTE = new ImageIcon("GUI\\Icons\\colored\\note.png");
    public static final ImageIcon NOTEdark = new ImageIcon("GUI\\Icons\\dark\\note.png");

    public static final ImageIcon RULES = new ImageIcon("GUI\\Icons\\colored\\rules.png");
    public static final ImageIcon RULESdark = new ImageIcon("GUI\\Icons\\dark\\rules.png");

    public static final ImageIcon HOME = new ImageIcon("GUI\\Icons\\colored\\home.png");
    public static final ImageIcon HOMEdark = new ImageIcon("GUI\\Icons\\dark\\home.png");

    public static final ImageIcon LOGO = new ImageIcon("GUI\\Icons\\colored\\family.png");
    public static final ImageIcon LOGObackground = new ImageIcon("GUI\\Icons\\dark\\family.png");

    public static final ImageIcon EDIT = new ImageIcon("GUI\\Icons\\dark\\edit-account.png");

    public static final ImageIcon SAVE = new ImageIcon("GUI\\Icons\\dark\\save.png");

    public static final ImageIcon UNKNOWN = new ImageIcon("GUI\\Icons\\dark\\unknown photo.png");


    public static ImageIcon reSizeImageIcon(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
