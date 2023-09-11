import random

# Create a deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

deck = []
for suit in suits:
    for rank, value in ranks.items():
        deck.append((rank + ' of ' + suit, value))

# Shuffle the deck
random.shuffle(deck)

# Define a function to calculate the value of a hand
def hand_value(hand):
    value = sum(card[1] for card in hand)
    if value > 21 and any(card[0].startswith('Ace') for card in hand):
        for card in hand:
            if card[0].startswith('Ace'):
                value -= 10
                if value <= 21:
                    break
    return value

# Define a function to display a hand
def display_hand(hand, hide=False):
    if hide:
        print('Hidden Card')
        print(hand[1][0])
    else:
        for card in hand:
            print(card[0])

# Deal the initial cards
player_hand = [deck.pop(), deck.pop()]
computer_hand = [deck.pop(), deck.pop()]

# Play the game
while True:
    # Display the hands
    print('Your Hand:')
    display_hand(player_hand)
    print('Computer Hand:')
    display_hand(computer_hand, hide=True)

    # Check for blackjack
    if hand_value(player_hand) == 21:
        print('Blackjack! You win!')
        break
    elif hand_value(computer_hand) == 21:
        print('Computer has blackjack. You lose.')
        break

    # Ask the player to hit or stand
    while True:
        choice = input('Do you want to hit or stand? ')
        if choice.lower() == 'hit':
            player_hand.append(deck.pop())
            print('You drew the', player_hand[-1][0])
            if hand_value(player_hand) > 21:
                print('Bust! You lose.')
                break
        elif choice.lower() == 'stand':
            break

    # Computer plays
    while hand_value(computer_hand) < 17:
        computer_hand.append(deck.pop())
        print('Computer drew the', computer_hand[-1][0])
        if hand_value(computer_hand) > 21:
            print('Computer busts. You win!')
            break

    # Check for win/loss/tie
    player_value = hand_value(player_hand)
    computer_value = hand_value(computer_hand)
    if player_value > computer_value:
        print('You win!')
    elif computer_value > player_value:
        print('You lose.')
    else:
        print('Push.')

    # Ask to play again or exit
    play_again = input('Do you want to play again? ')
    if play_again.lower() != 'yes':
        break

