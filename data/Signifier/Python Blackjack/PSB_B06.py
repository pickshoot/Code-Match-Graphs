import random

# Function to calculate the total value of the hand
def calculate_hand(hand):
    total = 0
    num_aces = 0
    for card in hand:
        if card[0] == "A":
            num_aces += 1
            total += 11
        elif card[0] in ["K", "Q", "J"]:
            total += 10
        else:
            total += int(card[0])
    # Handle aces
    while total > 21 and num_aces > 0:
        total -= 10
        num_aces -= 1
    return total

# Function to print the current state of the game
def print_game_state(player_hand, dealer_hand, player_total, dealer_total, game_over=False):
    print("\nPlayer's hand:", player_hand, "(", player_total, ")")
    if game_over:
        print("Dealer's hand:", dealer_hand, "(", dealer_total, ")")
    else:
        print("Dealer's hand:", [dealer_hand[0], "??"])
    print()

# Set up the deck of cards
deck = []
for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
    for value in range(2, 11):
        deck.append((str(value), suit))
    for face in ["J", "Q", "K", "A"]:
        deck.append((face, suit))

# Shuffle the deck
random.shuffle(deck)

# Deal the initial hands
player_hand = [deck.pop(), deck.pop()]
dealer_hand = [deck.pop(), deck.pop()]

# Calculate the initial totals
player_total = calculate_hand(player_hand)
dealer_total = calculate_hand(dealer_hand)

# Print the initial state of the game
print_game_state(player_hand, dealer_hand, player_total, dealer_total)

# Player's turn
while player_total < 21:
    choice = input("Do you want to hit or stand? ")
    if choice.lower() == "hit":
        player_hand.append(deck.pop())
        player_total = calculate_hand(player_hand)
        print_game_state(player_hand, dealer_hand, player_total, dealer_total)
    elif choice.lower() == "stand":
        break

# Check if the player went bust
if player_total > 21:
    print("Bust! You lose!")
else:
    # Dealer's turn
    print_game_state(player_hand, dealer_hand, player_total, dealer_total, game_over=True)
    while dealer_total < 17:
        dealer_hand.append(deck.pop())
        dealer_total = calculate_hand(dealer_hand)
        print_game_state(player_hand, dealer_hand, player_total, dealer_total, game_over=True)

    # Check if the dealer went bust
    if dealer_total > 21:
        print("Dealer bust! You win!")
    elif dealer_total > player_total:
        print("Dealer wins!")
    elif player_total > dealer_total:
        print("You win!")
    else:
        print("It's a tie!")
