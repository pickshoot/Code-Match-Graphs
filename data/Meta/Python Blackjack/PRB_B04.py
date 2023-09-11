import random

# Function to calculate the value of a hand
def calculate_hand_value(hand):
    value = 0
    has_ace = False
    for card in hand:
        if card[0] in ['J', 'Q', 'K']:
            value += 10
        elif card[0] == 'A':
            has_ace = True
            value += 11
        else:
            value += int(card[0])
    if has_ace and value > 21:
        value -= 10
    return value

# Function to deal a card from the deck
def deal_card(deck):
    card = random.choice(deck)
    deck.remove(card)
    return card

# Function to display the player's hand and the dealer's face-up card
def display_hands(player_hand, dealer_hand, hide_dealer_card=True):
    print("Player's hand:", player_hand)
    if hide_dealer_card:
        print("Dealer's hand:", [dealer_hand[0], 'XX'])
    else:
        print("Dealer's hand:", dealer_hand)

# Function to play the game
def play_game():
    deck = ['2H', '3H', '4H', '5H', '6H', '7H', '8H', '9H', '10H', 'JH', 'QH', 'KH', 'AH',
            '2D', '3D', '4D', '5D', '6D', '7D', '8D', '9D', '10D', 'JD', 'QD', 'KD', 'AD',
            '2C', '3C', '4C', '5C', '6C', '7C', '8C', '9C', '10C', 'JC', 'QC', 'KC', 'AC',
            '2S', '3S', '4S', '5S', '6S', '7S', '8S', '9S', '10S', 'JS', 'QS', 'KS', 'AS']

    while True:
        # Get the player's bet
        bet = int(input("Enter your bet: "))
        print()

        # Deal the cards
        player_hand = [deal_card(deck), deal_card(deck)]
        dealer_hand = [deal_card(deck), deal_card(deck)]

        # Display the hands
        display_hands(player_hand, dealer_hand)

        # Player's turn
        while True:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == 'hit':
                player_hand.append(deal_card(deck))
                display_hands(player_hand, dealer_hand)
                if calculate_hand_value(player_hand) > 21:
                    print("Player busts! Dealer wins!")
                    print(f"You lost ${bet}")
                    break
            elif choice.lower() == 'stand':
                break

        # Dealer's turn
        if calculate_hand_value(player_hand) <= 21:
            display_hands(player_hand, dealer_hand, False)
            while calculate_hand_value(dealer_hand) < 17:
                dealer_hand.append(deal_card(deck))
                display_hands(player_hand, dealer_hand, False)

            # Determine the winner
            player_value = calculate_hand_value(player_hand)
            dealer_value = calculate_hand_value(dealer_hand)
            if dealer_value > 21:
                print("Dealer busts! Player wins!")
                print(f"You won ${bet}")
            elif dealer_value == player_value:
                print("It's a tie!")
            elif dealer_value > player_value:
                print("Dealer wins!")
                print(f"You lost ${bet}")
            else:
                print("Player wins!")
                print(f"You won ${bet}")

        # Ask the player if they want to play again
        choice = input("Do you want to play again? ")
        if choice.lower() != 'yes':
            break

    print("Thanks for playing!")

play_game()
