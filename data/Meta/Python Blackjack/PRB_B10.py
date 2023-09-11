import random

def deal_card():
    """Return a random card from the deck."""
    cards = [2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K', 'A']
    return random.choice(cards)

def calculate_hand(hand):
    """Calculate the value of a hand of cards."""
    # Initialize the hand value and the number of aces in the hand.
    value = 0
    num_aces = 0
    # Calculate the value of the hand, treating aces as 1.
    for card in hand:
        if card == 'A':
            num_aces += 1
            value += 1
        elif card in ['K', 'Q', 'J']:
            value += 10
        else:
            value += card
    # If the hand contains an ace and the hand value is low enough,
    # treat the ace as 11.
    for i in range(num_aces):
        if value + 10 <= 21:
            value += 10
    return value

def print_hand(hand):
    """Print the cards in a hand."""
    for card in hand:
        print(card, end=' ')
    print()

def play_again():
    """Ask the user if they want to play again."""
    play_again = input("Do you want to play again? (y/n) ")
    return play_again.lower() == 'y'

def play_blackjack():
    """Play a game of blackjack."""
    print("Welcome to blackjack!")
    money = 100
    while True:
        # Check if the player has any money left.
        if money <= 0:
            print("You're out of money!")
            break
        print("You have ${}".format(money))
        # Ask the player how much they want to bet.
        bet = int(input("How much do you want to bet? "))
        # Deal the cards.
        player_hand = [deal_card(), deal_card()]
        dealer_hand = [deal_card(), deal_card()]
        # Show the cards.
        print("Dealer's hand:")
        print(dealer_hand[0], "X")
        print("Your hand:")
        print_hand(player_hand)
        # Player's turn.
        while True:
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                player_hand.append(deal_card())
                print("Your hand:")
                print_hand(player_hand)
                if calculate_hand(player_hand) > 21:
                    print("Bust! You lose ${}".format(bet))
                    money -= bet
                    break
            elif action.lower() == 'stand':
                break
        # Dealer's turn.
        if calculate_hand(player_hand) <= 21:
            print("Dealer's hand:")
            print_hand(dealer_hand)
            while calculate_hand(dealer_hand) < 17:
                dealer_hand.append(deal_card())
                print("Dealer hits!")
                print_hand(dealer_hand)
            if calculate_hand(dealer_hand) > 21:
                print("Dealer busts! You win ${}".format(bet))
                money += bet
            elif calculate_hand(dealer_hand) > calculate_hand(player_hand):
                print("Dealer wins! You lose ${}".format(bet))
                money -= bet
            elif calculate_hand(dealer_hand) < calculate_hand(player_hand):
                print("You win ${}".format(bet))
                money += bet
            else:
                print("Push!")
        if not play_again():
            break

play_blackjack()
