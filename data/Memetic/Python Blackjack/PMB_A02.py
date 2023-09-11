import random

# initialize the deck
deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K', 'A']

# initialize the values of face cards and Ace
face_values = {'J': 10, 'Q': 10, 'K': 10, 'A': 11}

# function to get the total value of a hand
def get_total(hand):
    total = 0
    aces = 0
    for card in hand:
        if card == 'A':
            aces += 1
        else:
            total += face_values.get(card, card)
    while total > 21 and aces > 0:
        total -= 10
        aces -= 1
    return total

# function to deal a card
def deal_card():
    return random.choice(deck)

# function to play a round of Blackjack
def play_round():
    # initialize the player's and dealer's hands
    player_hand = [deal_card(), deal_card()]
    dealer_hand = [deal_card(), deal_card()]

    # check for a natural Blackjack
    if get_total(player_hand) == 21:
        print("You have a Blackjack!")
        return

    # player's turn
    while True:
        print("Your hand:", player_hand)
        print("Your total:", get_total(player_hand))
        if get_total(player_hand) > 21:
            print("Bust! You lose.")
            return
        action = input("Hit or stand? ")
        if action.lower() == "hit":
            player_hand.append(deal_card())
        else:
            break

    # dealer's turn
    while get_total(dealer_hand) < 17:
        dealer_hand.append(deal_card())
    print("Dealer's hand:", dealer_hand)
    print("Dealer's total:", get_total(dealer_hand))
    if get_total(dealer_hand) > 21:
        print("Dealer busts! You win.")
        return
    
    # determine the winner
    player_total = get_total(player_hand)
    dealer_total = get_total(dealer_hand)
    if player_total > dealer_total:
        print("You win!")
    elif player_total < dealer_total:
        print("Dealer wins.")
    else:
        print("It's a tie.")

while True:
    play_round()
    play_again = input("Play again? (y/n) ")
    if play_again.lower() != "y":
        break

print("Thanks for playing!")