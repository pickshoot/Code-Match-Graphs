import random

# Define a function to calculate the value of a hand
def calculate_hand_value(hand):
    total_value = 0
    num_aces = 0
    for card in hand:
        if card[1] in ['jack', 'queen', 'king']:
            total_value += 10
        elif card[1] == 'ace':
            num_aces += 1
            total_value += 11
        else:
            total_value += int(card[1])
    while num_aces > 0 and total_value > 21:
        total_value -= 10
        num_aces -= 1
    return total_value

# Define a function to deal cards to the player and the dealer
def deal_cards(deck):
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    return player_hand, dealer_hand

# Define a function to display the cards
def display_cards(player_hand, dealer_hand, show_dealer_hand):
    print("Your cards:")
    for card in player_hand:
        print(f"{card[1]} of {card[0]}")
    print(f"\nDealer's cards:\n{'-'*18}")
    if show_dealer_hand:
        for card in dealer_hand:
            print(f"{card[1]} of {card[0]}")
    else:
        print(f"{dealer_hand[0][1]} of {dealer_hand[0][0]}")
        print("<face down card>")

# Define a function to check if the player wants to hit or stand
def player_hit_or_stand():
    while True:
        choice = input("\nDo you want to hit or stand? ").lower()
        if choice in ['hit', 'stand']:
            return choice
        print("Invalid input. Please enter 'hit' or 'stand'.")

# Define a function to play a round of Blackjack
def play_round(deck, money):
    # Ask the user to place a bet
    while True:
        bet = int(input(f"\nYou have {money} dollars. How much do you want to bet? "))
        if bet <= money:
            break
        print("Invalid bet. You don't have enough money.")

    # Deal the cards and display them
    player_hand, dealer_hand = deal_cards(deck)
    display_cards(player_hand, dealer_hand, False)

    # Let the player hit or stand
    while True:
        choice = player_hit_or_stand()
        if choice == 'hit':
            player_hand.append(deck.pop())
            display_cards(player_hand, dealer_hand, False)
            if calculate_hand_value(player_hand) > 21:
                print("Bust! You lose.")
                return -bet
        else:
            break

    # Let the dealer play
    while calculate_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        display_cards(player_hand, dealer_hand, True)
        if calculate_hand_value(dealer_hand) > 21:
            print("Dealer busts! You win.")
            return bet
    if calculate_hand_value(dealer_hand) > calculate_hand_value(player_hand):
        print("Dealer wins.")
        return -bet
    elif calculate_hand_value(dealer_hand) < calculate_hand_value(player_hand):
        print("You win!")
        return bet
    else:
        print("It's a tie.")
        return 0

# Define a function to play multiple rounds of Blackjack
def play_game():
    money = 100
    while True:
        deck = []
        # Create a deck of cards
        suits = ['hearts', 'diamonds', 'clubs', 'spades']
        ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'jack', 'queen', 'king', 'ace']
        for suit in suits:
            for rank in ranks:
                deck.append((suit, rank))
        random.shuffle(deck)

        # Play a round of Blackjack
        money += play_round(deck, money)

        # Ask the user if they want to play again
        if money == 0:
            print("You're out of money. Game over.")
            break
        while True:
            choice = input("\nDo you want to play again? ").lower()
            if choice in ['yes', 'no']:
                break
            print("Invalid input. Please enter 'yes' or 'no'.")
        if choice == 'no':
            print("Thanks for playing!")
            break

play_game()
