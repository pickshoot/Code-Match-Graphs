import random

# Initialize the deck of cards
deck = [
    "Ace of Spades", "2 of Spades", "3 of Spades", "4 of Spades", "5 of Spades", "6 of Spades", 
    "7 of Spades", "8 of Spades", "9 of Spades", "10 of Spades", "Jack of Spades", "Queen of Spades", "King of Spades", 
    "Ace of Hearts", "2 of Hearts", "3 of Hearts", "4 of Hearts", "5 of Hearts", "6 of Hearts", 
    "7 of Hearts", "8 of Hearts", "9 of Hearts", "10 of Hearts", "Jack of Hearts", "Queen of Hearts", "King of Hearts",
    "Ace of Clubs", "2 of Clubs", "3 of Clubs", "4 of Clubs", "5 of Clubs", "6 of Clubs", 
    "7 of Clubs", "8 of Clubs", "9 of Clubs", "10 of Clubs", "Jack of Clubs", "Queen of Clubs", "King of Clubs", 
    "Ace of Diamonds", "2 of Diamonds", "3 of Diamonds", "4 of Diamonds", "5 of Diamonds", "6 of Diamonds", 
    "7 of Diamonds", "8 of Diamonds", "9 of Diamonds", "10 of Diamonds", "Jack of Diamonds", "Queen of Diamonds", "King of Diamonds"
]

# Initialize the values of the cards
card_values = {
    "Ace of Spades": 11, "2 of Spades": 2, "3 of Spades": 3, "4 of Spades": 4, "5 of Spades": 5, "6 of Spades": 6, 
    "7 of Spades": 7, "8 of Spades": 8, "9 of Spades": 9, "10 of Spades": 10, "Jack of Spades": 10, "Queen of Spades": 10, "King of Spades": 10, 
    "Ace of Hearts": 11, "2 of Hearts": 2, "3 of Hearts": 3, "4 of Hearts": 4, "5 of Hearts": 5, "6 of Hearts": 6, 
    "7 of Hearts": 7, "8 of Hearts": 8, "9 of Hearts": 9, "10 of Hearts": 10, "Jack of Hearts": 10, "Queen of Hearts": 10, "King of Hearts": 10,
    "Ace of Clubs": 11, "2 of Clubs": 2, "3 of Clubs": 3, "4 of Clubs": 4, "5 of Clubs": 5, "6 of Clubs": 6, 
    "7 of Clubs": 7, "8 of Clubs": 8, "9 of Clubs": 9, "10 of Clubs": 10, "Jack of Clubs": 10, "Queen of Clubs": 10, "King of Clubs": 10, 
    "Ace of Diamonds": 11, "2 of Diamonds": 2, "3 of Diamonds": 3, "4 of Diamonds": 4, "5 of Diamonds": 5, "6 of Diamonds": 6, 
    "7 of Diamonds": 7, "8 of Diamonds": 8, "9 of Diamonds": 9, "10 of Diamonds": 10, "Jack of Diamonds": 10, "Queen of Diamonds": 10, "King of Diamonds": 10
}

# Initialize the player's hand and score
player_hand = []
player_score = 0

# Initialize the dealer's hand and score
dealer_hand = []
dealer_score = 0

# Shuffle the deck
random.shuffle(deck)

# Deal two cards to the player and two cards to the dealer
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())

# Calculate the player's score
for card in player_hand:
    player_score += card_values[card]

# Calculate the dealer's score
for card in dealer_hand:
    dealer_score += card_values[card]

# Print the initial hands and scores
print("Player's hand:", player_hand)
print("Player's score:", player_score)
print("Dealer's hand:", dealer_hand[0])
print("Dealer's score: ?", "\n")

# Player's turn
while player_score < 21:
    # Ask the player if they want to hit or stand
    action = input("Do you want to hit or stand? ")
    if action == "hit":
        # Deal another card to the player
        player_hand.append(deck.pop())
        player_score = 0
        for card in player_hand:
            player_score += card_values[card]
        # Print the updated hand and score
        print("Player's hand:", player_hand)
        print("Player's score:", player_score)
    elif action == "stand":
        break

# Dealer's turn
while dealer_score < 17:
    # Deal another card to the dealer
    dealer_hand.append(deck.pop())
    dealer_score = 0
    for card in dealer_hand:
        dealer_score += card_values[card]

# Determine the winner
if player_score > 21:
    print("Player busts! Dealer wins.")
elif dealer_score > 21:
    print("Dealer busts! Player wins.")
elif player_score > dealer_score:
    print("Player wins!")
elif player_score < dealer_score:
    print("Dealer wins!")
else:
    print("It's a tie!")

# Print the final hands and scores
print("Player's hand:", player_hand)
print("Player's score:", player_score)
print("Dealer's hand:", dealer_hand)
print("Dealer's score:", dealer_score)