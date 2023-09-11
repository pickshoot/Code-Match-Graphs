import random

# Define the values of the cards
card_values = {"Ace": 11, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "Jack": 10, "Queen": 10, "King": 10}

# Define the deck of cards
deck = list(card_values.keys()) * 4

# Define the function to calculate the total value of a hand
def calculate_hand(hand):
    total = sum([card_values[card] for card in hand])
    # Check for aces and adjust the total value accordingly
    for card in hand:
        if card == "Ace" and total > 21:
            total -= 10
    return total

# Define the function to play the game
def play_blackjack():
    # Shuffle the deck
    random.shuffle(deck)
    # Deal the initial cards
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]

    # Show the player's hand and the dealer's first card
    print(f"Player's hand: {player_hand}")
    print(f"Dealer's hand: [{dealer_hand[0]}, ?]")

    # Player's turn
    while True:
        # Ask the player if they want to hit or stand
        action = input("Do you want to hit or stand? ")
        if action.lower() == "hit":
            # Deal another card to the player
            player_hand.append(deck.pop())
            # Show the player's updated hand
            print(f"Player's hand: {player_hand}")
            # Check if the player has gone bust
            if calculate_hand(player_hand) > 21:
                print("Player busts!")
                return -1
        elif action.lower() == "stand":
            break
        else:
            print("Invalid input. Please enter 'hit' or 'stand'.")

    # Dealer's turn
    while calculate_hand(dealer_hand) < 17:
        # Deal another card to the dealer
        dealer_hand.append(deck.pop())

    # Show the dealer's hand
    print(f"Dealer's hand: {dealer_hand}")

    # Check the outcome of the game
    player_total = calculate_hand(player_hand)
    dealer_total = calculate_hand(dealer_hand)
    if dealer_total > 21:
        print("Dealer busts!")
        return 1
    elif player_total > dealer_total:
        print("Player wins!")
        return 1
    elif dealer_total > player_total:
        print("Dealer wins!")
        return -1
    else:
        print("It's a tie!")
        return 0

# Play the game
result = play_blackjack()
if result == 1:
    print("Congratulations, you won!")
elif result == -1:
    print("Sorry, you lost.")
else:
    print("It's a tie!")
