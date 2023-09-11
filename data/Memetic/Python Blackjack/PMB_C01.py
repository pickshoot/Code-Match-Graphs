import random

# Initialize deck of cards
cards = {'Ace': 11, 'King': 10, 'Queen': 10, 'Jack': 10,
         '10': 10, '9': 9, '8': 8, '7': 7, '6': 6,
         '5': 5, '4': 4, '3': 3, '2': 2}
deck = list(cards.keys()) * 4

# Shuffle the deck
random.shuffle(deck)

# Deal the cards
player_hand = [deck.pop(), deck.pop()]
dealer_hand = [deck.pop(), deck.pop()]

# Calculate the value of a hand
def hand_value(hand):
    value = sum(cards[card] for card in hand)
    for card in hand:
        if card == 'Ace' and value > 21:
            value -= 10
    return value

# Play the game
while True:
    print(f"Player's hand: {player_hand} (value: {hand_value(player_hand)})")
    print(f"Dealer's hand: [{dealer_hand[0]}, _]")

    # Player's turn
    if hand_value(player_hand) == 21:
        print("Blackjack! Player wins!")
        break
    elif hand_value(player_hand) > 21:
        print("Bust! Player loses.")
        break
    else:
        action = input("Hit or stand? ")
        if action.lower() == 'hit':
            player_hand.append(deck.pop())
        else:
            # Dealer's turn
            while hand_value(dealer_hand) < 17:
                dealer_hand.append(deck.pop())

            # Determine the winner
            print(f"Player's hand: {player_hand} (value: {hand_value(player_hand)})")
            print(f"Dealer's hand: {dealer_hand} (value: {hand_value(dealer_hand)})")
            if hand_value(dealer_hand) > 21:
                print("Dealer busts! Player wins!")
            elif hand_value(player_hand) > hand_value(dealer_hand):
                print("Player wins!")
            elif hand_value(player_hand) < hand_value(dealer_hand):
                print("Dealer wins!")
            else:
                print("Push (tie)!")

            break
# End the game
print("Thanks for playing!")