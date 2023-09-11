import random

# Define the cards and their values
cards = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
values = {"Ace": 11, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "Jack": 10, "Queen": 10, "King": 10}

# Define the deck of cards
deck = cards * 4
#random.shuffle(deck) # added to test better
# Define the function for dealing a card
def deal_card():
    return deck.pop()

# Define the function for calculating the value of a hand
def calculate_hand(hand):
    total = sum(values[card] for card in hand)
    aces = hand.count("Ace")
    while total > 21 and aces:
        total -= 10
        aces -= 1
    return total

# Deal the initial cards
player_hand = [deal_card(), deal_card()]
dealer_hand = [deal_card(), deal_card()]

# Play the game
while True:
    print("Player hand:", player_hand)
    print("Dealer hand:", [dealer_hand[0], "???"])
    if calculate_hand(player_hand) == 21:
        print("Blackjack! You win!")
        break
    elif calculate_hand(player_hand) > 21:
        print("Bust! You lose.")
        break
    else:
        action = input("Hit or stand? ").lower()
        if action == "hit":
            player_hand.append(deal_card())
        elif action == "stand":
            while calculate_hand(dealer_hand) < 17:
                dealer_hand.append(deal_card())
            print("Dealer hand:", dealer_hand)
            if calculate_hand(dealer_hand) > 21:
                print("Dealer bust! You win!")
            elif calculate_hand(dealer_hand) >= calculate_hand(player_hand):
                print("Dealer wins!")
            else:
                print("You win!")
            break
        else:
            print("Invalid action. Please enter 'hit' or 'stand'.")


