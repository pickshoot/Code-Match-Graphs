package blackJack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import blackJack.Hand.Result;

import java.awt.event.ActionListener;


public class GUI{
	private JFrame frame;
	private JPanel playingFieldPanel;
	
	private JPanel playerPanel;
	private JPanel dealerPanel;
	
	private JPanel buttonPanel;
	private JPanel topPanel;
	private JLabel topPanelText = new JLabel();
	
	private JPanel gameInstructionsPanel;
	
	private HandPane dealerHand;
	public ArrayList <JButton> buttonArray = new ArrayList<JButton>();
	private ArrayList <HandPane> playerHandsArray = new ArrayList<HandPane>();
	private String[] buttons = {"Hit", "Stand", "Split", "Bet 10", "Bet 20", "Play round"};

    public enum ButtonName{
        Hit, Stand, Split, Bet10, Bet20, PlayRound;
    }
	
	private ActionListener listener;
	
	public GUI(ActionListener listener) {
		this.listener = listener;
		createFrame();
		createButtonPanel();
		playingFieldPanel();
		createDealerPanel();
		createPlayerPanel();
		createTopPanel();
		createGameInstructionsPanel();
		setGameInstructions();
	}
	
	private void createFrame() {
		frame = new JFrame("BlackJack");
		frame.setLayout(new BorderLayout());
		frame.setBounds(0,0,1600,900);
		frame.setMinimumSize(new Dimension(100, 100));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		createButtons();
		for(JButton button: buttonArray) {
			buttonPanel.add(button);
		}
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		for(int i = 0; i < buttonArray.size(); i++) {
            buttonArray.get(i).setEnabled(false);
        }
        buttonArray.get(ButtonName.PlayRound.ordinal()).setEnabled(true);
	}
	
	private void playingFieldPanel() {
		playingFieldPanel = new JPanel();
		playingFieldPanel.setLayout(new BorderLayout());
		frame.add(playingFieldPanel, BorderLayout.CENTER);
	}
	
	private void createPlayerPanel() {
		playerPanel = new JPanel();
		playerPanel.setLayout(new FlowLayout());
		playingFieldPanel.add(playerPanel, BorderLayout.SOUTH);
	}
	
	private void createDealerPanel() {
		dealerPanel = new JPanel();
		dealerPanel.setLayout(new FlowLayout());
		playingFieldPanel.add(dealerPanel, BorderLayout.NORTH);
	}
	
	private void createButtons() {
		for(String s: buttons) {
			JButton button = new JButton(s);
			buttonArray.add(button);
			button.addActionListener(listener);
		}
	}
	
	private void createTopPanel() {
		topPanel = new JPanel();
		frame.add(topPanel, BorderLayout.NORTH);
		frame.setVisible(true);
		topPanel.add(topPanelText);
		topPanelText.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	}
	
	public void setTopPanelText(int money) {
		topPanelText.setText("You have " + money + "$");
	}
	
	public void setTopPanelText(int money, int moneyDifference) {
		int moneyafter = money-moneyDifference;
		if(moneyafter > 0) {
			topPanelText.setText("You have " + money + "$ You gained "+ moneyafter + "$");
		}
		else if(moneyafter < 0) {
			topPanelText.setText("You have " + money + "$ You lost "+ moneyafter + "$");
		}
		else {
			topPanelText.setText("You have " + money + "$ You tied ");
		}
		
	}
	
	private void createGameInstructionsPanel() {
		gameInstructionsPanel =  new JPanel();
		gameInstructionsPanel.setLayout(new FlowLayout());
		playingFieldPanel.add(gameInstructionsPanel, BorderLayout.CENTER);
	}
	
	
	private void setGameInstructions() {
		setVisibleGameInstructions(false);
		JLabel gameInstructionsText = new JLabel();
		gameInstructionsText.setFont(new Font("TimesRoman", Font.BOLD, 24));
		gameInstructionsText.setBorder(new EmptyBorder(100, 200, 0, 200));
		gameInstructionsText.setText("<html><header>Welcome to the BlackJack table!</header> <br> <body>Press \"Play round\" and then select bet amount.<br></body></html>");
		
		JLabel rules = new JLabel();
		rules.setBorder(new EmptyBorder(20, 400, 200, 400));
		rules.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		rules.setText(
				"<html>"
				+	"<body>"
				+		"* The goal is to beat the dealers hand without going above a score of 21."
				+	"<br>"
				+ 		"* Aces are worth 1 or 11, pictured cards like kings, queens and knights are worth 10."
				+	"<br>"
				+ 		"* The active hand is marked blue, winning hands turn green while losing hands turn red, a tied hand turns gray."
				+	"<br>"
				+		"* Two cards of equivelent value can be split creating a new hand, the active hand is marked blue, "
				+ 		"<br>if the player stands with two hands the second hand will become active."
				+ 	"</body>"
				+"</html>"
				);
		gameInstructionsPanel.add(gameInstructionsText);
		gameInstructionsPanel.add(rules);
		
		buttonArray.get(ButtonName.PlayRound.ordinal()).setBackground(Color.green);
		setVisibleGameInstructions(true);
	}
	
