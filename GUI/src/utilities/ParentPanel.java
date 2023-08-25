package GUI.src.utilities;

public interface ParentPanel{
    /* 
     * This interface is created for the generalization of showEffect method in the TransparentButton/ColoredButton
     * so that the cast in the method can be used by TransparentButtons that are added both on MenuBar and MembersPanel
    */
    public void showMyTab(String buttonName);
}
