#Import necessary libraries
import random
class Suit:
    DIAMONDS = "Diamonds"
    CLUBS = "Clubs"
    HEARTS = "Hearts"
    SPADES = "Spades"

class Rank:
    ACE = 1
    TWO = 2
    THREE = 3
    FOUR = 4
    FIVE = 5
    SIX = 6
    SEVEN = 7
    EIGHT = 8
    NINE = 9
    TEN = 10
    JACK = 11
    QUEEN = 12
    KING = 13

    def __init__(self, value):
        self.value = value


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def getValue(self):
        return self.rank.value

    def getBonus(self):
        if self.suit == Suit.DIAMONDS:
            return 4
        elif self.suit == Suit.CLUBS:
            return 6
        elif self.suit == Suit.HEARTS:
            return 8
        else:
            return 10

    def __str__(self):
        rank_names = {1: "ACE", 2: "TWO", 3: "THREE", 4: "FOUR", 5: "FIVE", 6: "SIX", 7: "SEVEN", 8: "EIGHT", 9: "NINE", 10: "TEN", 11: "JACK", 12: "QUEEN", 13: "KING"}
        return f"{rank_names[self.rank.value]} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        # Create a new deck of cards and add each card to it
        for suit in [Suit.DIAMONDS, Suit.CLUBS, Suit.HEARTS, Suit.SPADES]:
            for rank in [Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING]:
                self.cards.append(Card(suit, Rank(rank)))

    def shuffle(self):
        # Shuffle the deck to randomize the order of the cards
        random.shuffle(self.cards)

    def isEmpty(self):
        # Check if the deck is empty to determine if the game should continue or end
        return not bool(self.cards)

    def drawCard(self):
        if self.isEmpty():
            return None
        return self.cards.pop(0)

def play():
    print("\n------- Playing a game round")
    # Draw three cards from the deck to play a game round
    card1 = deck.drawCard()
    card2 = deck.drawCard()
    card3 = deck.drawCard()
    # Print the value and bonus of each card drawn
    print(f"Card 1: {card1} → Value = {card1.getValue() + card1.getBonus()}")
    print(f"Card 2: {card2} → Value = {card2.getValue() + card2.getBonus()}")
    print(f"Card 3: {card3} → Value = {card3.getValue() + card3.getBonus()}")

    # Determine if the player has won or lost the game round based on the values of the cards.
    value1 = card1.getValue() + card1.getBonus()
    value2 = card2.getValue() + card2.getBonus()
    value3 = card3.getValue() + card3.getBonus()
    if (value3 > value1 and value3 < value2) or (value3 < value1 and value3 > value2):
        print("\nYou win!")
    else:
        print("\nYou lose.")


# Main program
deck = Deck()
deck.shuffle()

print("Welcome to LuckyCard game!")

# Loop until the deck is empty or the user quits
while not deck.isEmpty():
    # Prompt the user to play a game round or quit
    choice = input("Press 'p' to play a game round, or 'q' to quit: ")
    if choice == 'p':
        play()
    elif choice == 'q':
        print("Thanks for playing!")
        break
    else:
        print("Invalid input. Please try again.")