	public void setVisibleGameInstructions(boolean bool) {
		gameInstructionsPanel.setVisible(bool);
	}
	
	/**Displays all cards that an actor holds in their hand(s)*/
	public void displayCard(Actor actor) {
		for(int i = 0; i < actor.arrayOfHands.size(); i++) {
			if(actor.getType().equals("Player")) {
				if(playerHandsArray.size() < i+1 || playerHandsArray.size() == 0) {
					playerHandsArray.add(new HandPane());
					//updates all handPanels regardless if anything has changed. this is to avoid graphic issues when spliting hands
					for(int k = 0; k <playerHandsArray.size(); k++) {
						playerHandsArray.get(k).forceUpdateHandPane(actor.arrayOfHands.get(k));
					}
				}
				//attempts to update panel with any new cards that have been added, old cards are not re-rendered
				playerHandsArray.get(i).updateHandPane(actor.arrayOfHands.get(i));
				if(playerHandsArray.get(i).panelHasChanged()) {
					playerPanel.add(playerHandsArray.get(i).updateFrame(actor.arrayOfHands.get(i)));
				}
			}
			else if(actor.getType().equals("Dealer")){
				//creates delarHand if this is the first time calling the function after Game.resetGame();
				if(dealerHand == null) {
					dealerHand = new HandPane();
				}
				dealerHand.updateHandPane(actor.arrayOfHands.get(i));
				if(dealerHand.panelHasChanged()) {
					dealerPanel.add(dealerHand.updateFrame(actor.arrayOfHands.get(i)));
				}
			}
			else {
				System.out.println("unsupported type of actor");
			}
		}
		frame.setVisible(true);
		updateInfo(actor);
	}
	
	//updates border text with info like hand value and bet amount
	public void updateInfo(Actor actor) {
		if(actor.getType().equals("Player")) {
			for(int i = 0; i < actor.arrayOfHands.size(); i++) {
				int value = actor.arrayOfHands.get(i).getValue();
				TitledBorder titledBorder = BorderFactory.createTitledBorder(actor.getType()+" Hand, Value " + value + ", bet amount: " + actor.arrayOfHands.get(i).getBet());
				Result result = actor.arrayOfHands.get(i).getResult();
				switch(result) {
				case undecided: break;
				case lost:	titledBorder.setTitleColor(Color.RED); 		break;
				case won: 	titledBorder.setTitleColor(Color.GREEN);	break;
				case active: titledBorder.setTitleColor(Color.blue); 	break;
				}
				playerHandsArray.get(i).setBorder(titledBorder);
			}
		}
		
		else if(actor.getType().equals("Dealer")) {
			int value = actor.arrayOfHands.get(0).getValue();
			//the dealers first card is hidden from the start so the value is subtracted from the total
			if(actor.arrayOfHands.get(0).getCard(0).getHidden()) {
				value = value - actor.arrayOfHands.get(0).getCard(0).getCardValue();
			}
			TitledBorder titledBorder = BorderFactory.createTitledBorder(actor.getType()+" Hand, Value " + value);
			dealerHand.setBorder(titledBorder);
		}
	}
	
	public void revealCard(Dealer dealer) {
		dealerHand.revealCard(dealer);
	}
	
	public void clearPlayingField() {
		for(int i = 0; i < playerHandsArray.size(); i++) {
			System.out.println("invis");
			playerHandsArray.get(i).setVisible(false);
			frame.setVisible(true);
		}
		playerHandsArray.clear();
		//prevents fatal errors.
		if(dealerHand != null) {
			dealerHand.setVisible(false);
			dealerPanel.removeAll();
			dealerHand = null;
		}
		
	}
	
	public void setupPlayingField() {
        for(int i = 0; i < playerHandsArray.size(); i++) {
        	playerHandsArray.get(i).setVisible(true);
            frame.setVisible(true);
        }
        //dealerHand.setVisible(true);
    }
	
	public void gameOver() {
		gameInstructionsPanel.removeAll();
		JLabel lbl = new JLabel();
		lbl.setFont(new Font("TimesRoman", Font.BOLD, 40));
		lbl.setText("Gameover");
		gameInstructionsPanel.add(lbl);
		setVisibleGameInstructions(true);
	}
}
