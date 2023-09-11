import random

# Define global variables for card values and suits
values = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
suits = ["Clubs", "Diamonds", "Hearts", "Spades"]

# Define a function to create a new deck of cards
def create_deck():
    deck = []
    for suit in suits:
        for value in values:
            card = value + " of " + suit
            deck.append(card)
    return deck

# Define a function to deal a card from the deck
def deal_card(deck):
    card = random.choice(deck)
    deck.remove(card)
    return card

# Define a function to calculate the value of a hand
def calculate_hand(hand):
    value = 0
    ace_count = 0
    for card in hand:
        if card.startswith("Ace"):
            value += 11
            ace_count += 1
        elif card.startswith("King") or card.startswith("Queen") or card.startswith("Jack"):
            value += 10
        else:
            value += int(card.split()[0])
    while value > 21 and ace_count > 0:
        value -= 10
        ace_count -= 1
    return value

# Define the main function for the game
def play_blackjack():
    # Create a new deck of cards and shuffle it
    deck = create_deck()
    random.shuffle(deck)

    # Deal two cards to the player and two cards to the dealer
    player_hand = [deal_card(deck), deal_card(deck)]
    dealer_hand = [deal_card(deck), deal_card(deck)]

    # Display the initial hands
    print("Player's hand:", player_hand)
    print("Dealer's hand:", dealer_hand[0])

    # Player's turn
    while True:
        # Ask the player if they want to hit or stand
        action = input("Do you want to hit or stand? ")
        if action.lower() == "hit":
            # Deal another card to the player and display their new hand
            player_hand.append(deal_card(deck))
            print("Player's hand:", player_hand)
            # Check if the player has bust
            if calculate_hand(player_hand) > 21:
                print("Bust! Dealer wins.")
                return
        elif action.lower() == "stand":
            # Player has chosen to stand
            print("Player stands with a hand value of", calculate_hand(player_hand))
            break
        else:
            # Invalid input
            print("Invalid input. Please enter 'hit' or 'stand'.")

    # Dealer's turn
    while calculate_hand(dealer_hand) < 17:
        # Dealer's hand is less than 17, so deal another card to the dealer
        dealer_hand.append(deal_card(deck))
    # Display the dealer's final hand
    print("Dealer's hand:", dealer_hand)

    # Determine the winner
    player_value = calculate_hand(player_hand)
    dealer_value = calculate_hand(dealer_hand)
    if dealer_value > 21:
        print("Dealer busts! Player wins.")
    elif player_value > dealer_value:
        print("Player wins!")
    elif dealer_value > player_value:
        print("Dealer wins.")
    else:
        print("It's a tie!")

# Call the main function to start the game
play_blackjack()