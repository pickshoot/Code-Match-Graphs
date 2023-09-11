import random

def draw_card():
    """Returns a random card from the deck."""
    deck = [
        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
    ]
    return random.choice(deck)

def calculate_score(hand):
    """Calculates the score of a hand of cards."""
    score = 0
    num_aces = 0
    for card in hand:
        if card.isdigit():
            score += int(card)
        elif card in ["J", "Q", "K"]:
            score += 10
        elif card == "A":
            num_aces += 1
            score += 11
    while num_aces > 0 and score > 21:
        score -= 10
        num_aces -= 1
    return score

def show_hand(hand, hide_first_card=False):
    """Displays the cards in a hand."""
    if hide_first_card:
        print("[?] " + " ".join(hand[1:]))
    else:
        print(" ".join(hand))

def blackjack():
    """Plays a game of Blackjack."""
    print("Welcome to Blackjack!")
    player_hand = [draw_card(), draw_card()]
    dealer_hand = [draw_card(), draw_card()]

    while True:
        print("Your hand:")
        show_hand(player_hand)
        player_score = calculate_score(player_hand)
        if player_score == 21:
            print("Blackjack! You win!")
            return
        elif player_score > 21:
            print("Bust! You lose.")
            return

        print("Dealer's hand:")
        show_hand(dealer_hand, hide_first_card=True)
        dealer_score = calculate_score(dealer_hand[:1])
        if dealer_score == 21:
            print("Dealer has Blackjack! You lose.")
            return

        action = input("Do you want to hit or stand? ")
        if action.lower() == "hit":
            player_hand.append(draw_card())
        elif action.lower() == "stand":
            while calculate_score(dealer_hand) < 17:
                dealer_hand.append(draw_card())
            print("Dealer's hand:")
            show_hand(dealer_hand)
            dealer_score = calculate_score(dealer_hand)
            if dealer_score > 21:
                print("Dealer busts! You win!")
            elif dealer_score > player_score:
                print("You lose.")
            elif dealer_score < player_score:
                print("You win!")
            else:
                print("It's a tie!")
            return
        else:
            print("Invalid input. Please try again.")
            continue

if __name__ == "__main__":
    blackjack()