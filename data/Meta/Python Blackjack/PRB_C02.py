import random

# Define the values and suits of the cards
values = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
suits = ['♠', '♣', '♥', '♦']

# Define the point values of the cards
points = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10, 'A': 11}

# Define a function to create a deck of cards with the specified number of decks
def create_deck(num_decks):
    deck = []
    for i in range(num_decks):
        for suit in suits:
            for value in values:
                card = value + suit
                deck.append(card)
    random.shuffle(deck)
    return deck

# Define a function to deal a card from the deck
def deal_card(deck):
    card = deck.pop(0)
    return card

# Define a function to calculate the total point value of a hand
def calculate_hand(hand):
    total = 0
    num_aces = 0
    for card in hand:
        value = card[:-1]
        if value == 'A':
            num_aces += 1
        total += points[value]
    while total > 21 and num_aces > 0:
        total -= 10
        num_aces -= 1
    return total

# Define the main function that runs the game
def play_blackjack():
    # Prompt the user for their name and welcome them to the game
    name = input("What's your name? ")
    print(f"Welcome to the game of Blackjack, {name}!")

    # Explain the rules of the game
    print("The goal of the game is to have a hand with a total point value as close to 21 as possible without going over.")
    print("Numbered cards are worth their face value, face cards (J, Q, K) are worth 10, and Aces are worth 1 or 11.")
    print("You'll be playing against the computer. The computer will hit until its hand is worth at least 17.")
    print("Let's get started!")

    # Initialize the user's bankroll
    bankroll = 100

    # Play the game until the user quits or runs out of money
    while True:
        # Prompt the user for their bet
        print(f"Your current bankroll is ${bankroll}.")
        bet = int(input("How much would you like to bet? "))
        if bet > bankroll:
            print("Sorry, you don't have enough money to make that bet. Please try again.")
            continue

        # Create and shuffle the deck
        num_decks = int(input("How many decks would you like to play with? "))
        deck = create_deck(num_decks)

        # Deal the initial hands
        user_hand = [deal_card(deck), deal_card(deck)]
        computer_hand = [deal_card(deck), deal_card(deck)]

        # Show the hands
        print(f"Your hand: {', '.join(user_hand)}")
        print(f"Computer's hand: {computer_hand[0]}, [face down]")

        # Play the user's turn
        while True:
            choice = input("Would you like to hit or stand? ").lower()
            if choice == 'hit':
                user_hand.append(deal_card(deck))
                print(f"Your hand: {', '.join(user_hand)}")
                if calculate_hand(user_hand) > 21:
                    print("You bust!")
                    bankroll -= bet
                    break
            elif choice == 'stand':
                print(f"You stand with a hand worth {calculate_hand(user_hand)}.")
                break
            else:
                print("Invalid choice. Please try again.")

        # Play the computer's turn
        if calculate_hand(user_hand) <= 21:
            print(f"Computer's turn: {', '.join(computer_hand)}")
            while calculate_hand(computer_hand) < 17:
                computer_hand.append(deal_card(deck))
                print(f"Computer hits and receives {computer_hand[-1]}")
            print(f"Computer stands with a hand worth {calculate_hand(computer_hand)}.")

        # Determine the winner
        user_score = calculate_hand(user_hand)
        computer_score = calculate_hand(computer_hand)
        if user_score > 21:
            print("You lose!")
            bankroll -= bet
        elif computer_score > 21:
            print("You win!")
            bankroll += bet
        elif user_score > computer_score:
            print("You win!")
            bankroll += bet
        elif computer_score > user_score:
            print("You lose!")
            bankroll -= bet
        else:
            print("It's a tie!")

        # Ask the user if they want to play again
        if bankroll == 0:
            print("You're out of money! Game over.")
            break
        choice = input("Would you like to play again? ").lower()
        if choice == 'yes':
            continue
        else:
            print("Thanks for playing!")
            break

play_blackjack()
