import random

def draw_card():
    """Return a random card from a deck of cards."""
    deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    return random.choice(deck)

def calculate_score(hand):
    """Calculate the score of a hand of cards."""
    score = 0
    num_aces = 0
    for card in hand:
        if card.isdigit():
            score += int(card)
        elif card in ['J', 'Q', 'K']:
            score += 10
        else:
            num_aces += 1
            score += 11
    while num_aces > 0 and score > 21:
        num_aces -= 1
        score -= 10
    return score

def play_blackjack():
    """Play a game of Blackjack."""
    print("Welcome to Blackjack!")
    player_hand = [draw_card(), draw_card()]
    dealer_hand = [draw_card(), draw_card()]
    print("Your hand:", player_hand)
    print("Dealer's hand:", dealer_hand[0])
    while True:
        player_score = calculate_score(player_hand)
        if player_score == 21:
            print("Blackjack! You win!")
            return
        elif player_score > 21:
            print("Bust! You lose!")
            return
        else:
            action = input("Hit or stand? ")
            if action.lower() == 'hit':
                player_hand.append(draw_card())
                print("Your hand:", player_hand)
            else:
                break
    dealer_score = calculate_score(dealer_hand)
    while dealer_score < 17:
        dealer_hand.append(draw_card())
        dealer_score = calculate_score(dealer_hand)
    print("Your hand:", player_hand)
    print("Dealer's hand:", dealer_hand)
    if dealer_score > 21:
        print("Dealer bust! You win!")
    elif dealer_score > player_score:
        print("You lose!")
    elif dealer_score < player_score:
        print("You win!")
    else:
        print("Push! It's a tie.")
play_blackjack()