import random

def calculate_hand(hand):
    total = sum(hand)
    if 1 in hand and total + 10 <= 21:
        return total + 10
    else:
        return total

def deal_cards():
    deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11] * 4
    random.shuffle(deck)
    player_hand = [deck.pop() for _ in range(2)]
    dealer_hand = [deck.pop() for _ in range(2)]
    return player_hand, dealer_hand, deck

def hit(hand, deck):
    hand.append(deck.pop())

def print_hand(hand):
    print(" ".join(str(card) for card in hand))

def blackjack():
    player_hand, dealer_hand, deck = deal_cards()

    print("Player's hand: ")
    print_hand(player_hand)

    print("Dealer's hand: ")
    print(dealer_hand[0], "X")

    while True:
        choice = input("Do you want to hit or stand? ")
        if choice == "hit":
            hit(player_hand, deck)
            print("Player's hand: ")
            print_hand(player_hand)
            if calculate_hand(player_hand) > 21:
                print("Bust! You lose.")
                return
        else:
            break

    while calculate_hand(dealer_hand) < 17:
        hit(dealer_hand, deck)
        print("Dealer's hand: ")
        print_hand(dealer_hand)

    player_score = calculate_hand(player_hand)
    dealer_score = calculate_hand(dealer_hand)

    print("Player's hand: ")
    print_hand(player_hand)
    print("Player's score:", player_score)

    print("Dealer's hand: ")
    print_hand(dealer_hand)
    print("Dealer's score:", dealer_score)

    if dealer_score > 21:
        print("Dealer busts! You win.")
    elif player_score > 21:
        print("Bust! You lose.")
    elif player_score == dealer_score:
        print("Push!")
    elif player_score > dealer_score:
        print("You win!")
    else:
        print("You lose!")

while True:
    blackjack()
    play_again = input("Do you want to play again? ")
    if play_again.lower() != "yes":
        break
