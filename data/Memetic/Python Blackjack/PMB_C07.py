import random

# Define the deck of cards
suits = ['hearts', 'diamonds', 'clubs', 'spades']
ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
deck = [(s, r) for s in suits for r in ranks]

# Define a function to calculate the value of a hand
def calculate_hand(hand):
    # Initialize the total value to 0
    total = 0
    # Iterate over the cards in the hand
    for card in hand:
        # Get the rank of the card
        rank = card[1]
        # If the rank is an ace, add 11 to the total
        if rank == 'A':
            total += 11
        # If the rank is a face card, add 10 to the total
        elif rank in ['K', 'Q', 'J']:
            total += 10
        # Otherwise, add the numeric value of the rank to the total
        else:
            total += int(rank)
    # If the total value of the hand is over 21 and there is an ace, count the ace as 1 instead of 11
    if total > 21 and 'A' in [card[1] for card in hand]:
        total -= 10
    return total
    
#Define a function to deal a hand
def deal_hand(deck):
    # Shuffle the deck
    random.shuffle(deck)
    # Deal two cards from the deck
    hand = [deck.pop(), deck.pop()]
    return hand

# Define the main function for the game
def play_game():
    # Deal two cards to the player and dealer
    player_hand = deal_hand(deck)
    dealer_hand = deal_hand(deck)

    # Print the player's hand and one of the dealer's cards
    print("Player's hand:", player_hand)
    print("Dealer's hand:", [dealer_hand[0], 'X'])

    # Loop for the player's turn
    while True:
        # Ask the player if they want to hit or stand
        action = input("Do you want to hit or stand? ")
        # If the player hits, deal another card and add it to their hand
        if action == 'hit':
            player_hand.append(deck.pop())
            # Print the player's new hand
            print("Player's hand:", player_hand)
            # If the player's total value is over 21, the player busts and the game ends
            if calculate_hand(player_hand) > 21:
                print("Player busts! Dealer wins.")
                return
        # If the player stands, break out of the loop and continue with the dealer's turn
        elif action == 'stand':
            break
        # If the player enters an invalid action, ask again
        else:
            print("Invalid action. Please enter 'hit' or 'stand'.")

    # Loop for the dealer's turn
    while True:
        # If the dealer's total value is less than 17, the dealer hits and adds a card to their hand
        if calculate_hand(dealer_hand) < 17:
            dealer_hand.append(deck.pop())
            # Print the dealer's new hand
            print("Dealer hits:", dealer_hand)
            # If the dealer's total value is over 21, the dealer busts and the player wins
            if calculate_hand(dealer_hand) > 21:
                print("Dealer busts! Player wins.")
                return
        # If the dealer's total value is 17 or higher, the dealer stands and the game ends
        else:
            print("Dealer stands:", dealer_hand)
            # Calculate the total value of the player's hand and the dealer's hand
            player_total = calculate_hand(player_hand)
            dealer_total = calculate_hand(dealer_hand)
            # Determine the winner based on the highest total value
            if player_total > dealer_total:
                print("Player wins!")
            elif player_total < dealer_total:
                print("Dealer wins!")
            else:
                print("It's a tie!")
            return

# Call the main function to start the game
play_game()