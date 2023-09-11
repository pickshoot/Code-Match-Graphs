import random

def get_card_value(card):
    if card in ['J', 'Q', 'K']:
        return 10
    elif card == 'A':
        return 11
    else:
        return int(card)

def calculate_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        card_value = get_card_value(card)
        if card_value == 11:
            num_aces += 1
        value += card_value
    while num_aces > 0 and value > 21:
        value -= 10
        num_aces -= 1
    return value

def get_player_move():
    while True:
        move = input("Do you want to hit or stand? ")
        if move.lower() in ['hit', 'stand']:
            return move.lower()
        print("Invalid move. Please enter 'hit' or 'stand'.")

def play_game():
    deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop()]
    print(f"Dealer shows: {dealer_hand[0]}")
    while True:
        player_hand_value = calculate_hand_value(player_hand)
        print(f"Your hand: {player_hand} ({player_hand_value})")
        if player_hand_value == 21:
            print("Blackjack! You win!")
            return
        elif player_hand_value > 21:
            print("Bust! You lose.")
            return
        move = get_player_move()
        if move == 'hit':
            player_hand.append(deck.pop())
        else:
            dealer_hand_value = calculate_hand_value(dealer_hand)
            if dealer_hand_value >= 17:
                print(f"Dealer's hand: {dealer_hand} ({dealer_hand_value})")
                if dealer_hand_value > 21:
                    print("Dealer busts! You win!")
                elif dealer_hand_value == player_hand_value:
                    print("Push! It's a tie.")
                elif dealer_hand_value < player_hand_value:
                    print("You win!")
                else:
                    print("You lose.")
                return
            else:
                print("Dealer hits.")
                dealer_hand.append(deck.pop())

if __name__ == '__main__':
    while True:
        play_game()
        while True:
            play_again = input("Do you want to play again? (y/n) ")
            if play_again.lower() == 'n':
                print("Thanks for playing!")
                quit()
            elif play_again.lower() == 'y':
                break
            else:
                print("Invalid input. Please enter 'y' or 'n'.")