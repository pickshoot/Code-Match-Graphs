import random

# Define global variables
suits = ['hearts', 'diamonds', 'clubs', 'spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10,
          'King': 10}


# Define classes
class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = values[rank]

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:
    def __init__(self):
        self.cards = []
        for suit in suits:
            for rank in ranks:
                self.cards.append(Card(suit, rank))
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()


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

    def __str__(self):
        hand_str = ""
        for card in self.cards:
            hand_str += str(card) + "\n"
        return hand_str


class Chips:
    def __init__(self, total=100):
        self.total = total
        self.bet = 0

    def win_bet(self):
        self.total += self.bet

    def lose_bet(self):
        self.total -= self.bet


# Define functions
def take_bet(chips):
    while True:
        try:
            chips.bet = int(input("How many chips would you like to bet? "))
        except ValueError:
            print("Sorry, please provide an integer.")
        else:
            if chips.bet > chips.total:
                print("Sorry, you don't have enough chips! You have", chips.total)
            else:
                break


def hit(deck, hand):
    hand.add_card(deck.deal_card())
    hand.adjust_for_ace()


def hit_or_stand(deck, hand):
    global playing
    while True:
        choice = input("Would you like to Hit or Stand? Enter 'h' or 's' ")
        if choice[0].lower() == 'h':
            hit(deck, hand)
        elif choice[0].lower() == 's':
            print("Player stands. Dealer is playing.")
            playing = False
        else:
            print("Sorry, please try again.")
            continue
        break


def show_some(player, dealer):
    print("\nDealer's Hand:")
    print(" <card hidden>")
    print("", dealer.cards[1])
    print("\nPlayer's Hand:", player)


def show_all(player, dealer):
    print("\nDealer's Hand:", dealer)
    print("Dealer's Hand =", dealer.value)
    print("\nPlayer's Hand:", player)
    print("Player's Hand =", player.value)


def player_busts(player_chips):
    print("Player busts!")
    player_chips.lose_bet()


def player_wins(player_chips):
    print("Player wins!")
    player_chips.win_bet()


def dealer_busts(player_chips):
    print("Dealer busts!")
    player_chips.win_bet()


def dealer_wins(player_chips):
    print("Dealer wins!")
    player_chips.lose_bet()


def push():
    print("Dealer and player tie! It's a push.")


# Game logic
player_chips = Chips()

while True:
    print(
        "Welcome to Blackjack! Get as close to 21 as you can without going over. Dealer hits until she reaches 17. Aces count as 1 or 11.")

    # Initialize deck and hands
    deck = Deck()
    player_hand = Hand()
    dealer_hand = Hand()

    # Deal two cards to each player
    player_hand.add_card(deck.deal_card())
    player_hand.add_card(deck.deal_card())
    dealer_hand.add_card(deck.deal_card())
    dealer_hand.add_card(deck.deal_card())

    # Prompt player to bet
    take_bet(player_chips)

    # Show cards
    show_some(player_hand, dealer_hand)

    playing = True
    while playing:
        # Prompt player to hit or stand
        hit_or_stand(deck, player_hand)

        # Show cards
        show_some(player_hand, dealer_hand)

        # Check if player busts
        if player_hand.value > 21:
            player_busts(player_chips)
            break

    # If player hasn't busted, dealer plays until hand value reaches 17 or greater
    if player_hand.value <= 21:
        while dealer_hand.value < 17:
            hit(deck, dealer_hand)

        # Show all cards
        show_all(player_hand, dealer_hand)

        # Check for winning scenarios
        if dealer_hand.value > 21:
            dealer_busts(player_chips)
        elif dealer_hand.value > player_hand.value:
            dealer_wins(player_chips)
        elif player_hand.value > dealer_hand.value:
            player_wins(player_chips)
        else:
            push()

    # Inform player of their current chip total
    print("\nPlayer's total chips are at:", player_chips.total)

    # Ask to play again
    new_game = input("Would you like to play again? Enter 'y' or 'n' ")
    if new_game[0].lower() == 'y':
        continue
    else:
        print("Thanks for playing!")
        break
