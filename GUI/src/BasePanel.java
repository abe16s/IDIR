package GUI.src;

import java.awt.CardLayout;

import javax.swing.JPanel;

import GUI.src.utilities.ParentPanel;

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

    public void addEditMember(AddMemberPanel addMemberPanel) {
        /*For adding an edit panel of an existing member*/
        add(addMemberPanel, "editMember");
        showMyTab("editMember");   
    }

    public void createIndividualProfile(IndividualProfile individualProfile) {
        /*For adding and showing an individual profile panel*/
        add(individualProfile, "Profile");
        showMyTab("Profile");
    }

}
