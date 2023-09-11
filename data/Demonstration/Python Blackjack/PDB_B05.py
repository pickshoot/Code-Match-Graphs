import random

# Define global constants
DECK = [
    "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
]
VALUES = {
    "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10,
    "J": 10, "Q": 10, "K": 10, "A": 11
}
DEALER_THRESHOLD = 17
MAX_SCORE = 21

def main():
    # Initialize the game
    deck = DECK * 4
    random.shuffle(deck)

    dealer_hand = [draw_card(deck)]
    player_hand = [draw_card(deck), draw_card(deck)]

    print_game_state(player_hand, dealer_hand[0])

    # Player's turn
    while True:
        action = input("Do you want to hit or stand? ").lower()

        if action == "hit":
            player_hand.append(draw_card(deck))
            print_game_state(player_hand, dealer_hand[0])

            if get_hand_score(player_hand) > MAX_SCORE:
                print("You busted! Dealer wins.")
                return
        elif action == "stand":
            break
        else:
            print("Invalid action. Please enter 'hit' or 'stand'.")

    # Dealer's turn
    while get_hand_score(dealer_hand) < DEALER_THRESHOLD:
        dealer_hand.append(draw_card(deck))
        print_game_state(player_hand, dealer_hand[0])

    player_score = get_hand_score(player_hand)
    dealer_score = get_hand_score(dealer_hand)

    if dealer_score > MAX_SCORE:
        print("Dealer busted! You win.")
    elif dealer_score > player_score:
        print("Dealer wins.")
    elif dealer_score < player_score:
        print("You win!")
    else:
        print("It's a tie!")

def draw_card(deck):
    return deck.pop()

def get_hand_score(hand):
    score = sum(VALUES[card] for card in hand)

    # If there is an Ace and the score is over 21, reduce the score by 10
    if "A" in hand and score > MAX_SCORE:
        score -= 10

    return score

def print_game_state(player_hand, dealer_card):
    print(f"Dealer's hand: {dealer_card} ?")
    print(f"Your hand: {' '.join(player_hand)} (score: {get_hand_score(player_hand)})")

if __name__ == "__main__":
    main()
