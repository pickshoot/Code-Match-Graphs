import random

def draw_card():
    """
    Returns a random card from a deck of cards.
    """
    deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11] * 4
    return random.choice(deck)

def calculate_score(hand):
    """
    Calculates the score of a hand of cards.
    """
    score = sum(hand)
    if score > 21 and 11 in hand:
        hand.remove(11)
        hand.append(1)
        score = sum(hand)
    return score

def play_game():
    """
    Plays a game of Blackjack.
    """
    print("Welcome to Blackjack!")
    player_hand = [draw_card(), draw_card()]
    dealer_hand = [draw_card(), draw_card()]

    while True:
        print(f"Your hand: {player_hand}")
        print(f"Dealer's hand: [{dealer_hand[0]}, *]")

        player_score = calculate_score(player_hand)
        dealer_score = calculate_score(dealer_hand)

        if player_score > 21:
            print("You went bust! Dealer wins.")
            return

        if dealer_score > 21:
            print("Dealer went bust! You win.")
            return

        if len(player_hand) == 5:
            print("You have 5 cards! You win.")
            return

        if input("Do you want to hit? (y/n): ").lower() == "y":
            player_hand.append(draw_card())
        else:
            while dealer_score < 17:
                dealer_hand.append(draw_card())
                dealer_score = calculate_score(dealer_hand)

            print(f"Your hand: {player_hand}")
            print(f"Dealer's hand: {dealer_hand}")

            if player_score > dealer_score:
                print("You win!")
            elif player_score == dealer_score:
                print("It's a tie!")
            else:
                print("Dealer wins!")
            return

if __name__ == "__main__":
    play_game()