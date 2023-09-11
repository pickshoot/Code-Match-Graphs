import random

# Define the card values
card_values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define the deck of cards
deck = list(card_values.keys()) * 4

# Define the initial state of the game
player_hand = []
dealer_hand = []
player_score = 0
dealer_score = 0
game_over = False

# Shuffle the deck
random.shuffle(deck)

# Deal the first two cards to the player and the dealer
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())

# Calculate the initial scores
for card in player_hand:
    player_score += card_values[card]
for card in dealer_hand:
    dealer_score += card_values[card]

# Check for blackjack
if player_score == 21:
    print("Blackjack! You win!")
    game_over = True
elif dealer_score == 21:
    print("Dealer has blackjack. You lose.")
    game_over = True

# Keep playing until the game is over
while not game_over:
    # Display the player's hand and score
    print("Your hand:", player_hand)
    print("Your score:", player_score)

    # Ask the player if they want to hit or stand
    choice = input("Do you want to hit or stand? ")

    if choice.lower() == "hit":
        # Deal a card to the player
        player_hand.append(deck.pop())
        player_score = 0
        for card in player_hand:
            player_score += card_values[card]

        # Check for bust
        if player_score > 21:
            print("Bust! You lose.")
            game_over = True

    elif choice.lower() == "stand":
        # Dealer's turn
        while dealer_score < 17:
            dealer_hand.append(deck.pop())
            dealer_score = 0
            for card in dealer_hand:
                dealer_score += card_values[card]

        # Check for bust or win
        if dealer_score > 21:
            print("Dealer busts! You win!")
        elif dealer_score > player_score:
            print("Dealer wins!")
        elif player_score > dealer_score:
            print("You win!")
        else:
            print("It's a tie!")
        game_over = True

    else:
        print("Invalid input. Please enter 'hit' or 'stand'.")

