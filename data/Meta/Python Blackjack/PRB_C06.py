import random

def create_deck(num_decks):
    # Create a deck of cards with num_decks number of decks
    ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
    suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
    deck = []
    for i in range(num_decks):
        for rank in ranks:
            for suit in suits:
                deck.append((rank, suit))
    return deck

def shuffle_deck(deck):
    # Shuffle the deck of cards
    random.shuffle(deck)

def deal_cards(deck, num_cards):
    # Deal a specified number of cards from the deck
    hand = []
    for i in range(num_cards):
        hand.append(deck.pop())
    return hand

def calculate_hand(hand):
    # Calculate the value of a hand of cards
    total = 0
    num_aces = 0
    for card in hand:
        rank = card[0]
        if rank == 'Ace':
            num_aces += 1
            total += 11
        elif rank in ['King', 'Queen', 'Jack']:
            total += 10
        else:
            total += int(rank)
    while total > 21 and num_aces > 0:
        total -= 10
        num_aces -= 1
    return total

def display_hand(hand, hide_first_card=False):
    # Display a hand of cards
    if hide_first_card:
        print("['*', '*']", end=' ')
        for card in hand[1:]:
            print(card, end=' ')
    else:
        for card in hand:
            print(card, end=' ')
    print()

def get_player_bet(bankroll):
    # Prompt the player for their bet
    while True:
        bet = input(f"Enter your bet (1-{bankroll}): ")
        if not bet.isdigit():
            print("Please enter a valid bet.")
        elif int(bet) > bankroll:
            print("You don't have enough money for that bet.")
        else:
            return int(bet)

def play_game():
    # Prompt the player for their name
    name = input("What is your name? ")

    # Welcome the player to the game
    print(f"Welcome to the game, {name}!")

    # Explain the rules of the game
    print("The rules of Blackjack are simple: try to get as close to 21 as possible without going over.\n\
    Face cards are worth 10, Aces are worth 1 or 11, and all other cards are worth their face value.\n\
    You will be playing against the computer, and whoever gets closer to 21 without going over wins.")

    # Prompt the player for the number of decks to play with
    num_decks = int(input("How many decks of cards would you like to play with? "))

    # Create and shuffle the deck of cards
    deck = create_deck(num_decks)
    shuffle_deck(deck)

    # Set the player's initial bankroll
    bankroll = 100

    # Play the game until the player quits or runs out of money
    while True:
        print(f"\nYour current bankroll is {bankroll}.")
        if bankroll == 0:
            print("You have run out of money. Game over!")
            break

        # Prompt the player for their bet
        bet = get_player_bet(bankroll)

        # Deal the initial hands
        player_hand = deal_cards(deck, 2)
        computer_hand = deal_cards(deck, 2)

        # Display the hands
        print("Your hand is: ", end='')
        display_hand(player_hand)
        print("The computer's hand is: ", end='')
        display_hand(computer_hand, True)

        # Player's turn
        while True:
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                player_hand += deal_cards(deck, 1)
                print("Your hand is now: ", end='')
                display_hand(player_hand)
                if calculate_hand(player_hand) > 21:
                    print("Bust! You lose.")
                    bankroll -= bet
                    break
            elif action.lower() == 'stand':
                break
            else:
                print("Please enter a valid action.")

        # Computer's turn
        if calculate_hand(player_hand) <= 21:
            print("\nThe computer's hand is: ", end='')
            display_hand(computer_hand)
            while calculate_hand(computer_hand) < 17:
                computer_hand += deal_cards(deck, 1)
                print("The computer hits.")
                print("The computer's hand is now: ", end='')
                display_hand(computer_hand)
            if calculate_hand(computer_hand) > 21:
                print("The computer busts! You win.")
                bankroll += bet
            elif calculate_hand(computer_hand) > calculate_hand(player_hand):
                print("The computer wins.")
                bankroll -= bet
            elif calculate_hand(computer_hand) < calculate_hand(player_hand):
                print("You win!")
                bankroll += bet
            else:
                print("It's a tie!")

play_game()
