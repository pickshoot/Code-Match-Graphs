import random

def create_deck():
    deck = []
    suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
    ranks = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
    for suit in suits:
        for rank in ranks:
            deck.append(rank + " of " + suit)
    return deck

def deal_cards(deck):
    hand = []
    for i in range(2):
        card = deck.pop()
        hand.append(card)
    return hand

def calculate_score(hand):
    score = 0
    for card in hand:
        rank = card.split()[0]
        if rank == "Ace":
            score += 11
        elif rank in ["Jack", "Queen", "King"]:
            score += 10
        else:
            score += int(rank)
    # adjust score for aces
    num_aces = hand.count("Ace")
    while score > 21 and num_aces:
        score -= 10
        num_aces -= 1
    return score

def display_hand(hand):
    print("Hand: ", end="")
    for card in hand:
        print(card + " ", end="")
    print()

def blackjack():
    deck = create_deck()
    random.shuffle(deck)
    player_hand = deal_cards(deck)
    dealer_hand = deal_cards(deck)
    player_score = calculate_score(player_hand)
    dealer_score = calculate_score(dealer_hand)
    print("Player:")
    display_hand(player_hand)
    print("Score:", player_score)
    print()
    print("Dealer:")
    display_hand([dealer_hand[0]])
    print()
    if player_score == 21:
        print("Blackjack! You win!")
        return
    while True:
        choice = input("Hit or stand? ")
        if choice.lower() == "hit":
            player_hand.append(deck.pop())
            player_score = calculate_score(player_hand)
            print("Player:")
            display_hand(player_hand)
            print("Score:", player_score)
            print()
            if player_score > 21:
                print("Bust! You lose.")
                return
        elif choice.lower() == "stand":
            while dealer_score < 17:
                dealer_hand.append(deck.pop())
                dealer_score = calculate_score(dealer_hand)
            print("Dealer:")
            display_hand(dealer_hand)
            print("Score:", dealer_score)
            print()
            if dealer_score > 21:
                print("Dealer bust! You win!")
                return
            elif player_score > dealer_score:
                print("You win!")
                return
            elif player_score < dealer_score:
                print("You lose.")
                return
            else:
                print("Push!")
                return

blackjack()
