import random

# Define the card values
card_values = {'Ace': 11, 'King': 10, 'Queen': 10, 'Jack': 10, 
               'Ten': 10, 'Nine': 9, 'Eight': 8, 'Seven': 7, 
               'Six': 6, 'Five': 5, 'Four': 4, 'Three': 3, 'Two': 2}

# Define the deck of cards
deck = list(card_values.keys()) * 4

# Define the player and dealer hands
player_hand = []
dealer_hand = []

# Define the initial deal
for i in range(2):
    player_card = random.choice(deck)
    player_hand.append(player_card)
    deck.remove(player_card)
    
    dealer_card = random.choice(deck)
    dealer_hand.append(dealer_card)
    deck.remove(dealer_card)

# Define the game loop
while True:
    # Print the player's hand and ask for their move
    print(f"Your hand: {player_hand}")
    move = input("Hit or stand? ")
    
    if move.lower() == "hit":
        # Deal the player a new card
        player_card = random.choice(deck)
        player_hand.append(player_card)
        deck.remove(player_card)
        
        # Check if the player has busted
        if sum([card_values[card] for card in player_hand]) > 21:
            print("Busted! You lose.")
            break
    else:
        # The player stands; reveal the dealer's hand
        print(f"Dealer's hand: {dealer_hand}")
        
        while sum([card_values[card] for card in dealer_hand]) < 17:
            # The dealer hits
            dealer_card = random.choice(deck)
            dealer_hand.append(dealer_card)
            deck.remove(dealer_card)
            
            # Check if the dealer has busted
            if sum([card_values[card] for card in dealer_hand]) > 21:
                print("Dealer busts! You win.")
                break
        
        # Determine the winner
        player_total = sum([card_values[card] for card in player_hand])
        dealer_total = sum([card_values[card] for card in dealer_hand])
        
        if dealer_total > player_total and dealer_total <= 21:
            print("Dealer wins!")
        elif dealer_total == player_total:
            print("Push!")
        else:
            print("You win!")
            
        break