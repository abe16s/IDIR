package GUI.src;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class BasePanel extends JPanel {
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

    public void addEditMember(AddMemberPanel addMemberPanel, String MemberID) {
        /*For adding an edit panel of an existing member*/
        add(addMemberPanel, MemberID+"edit");
        showMyTab(MemberID+"edit");   
    }

    public void createIndividualProfile(IndividualProfile individualProfile, String MemberID) {
        /*For adding and showing an individual profile panel*/
        add(individualProfile, MemberID);
        showMyTab(MemberID);
    }

}
