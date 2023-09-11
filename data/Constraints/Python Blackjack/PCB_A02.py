import random


class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __str__(self):
        return f"{self.value} of {self.suit}"


class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for value in range(2, 11):
                self.cards.append(Card(suit, str(value)))
            for face in ["Jack", "Queen", "King", "Ace"]:
                self.cards.append(Card(suit, face))

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
        if card.value.isdigit():
            self.value += int(card.value)
        elif card.value != "Ace":
            self.value += 10
        else:
            self.aces += 1

    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1


class Chips:
    def __init__(self):
        self.total = 100
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
            print("Sorry, please provide an integer.")
        else:
            if chips.bet > chips.total:
                print(f"Sorry, you don't have enough chips! You have {chips.total} chips.")
            else:
                break


def hit(deck, hand):
    hand.add_card(deck.deal())
    hand.adjust_for_ace()


def hit_or_stand(deck, hand):
    global playing
    while True:
        x = input("Would you like to hit or stand? Enter 'h' or 's' ")
        if x[0].lower() == 'h':
            hit(deck, hand)
        elif x[0].lower() == 's':
            print("Player stands. Dealer is playing.")
            playing = False
        else:
            print("Sorry, please try again.")
            continue
        break


def show_some(player, dealer):
    print("\nDealer's Hand:")
    print(" <hidden card>")
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

#Start the game
while True:
    print("Welcome to Blackjack! Get as close to 21 as you can without going over! Dealer hits until she reaches 17. Aces count as 1 or 11.")

    # Initialize the deck, player's hand, dealer's hand, and chips
    deck = Deck()
    deck.shuffle()
    player_hand = Hand()
    player_hand.add_card(deck.deal())
    player_hand.add_card(deck.deal())
    dealer_hand = Hand()
    dealer_hand.add_card(deck.deal())
    dealer_hand.add_card(deck.deal())
    player_chips = Chips()

    # Prompt the player for their bet
    take_bet(player_chips)

    # Show the cards
    show_some(player_hand, dealer_hand)

    # Start playing the game
    playing = True
    while playing:
        # Prompt the player to hit or stand
        hit_or_stand(deck, player_hand)
        # Show the cards
        show_some(player_hand, dealer_hand)
        # Check if the player busts
        if player_hand.value > 21:
            player_busts(player_hand, dealer_hand, player_chips)
            break

    # If the player hasn't busted, play the dealer's hand until 17
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

    # Inform the player of their total chips
    print(f"\nPlayer's total chips: {player_chips.total}")

    # Ask the player if they want to play again
    new_game = input("Would you like to play another hand? Enter 'y' or 'n' ")
    if new_game[0].lower() == 'y':
        continue
    else:
        print("Thanks for playing!")
        break