import random

# Define the card values
CARD_VALUES = {
"Ace": 11,
"2": 2,
"3": 3,
"4": 4,
"5": 5,
"6": 6,
"7": 7,
"8": 8,
"9": 9,
"10": 10,
"Jack": 10,
"Queen": 10,
"King": 10
}

# Define the deck
DECK = list(CARD_VALUES.keys()) * 4

# Define the player and dealer classes
class Player:
    def __init__(self):
        self.hand = []

    def add_card(self, card):
        self.hand.append(card)

    def get_hand_value(self):
        # Calculate the total value of the hand
        hand_value = sum([CARD_VALUES[card] for card in self.hand])

        # If the hand contains an Ace and the value is over 21, adjust the value down by 10
        if "Ace" in self.hand and hand_value > 21:
            hand_value -= 10

        return hand_value

class Dealer(Player):
    def __init__(self):
        super().__init__()

    def play(self):
        # Dealer must hit until their hand value is 17 or greater
        while self.get_hand_value() < 17:
            self.add_card(random.choice(DECK))

# Define the game loop
def play_game():
    # Initialize the player and dealer
    player = Player()
    dealer = Dealer()
    # Shuffle the deck
    random.shuffle(DECK)

    # Deal the initial cards
    player.add_card(random.choice(DECK))
    dealer.add_card(random.choice(DECK))
    player.add_card(random.choice(DECK))
    dealer.add_card(random.choice(DECK))

    # Print the initial hands
    print(f"Player's hand: {player.hand}")
    print(f"Dealer's hand: [{dealer.hand[0]}, *]")

    # Player's turn
    while player.get_hand_value() < 21:
        hit_or_stand = input("Do you want to hit or stand? ").lower()

        if hit_or_stand == "hit":
            player.add_card(random.choice(DECK))
            print(f"Player's hand: {player.hand}")
        elif hit_or_stand == "stand":
            break

    # Determine the outcome of the game
    if player.get_hand_value() > 21:
        print("Player busts! Dealer wins.")
    else:
        # Dealer's turn
        print(f"Dealer's hand: {dealer.hand}")
        dealer.play()

        if dealer.get_hand_value() > 21:
            print("Dealer busts! Player wins.")
        elif dealer.get_hand_value() > player.get_hand_value():
            print("Dealer wins.")
        elif dealer.get_hand_value() < player.get_hand_value():
            print("Player wins.")
        else:
            print("It's a tie!")

# Play the game
play_game()
