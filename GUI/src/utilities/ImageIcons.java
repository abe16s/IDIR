package GUI.src.utilities;

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

    public static final ImageIcon UNKNOWN = new ImageIcon("GUI\\Icons\\dark\\unknonwn-photo.png");

    public static final ImageIcon ADD_MEMBER = new ImageIcon("GUI\\Icons\\dark\\add-user-male.png");
    
    public static final ImageIcon CHANGE = new ImageIcon("GUI\\Icons\\colored\\change.png");

    public static final ImageIcon FILE = new ImageIcon("GUI\\Icons\\colored\\file.png");

    public static final ImageIcon Discard_FILE = new ImageIcon("GUI\\Icons\\dark\\file discard.png");
    
    public static final ImageIcon DOWN_ARROW = new ImageIcon("GUI\\Icons\\dark\\down-arrow.png");
    public static final ImageIcon RIGHT_ARROW = new ImageIcon("GUI\\Icons\\dark\\right-arrow.png");

    public static final ImageIcon DISCARD_RECEIPT = new ImageIcon("GUI\\Icons\\dark\\delete-receipt.png");
    
    public static final ImageIcon RECEIPT = new ImageIcon("GUI\\Icons\\dark\\receipt.png");

    public static final ImageIcon PAYMENT_HISTORY = new ImageIcon("GUI\\Icons\\dark\\payment-history.png");

    public static ImageIcon reSize(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
