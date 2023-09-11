import random

# Create a deck of cards
deck = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"] * 4

# Define card values
card_values = {"2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "J": 10, "Q": 10, "K": 10, "A": 11}

# Define player and dealer hands
player_hand = []
dealer_hand = []

# Define the game loop
def play_game():
    # Shuffle the deck
    random.shuffle(deck)
    
    # Deal the initial hands
    player_hand.append(deck.pop())
    dealer_hand.append(deck.pop())
    player_hand.append(deck.pop())
    dealer_hand.append(deck.pop())
    
    # Show the hands
    print("Your hand: ", player_hand)
    print("Dealer's hand: ", [dealer_hand[0], "X"])
    
    # Player turn
    while True:
        choice = input("Do you want to hit or stand? ")
        if choice.lower() == "hit":
            player_hand.append(deck.pop())
            print("Your hand: ", player_hand)
            if get_hand_value(player_hand) > 21:
                print("Bust! You lose.")
                return
        elif choice.lower() == "stand":
            break
    
    # Dealer turn
    print("Dealer's hand: ", dealer_hand)
    while get_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        print("Dealer hits")
        print("Dealer's hand: ", dealer_hand)
    if get_hand_value(dealer_hand) > 21:
        print("Dealer busts! You win.")
        return
    
    # Determine the winner
    player_score = get_hand_value(player_hand)
    dealer_score = get_hand_value(dealer_hand)
    if player_score > dealer_score:
        print("You win!")
    elif dealer_score > player_score:
        print("Dealer wins.")
    else:
        print("It's a tie!")

#Calculate the value of a hand
def get_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        value += card_values[card]
        if card == "A":
            num_aces += 1
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

#Main game loop
while True:
    play_game()
    again = input("Do you want to play again? ")
    if again.lower() != "yes":
        break