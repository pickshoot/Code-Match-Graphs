import random

# Define the deck of cards
deck = [
    "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"
] * 4

# Define the values of each card
card_values = {
    "Ace": 1,
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
    "King": 10,
}

# Define the function to calculate the value of a hand
def hand_value(hand):
    value = sum([card_values[card] for card in hand])
    if "Ace" in hand and value <= 11:
        value += 10
    return value

# Define the function to deal a hand
def deal(deck, num_cards=2):
    hand = []
    for i in range(num_cards):
        card = random.choice(deck)
        deck.remove(card)
        hand.append(card)
    return hand

# Define the function to check if the player wants to hit or stand
def hit_or_stand():
    while True:
        choice = input("Do you want to hit or stand? ")
        if choice.lower() == "hit":
            return True
        elif choice.lower() == "stand":
            return False
        else:
            print("Invalid choice, please enter 'hit' or 'stand'.")

# Define the main function to play the game
def play_blackjack():
    # Initialize the deck and the player's and dealer's hands
    deck = [
        "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"
    ] * 4
    player_hand = deal(deck)
    dealer_hand = deal(deck)

    # Show the player's hand and the dealer's face-up card
    print("Player's hand:", player_hand)
    print("Dealer's hand:", [dealer_hand[0], "X"])

    # Player's turn
    while True:
        # Ask the player if they want to hit or stand
        if not hit_or_stand():
            break

        # Deal another card to the player and show their new hand
        player_hand += deal(deck, 1)
        print("Player's hand:", player_hand)

        # Check if the player has busted
        if hand_value(player_hand) > 21:
            print("Player has busted! Dealer wins.")
            return

    # Dealer's turn
    while hand_value(dealer_hand) < 17:
        # Dealer hits if their hand is less than 17
        dealer_hand += deal(deck, 1)

    # Show the final hands
    print("Player's hand:", player_hand)
    print("Dealer's hand:", dealer_hand)

    # Check who wins
    player_value = hand_value(player_hand)
    dealer_value = hand_value(dealer_hand)
    if dealer_value > 21:
        print("Dealer has busted! Player wins.")
    elif player_value > dealer_value:
        print("Player wins!")
    elif dealer_value > player_value:
        print("Dealer wins!")
    else:
        print("It's a tie!")

# Play the game
play_blackjack()
