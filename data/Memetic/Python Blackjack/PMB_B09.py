import random

# Define the deck of cards
deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K', 'A']

# Define the value of each card
card_value = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10, 'A': 11}

# Define the Blackjack game class
class Blackjack:
    def __init__(self):
        self.deck = deck.copy()
        self.player_cards = []
        self.dealer_cards = []
        self.player_score = 0
        self.dealer_score = 0
        self.game_over = False

    def deal_cards(self):
        # Shuffle the deck
        random.shuffle(self.deck)

        # Deal the initial two cards to the player and the dealer
        self.player_cards = [self.deck.pop(), self.deck.pop()]
        self.dealer_cards = [self.deck.pop(), self.deck.pop()]

        # Calculate the initial scores of the player and the dealer
        self.player_score = sum(card_value[str(card)] for card in self.player_cards)
        self.dealer_score = sum(card_value[str(card)] for card in self.dealer_cards)

    def hit(self):
        #Deal an additional card to the player and recalculate their score
        self.player_cards.append(self.deck.pop())
        self.player_score = sum(card_value[str(card)] for card in self.player_cards)
        # Check if the player has busted
        if self.player_score > 21:
            print("You have busted!")
            self.game_over = True

    def stand(self):
        # Keep dealing cards to the dealer until their score is 17 or more
        while self.dealer_score < 17:
            self.dealer_cards.append(self.deck.pop())
            self.dealer_score = sum(card_value[str(card)] for card in self.dealer_cards)

        # Determine the winner of the game
        if self.dealer_score > 21:
            print("You win! The dealer has busted.")
        elif self.player_score > self.dealer_score:
            print("You win!")
        elif self.player_score < self.dealer_score:
            print("You lose.")
        else:
            print("It's a tie.")

        self.game_over = True

    def play_game(self):
        # Deal the initial two cards
        self.deal_cards()

        # Print the player's cards and score
        print("Your cards: ", self.player_cards)
        print("Your score: ", self.player_score)

        # Print the dealer's up card
        print("Dealer's up card: ", self.dealer_cards[0])

        # Keep prompting the player to hit or stand until the game is over
        while not self.game_over:
            action = input("Do you want to hit or stand? ").lower()
            if action == "hit":
                self.hit()
                if not self.game_over:
                    print("Your cards: ", self.player_cards)
                    print("Your score: ", self.player_score)
            elif action == "stand":
                self.stand()

# Play the game
if __name__ == "__main__":
    game = Blackjack()
    game.play_game()