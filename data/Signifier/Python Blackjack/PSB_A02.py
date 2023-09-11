import random

def deal_card():
    """Return a random card from the deck"""
    deck = [
        "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"
    ]
    return random.choice(deck)

def calculate_hand(hand):
    """Calculate the value of a hand"""
    value = 0
    num_aces = 0
    for card in hand:
        if card.isdigit():
            value += int(card)
        elif card in ["Jack", "Queen", "King"]:
            value += 10
        elif card == "Ace":
            num_aces += 1
            value += 11
    while num_aces > 0 and value > 21:
        value -= 10
        num_aces -= 1
    return value

def play_blackjack():
    """Play a game of blackjack"""
    print("Let's play Blackjack!")
    player_hand = [deal_card(), deal_card()]
    dealer_hand = [deal_card(), deal_card()]
    player_turn = True
    while True:
        print(f"Player hand: {player_hand}, value: {calculate_hand(player_hand)}")
        print(f"Dealer hand: [{dealer_hand[0]}, ?]")
        if player_turn:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                player_hand.append(deal_card())
                if calculate_hand(player_hand) > 21:
                    print("Bust! You lose.")
                    return
            elif action == "stand":
                player_turn = False
        else:
            if calculate_hand(dealer_hand) >= 17:
                break
            else:
                dealer_hand.append(deal_card())
    print(f"Player hand: {player_hand}, value: {calculate_hand(player_hand)}")
    print(f"Dealer hand: {dealer_hand}, value: {calculate_hand(dealer_hand)}")
    player_value = calculate_hand(player_hand)
    dealer_value = calculate_hand(dealer_hand)
    if player_value > 21:
        print("Bust! You lose.")
    elif dealer_value > 21:
        print("Dealer bust! You win.")
    elif dealer_value > player_value:
        print("Dealer wins!")
    elif player_value > dealer_value:
        print("You win!")
    else:
        print("It's a tie!")

if __name__ == '__main__':
    play_blackjack()