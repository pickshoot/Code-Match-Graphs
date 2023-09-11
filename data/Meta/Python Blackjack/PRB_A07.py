import random

# Define the values of cards
card_values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6,
               '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10,
               'Queen': 10, 'King': 10}

# Define the suits of cards
card_suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']

# Define the function to calculate the total value of a hand
def calculate_hand(hand):
    hand_value = sum(card_values[card.split()[0]] for card in hand)
    #hand_value = sum(card_values[card] for card in hand)
    num_aces = hand.count('Ace')
    while num_aces > 0 and hand_value > 21:
        hand_value -= 10
        num_aces -= 1
    return hand_value

# Define the function to deal a card
def deal_card():
    return f'{random.choice(list(card_values.keys()))} of {random.choice(card_suits)}'

# Define the main function to play the game
def play_game():
    print("Welcome to Blackjack!")
    while True:
        # Initialize the game
        player_hand = [deal_card(), deal_card()]
        computer_hand = [deal_card(), deal_card()]

        # Show the cards
        print(f"\nYour cards: {', '.join(player_hand)}")
        print(f"Computer's card: {computer_hand[0]}")

        # Player's turn
        while True:
            choice = input("\nDo you want to hit or stand? ")
            if choice.lower() == 'hit':
                player_hand.append(deal_card())
                print(f"\nYour cards: {', '.join(player_hand)}")
                if calculate_hand(player_hand) > 21:
                    print("\nBust! You lose.")
                    break
            elif choice.lower() == 'stand':
                break

        # Computer's turn
        while calculate_hand(computer_hand) < 17:
            computer_hand.append(deal_card())

        # Determine the winner
        player_total = calculate_hand(player_hand)
        computer_total = calculate_hand(computer_hand)
        print(f"\nYour total: {player_total}")
        print(f"Computer's total: {computer_total}")
        if player_total > 21:
            print("\nYou lose.")
        elif computer_total > 21:
            print("\nYou win!")
        elif player_total > computer_total:
            print("\nYou win!")
        elif player_total < computer_total:
            print("\nYou lose.")
        else:
            print("\nPush.")

        # Ask if the player wants to play again
        choice = input("\nDo you want to play again? ")
        if choice.lower() != 'yes':
            break

# Start the game
play_game()
