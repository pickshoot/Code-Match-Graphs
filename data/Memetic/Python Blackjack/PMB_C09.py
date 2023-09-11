import random

# Initialize the deck of cards
suits = ['Hearts', 'Diamonds', 'Spades', 'Clubs']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = [(s, r) for s in suits for r in ranks]

# Define the value of each card
card_values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

def get_card_value(card):
    """
    Returns the numerical value of a card (e.g. 'King' -> 10)
    """
    return card_values[card[1]]

def get_hand_value(hand):
    """
    Returns the numerical value of a hand of cards
    """
    hand_value = sum([get_card_value(card) for card in hand])
    aces = sum([1 for card in hand if card[1] == 'Ace'])
    while hand_value > 21 and aces:
        hand_value -= 10
        aces -= 1
    return hand_value

def get_player_move():
    """
    Prompts the player for their move (hit or stand) and returns it
    """
    move = input("Do you want to hit or stand? ")
    while move not in ['hit', 'stand']:
        move = input("Invalid move. Do you want to hit or stand? ")
    return move

def play_game():
    """
    Plays a game of Blackjack
    """
    # Shuffle the deck and deal the cards
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]

    # Player's turn
    while True:
        print(f"Player's hand: {player_hand} ({get_hand_value(player_hand)})")
        print(f"Dealer's hand: [{dealer_hand[0]}, ?]")
        move = get_player_move()
        if move == 'hit':
            player_hand.append(deck.pop())
            if get_hand_value(player_hand) > 21:
                print(f"Player's hand: {player_hand} ({get_hand_value(player_hand)})")
                print("Bust! Dealer wins.")
                return
        else:
            break
        
    # Dealer's turn
    while get_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())

    # Determine the winner
    player_hand_value = get_hand_value(player_hand)
    dealer_hand_value = get_hand_value(dealer_hand)
    print(f"Player's hand: {player_hand} ({player_hand_value})")
    print(f"Dealer's hand: {dealer_hand} ({dealer_hand_value})")
    if dealer_hand_value > 21:
        print("Dealer busts! Player wins.")
    elif dealer_hand_value > player_hand_value:
        print("Dealer wins.")
    elif player_hand_value > dealer_hand_value:
        print("Player wins.")
    else:
        print("It's a tie.")

if __name__ == '__main__':
    play_game()