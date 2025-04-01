package Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Auction.ErrorHandler;
import Auction.Manual;
import Auction.Meno;
import Object.Kniha;
import Object.Obraz;
import Object.PredmetAukcie;
import Object.Socha;
import Serialization.OverallProfit;
import Serialization.OverallProfitSerializer;
import Strategy.Bot;

/**
 * this is the view part of the code, this is the main GUI, the main window of the program
 * @author Marek Dieska
 */

public class AukciaGUI extends JFrame implements Observer, ActionListener {
	protected JTextArea botInfo;
	protected JTextPane textArea;
	protected JScrollPane botScroll;
	private JButton sochaButton, obrazButton, knihaButton, kupitButton, nekupitButton;
	protected JButton saveProfitButton;
	protected JButton loadProfitButton;
	private JButton cheatButton;
	protected JButton manualButton;
	protected JPanel mainPanel;
    private Bot[] bots;
    private JLabel balanceLabel;
	protected JLabel rankLabel;
	protected JLabel paymentLabel;
    protected JTextField paymentField;
    private PredmetAukcie predmet;
    int cena;
	private int rozdiel, lastpayment = 0, payment;
    private int maxLimit, savedProfit;
    private JLabel imageLabel;
    protected JPanel rankPanel, spacePanel1, spacePanel2, rankPaymentPanel, botScrollPanel, buttonPanel;
	protected JPanel balancePanel;
	protected JPanel spacePanelRank;
    private int Balance;
    private Aukcia aukcia;
    private Bot bot;
    private int[] botLimits = {0, 0, 0};
    private OverallProfit overallProfit;
    private Hrac hrac;
    protected String bgColor = "#0B5755", buttonColor = "#073635", textColor = "#FAF2DC", manualColor = "#333F27";
    private String buttonFont = "NSimSun";
    private boolean cheat = false;

    /**
     * constructor of the AukciaGUI, this setups the whole main GUI
     */
    public AukciaGUI() {
        super("SALASH");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setMinimumSize(new Dimension(1000, 500));

        
        overallProfit = new OverallProfit(0);
        hrac = new Hrac(0);
        aukcia = new Aukcia(overallProfit, hrac);
        aukcia.addObserver(this);
        mainPanel = new JPanel(new BorderLayout());
        
        
        
        spacePanel1 = new JPanel();
        spacePanel1.setPreferredSize(new Dimension(300, 50));
        
        spacePanel2 = new JPanel();
        spacePanel2.setPreferredSize(new Dimension(100, 50));
        
        spacePanelRank = new JPanel();
        spacePanelRank.setPreferredSize(new Dimension(50, 200));

        textArea = new JTextPane();
        textArea.setForeground(Color.decode(textColor));
        Font textFont = new Font("Papyrus", Font.BOLD, 34);
        textArea.setFont(textFont);
        textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
        textArea.setEditable(false);
        textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        StyledDocument documentStyle = textArea.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        textArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        

        balanceLabel = new JLabel("" + aukcia.getPlayerBalance());
        balancePanel = new JPanel();
        balanceLabel.setForeground(Color.decode("#DEBF3F"));
        balancePanel.setBackground(Color.decode(bgColor));
        Font balanceFont = new Font("Bell MT", Font.BOLD, 20);
        balanceLabel.setFont(balanceFont);
        balancePanel.add(balanceLabel, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon("coin_pix.png");
        Image img = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        balanceLabel.setIcon(resizedIcon);
       
        
        saveProfitButton = new JButton("Save");
        saveProfitButton.setPreferredSize(new Dimension(50, 20));
        saveProfitButton.setForeground(Color.decode(textColor));
        saveProfitButton.setBorderPainted(false);
        saveProfitButton.setFocusPainted(false);
        
        saveProfitButton.addActionListener(this);
        
        loadProfitButton = new JButton("Load");
        loadProfitButton.setPreferredSize(new Dimension(50, 20));
        loadProfitButton.setForeground(Color.decode(textColor));
        loadProfitButton.setBorderPainted(false);
        loadProfitButton.setFocusPainted(false);
        
        loadProfitButton.addActionListener(this);

        Font serializeFont = new Font("Papyrus", Font.BOLD, 15);
        saveProfitButton.setFont(serializeFont);
        loadProfitButton.setFont(serializeFont);
        rankLabel = new JLabel(aukcia.getPlayerRank());
        rankLabel.setForeground(new Color(230, 180, 50));
        rankLabel.setFont(new Font("Algerian", Font.PLAIN, 30));
        rankPanel = new JPanel();
        rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS));
        rankPanel.add(rankLabel);

