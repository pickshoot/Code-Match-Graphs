import random

# create a deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = [(rank, suit) for suit in suits for rank in ranks]

# function to calculate the value of a hand
def hand_value(hand):
    value = 0
    ace_count = 0
    for card in hand:
        if card[0] == 'Ace':
            value += 11
            ace_count += 1
        elif card[0] in ['King', 'Queen', 'Jack']:
            value += 10
        else:
            value += int(card[0])
    while value > 21 and ace_count > 0:
        value -= 10
        ace_count -= 1
    return value

# function to display a hand
def show_hand(hand):
    for card in hand:
        print(f"{card[0]} of {card[1]}")

# main game loop
while True:
    print("Let's play Blackjack!")
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]

    # show the hands
    print("\nDealer's hand:")
    print("Card hidden")
    print(f"{dealer_hand[1][0]} of {dealer_hand[1][1]}")
    print("\nYour hand:")
    show_hand(player_hand)

    # player's turn
    while True:
        choice = input("\nDo you want to hit or stand? ")
        if choice.lower() == "hit":
            player_hand.append(deck.pop())
            show_hand(player_hand)
            if hand_value(player_hand) > 21:
                print("\nBust! You lose.")
                break
        elif choice.lower() == "stand":
            break

    # dealer's turn
    if hand_value(player_hand) <= 21:
        print("\nDealer's turn")
        print("Dealer's hand:")
        show_hand(dealer_hand)
        while hand_value(dealer_hand) < 17:
            dealer_hand.append(deck.pop())
            print("Dealer hits")
            show_hand(dealer_hand)
        if hand_value(dealer_hand) > 21:
            print("\nDealer busts! You win.")
        elif hand_value(dealer_hand) >= hand_value(player_hand):
            print("\nDealer wins.")
        else:
            print("\nYou win!")

    # play again?
    again = input("\nDo you want to play again? ")
    if again.lower() != "yes":
        break