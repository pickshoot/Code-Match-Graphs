import random

def calculate_hand_value(hand):
    """Calculates the value of a given hand."""
    hand_value = sum(hand)
    if 11 in hand and hand_value > 21:
        hand_value -= 10
    return hand_value

def deal_card():
    """Deals a card from a deck of cards."""
    card = random.randint(2, 11)
    if card == 11:
        card = 10
    return card

def play_blackjack():
    """Plays a game of Blackjack."""
    print("Welcome to Blackjack!")
    balance = 100
    while balance > 0:
        print("Balance:", balance)
        bet = int(input("Place your bet: "))
        player_hand = [deal_card(), deal_card()]
        dealer_hand = [deal_card(), deal_card()]
        player_hand_value = calculate_hand_value(player_hand)
        dealer_face_up_card = dealer_hand[0]
        print("Your hand:", player_hand)
        print("Dealer's face-up card:", dealer_face_up_card)
        while True:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                player_hand.append(deal_card())
                player_hand_value = calculate_hand_value(player_hand)
                print("Your hand:", player_hand)
                if player_hand_value > 21:
                    print("Bust! You lose.")
                    balance -= bet
                    break
            elif action == "stand":
                print("Dealer's hand:", dealer_hand)
                dealer_hand_value = calculate_hand_value(dealer_hand)
                while dealer_hand_value < 17:
                    dealer_hand.append(deal_card())
                    dealer_hand_value = calculate_hand_value(dealer_hand)
                    print("Dealer's hand:", dealer_hand)
                if dealer_hand_value > 21:
                    print("Dealer busts! You win.")
                    balance += bet
                    break
                elif dealer_hand_value > player_hand_value:
                    print("Dealer wins!")
                    balance -= bet
                    break
                elif dealer_hand_value < player_hand_value:
                    print("You win!")
                    balance += bet
                    break
                else:
                    print("It's a tie!")
                    break
            else:
                print("Invalid action. Please try again.")
        if balance <= 0:
            print("Game over. You are out of money.")
            break
        else:
            play_again = input("Do you want to play again? (y/n) ")
            if play_again.lower() == "n":
                break

play_blackjack()
