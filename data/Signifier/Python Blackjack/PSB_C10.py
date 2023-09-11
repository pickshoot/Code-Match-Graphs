import random

# Create a deck of cards
deck = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King'] * 4

# Calculate the value of a hand
def get_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        if card == 'Ace':
            num_aces += 1
            value += 11
        elif card in ['Jack', 'Queen', 'King']:
            value += 10
        else:
            value += int(card)
    while value > 21 and num_aces:
        value -= 10
        num_aces -= 1
    return value

# Shuffle the deck
def shuffle_deck():
    random.shuffle(deck)

# Deal a card from the deck
def deal_card():
    return deck.pop()

# Play the game
def play_game():
    shuffle_deck()
    player_hand = [deal_card(), deal_card()]
    dealer_hand = [deal_card()]
    while True:
        print("Player's hand:", player_hand, get_hand_value(player_hand))
        print("Dealer's hand:", dealer_hand, get_hand_value(dealer_hand))
        if get_hand_value(player_hand) > 21:
            print("Bust! You lose.")
            break
        elif get_hand_value(player_hand) == 21:
            print("Blackjack! You win.")
            break
        choice = input("Hit or stand? ")
        if choice.lower() == 'hit':
            player_hand.append(deal_card())
        elif choice.lower() == 'stand':
            while get_hand_value(dealer_hand) < 17:
                dealer_hand.append(deal_card())
            print("Player's hand:", player_hand, get_hand_value(player_hand))
            print("Dealer's hand:", dealer_hand, get_hand_value(dealer_hand))
            if get_hand_value(dealer_hand) > 21:
                print("Dealer busts! You win.")
            elif get_hand_value(dealer_hand) >= get_hand_value(player_hand):
                print("Dealer wins.")
            else:
                print("You win!")
            break

# Run the game
play_game()
