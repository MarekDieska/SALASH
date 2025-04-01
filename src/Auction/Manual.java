package Auction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import Observer.AukciaGUI;

/**
 * This class is a child of AukciaGUI and its purpose is to tech player how the program is supposed to be played
 * @author Marek Dieska
 */

public class Manual extends AukciaGUI {
	boolean cheat = false;
	private String bgColor = "#51315E", manualColor = "#651518";
	private JLabel inputLabel;
	
	/**
	 * Constructor for Manual
	 */
	public Manual() {
		super();
		setColors(bgColor, manualColor);
		manualButton.setText("Exit");
		rankLabel.setText("Rank");
		botInfo.setText("                                         \n");
		textArea.setText("Press buttons to see\n what they do\n");
		botInfo.setVisible(true);
		showNameInput();
	}
	
	/**
	 * called when any button is pressed, this function performs every button's purpose
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Socha")) {
            textArea.setText("");
            textArea.setForeground(new Color(250, 143, 54));
            updtBtnVsb();
            appendText("===============================\nHere will be the info about the statue\nthe price depends on the provided\n information, try to\n get as close as you possibly can\n===============================\n");
        } else if (buttonText.equals("Obraz")) {
            textArea.setForeground(new Color(53, 236, 255));
            updtBtnVsb();
            appendText("===============================\nHere will be the info about the painting\nthe price depends on the provided\n information, try to\n get as close as you possibly can\n===============================\n");
        } else if (buttonText.equals("Kniha")) {
            textArea.setForeground(new Color(159, 187, 253));
            updtBtnVsb();
            appendText("===============================\nHere will be the info about the book\nthe price depends on the provided\n information, try to\n get as close as you possibly can\n===============================\n");
        } if (buttonText.equals("Prihodit")) {
        	textArea.setText("");
        	botInfo.setText("                                         \n");
        	appendText("===============================\nIf you bid more than the\n bots, you get the object,\n otherwise the bots will bid.\n Info about the bots is ----->\n===============================");
        	botInfo.append("******************************\nHERE\n******************************\n");
        } else if (buttonText.equals("Opustit")) {
            updateButtonsVisibility();
            botInfo.setText("                                         \n");
            appendText("Zostatok: " + "\n");
        }
        if (e.getSource() == saveProfitButton) {
            botInfo.append("This saves progress\n");
        }
        if (e.getSource() == loadProfitButton) {
            rankLabel.setText("Loaded");
            inputLabel.setText("Name");
            botInfo.append("This loads progress\n");
        }
        if (buttonText.equals("Cheat")) {
            cheat = !cheat;
            if(cheat) {
            	botInfo.append("bots are not bidding\n");
            } else {
            	botInfo.append("bots are bidding\n");
            }
        }
        if (buttonText.equals("Exit")) {
        	this.setVisible(false);
        }
    }
	
	/**
	 * needed to be called to initialize and show the left side of the screen
	 */
	@Override
	public void showNameInput() {
		inputLabel = new JLabel("Name");
        inputLabel.setForeground(Color.decode("#E7C75F"));
        inputLabel.setFont(new Font("Algerian", Font.BOLD, 25)); 
        rankPanel.add(inputLabel, BorderLayout.WEST);
        rankPanel.add(saveProfitButton);
        rankPanel.add(loadProfitButton);
        rankPanel.add(spacePanelRank);
        paymentField.setText("Put bid here");
        paymentField.setEditable(false);
        rankPanel.add(paymentLabel);
        rankPanel.add(paymentField);
	}
}