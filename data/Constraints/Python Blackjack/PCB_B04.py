import random

# Define global constants for the game
CARD_VALUES = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10, 'A': 11}
DECK_SIZE = 52

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = []
        self.hand_value = 0
        self.num_aces = 0

    def add_card(self, card):
        self.hand.append(card)
        self.hand_value += CARD_VALUES[card[:-1]]
        if card[:-1] == 'A':
            self.num_aces += 1
        while self.hand_value > 21 and self.num_aces:
            self.hand_value -= 10
            self.num_aces -= 1

    def clear_hand(self):
        self.hand = []
        self.hand_value = 0
        self.num_aces = 0

class Blackjack:
    def __init__(self):
        self.players = []
        self.deck = []
        self.dealer = Player('Dealer')
        self.num_decks = 1
        self.initialize_deck()

    def initialize_deck(self):
        self.deck = [f'{card}{suit}' for card in CARD_VALUES.keys() for suit in ['H', 'D', 'C', 'S']] * self.num_decks
        random.shuffle(self.deck)

    def add_player(self, player):
        self.players.append(player)

    def deal_cards(self):
        for _ in range(2):
            for player in [self.dealer] + self.players:
                card = self.deck.pop()
                player.add_card(card)

    def play_game(self):
        print("Let's play Blackjack!")
        while True:
            try:
                self.num_decks = int(input("Enter the number of decks to use (1-8): "))
                if self.num_decks < 1 or self.num_decks > 8:
                    raise ValueError
                break
            except ValueError:
                print("Please enter a valid number of decks (1-8)")

        self.initialize_deck()

        num_players = 0
        while True:
            try:
                num_players = int(input("Enter the number of players (1-6): "))
                if num_players < 1 or num_players > 6:
                    raise ValueError
                break
            except ValueError:
                print("Please enter a valid number of players (1-6)")

        for i in range(num_players):
            name = input(f"Enter the name of player {i + 1}: ")
            player = Player(name)
            self.add_player(player)

        self.deal_cards()

        # Show dealer's up card
        print(f"\nDealer's up card: {self.dealer.hand[1]}")

        # Player turns
        for player in self.players:
            while True:
                print(f"\n{player.name}'s turn:")
                print(f"{player.name}'s hand: {', '.join(player.hand)}")
                print(f"{player.name}'s hand value: {player.hand_value}")
                choice = input("Do you want to hit or stand? ")
                if choice.lower() == 'hit':
                    player.add_card
                if player.hand_value > 21:
                    print(f"{player.name} busts with {player.hand_value}!")
                    break
                elif choice.lower() == 'stand':
                    print(f"{player.name} stands with {player.hand_value}.")
                    break
                else:
                    print("Please enter 'hit' or 'stand'")

        # Dealer's turn
        print("\nDealer's turn:")
        print(f"Dealer's hand: {', '.join(self.dealer.hand)}")
        print(f"Dealer's hand value: {self.dealer.hand_value}")
        while self.dealer.hand_value < 17:
            card = self.deck.pop()
            self.dealer.add_card(card)
            print(f"Dealer hits and receives {card}.")
            print(f"Dealer's hand: {', '.join(self.dealer.hand)}")
            print(f"Dealer's hand value: {self.dealer.hand_value}")
        if self.dealer.hand_value > 21:
            print("Dealer busts!")
        else:
            print(f"Dealer stands with {self.dealer.hand_value}.")

        # Determine winners
        for player in self.players:
            if player.hand_value > 21:
                print(f"{player.name} loses with {player.hand_value}.")
            elif self.dealer.hand_value > 21:
                print(f"{player.name} wins with {player.hand_value}!")
            elif player.hand_value > self.dealer.hand_value:
                print(f"{player.name} wins with {player.hand_value}!")
            elif player.hand_value == self.dealer.hand_value:
                print(f"{player.name} ties with the dealer with {player.hand_value}!")
            else:
                print(f"{player.name} loses with {player.hand_value}.")

        # Ask if players want to play again
        while True:
            choice = input("Do you want to play again? ")
            if choice.lower() == 'yes':
                for player in [self.dealer] + self.players:
                    player.clear_hand()
                self.deck = []
                self.initialize_deck()
                self.deal_cards()
                break
            elif choice.lower() == 'no':
                print("Thanks for playing!")
                return
            else:
                print("Please enter 'yes' or 'no'.")

if __name__ == '__main__':
    game = Blackjack()
    game.play_game()