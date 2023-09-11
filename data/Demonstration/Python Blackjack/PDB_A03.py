import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def getValue(self):
        if self.rank in ["J", "Q", "K"]:
            return 10
        elif self.rank == "A":
            return 11
        else:
            return int(self.rank)


class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def addCard(self, card):
        self.cards.append(card)
        self.value += card.getValue()
        if card.rank == "A":
            self.aces += 1

    def adjustForAce(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1


class Chips:
    def __init__(self):
        self.total = 100
        self.bet = 0

    def winBet(self):
        self.total += self.bet

    def loseBet(self):
        self.total -= self.bet


def takeBet(chips):
    while True:
        try:
            chips.bet = int(input("How many chips would you like to bet? "))
        except ValueError:
            print("Please enter an integer.")
        else:
            if chips.bet > chips.total:
                print("You don't have enough chips. You have", chips.total)
            else:
                break


def hit(deck, hand):
    hand.addCard(deck.deal())
    hand.adjustForAce()


def hitOrStand(deck, hand):
    global playing
    while True:
        choice = input("Would you like to hit or stand? Enter 'h' or 's': ")
        if choice == 'h':
            hit(deck, hand)
            print("Your hand:", *hand.cards, sep='\n')
            print("Total value of your hand:", hand.value)
            if hand.value > 21:
                print("You bust.")
                chips.loseBet()
                playing = False
                break
        elif choice == 's':
            print("You stand.")
            break
        else:
            print("Invalid input. Please try again.")


def showSome(playerHand, dealerHand):
    print("\nDealer's hand:")
    print("<card hidden>")
    print(dealerHand.cards[1])
    print("\nYour hand:")
    print(*playerHand.cards[:2], sep='\n')
    print("Total value of your hand:", playerHand.cards[0].getValue() + playerHand.cards[1].getValue())

def showAll(playerHand, dealerHand):
    print("\nDealer's hand:")
    print(*dealerHand.cards, sep='\n')
    print("Total value of dealer's hand:", dealerHand.value)
    print("\nYour hand:")
    print(*playerHand.cards, sep='\n')
    print("Total value of your hand:", playerHand.value)

def playerWins(chips):
    print("You win!")
    chips.winBet()

def playerLoses(chips):
    print("Dealer wins.")
    chips.loseBet()
def dealerBusts(chips):
    print("Dealer busts. You win!")
    chips.winBet()

def dealerWins(chips):
    print("Dealer wins.")
    chips.loseBet()

def push():
    print("It's a tie.")

while True:
    print("Welcome to BlackJack! Get as close to 21 as possible without going over!\n Dealer hits until he reaches 17. Aces count as 1 or 11.")
    deck = Deck()
    deck.shuffle()

    playerHand = Hand()
    playerHand.addCard(deck.deal())
    playerHand.addCard(deck.deal())

    dealerHand = Hand()
    dealerHand.addCard(deck.deal())
    dealerHand.addCard(deck.deal())

    playerChips = Chips()
    takeBet(playerChips)

    showSome(playerHand, dealerHand)

    playing = True
    while playing:
        hitOrStand(deck, playerHand)

        if playing:
            while dealerHand.value < 17:
                hit(deck, dealerHand)

            showAll(playerHand, dealerHand)

            if dealerHand.value > 21:
                dealerBusts(playerChips)
            elif dealerHand.value > playerHand.value:
                dealerWins(playerChips)
            elif playerHand.value > dealerHand.value:
                playerWins(playerChips)
            else:
                push()

        print("\nTotal chips:", playerChips.total)

        newGame = input("Would you like to play again? Enter 'y' or 'n': ")
        if newGame == 'y':
            playing = True
            continue
        else:
            print("Thanks for playing!")
            break

