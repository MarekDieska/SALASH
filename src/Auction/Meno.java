package Auction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Observer.AukciaGUI;

/**
 * this class shows the main menu for name input and pick from three options for a random starting balance
 * @author Marek Dieska
 */

public class Meno extends JDialog {
    private JTextField inputField;
    private String inputText;
    private int balance = 1;

    /**
     * intitializer for Meno class
     * @param parent the AukciaGUI class, because this window updates values in the main window
     * @param message the name that the player provides
     */
    public Meno(JFrame parent, String message) {
        super(parent, "Meno", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 420);
        setMinimumSize(new Dimension(200, 100));
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        label.setFont(new Font("Century", Font.BOLD, 24));
        label.setForeground(Color.decode("#FAF3DD"));
        label.setText(message);
        JLabel imageLabel = new JLabel();
        imageLabel.setBackground(new Color(156,205,217));
        imageLabel.setOpaque(true);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon("logo.png");
        Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        imageLabel.setIcon(resizedIcon);
        
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(20,100));
        spacePanel.setBackground(Color.decode("#0E6C69"));
        
        panel.add(spacePanel);
        panel.add(label, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.EAST);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(10, 5));
        inputField.setFont(new Font("Century", Font.BOLD, 24));
        inputField.setForeground(Color.WHITE);
        inputField.setBackground(Color.decode("#0E6C69"));
        inputField.setBorder(new EmptyBorder(0,0,0,0));
        inputField.setCaretColor(Color.WHITE);
        panel.setBackground(Color.decode("#0E6C69"));
        panel.add(spacePanel, BorderLayout.WEST);
        panel.add(inputField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#0E6C69"));

        JButton imageButton1 = createButton("coin_pix.png");
        imageButton1.setBackground(Color.decode("#13908C"));
        //imageButton1.setBorder(new EtchedBorder(20, Color.decode("#DEBF3F"), Color.decode("#DEBF3F")));
        buttonPanel.add(imageButton1);

        JButton imageButton2 = createButton("coin_pix.png");
        imageButton2.setBackground(Color.decode("#13908C"));
        //imageButton2.setBorder(new EtchedBorder(20, Color.decode("#DEBF3F"), Color.decode("#DEBF3F")));
        buttonPanel.add(imageButton2);

        JButton imageButton3 = createButton("coin_pix.png");
        imageButton3.setBackground(Color.decode("#13908C"));
        //imageButton3.setBorder(new EtchedBorder(20, Color.decode("#DEBF3F"), Color.decode("#DEBF3F")));
        buttonPanel.add(imageButton3);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
    }
    
    /**
     * this is the function that creates a random number that will be player's starting balance
     * @return starting balance of the player
     */
    int getBalance()
    {
    	Random rand = new Random();
    	balance = (rand.nextInt(2500) + 1000);
    	return balance;
    }

    /**
     * this function creates every button that is created on the bottom of the main menu, also adds it's purpose to it
     * @param imagePath the image that will be on the button
     * @return the button with the function that it does
     */
    
    private JButton createButton(String imagePath) {
        JButton imageButton = new JButton();
        imageButton.setIcon(new ImageIcon(imagePath));
        imageButton.setContentAreaFilled(true);
        imageButton.setBorderPainted(true);
        imageButton.setFocusPainted(true);

        imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputText = inputField.getText();
                int newBalance = getBalance();
                dispose();
                ((AukciaGUI) getParent()).addBalance(newBalance);
            }
        });

        return imageButton;
    }

    /**
     * this function returns the given name
     * @return given name in the input field
     */
    public String getInputText() {
        return inputText;
    }
}