        paymentField = new JTextField(8);
        paymentField.setBackground(new Color(45, 45, 45));
        paymentField.setForeground(Color.decode(textColor));
        paymentField.setMaximumSize(new Dimension(Integer.MAX_VALUE, paymentField.getPreferredSize().height)); 
        paymentLabel = new JLabel("Suma:");
        paymentLabel.setForeground(new Color(220, 220, 220));

        rankPaymentPanel = new JPanel();
        rankPaymentPanel.setLayout(new BoxLayout(rankPaymentPanel, BoxLayout.Y_AXIS));
        rankPaymentPanel.add(rankPanel);
        rankPaymentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rankPaymentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        botInfo = new JTextArea("                                         \n");
        botInfo.setForeground(Color.decode("#FAF3DD"));
        botInfo.setBorder(new EmptyBorder(0, 0, 0, 0));
        botInfo.setEditable(false);
        
        
        botScrollPanel = new JPanel(new BorderLayout());
        botScrollPanel.add(botInfo, BorderLayout.CENTER);
        

        botScroll = new JScrollPane(botScrollPanel);
        botScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

        sochaButton = new JButton("Socha");
        sochaButton.setPreferredSize(new Dimension(100, 50));
        sochaButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        obrazButton = new JButton("Obraz");
        obrazButton.setPreferredSize(new Dimension(100, 50));
        obrazButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        knihaButton = new JButton("Kniha");
        knihaButton.setPreferredSize(new Dimension(100, 50));
        knihaButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        kupitButton = new JButton("Prihodit");
        kupitButton.setPreferredSize(new Dimension(140, 50));
        kupitButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        nekupitButton = new JButton("Opustit");
        nekupitButton.setPreferredSize(new Dimension(140, 50));
        nekupitButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        kupitButton.setVisible(false);
        nekupitButton.setVisible(false);
        
        
        cheatButton = new JButton("Cheat");
        cheatButton.setPreferredSize(new Dimension(100, 50));
        cheatButton.setForeground(Color.decode(textColor));
        cheatButton.setBackground(Color.decode(buttonColor));
        cheatButton.setFont(new Font(buttonFont, Font.BOLD, 20));
        
        manualButton = new JButton("Manual");
        manualButton.setPreferredSize(new Dimension(100, 50));
        manualButton.setForeground(Color.decode(textColor));
        manualButton.setBackground(Color.decode(buttonColor));
        manualButton.setFont(new Font(buttonFont, Font.BOLD, 20));

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(spacePanel1);
        sochaButton.setForeground(Color.decode(textColor));
        sochaButton.setBackground(Color.decode(buttonColor));
        buttonPanel.add(sochaButton);
        obrazButton.setForeground(Color.decode(textColor));
        obrazButton.setBackground(Color.decode(buttonColor));
        buttonPanel.add(obrazButton);
        knihaButton.setForeground(Color.decode(textColor));
        knihaButton.setBackground(Color.decode(buttonColor));
        buttonPanel.add(knihaButton);
        kupitButton.setForeground(Color.decode(textColor));
        kupitButton.setBackground(Color.decode("#093717"));
        buttonPanel.add(kupitButton);
        nekupitButton.setForeground(Color.decode(textColor));
        nekupitButton.setBackground(Color.decode("#370909"));
        buttonPanel.add(nekupitButton);
        
        
        
