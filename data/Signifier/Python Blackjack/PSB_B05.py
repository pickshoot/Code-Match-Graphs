import random

# Define the card values and suits
card_values = {
    "2": 2,
    "3": 3,
    "4": 4,
    "5": 5,
    "6": 6,
    "7": 7,
    "8": 8,
    "9": 9,
    "10": 10,
    "J": 10,
    "Q": 10,
    "K": 10,
    "A": 11
}

suits = ["C", "S", "H", "D"]


# Define the functions for the game
def get_card_deck():
    deck = []
    for suit in suits:
        for value in card_values.keys():
            deck.append(value + suit)
    return deck


def get_card_value(card):
    return card_values[card[0]]


def get_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        value += get_card_value(card)
        if card[0] == "A":
            num_aces += 1
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value


def deal_card(deck, hand):
    card = random.choice(deck)
    hand.append(card)
    deck.remove(card)


def play_game():
    # Initialize the deck and hands
    deck = get_card_deck()
    random.shuffle(deck)
    player_hand = []
    dealer_hand = []

    # Deal the initial cards
    deal_card(deck, player_hand)
    deal_card(deck, dealer_hand)
    deal_card(deck, player_hand)
    deal_card(deck, dealer_hand)

    # Player's turn
    while True:
        print(f"Player's hand: {player_hand} ({get_hand_value(player_hand)})")
        if get_hand_value(player_hand) > 21:
            print("Player busts! Dealer wins.")
            return
        elif get_hand_value(player_hand) == 21:
            print("Player has Blackjack!")
            break
        else:
            action = input("Hit or stand? ")
            if action.lower() == "hit":
                deal_card(deck, player_hand)
            else:
                break

    # Dealer's turn
    while get_hand_value(dealer_hand) < 17:
        deal_card(deck, dealer_hand)
    print(f"Dealer's hand: {dealer_hand} ({get_hand_value(dealer_hand)})")
    if get_hand_value(dealer_hand) > 21:
        print("Dealer busts! Player wins.")
    elif get_hand_value(dealer_hand) == 21:
        print("Dealer has Blackjack! Dealer wins.")
    elif get_hand_value(dealer_hand) > get_hand_value(player_hand):
        print("Dealer wins.")
    elif get_hand_value(dealer_hand) < get_hand_value(player_hand):
        print("Player wins!")
    else:
        print("It's a tie!")


# Play the game
play_game()
