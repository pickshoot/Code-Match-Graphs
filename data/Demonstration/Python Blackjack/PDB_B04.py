import random

#Define card values and suits
card_values = {"Ace": 11, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "Jack": 10, "Queen": 10, "King": 10}
card_suits = ["Clubs", "Diamonds", "Hearts", "Spades"]

#Define function to create a deck of cards
def create_deck():
    deck = []
    for suit in card_suits:
        for value in card_values:
            card = value + " of " + suit
            deck.append(card)
    random.shuffle(deck)
    return deck

#Define function to calculate the value of a hand
def calculate_hand(hand):
    total = 0
    aces = 0
    for card in hand:
        value = card.split()[0]
        if value == "Ace":
            aces += 1
        total += card_values[value]
    while total > 21 and aces > 0:
        total -= 10
        aces -= 1
    return total

#Define function to play the game
def play_blackjack():
    print("Welcome to Blackjack!")
    deck = create_deck()
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    print("Dealer's hand: [hidden card, " + dealer_hand[1] + "]")
    print("Your hand: " + ", ".join(player_hand))
    while True:
        player_total = calculate_hand(player_hand)
        if player_total == 21:
            print("Blackjack! You win!")
            return
        if player_total > 21:
            print("Bust! You lose.")
            return
        action = input("Do you want to hit or stand? ")
        if action.lower() == "hit":
            player_hand.append(deck.pop())
            print("Your hand: " + ", ".join(player_hand))
        elif action.lower() == "stand":
            break
        else:
            print("Invalid input. Please enter 'hit' or 'stand'.")
    dealer_total = calculate_hand(dealer_hand)
    while dealer_total < 17:
        dealer_hand.append(deck.pop())
        dealer_total = calculate_hand(dealer_hand)
    print("Dealer's hand: " + ", ".join(dealer_hand))
    if dealer_total > 21:
        print("Dealer bust! You win!")
        return
    if dealer_total > player_total:
        print("Dealer wins!")
        return
    elif dealer_total < player_total:
        print("You win!")
        return
    else:
        print("It's a tie!")
        return
#Call the play_blackjack function to start the game

play_blackjack()
