import random

# Define the card deck
deck = [
    ('Ace', 11), ('Two', 2), ('Three', 3), ('Four', 4), ('Five', 5),
    ('Six', 6), ('Seven', 7), ('Eight', 8), ('Nine', 9), ('Ten', 10),
    ('Jack', 10), ('Queen', 10), ('King', 10)
]

# Define the initial amount of money for the player
money = 100

# Define the function to calculate the hand value
def hand_value(hand):
    value = sum(card[1] for card in hand)
    num_aces = sum(1 for card in hand if card[0] == 'Ace')
    while num_aces > 0 and value > 21:
        value -= 10
        num_aces -= 1
    return value

# Define the function to play a round of Blackjack
def play_round():
    global money
    # Ask the player for their bet
    bet = int(input(f"You have {money} dollars. How much do you want to bet? "))
    # Deal the cards
    player_hand = [deck.pop(random.randint(0, len(deck)-1)) for _ in range(2)]
    dealer_hand = [deck.pop(random.randint(0, len(deck)-1)) for _ in range(2)]
    # Show the hands
    print(f"Your hand: {player_hand}, value: {hand_value(player_hand)}")
    print(f"Dealer's hand: [{dealer_hand[0][0]}, -]")
    # Player's turn
    while True:
        action = input("Do you want to hit or stand? ")
        if action.lower() == 'hit':
            player_hand.append(deck.pop(random.randint(0, len(deck)-1)))
            print(f"Your hand: {player_hand}, value: {hand_value(player_hand)}")
            if hand_value(player_hand) > 21:
                print("Bust! You lose your bet.")
                money -= bet
                return
        elif action.lower() == 'stand':
            break
    # Dealer's turn
    print(f"Dealer's hand: {dealer_hand}, value: {hand_value(dealer_hand)}")
    while hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop(random.randint(0, len(deck)-1)))
        print(f"Dealer's hand: {dealer_hand}, value: {hand_value(dealer_hand)}")
        if hand_value(dealer_hand) > 21:
            print("Dealer busts! You win your bet.")
            money += bet
            return
    # Determine the winner
    if hand_value(player_hand) > hand_value(dealer_hand):
        print("You win!")
        money += bet
    elif hand_value(player_hand) < hand_value(dealer_hand):
        print("Dealer wins!")
        money -= bet
    else:
        print("It's a tie!")

# Play the game
while True:
    print(f"You have {money} dollars.")
    if money <= 0:
        print("You're out of money. Game over!")
        break
    play_round()
    if input("Do you want to play again? (y/n) ").lower() != 'y':
        break
