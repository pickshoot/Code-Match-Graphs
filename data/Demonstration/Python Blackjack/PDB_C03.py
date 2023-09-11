import random
card_values = {
    "Ace": 1,
    "Two": 2,
    "Three": 3,
    "Four": 4,
    "Five": 5,
    "Six": 6,
    "Seven": 7,
    "Eight": 8,
    "Nine": 9,
    "Ten": 10,
    "Jack": 10,
    "Queen": 10,
    "King": 10,
} #had to add this
class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    suits = ('Hearts', 'Diamonds', 'Clubs', 'Spades')
    ranks = ('Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight',
             'Nine', 'Ten', 'Jack', 'Queen', 'King', 'Ace')

    def __init__(self):
        self.cards = [Card(suit, rank) for suit in self.suits for rank in self.ranks]

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank == 'Ace':
            self.aces += 1
        self.value += self.get_card_value(card)

    def get_card_value(self, card):
        if card.rank in ['Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten']:
            return card_values[card.rank]
        elif card.rank in ['Jack', 'Queen', 'King']:
            return 10
        else:
            return 11

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
            print("Sorry, please enter an integer.")
        else:
            if chips.bet > chips.total:
                print(f"Sorry, you do not have enough chips. You have {chips.total} chips remaining.")
            else:
                break

def hit(deck, hand):
    hand.add_card(deck.deal())
    hand.adjust_for_ace()

def hit_or_stand(deck, hand):
    global playing

    while True:
        choice = input("Would you like to hit or stand? Enter 'h' or 's' ")

        if choice.lower() == 'h':
            hit(deck, hand)
        elif choice.lower() == 's':
            print("Player stands. Dealer is playing.")
            playing = False
        else:
            print("Sorry, please enter 'h' or 's' ")
            continue
        break

def show_some(player, dealer):
    print("\nDealer's Hand:")
    print(" <card hidden>")
    print("", dealer.cards[1])
    print("\nPlayer's Hand:", *player.cards, sep='\n ')

def show_all(player, dealer):
    print("\nDealer's Hand:", *dealer.cards, sep='\n ')
    print("Dealer's Hand =", dealer.value)
    print("\nPlayer's Hand:", *player.cards, sep='\n ')
    print("Player's Hand =", player.value)

def player_busts(player, dealer, chips):
    print("Player busts!")
    chips.lose_bet()

def player_wins(player, dealer, chips):
    print("Playerwins!")
    chips.win_bet()

def dealer_busts(player, dealer, chips):
    print("Dealer busts!")
    chips.win_bet()

def dealer_wins(player, dealer, chips):
    print("Dealer wins!")
    chips.lose_bet()

def push(player, dealer):
    print("Dealer and Player tie! It's a push.")

#GAME LOGIC
while True:
    # Print welcome message and create deck
    print("Welcome to Blackjack!")
    deck = Deck()
    deck.shuffle()
    # Set up player and dealer hands, and give them two cards each
    player_hand = Hand()
    dealer_hand = Hand()
    player_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())
    player_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())

    # Set up player chips
    player_chips = Chips()

    # Prompt player for bet
    take_bet(player_chips)

    # Show initial hands
    show_some(player_hand, dealer_hand)

    playing = True
    while playing:
        # Prompt player to hit or stand
        hit_or_stand(deck, player_hand)

        # Show updated hand
        show_some(player_hand, dealer_hand)

        # Check if player busts
        if player_hand.value > 21:
            player_busts(player_hand, dealer_hand, player_chips)
            break

    # If player hasn't busted, play dealer's hand
    if player_hand.value <= 21:
        while dealer_hand.value < 17:
            hit(deck, dealer_hand)

        # Show final hands
        show_all(player_hand, dealer_hand)

        # Check for dealer bust
        if dealer_hand.value > 21:
            dealer_busts(player_hand, dealer_hand, player_chips)
        else:
            # Compare hands and determine winner
            if player_hand.value > dealer_hand.value:
                player_wins(player_hand, dealer_hand, player_chips)
            elif player_hand.value < dealer_hand.value:
                dealer_wins(player_hand, dealer_hand, player_chips)
            else:
                push(player_hand, dealer_hand)

    # Show player's total chips
    print(f"\nPlayer's total chips: {player_chips.total}")

    # Ask player if they want to play again
    play_again = input("Would you like to play again? Enter 'y' or 'n' ")
    if play_again.lower() == 'y':
        continue
    else:
        print("Thanks for playing!")
        break
