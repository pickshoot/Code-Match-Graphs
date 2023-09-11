import random

# Define the cards in a deck
deck = [
    "Ace of Spades", "2 of Spades", "3 of Spades", "4 of Spades", "5 of Spades",
    "6 of Spades", "7 of Spades", "8 of Spades", "9 of Spades", "10 of Spades",
    "Jack of Spades", "Queen of Spades", "King of Spades",
    "Ace of Hearts", "2 of Hearts", "3 of Hearts", "4 of Hearts", "5 of Hearts",
    "6 of Hearts", "7 of Hearts", "8 of Hearts", "9 of Hearts", "10 of Hearts",
    "Jack of Hearts", "Queen of Hearts", "King of Hearts",
    "Ace of Clubs", "2 of Clubs", "3 of Clubs", "4 of Clubs", "5 of Clubs",
    "6 of Clubs", "7 of Clubs", "8 of Clubs", "9 of Clubs", "10 of Clubs",
    "Jack of Clubs", "Queen of Clubs", "King of Clubs",
    "Ace of Diamonds", "2 of Diamonds", "3 of Diamonds", "4 of Diamonds",
    "5 of Diamonds", "6 of Diamonds", "7 of Diamonds", "8 of Diamonds",
    "9 of Diamonds", "10 of Diamonds", "Jack of Diamonds", "Queen of Diamonds",
    "King of Diamonds"
]

# Define the values of each card
card_values = {
    "Ace of Spades": 1, "2 of Spades": 2, "3 of Spades": 3, "4 of Spades": 4,
    "5 of Spades": 5, "6 of Spades": 6, "7 of Spades": 7, "8 of Spades": 8,
    "9 of Spades": 9, "10 of Spades": 10, "Jack of Spades": 10,
    "Queen of Spades": 10, "King of Spades": 10,
    "Ace of Hearts": 1, "2 of Hearts": 2, "3 of Hearts": 3, "4 of Hearts": 4,
    "5 of Hearts": 5, "6 of Hearts": 6, "7 of Hearts": 7, "8 of Hearts": 8,
    "9 of Hearts": 9, "10 of Hearts": 10, "Jack of Hearts": 10,
    "Queen of Hearts": 10, "King of Hearts": 10,
    "Ace of Clubs": 1, "2 of Clubs": 2, "3 of Clubs": 3, "4 of Clubs": 4,
    "5 of Clubs": 5, "6 of Clubs": 6, "7 of Clubs": 7, "8 of Clubs": 8,
    "9 of Clubs": 9, "10 of Clubs": 10, "Jack of Clubs": 10,
    "Queen of Clubs": 10, "King of Clubs": 10,
    "Ace of Diamonds": 1, "2 of Diamonds": 2, "3 of Diamonds": 3,
    "4 of Diamonds": 4, "5 of Diamonds": 5, "6 of Diamonds": 6,
    "7 of Diamonds": 7, "8 of Diamonds": 8, "9 of Diamonds":9,
    "10 of Diamonds": 10, "Jack of Diamonds": 10, "Queen of Diamonds": 10,
    "King of Diamonds": 10
}
#Define the initial state of the game

player_hand = []
dealer_hand = []
game_over = False
#Define functions to deal cards and calculate hand values

def deal_card(hand):
    card = random.choice(deck)
    hand.append(card)
    deck.remove(card)

def calculate_hand_value(hand):
    hand_value = sum(card_values[card] for card in hand)
    num_aces = sum(1 for card in hand if card.startswith("Ace"))
    while num_aces > 0 and hand_value <= 11:
        hand_value += 10
        num_aces -= 1
    return hand_value
# two cards to the player and dealer

deal_card(player_hand)
deal_card(player_hand)
deal_card(dealer_hand)
deal_card(dealer_hand)
#Play the game

while not game_over:
    print("Your hand:", player_hand)
    print("Dealer's hand:", dealer_hand[0])
    # Check for a blackjack
    if calculate_hand_value(player_hand) == 21:
        print("Blackjack! You win!")
        game_over = True
        continue
    elif calculate_hand_value(dealer_hand) == 21:
        print("Dealer has blackjack. You lose.")
        game_over = True
        continue

    # Ask the player to hit or stand
    while True:
        action = input("Do you want to hit or stand? ")
        if action.lower() == "hit":
            deal_card(player_hand)
            print("Your hand:", player_hand)
            if calculate_hand_value(player_hand) > 21:
                print("Bust! You lose.")
                game_over = True
                break
        elif action.lower() == "stand":
            break

    # Dealer's turn
    if not game_over:
        while calculate_hand_value(dealer_hand) < 17:
            deal_card(dealer_hand)
        print("Dealer's hand:", dealer_hand)
        if calculate_hand_value(dealer_hand) > 21:
            print("Dealer busts! You win!")
        elif calculate_hand_value(player_hand) > calculate_hand_value(dealer_hand):
            print("You win!")
        elif calculate_hand_value(player_hand) < calculate_hand_value(dealer_hand):
            print("You lose.")
        else:
            print("Push (tie).")
        game_over = True

