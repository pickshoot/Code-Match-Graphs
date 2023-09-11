package blackJack;

import javax.swing.JButton;

import blackJack.GUI.ButtonName;
import blackJack.Hand.Result;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener{
	
	private ArrayList<Card> usedCards = new ArrayList<Card>();
	private Deck deck;
	private GUI gui;
	private Player player;
	private Dealer dealer;
	private int currentHand;
	private int activePlayers;
	private int cashBeforeGame;
	
	public Game() {
		activePlayers = 0;
		currentHand = 0;
		deck = new Deck();
		gui = new GUI(this);
		player = new Player();
		dealer = new Dealer();
		
	}
		
	private void deal() {
        for(int i = 0; i < 2 ; i++) {
            for(int j = 0; j < player.arrayOfHands.size(); j++) {
                player.addCard(deck.getCard(0), j);
            }
            dealer.addCard(deck.getCard(0), 0);
            gui.displayCard(dealer);
        }
    }
	
	//Runs player.split and also adds one new Card to each hand with less than two Cards.
	private void splitHand() {
		player.split(currentHand, player.arrayOfHands.get(currentHand).getBet());
		for(int k = 0; k < player.arrayOfHands.size(); k++) {
			if(player.arrayOfHands.get(k).getSize() < 2 ) {
				player.addCard(deck.getCard(0), k);
				gui.displayCard(player);
			}
		}
	}
	
	private void setActiveHand() {
		player.arrayOfHands.get(currentHand).setResult(Result.active);
	}
	
	//Checks the conditions for the hand in play. Allows split if requirements are met, auto-stands if valueOfHand >= 21.
	private void checkHand() {
		setActiveHand();
		gui.buttonArray.get(ButtonName.Split.ordinal()).setEnabled(false);
		gui.displayCard(player);
		Card tempCard1  = player.getCard(currentHand, 0);
		Card tempCard2  = player.getCard(currentHand, 1);
		if(tempCard1.getCardValue() == tempCard2.getCardValue() && player.arrayOfHands.get(currentHand).getSize() < 3 
				&& player.arrayOfHands.get(currentHand).getBet() <= player.getAccountBalance()) {
			offerSplit();
		}
		
		if(player.arrayOfHands.get(currentHand).getValue() < 21) {
			stayOrTakeCard();
		}
		else if(player.arrayOfHands.get(currentHand).getValue() == 21) {
			activePlayers++;
			stand();
		}
		else{
			player.arrayOfHands.get(currentHand).setResult(Result.lost);
			stand();
			
			gui.updateInfo(player);
		}
	}
	
	private void offerSplit() {
		gui.buttonArray.get(ButtonName.Split.ordinal()).setEnabled(true);
	}
	
	private void stayOrTakeCard() {
		gui.buttonArray.get(ButtonName.Hit.ordinal()).setEnabled(true);
		gui.buttonArray.get(ButtonName.Stand.ordinal()).setEnabled(true);
	}
	
	//Controls how many Cards dealer takes, compareHands and updates GUI.
	private void checkWinCondition() {
		gui.revealCard(dealer);
		gui.displayCard(dealer);
		while(dealer.arrayOfHands.get(0).getValue() < 17 && activePlayers > 0) {
			dealer.addCard(deck.getCard(0), 0);
			gui.displayCard(dealer);
		}
		for(int i = 0; i < player.arrayOfHands.size(); i++) {
			compareHands(player.arrayOfHands.get(i));
		}
		if(gui.buttonArray.get(ButtonName.Split.ordinal()).isEnabled()) {
			gui.buttonArray.get(ButtonName.Split.ordinal()).setEnabled(false);
		}
		gui.buttonArray.get(ButtonName.PlayRound.ordinal()).setEnabled(true);
		gui.updateInfo(player);
		gui.setTopPanelText(player.getAccountBalance(), cashBeforeGame);
	}
	
	//Determines if the player hand beat the dealer hand.
	private void compareHands(Hand playerHand) {
		if(dealer.arrayOfHands.get(0).getValue() > 21 && playerHand.getValue() <= 21) {
			playerHand.setResult(Result.won);
			player.getWin(playerHand.getBet());
		}
		else if(playerHand.getValue() > 21) {
			playerHand.setResult(Result.lost);
		}
		else {
			if(playerHand.getValue() <= 21 && playerHand.getValue() > dealer.arrayOfHands.get(0).getValue()) {
				playerHand.setResult(Result.won);
				player.getWin(playerHand.getBet());
			}
			else if(playerHand.getValue() <= 21 && playerHand.getValue() == dealer.arrayOfHands.get(0).getValue()) {
				player.getWin(playerHand.getBet() / 2);
				playerHand.setResult(Result.undecided);
			}
			else {
				playerHand.setResult(Result.lost);
			}
		}
	}
	
	//Prepares for a new rounds by collecting all used Cards, clears GUI, re-stack and shuffles the deck if necessary.
	private void resetGame() {
		gui.clearPlayingField();
		cashBeforeGame = player.getAccountBalance();
		activePlayers = 0;
		for(int i = 0; i < player.arrayOfHands.size(); i++) {
			for(int k = 0; k < player.arrayOfHands.get(i).getSize(); k++) {
				usedCards.add(player.arrayOfHands.get(i).getCard(k));
			}
		}
		while(!player.arrayOfHands.isEmpty()) {
			player.arrayOfHands.remove(0);
		}
		for(int j = 0; j < dealer.arrayOfHands.get(0).getSize(); j++) {
			usedCards.add(dealer.arrayOfHands.get(0).getCard(j));
		}
		while(!dealer.arrayOfHands.isEmpty()) {
			dealer.arrayOfHands.remove(0);
		}
		if(deck.getSize() < 15) {
			deck.addCards(usedCards);
			deck.shuffleDeck();
			usedCards.clear();
		}
		gui.buttonArray.get(ButtonName.Bet10.ordinal()).setEnabled(true);
		gui.buttonArray.get(ButtonName.Bet20.ordinal()).setEnabled(true);
		gui.buttonArray.get(ButtonName.PlayRound.ordinal()).setEnabled(false);
		player.newHand();
		dealer.newHand();
		currentHand = 0;
		gui.setupPlayingField();
		if(player.getAccountBalance() <= 0) {
			gui.gameOver();
		}
	}
	
	//Updates currentHand by 1. Calls checkWinCondition if no more hands can be played.
	private void stand() {
		if(player.arrayOfHands.get(currentHand).getResult().equals(Result.active)){
            player.arrayOfHands.get(currentHand).setResult(Result.undecided);
        }
        currentHand++;
        if(currentHand >= player.arrayOfHands.size()) {
            gui.buttonArray.get(ButtonName.Hit.ordinal()).setEnabled(false);
            gui.buttonArray.get(ButtonName.Stand.ordinal()).setEnabled(false);
            checkWinCondition();
        }
        else {
            checkHand();
        }
    }
	// Checks if player.bet return true if so deal cards and updates GUI and starts the round.
	private void bet(int betAmount) {
		if(player.bet(betAmount)) {
			gui.setVisibleGameInstructions(false);
    		deal();
    		player.arrayOfHands.get(0).setBet(betAmount);
        	gui.displayCard(player);
        	gui.buttonArray.get(ButtonName.Bet10.ordinal()).setEnabled(false);
        	gui.buttonArray.get(ButtonName.Bet20.ordinal()).setEnabled(false);
        	gui.setTopPanelText(player.getAccountBalance());
        	checkHand();
    	}
		else {
			gui.gameOver();
		}
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        for(JButton button: gui.buttonArray) {
            if(e.getSource().equals(button)) {
                System.out.println(button.getText());
                if(button.getText().equals(gui.buttonArray.get(ButtonName.Bet10.ordinal()).getText())) {
                    bet(10);
                }
                if(button.getText().equals(gui.buttonArray.get(ButtonName.Bet20.ordinal()).getText())) {
                    bet(20);
                }
                if(button.getText().equals(gui.buttonArray.get(ButtonName.Split.ordinal()).getText())) {
                    splitHand();
                    checkHand();
                }
                if(button.getText().equals(gui.buttonArray.get(ButtonName.Stand.ordinal()).getText())) {
                    activePlayers++;
                    stand();
                }
                if(button.getText().equals(gui.buttonArray.get(ButtonName.Hit.ordinal()).getText())) {
                    button.setEnabled(false);
                    player.addCard(deck.getCard(0), currentHand);
                    checkHand();
                }
                if(button.getText().equals(gui.buttonArray.get(ButtonName.PlayRound.ordinal()).getText())) {
                    gui.buttonArray.get(ButtonName.PlayRound.ordinal()).setBackground(null);
                    resetGame();
                }
            }
        }
    }
}
