import random

suits = ('Hearts', 'Diamonds', 'Clubs', 'Spades')
ranks = ('Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Jack', 'Queen', 'King', 'Ace')
values = {'Two': 2, 'Three': 3, 'Four': 4, 'Five': 5, 'Six': 6, 'Seven': 7, 'Eight': 8, 'Nine': 9, 'Ten': 10, 'Jack': 10, 'Queen': 10, 'King': 10, 'Ace': 11}

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        
    def __str__(self):
        return f"{self.rank} of {self.suit}"
        
class Deck:
    def __init__(self):
        self.cards = []
        for suit in suits:
            for rank in ranks:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)
        
    def deal(self):
        return self.cards.pop()
        
class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += values[card.rank]
        
    def adjust_for_ace(self):
        while self.value > 21 and 'Ace' in [card.rank for card in self.cards]:
            self.value -= 10
            for card in self.cards:
                if card.rank == 'Ace':
                    card.rank = 'Ace (1)'
                    break

def play_game():
    print("Welcome to Blackjack!")

    # Create a new deck and shuffle it
    deck = Deck()
    deck.shuffle()

    # Create the player's and dealer's hands
    player_hand = Hand()
    dealer_hand = Hand()

    # Deal the initial two cards to the player and dealer
    player_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())
    player_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())

    # Display the player's hand and one of the dealer's cards
    print("Your hand:", *player_hand.cards, sep='\n')
    print("Dealer's hand:", dealer_hand.cards[0], "\n")

    # Player's turn
    while True:
        choice = input("Would you like to hit or stand? ")
        
        if choice.lower() == 'hit':
            player_hand.add_card(deck.deal())
            print("Your hand:", *player_hand.cards, sep='\n')
            
            if player_hand.value > 21:
                print("Bust! You lose.")
                return
            
        elif choice.lower() == 'stand':
            break

    # Dealer's turn
    while dealer_hand.value < 17:
        dealer_hand.add_card(deck.deal())
        # Show the dealer's hand
        print("Dealer's hand:", *dealer_hand.cards, sep='\n')

        # Determine the winner
        if dealer_hand.value > 21:
            print("Dealer busts! You win.")
        elif player_hand.value > dealer_hand.value:
            print("You win!")
        elif player_hand.value < dealer_hand.value:
            print("You lose.")
        else:
            print("It's a tie.")

        # Ask the player if they want to play again
        while True:
            choice = input("Would you like to play again? (y/n) ")
            if choice.lower() == 'y':
                play_game()
            elif choice.lower() == 'n':
                print("Thanks for playing!")
                return
            else:
                print("Invalid input. Please enter 'y' or 'n'.")

if __name__ == '__main__':
    play_game()