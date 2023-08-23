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

    public void addIndividualProfile(IndividualProfile individualProfile, String MemberID) {
        add(individualProfile, MemberID);
    }

    public void addEditMember(AddMemberPanel addMemberPanel, String MemberID) {
        add(addMemberPanel, MemberID+"edit");
        showMyTab(MemberID+"edit");   
    }

    public void createIndividualProfile(IndividualProfile individualProfile, String MemberID) {
        addIndividualProfile(individualProfile, MemberID);
        showMyTab(MemberID);
    }

}
