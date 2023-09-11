import random


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:
    def __init__(self):
        self.cards = []
        suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
        ranks = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
        for suit in suits:
            for rank in ranks:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
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
        if card.rank == "Ace":
            self.aces += 1
        self.value += self.card_value(card)

    def card_value(self, card):
        if card.rank in ["2", "3", "4", "5", "6", "7", "8", "9", "10"]:
            return int(card.rank)
        elif card.rank in ["Jack", "Queen", "King"]:
            return 10
        else:
            return 11

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
            print("Sorry, please enter a valid integer.")
        else:
            if chips.bet > chips.total:
                print(f"Sorry, you don't have enough chips. You have {chips.total} chips remaining.")
            else:
                break


def hit(deck, hand):
    hand.add_card(deck.deal_card())
    hand.adjust_for_ace()

def hit_or_stand(deck, hand):
    global playing
    while True:
        choice = input("Would you like to hit or stand? Enter 'h' or 's' ").lower()

        if choice == 'h':
            hit(deck, hand)

        elif choice == 's':
            print("Player stands. Dealer is playing.")
            playing = False

        else:
            print("Sorry, please try again.")
            continue

        break

def show_some(player, dealer):
    print("\nDealer's Hand:")
    print(" <card hidden>")
    print(f" {dealer.cards[1]}")
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

while True:
    print("Welcome to Blackjack! Get as close to 21 as you can without going over!\n")

    # Create and shuffle the deck
    deck = Deck()
    deck.shuffle()

    # Create player and dealer hands
    player_hand = Hand()
    player_hand.add_card(deck.deal_card())
    player_hand.add_card(deck.deal_card())

    dealer_hand = Hand()
    dealer_hand.add_card(deck.deal_card())
    dealer_hand.add_card(deck.deal_card())

    # Set up player's chips
    player_chips = Chips()

    # Prompt the player for their bet
    take_bet(player_chips)

    # Show cards (but keep dealer's second card hidden)
    show_some(player_hand, dealer_hand)

    playing = True

    while playing:
        # Prompt for player to hit or stand
        hit_or_stand(deck, player_hand)

        # Show cards (but keep dealer's second card hidden)
        show_some(player_hand, dealer_hand)

        # Check if player has busted
        if player_hand.value > 21:
            player_busts(player_hand, dealer_hand, player_chips)
            break

    # If player hasn't busted, play dealer's hand until dealer reaches 17
    if player_hand.value <= 21:
        while dealer_hand.value < 17:
            hit(deck, dealer_hand)

        # Show all cards
        show_all(player_hand, dealer_hand)

        # Check for winning scenarios
        if dealer_hand.value > 21:
            dealer_busts(player_hand, dealer_hand, player_chips)

        elif dealer_hand.value > player_hand.value:
            dealer_wins(player_hand, dealer_hand, player_chips)

        elif dealer_hand.value < player_hand.value:
            player_wins(player_hand, dealer_hand, player_chips)

        else:
            push(player_hand, dealer_hand)

    # Inform the player of their chip total
    print(f"\nPlayer's total chips: {player_chips.total}")

    # Ask to play again
    play_again = input("Would you like to play again? Enter 'y' or 'n' ").lower()

    if play_again == 'y':
        continue
    else:
        print("Thanks for playing!")
        break