        buttonPanel.add(spacePanel2);
        buttonPanel.add(cheatButton, BorderLayout.EAST);
        buttonPanel.add(manualButton, BorderLayout.EAST);
        
        JButton[] buttons = {sochaButton, obrazButton, knihaButton, kupitButton, nekupitButton, saveProfitButton, loadProfitButton, cheatButton, manualButton};
        for (JButton button : buttons) {
            button.setBorder(null);
        }

        sochaButton.addActionListener(this);
        obrazButton.addActionListener(this);
        knihaButton.addActionListener(this);
        kupitButton.addActionListener(this);
        nekupitButton.addActionListener(this);
        cheatButton.addActionListener(this);
        manualButton.addActionListener(this);
        
        setColors(bgColor, manualColor);
        textArea.setBorder(null);
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(balancePanel, BorderLayout.NORTH);
        mainPanel.add(rankPaymentPanel, BorderLayout.WEST);
        mainPanel.add(botScroll, BorderLayout.EAST);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }
    
    /**
     * function to set the colors for the background of the GUI and for the color of the Manual button
     * @param bgColor this is the hexadecimal code of the bacground color
     * @param manualColor this is the hexadecimal code of the Manual button color
     */
    public void setColors(String bgColor, String manualColor) {
    	balancePanel.setBackground(Color.decode(bgColor));
		rankPanel.setBackground(Color.decode(bgColor));
		rankPaymentPanel.setBackground(Color.decode(bgColor));
		buttonPanel.setBackground(Color.decode(bgColor));
		botScrollPanel.setBackground(Color.decode(bgColor));
		spacePanel1.setBackground(Color.decode(bgColor));
		spacePanel2.setBackground(Color.decode(bgColor));
		textArea.setBackground(Color.decode(bgColor));
		saveProfitButton.setBackground(Color.decode(bgColor));
		loadProfitButton.setBackground(Color.decode(bgColor));
		spacePanelRank.setBackground(Color.decode(bgColor));
		botScroll.setBackground(Color.decode(bgColor));
		botInfo.setBackground(Color.decode(bgColor));
		paymentLabel.setBackground(Color.decode(bgColor));
		manualButton.setBackground(Color.decode(manualColor));
    }

    /**
     * this function updates the player balance, this function is also connected to the observer design pattern
     */
    public void update() {
        balanceLabel.setText("" + aukcia.getPlayerBalance());
    }

    /**
     * this function adds the picked balance at the start to the player balance
     * @param newBalance the amount of the randomly picked balance
     * @return player balance
     */
    public int addBalance(int newBalance) {
        aukcia.addBalance(newBalance);
        Balance = aukcia.getPlayerBalance();
        balanceLabel.setText("" + Balance);
        return Balance;
    }
    
    /**
     * this function returns the overall profit that the player made throughout the auction
     * @return the overall profit made
     */
    public OverallProfit getOP() {
    	return overallProfit;
    }
    
    /**
     * this function returns the player entity
     * @return player entity
     */
    public Hrac getHrac() {
    	return hrac;
    }

    /**
     * this function creates the bots that the player is against in the auction, this function is part of the observer design pattern
     */
    private void createBots() {
        if (predmet != null && bots == null) {
            bots = new Bot[3];
            for (int i = 0; i < 3; i++) {
                bots[i] = new Bot(cena);
                botLimits[i] = bots[i].getBotLimit();
                if(botLimits[i] > maxLimit) {
                	maxLimit = botLimits[i];
                }
                bots[i].addObserver(this);
                bots[i].setBidListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onBidAccepted((Bot) e.getSource(), payment);
                    }
                });
                aukcia.addBot(bots[i]);
            }
        }
    }

    /**
     * this function compares what bots would bid and what player bids and decides if the bots will bid
     * if not the bot is set to null
     */
    private void checkBid() {
        for (int i = 0; i < bots.length; i++) {
            if (bots[i] != null) {
                if (bots[i].checkBid() == 0) {
                    bots[i] = null;
                }
            }
        }
    }

    /**
     * this function happens if bot accepts the bid, the main purpose of the function is to write
     * that bot bid into the GUI console
     * @param bot has his max limit if, the player's bid is lower the text is printed
     * @param payment the amount that player bid
     */
    @Override
    public void onBidAccepted(Bot bot, int payment) {
    	payment = (int) Double.parseDouble(paymentField.getText());
        if (payment <= bot.getBotLimit() && bot != null) {
            botInfo.append("Bot " + bot.getName() + " prihodil\n");
        }
    }
    
    /**
     * this function does what the buttons are supposed to do, the socha, obraz, kniha buttons generate the object and
     * show their information on the text field, they also update the buttons and bots, the the opustit and prihodit buttons
     * determine the outcome of the auction, if the player presses the opustit button, nothing much happens, the auction stops
     * button visibility is change and player leaves the actual auction. If the player puts amount in the payment input
     * field and then presses the kupit button, one of three things can happen, either he put an invalid amount and an
     * error message pops out, or he put number that is too low and the bots bid, or the player puts high enough number and
     * he or she gets the object. If the player presses the save button, he saves his overall profit made, if he presses the
     * load button he loads his progress. If the player presses the cheat button, then the bots will not bid, or will bid
     * depending on the state of the cheat button, if the cheat is active player can buy objects foj just 1 sheep. If the player
     * presses the manual button, the manual is shown, here the information about what the buttons do is displayed.
     */
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        if (buttonText.equals("Socha")) {
            lastpayment = 0;
            textArea.setText("");
            textArea.setForeground(new Color(250, 143, 54));
            predmet = new Socha();
            updtBtnVsb();
            appendText("===============================\n" + predmet.vypisInfo() + "===============================\n");
            cena = predmet.getCena();
            createBots();
        } else if (buttonText.equals("Obraz")) {
            lastpayment = 0;
            textArea.setText("");
            textArea.setForeground(new Color(53, 236, 255));
            predmet = new Obraz();
            updtBtnVsb();
            appendText("===============================\n" + predmet.vypisInfo() + "===============================\n");
            cena = predmet.getCena();
            createBots();
        } else if (buttonText.equals("Kniha")) {
            lastpayment = 0;
            textArea.setText("");
            textArea.setForeground(new Color(159, 187, 253));
            predmet = new Kniha();
            updtBtnVsb();
            appendText("===============================\n"+ predmet.vypisInfo() + "===============================\n");
            cena = predmet.getCena();
            createBots();
        } if (buttonText.equals("Prihodit")) {
            try {
                int payment = (int) Double.parseDouble(paymentField.getText());
                if (payment < lastpayment) {
                    ErrorHandler.showError("Neplatná platba!");
                }
                else if (payment <= 0 || payment > aukcia.getPlayerBalance()) {
                    ErrorHandler.showError("Neplatná platba!");
                    return;
                }
                else if (payment > lastpayment) {
                	botInfo.append("******************************\n");
                	checkBid();
                	botInfo.append("******************************\n");
                    if (payment >= maxLimit) {
                    	Check check = new Check();
                    	check.setVisible(true);;
                    	botInfo.setText("                                         \n");
                        rozdiel = aukcia.deductBalance(payment, cena);
                        if (rozdiel >= 0) {
                            balanceLabel.setForeground(new Color(0, 153, 0));
                            aukcia.notifyObservers();
                        } else {
                            balanceLabel.setForeground(Color.RED);
                            aukcia.notifyObservers();
                        }
                        rankLabel.setText("" + aukcia.getPlayerRank());
                        updateButtonsVisibility();
                        if (predmet instanceof Socha) {
                            appendText("Zárobok za sochu: " + rozdiel);
                        } else if (predmet instanceof Obraz) {
                            appendText("Zárobok za obraz: " + rozdiel);
                        } else {
                            appendText("Zárobok za knihu: " + rozdiel);
                        }
                        if (aukcia.getPlayerRank().equals("Baca")) {
                            JOptionPane.showMessageDialog(this, "Vyhral/a si");
                        }
                        bots = null;
                        maxLimit = 0;
                    }
                }
                paymentField.setText("");
                lastpayment = payment;
            } catch (NumberFormatException ex) {
                ErrorHandler.showError("Zadaj sumu alebo desatinné čísla oddeľ bodkou");
            }
        } else if (buttonText.equals("Opustit")) {
            updateButtonsVisibility();
            botInfo.setText("                                         \n");
            appendText("Zostatok: " + aukcia.getPlayerBalance() + "\n");
            bots = null;
            maxLimit = 0;
            paymentField.setText("");
        }
        if (e.getSource() == saveProfitButton) {
            savedProfit = overallProfit.getProfit();
            
            OverallProfitSerializer.serializeOverallProfit(savedProfit);
            botInfo.append("Progress saved\n");
        }
        if (e.getSource() == loadProfitButton) {
            savedProfit = OverallProfitSerializer.LoadOverallProfit();
            hrac.setOverallProfit(savedProfit);
            rankLabel.setText("" + aukcia.getPlayerRank());
            botInfo.append("Saved progress loaded\n");
        }
        if (buttonText.equals("Cheat")) {
        	cheat = !cheat;
            botInfo.append("Cheat " + cheat + "\n");
        }
        if (buttonText.equals("Manual")) {
        	Manual manual = new Manual();
        	manual.setVisible(true);
        }
        if (cheat) {
            maxLimit = 0;
            lastpayment = 0;
        }
    }

    /**
     * this functions updates the buttons visibility to see the socha, kniha and obraz buttons
     */
    public void updateButtonsVisibility() {
        sochaButton.setVisible(true);
        obrazButton.setVisible(true);
        knihaButton.setVisible(true);
        kupitButton.setVisible(false);
        nekupitButton.setVisible(false);
    }

    /**
     * this function updates the buttons visibility to see the kupit and nekupit buttons
     */
    public void updtBtnVsb() {
        sochaButton.setVisible(false);
        obrazButton.setVisible(false);
        knihaButton.setVisible(false);
        kupitButton.setVisible(true);
        nekupitButton.setVisible(true);
    }

    /**
     * this function appends text to the main text area in the middle of the GUI
     * @param text whatever text that needs to be shown in the main text area
     */
    public void appendText(String text) {
    	textArea.setText(text);
    }

    /**
     * this function generates the main menu where the player puts his name and randomly picks his starting balance
     */
    public void showNameInput() {
        Meno dialog = new Meno(this, "Zadaj meno");
        dialog.setVisible(true);
        String inputText = dialog.getInputText();
        if (inputText != null && !inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Gratulujem, " + inputText + "! Tvoj nový zostatok je: " + Balance);
            JLabel inputLabel = new JLabel(inputText);
            inputLabel.setForeground(Color.decode("#E7C75F"));
            inputLabel.setBackground(Color.WHITE);
            inputLabel.setFont(new Font("Algerian", Font.BOLD, 22)); 
            rankPanel.add(inputLabel, BorderLayout.WEST);
            rankPanel.add(saveProfitButton);
            rankPanel.add(loadProfitButton);
            rankPanel.add(spacePanelRank);
            rankPanel.add(paymentLabel);
            rankPanel.add(paymentField);
        } else {
            ErrorHandler.showError("Zadaj meno!");
            dispose();
        }
    }

    /**
     * main method to lauch the JavaFX application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AukciaGUI gui = new AukciaGUI();
            try {
                ImageIcon icon = new ImageIcon("logo.png");
                if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                    System.err.println("Error loading icon image.");
                    return;
                }
                gui.setIconImage(icon.getImage());
                gui.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error setting icon: " + e.getMessage());
            }

            gui.showNameInput();
        });
    }
}