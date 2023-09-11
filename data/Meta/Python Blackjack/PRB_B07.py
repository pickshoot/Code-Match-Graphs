import random

# Define the deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}
deck = [(rank, suit) for rank in ranks for suit in suits]

# Define the function to calculate the hand value
def hand_value(hand):
    value = sum([values[card[0]] for card in hand])
    num_aces = sum([1 for card in hand if card[0] == 'Ace'])
    while value > 21 and num_aces:
        value -= 10
        num_aces -= 1
    return value

# Define the function to deal cards
def deal_cards():
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    return player_hand, dealer_hand

# Define the function for player's turn
def player_turn(player_hand):
    while True:
        print(f"Your current hand is: {player_hand}")
        print(f"Your current hand value is: {hand_value(player_hand)}")
        action = input("Do you want to hit or stand? ")
        if action.lower() == 'hit':
            player_hand.append(deck.pop())
            if hand_value(player_hand) > 21:
                print(f"Your current hand is: {player_hand}")
                print(f"Your current hand value is: {hand_value(player_hand)}")
                print("Sorry, you bust! Dealer wins!")
                return 0
        elif action.lower() == 'stand':
            print("You chose to stand.")
            return hand_value(player_hand)
        else:
            print("Invalid action. Please try again.")

# Define the function for dealer's turn
def dealer_turn(dealer_hand):
    while hand_value(dealer_hand) < 17:
        print(f"Dealer's current hand is: {dealer_hand}")
        print(f"Dealer's current hand value is: {hand_value(dealer_hand)}")
        print("Dealer hits.")
        dealer_hand.append(deck.pop())
    if hand_value(dealer_hand) > 21:
        print(f"Dealer's current hand is: {dealer_hand}")
        print(f"Dealer's current hand value is: {hand_value(dealer_hand)}")
        print("Dealer busts! You win!")
        return 1
    else:
        print(f"Dealer's current hand is: {dealer_hand}")
        print(f"Dealer's current hand value is: {hand_value(dealer_hand)}")
        print("Dealer stands.")
        return hand_value(dealer_hand)

# Define the function to play the game
def play_game():
    player_bankroll = int(input("How much money do you want to start with? "))
    deck = [(rank, suit) for rank in ranks for suit in suits]
    random.shuffle(deck)
    while True:
        if not deck:
            print("The deck is empty. Reshuffling the cards.")
            deck = [(rank, suit) for rank in ranks for suit in suits]
            random.shuffle(deck)
        print(f"Your current bankroll is: {player_bankroll}")
        bet = int(input("How much do you want to bet? "))
        if bet > player_bankroll:
            print("You don't have enough money. Please bet a smaller amount.")
            continue
        player_hand, dealer_hand = deal_cards()
        print(f"Dealer's face-up card is: {dealer_hand[0]}")
        player_score = player_turn(player_hand)
        if player_score == 0:
            player_bankroll -= bet
            play_again = input("Do you want to play again? (y/n) ")
            if play_again.lower() == 'y':
                continue
            else:
                print("Thanks for playing!")
                break
        dealer_score = dealer_turn(dealer_hand)
        if dealer_score == 1:
            player_bankroll += bet
        elif dealer_score > player_score:
            print("Dealer wins!")
            player_bankroll -= bet
        elif dealer_score < player_score:
            print("You win!")
            player_bankroll += bet
        else:
            print("It's a tie!")
        play_again = input("Do you want to play again? (y/n) ")
        if play_again.lower() == 'y':
            continue
        else:
            print("Thanks for playing!")
            break

play_game()
