import random

# Define global variables
suits = ('Hearts', 'Diamonds', 'Clubs', 'Spades')
ranks = ('Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Jack', 'Queen', 'King', 'Ace')
values = {'Two': 2, 'Three': 3, 'Four': 4, 'Five': 5, 'Six': 6, 'Seven': 7, 'Eight': 8, 'Nine': 9, 'Ten': 10, 'Jack': 10, 'Queen': 10, 'King': 10, 'Ace': 11}

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = values[rank]
    
    def __str__(self):
        return f"{self.rank} of {self.suit}"
        
class Deck:
    def __init__(self):
        self.deck = []
        for suit in suits:
            for rank in ranks:
                card = Card(suit, rank)
                self.deck.append(card)
    
    def shuffle(self):
        random.shuffle(self.deck)
    
    def deal(self):
        card = self.deck.pop()
        return card

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0
    
    def add_card(self, card):
        self.cards.append(card)
        self.value += card.value
        if card.rank == 'Ace':
            self.aces += 1
    
    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

class Chips:
    def __init__(self, total=100):
        self.total = total
        self.bet = 0
    
    def win_bet(self):
        self.total += self.bet
    
    def lose_bet(self):
        self.total -= self.bet

def take_bet(chips):
    while True:
        try:
            chips.bet = int(input("How many chips would you like to bet? "))
        except ValueError:
            print("Please enter a valid integer.")
        else:
            if chips.bet > chips.total:
                print(f"Sorry, you don't have enough chips. You have {chips.total} chips.")
            else:
                break

def hit(deck, hand):
    hand.add_card(deck.deal())
    hand.adjust_for_ace()

def hit_or_stand(deck, hand):
    global playing
    
    while True:
        choice = input("Would you like to hit or stand? Enter 'h' or 's': ")
        
        if choice.lower() == 'h':
            hit(deck, hand)
        elif choice.lower() == 's':
            print("Player stands. Dealer is playing.")
            playing = False
        else:
            print("Sorry, please enter 'h' or 's'.")
            continue
        break

def show_some(player, dealer):
    print("\nDealer's Hand:")
    print("<card hidden>")
    print(dealer.cards[1])
    print("\nPlayer's Hand:", *player.cards, sep='\n ')
    
def show_all(player, dealer):
    print("\nDealer's Hand:", *dealer.cards, sep='\n ')
    print("Dealer's Hand =",dealer.value)
    print("\nPlayer's Hand:", *player.cards, sep='\n ')
    print("Player's Hand =",player.value)

def player_busts(player, dealer, chips):
    print("Player busts!")
    chips.lose_bet()

def player_wins(player, dealer, chips):
    print("Player wins!")
    chips.win_bet()

def dealer_busts(player, dealer, chips):
    print("Dealer busts!")
    chips.win_bet()
    
def dealer_wins(player, dealer, chips):
    print("Dealer wins!")
    chips.lose_bet()
    
def push(player, dealer):
    print("It's a tie!")

# Set up the game
while True:
    print("Welcome to Blackjack! Get as close to 21 as you can without going over!\n\
          Dealer hits until she reaches 17. Aces count as 1 or 11.")
    
    # Create and shuffle the deck, deal two cards to each player
    deck = Deck()
    deck.shuffle()
    
    player_hand = Hand()
    player_hand.add_card(deck.deal())
    player_hand.add_card(deck.deal())
    
    dealer_hand = Hand()
    dealer_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())
    
    # Set up the player's chips
    player_chips = Chips()
    
    # Prompt the player for their bet
    take_bet(player_chips)
    
    # Show the cards
    show_some(player_hand, dealer_hand)
    
    playing = True
    
    while playing:
        # Prompt the player to hit or stand
        hit_or_stand(deck, player_hand)
        
        # Show the cards
        show_some(player_hand, dealer_hand)
        
        # Check if player busts
        if player_hand.value > 21:
            player_busts(player_hand, dealer_hand, player_chips)
            break
    
    # If player hasn't busted, play the dealer's hand
    if player_hand.value <= 21:
        while dealer_hand.value < 17:
            hit(deck, dealer_hand)
        
        # Show all cards
        show_all(player_hand, dealer_hand)
        
        # Check for winning conditions
        if dealer_hand.value > 21:
            dealer_busts(player_hand, dealer_hand, player_chips)
        elif dealer_hand.value > player_hand.value:
            dealer_wins(player_hand, dealer_hand, player_chips)
        elif dealer_hand.value < player_hand.value:
            player_wins(player_hand, dealer_hand, player_chips)
        else:
            push(player_hand, dealer_hand)
    
    # Show the player's total chips
    print(f"\nPlayer's total chips: {player_chips.total}")
    
    # Ask to play again
    play_again = input("Would you like to play again? Enter 'y' or 'n': ")
    if play_again.lower() == 'y':
        continue
    else:
        print("Thanks for playing!")
        break