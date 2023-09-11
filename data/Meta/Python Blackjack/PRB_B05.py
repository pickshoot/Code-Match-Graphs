import random

def deal_card():
    """Return a random card from the deck."""
    cards = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    return random.choice(cards)

def hand_value(hand):
    """Return the value of a hand of cards."""
    value = 0
    num_aces = 0
    for card in hand:
        if card == 'A':
            num_aces += 1
            value += 11
        elif card in ['K', 'Q', 'J']:
            value += 10
        else:
            value += int(card)
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

def play_blackjack():
    """Play a game of Blackjack."""
    print("Let's play Blackjack!")
    bankroll = 100
    while True:
        print(f"\nYou have ${bankroll}.")
        bet = int(input("How much would you like to bet? "))
        if bet > bankroll:
            print("Sorry, you don't have enough money to make that bet.")
            continue
        player_hand = [deal_card(), deal_card()]
        dealer_hand = [deal_card(), deal_card()]
        print(f"\nYour hand: {player_hand}")
        print(f"Dealer's hand: {dealer_hand[0]}, _")
        while True:
            action = input("\nWould you like to hit or stand? ")
            if action.lower() == 'hit':
                player_hand.append(deal_card())
                print(f"\nYour hand: {player_hand}")
                if hand_value(player_hand) > 21:
                    print("Bust! You lose.")
                    bankroll -= bet
                    break
            elif action.lower() == 'stand':
                dealer_hand[1] = deal_card()
                print(f"\nYour hand: {player_hand}")
                print(f"Dealer's hand: {dealer_hand}")
                while hand_value(dealer_hand) < 17:
                    dealer_hand.append(deal_card())
                    print(f"Dealer's hand: {dealer_hand}")
                if hand_value(dealer_hand) > 21:
                    print("Dealer busts! You win.")
                    bankroll += bet
                    break
                elif hand_value(dealer_hand) >= hand_value(player_hand):
                    print("Dealer wins!")
                    bankroll -= bet
                    break
                else:
                    print("You win!")
                    bankroll += bet
                    break
            else:
                print("Sorry, I didn't understand that.")
        if bankroll <= 0:
            print("Game over! You're out of money.")
            break
        play_again = input("\nWould you like to play again? ")
        if play_again.lower() != 'yes':
            break
    print(f"Thanks for playing! Your final bankroll is ${bankroll}.")

play_blackjack()
