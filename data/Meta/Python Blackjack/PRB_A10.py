import random

def deal_card():
    """Returns a random card from the deck."""
    deck = [2,3,4,5,6,7,8,9,10,'J','Q','K','A']
    return random.choice(deck)

def calculate_score(cards):
    """Takes a list of cards and returns the score calculated from the cards."""
    score = 0
    num_aces = 0
    for card in cards:
        if card == 'A':
            num_aces += 1
            score += 11
        elif card in ['J','Q','K']:
            score += 10
        else:
            score += card
    while score > 21 and num_aces:
        score -= 10
        num_aces -= 1
    return score

def compare(user_score, computer_score):
    """Compares the user's score and the computer's score and returns the result."""
    if user_score == computer_score:
        return "It's a tie!"
    elif computer_score == 21:
        return "Computer has a Blackjack. You lose!"
    elif user_score == 21:
        return "You have a Blackjack. You win!"
    elif user_score > 21:
        return "You went over. You lose!"
    elif computer_score > 21:
        return "Computer went over. You win!"
    elif user_score > computer_score:
        return "You win!"
    else:
        return "You lose!"

def play_game():
    print("Welcome to Blackjack!")

    user_cards = [deal_card(), deal_card()]
    computer_cards = [deal_card(), deal_card()]

    while True:
        user_score = calculate_score(user_cards)
        computer_score = calculate_score(computer_cards)
        print(f"Your cards: {user_cards}, current score: {user_score}")
        print(f"Computer's first card: {computer_cards[0]}")

        if user_score == 21 or computer_score == 21 or input("Do you want to draw another card? Type 'y' or 'n': ") == 'n':
            print(f"Computer's cards: {computer_cards}, final score: {computer_score}")
            print(compare(user_score, computer_score))
            break

        user_cards.append(deal_card())

        while computer_score < 17:
            computer_cards.append(deal_card())
            computer_score = calculate_score(computer_cards)

    if input("Do you want to play again? Type 'y' or 'n': ") == 'y':
        play_game()

play_game()
