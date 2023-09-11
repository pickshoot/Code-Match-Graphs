import random

def deal_card():
    """Returns a random card from the deck."""
    cards = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
    return random.choice(cards)

def calculate_score(cards):
    """Returns the total score of a list of cards."""
    score = 0
    for card in cards:
        if card == 'Ace':
            score += 11
        elif card in ['Jack', 'Queen', 'King']:
            score += 10
        else:
            score += int(card)
    # If the score is over 21 and there's an Ace, treat it as 1 instead of 11
    if score > 21 and 'Ace' in cards:
        score -= 10
    return score

def compare(user_score, computer_score):
    """Compares the user and computer scores and returns a string indicating the winner."""
    if user_score > 21:
        return "You went over. You lose."
    elif computer_score > 21:
        return "Computer went over. You win!"
    elif user_score == computer_score:
        return "It's a draw."
    elif user_score > computer_score:
        return "You win!"
    else:
        return "You lose."

def play_game():
    """Plays a game of Blackjack."""
    user_cards = []
    computer_cards = []
    game_over = False
    
    # Deal two cards to the user and one to the computer
    for i in range(2):
        user_cards.append(deal_card())
    computer_cards.append(deal_card())
    
    while not game_over:
        user_score = calculate_score(user_cards)
        computer_score = calculate_score(computer_cards)
        print(f"Your cards: {user_cards}, current score: {user_score}")
        print(f"Computer's first card: {computer_cards[0]}")
        
        # If user's score is already 21, end the game
        if user_score == 21:
            print("Blackjack! You win!")
            game_over = True
        else:
            # Ask user if they want to draw another card or end their turn
            user_choice = input("Type 'hit' to draw another card, or 'stand' to end your turn: ").lower()
            if user_choice == 'hit':
                user_cards.append(deal_card())
            elif user_choice == 'stand':
                game_over = True
                # Computer draws a card if its score is less than 17
            if computer_score < 17:
                computer_cards.append(deal_card())
            
            # Check if game is over
            if user_score > 21 or computer_score == 21 or len(computer_cards) == 5:
                game_over = True
            
    # Show the final scores and the winner
    print(f"Your final hand: {user_cards}, final score: {user_score}")
    print(f"Computer's final hand: {computer_cards}, final score: {computer_score}")
    print(compare(user_score, computer_score))
    
play_game()