import random

# define the cards and their values
card_values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5,
               '6': 6, '7': 7, '8': 8, '9': 9, '10': 10,
               'Jack': 10, 'Queen': 10, 'King': 10}

# define the deck of cards
deck = list(card_values.keys()) * 4


# define the function to calculate the total value of a hand
def hand_value(hand):
    value = sum([card_values[card] for card in hand])
    if value > 21 and 'Ace' in hand:
        value -= 10
    return value


# define the function to deal a hand
def deal_hand():
    return [deck.pop(random.randint(0, len(deck) - 1)) for i in range(2)]


# define the function to play the game
def play_game():
    # deal the player's and dealer's hands
    player_hand = deal_hand()
    dealer_hand = deal_hand()

    # show the initial hands
    print("Player's hand:", player_hand)
    print("Dealer's hand:", [dealer_hand[0], 'X'])

    # allow the player to hit or stand
    while True:
        choice = input("Do you want to hit or stand? ").lower()
        if choice == 'hit':
            player_hand.append(deck.pop(random.randint(0, len(deck) - 1)))
            print("Player's hand:", player_hand)
            if hand_value(player_hand) > 21:
                print("You busted! Dealer wins.")
                return
        elif choice == 'stand':
            break
        else:
            print("Invalid input. Please enter 'hit' or 'stand'")
            # reveal the dealer's hand and hit until the value is 17 or more
            print("Dealer's hand:", dealer_hand)
            while hand_value(dealer_hand) < 17:
                dealer_hand.append(deck.pop(random.randint(0, len(deck) - 1)))
                print("Dealer hits:", dealer_hand[-1])
            if hand_value(dealer_hand) > 21:
                print("Dealer busted! You win.")
                return

            # compare the hands and determine the winner
            player_value = hand_value(player_hand)
            dealer_value = hand_value(dealer_hand)
            if player_value > dealer_value:
                print("You win!")
            elif player_value < dealer_value:
                print("Dealer wins.")
            else:
                print("It's a tie.")

# play the game indefinitely until the player chooses to quit
while True:
    play_game()
    choice = input("Do you want to play again? (y/n) ").lower()
    if choice != 'y':
        break

# print a message to indicate the end of the game
print("Thanks for playing!")