import random

def create_deck():
    """Create a new deck of cards."""
    deck = []
    for suit in ['hearts', 'diamonds', 'clubs', 'spades']:
        for rank in range(2, 11):
            deck.append((rank, suit))
        for face_card in ['J', 'Q', 'K', 'A']:
            deck.append((face_card, suit))
    random.shuffle(deck)
    return deck

def get_card_value(card):
    """Return the value of a card."""
    if card[0] in ['J', 'Q', 'K']:
        return 10
    elif card[0] == 'A':
        return 11
    else:
        return card[0]

def calculate_hand_value(hand):
    """Return the value of a hand."""
    value = 0
    num_aces = 0
    for card in hand:
        card_value = get_card_value(card)
        if card_value == 11:
            num_aces += 1
        value += card_value
    while value > 21 and num_aces:
        value -= 10
        num_aces -= 1
    return value

def display_hand(hand):
    """Display a hand of cards."""
    for card in hand:
        print(f"{card[0]} of {card[1]}")

def play_blackjack():
    """Play a game of Blackjack."""
    deck = create_deck()
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    while True:
        print("Player's hand:")
        display_hand(player_hand)
        player_hand_value = calculate_hand_value(player_hand)
        print(f"Value: {player_hand_value}")
        if player_hand_value > 21:
            print("Bust! You lose.")
            return
        elif player_hand_value == 21:
            print("Blackjack! You win!")
            return
        print("Dealer's hand:")
        print(f"{dealer_hand[0][0]} of {dealer_hand[0][1]}")
        if calculate_hand_value(dealer_hand) == 21:
            print("Dealer has blackjack! You lose.")
            return
        action = input("What would you like to do? (hit/stay) ")
        if action == 'hit':
            player_hand.append(deck.pop())
        else:
            while calculate_hand_value(dealer_hand) < 17:
                dealer_hand.append(deck.pop())
            dealer_hand_value = calculate_hand_value(dealer_hand)
            print("Dealer's hand:")
            display_hand(dealer_hand)
            print(f"Value: {dealer_hand_value}")
            if dealer_hand_value > 21:
                print("Dealer busts! You win!")
                return
            elif dealer_hand_value > player_hand_value:
                print("Dealer wins!")
                return
            elif dealer_hand_value == player_hand_value:
                print("Push!")
                return
            else:
                print("You win!")
                return

play_blackjack()
