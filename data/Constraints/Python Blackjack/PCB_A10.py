import random


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:
    def __init__(self):
        self.cards = []
        suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
        ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
        for suit in suits:
            for rank in ranks:
                self.cards.append(Card(suit, rank))
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank == 'Ace' and self.value + 11 <= 21:
            self.value += 11
        elif card.rank in ['Jack', 'Queen', 'King']:
            self.value += 10
        else:
            self.value += int(card.rank)

    def __str__(self):
        return ', '.join(str(card) for card in self.cards)


class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()
        self.balance = 100

    def bet(self, amount):
        if amount > self.balance:
            print("Sorry, you don't have enough balance.")
            return False
        self.balance -= amount
        return True

    def win(self, amount):
        self.balance += amount

    def __str__(self):
        return f"{self.name} has {self.hand} ({self.hand.value})"

class BlackjackGame:
    def init(self):
        self.player = Player("Player")
        self.dealer = Player("Dealer")
        self.deck = Deck()

    def play(self):
        print("Welcome to Blackjack!")
        while True:
            print(f"You have {self.player.balance} in your balance.")
            bet_amount = int(input("How much would you like to bet? "))
            if self.player.bet(bet_amount):
                self.deal_cards()
                if self.check_blackjack():
                    continue
                while self.player_hand_value() <= 21:
                    if self.hit_or_stand() == 'stand':
                        break
                self.dealer_play()
                self.end_game()
            else:
                continue

    def deal_cards(self):
        for _ in range(2):
            self.player.hand.add_card(self.deck.deal_card())
            self.dealer.hand.add_card(self.deck.deal_card())
        print(f"Player's hand: {self.player.hand}")
        print(f"Dealer's hand: {self.dealer.hand.cards[0]}, [Hidden]")

    def check_blackjack(self):
        if self.player.hand.value == 21:
            print("Blackjack! You win.")
            self.player.win(1.5 * bet_amount)
            return True
        elif self.dealer.hand.value == 21:
            print("Dealer has Blackjack. You lose.")
            return True
        return False

    def player_hand_value(self):
        return self.player.hand.value

    def hit_or_stand(self):
        while True:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == 'hit':
                self.player.hand.add_card(self.deck.deal_card())
                print(f"Player's hand: {self.player.hand} ({self.player.hand.value})")
                if self.player.hand.value > 21:
                    print("Bust! You lose.")
                    return 'stand'
            elif choice.lower() == 'stand':
                break
        return choice.lower()

    def dealer_play(self):
        print(f"Dealer's hand: {self.dealer.hand}")
        while self.dealer.hand.value < 17:
            self.dealer.hand.add_card(self.deck.deal_card())
            print(f"Dealer hits and draws {self.dealer.hand.cards[-1]}")
            print(f"Dealer's hand: {self.dealer.hand}")
        if self.dealer.hand.value > 21:
            print("Dealer busts! You win.")
            self.player.win(2 * bet_amount)
        elif self.dealer.hand.value > self.player.hand.value:
            print("Dealer wins!")
        elif self.dealer.hand.value == self.player.hand.value:
            print("Push.")
            self.player.win(bet_amount)
        else:
            print("You win!")
            self.player.win(2 * bet_amount)

    def end_game(self):
        print(f"Your balance is {self.player.balance}.")
        while True:
            choice = input("Do you want to play again? (Y/N) ")
            if choice.lower() == 'y':
                self.reset()
                break
            elif choice.lower() == 'n':
                print("Thanks for playing!")
                exit()
            else:
                continue

    def reset(self):
        self.player.hand = Hand()
        self.dealer.hand = Hand()
        self.deck = Deck()
        print("\n\n\n")  # Add some spacing between games
        print("=" * 50)
        print("Starting a new game of Blackjack!")
        print("=" * 50)

if __name__ == '__main__':
    game = BlackjackGame()
    game.init()
    game.play()