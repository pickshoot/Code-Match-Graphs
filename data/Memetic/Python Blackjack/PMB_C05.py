import random

def get_card_value(card):
    """Get the numeric value of a card."""
    if card[0] in ['K', 'Q', 'J']:
        return 10
    elif card[0] == 'A':
        return 11
    else:
        return int(card[0])

def get_hand_value(hand):
    """Get the total value of a hand."""
    value = sum([get_card_value(card) for card in hand])
    aces = hand.count('A')
    while value > 21 and aces > 0:
        value -= 10
        aces -= 1
    return value

def print_hand(hand):
    """Print the player's hand."""
    print("Your hand: ", end='')
    for card in hand:
        print(card, end=' ')
    print('')

def play_game():
    """Play a game of Blackjack."""
    deck = ['2C', '2D', '2H', '2S', '3C', '3D', '3H', '3S', '4C', '4D', '4H', '4S', '5C', '5D', '5H', '5S', '6C', '6D', '6H', '6S', '7C', '7D', '7H', '7S', '8C', '8D', '8H', '8S', '9C', '9D', '9H', '9S', 'AC', 'AD', 'AH', 'AS', 'JC', 'JD', 'JH', 'JS', 'KC', 'KD', 'KH', 'KS', 'QC', 'QD', 'QH', 'QS']
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]

    # Print initial hands
    print_hand(player_hand)
    print("Dealer's hand: " + dealer_hand[0] + " *")

    # Player's turn
    while True:
        choice = input("Do you want to hit or stand? ").lower()
        if choice == 'hit':
            player_hand.append(deck.pop())
            print_hand(player_hand)
            if get_hand_value(player_hand) > 21:
                print("Bust! You lose.")
                return
        elif choice == 'stand':
            break
        else:
            print("Invalid choice. Please enter 'hit' or 'stand'.")

    # Dealer's turn
    while get_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
    print("Dealer's hand: ", end='')
    print(' '.join(dealer_hand))

    # Determine winner
    player_value = get_hand_value(player_hand)
    dealer_value = get_hand_value(dealer_hand)
    if player_value > 21:
        print("Bust! You lose.")
    elif dealer_value > 21:
        print("Dealer busts! You win.")
    elif player_value > dealer_value:
        print("You win!")
    elif dealer_value > player_value:
        print("You lose.")
    else:
        print("Push.")

#Main game loop
while True:
    play_game()
    play_again = input("Do you want to play again? ").lower()
    if play_again != 'yes':
        break

print("Thanks for playing!")