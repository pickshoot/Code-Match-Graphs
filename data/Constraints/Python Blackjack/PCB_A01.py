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
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ["Jack", "Queen", "King", "Ace"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank == "Ace" and self.value + 11 <= 21:
            self.value += 11
        elif card.rank == "Ace":
            self.value += 1
        elif card.rank in ["Jack", "Queen", "King"]:
            self.value += 10
        else:
            self.value += int(card.rank)

    def __str__(self):
        return f"Cards: {[str(card) for card in self.cards]}, Value: {self.value}"


class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()
        self.chips = 100

    def add_chips(self, amount):
        self.chips += amount

    def subtract_chips(self, amount):
        self.chips -= amount

    def __str__(self):
        return f"{self.name}: {self.hand}, Chips: {self.chips}"


class Dealer:
    def __init__(self):
        self.name = "Dealer"
        self.hand = Hand()

    def __str__(self):
        return f"{self.name}: {[str(card) for card in self.hand.cards[1:]]}, Value: {self.hand.value}"


class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player("Player")
        self.dealer = Dealer()
        self.play_game()

    def take_bet(self):
        while True:
            try:
                bet = int(input("Enter bet amount: "))
            except:
                print("Invalid input. Please enter an integer.")
            else:
                if bet <= self.player.chips:
                    self.player.subtract_chips(bet)
                    return bet
                else:
                    print(f"Insufficient chips. Your current chips: {self.player.chips}")

    def hit(self, hand):
        hand.add_card(self.deck.deal())

    def hit_or_stand(self, hand):
        while True:
            choice = input("Would you like to hit or stand? ")
            if choice.lower() == "hit":
                self.hit(hand)
                print(f"{hand}")
                if hand.value > 21:
                    print("Bust!")
                    return "Bust"
            elif choice.lower() == "stand":
                print("Stand.")
                return "Stand"
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

    def show_all(self):
        print(self.dealer)
        print(self.player)

    def show_some(self):
        print(f"{self.dealer.name}: {self.dealer.hand.cards[1]}")
        print(self.player)

    def player_wins(self, bet):
        print("Player wins!")
        self.player.add_chips(bet * 2)

    def dealer_wins(self, bet):
        print("Dealer wins!")

    def push(self, bet):
        print("Push!")
        self.player.add_chips(bet)

    def play_game(self):
        print("Welcome to Blackjack!")
        while True:
            bet = self.take_bet()
            self.player.hand = Hand()
            self.dealer.hand = Hand()
            for i in range(2):
                self.player.hand.add_card(self.deck.deal())
                self.dealer.hand.add_card(self.deck.deal())
            self.show_some()
            while True:
                player_choice = self.hit_or_stand(self.player.hand)
                if player_choice == "Bust":
                    self.dealer_wins(bet)
                    break
                elif player_choice == "Stand":
                    while self.dealer.hand.value < 17:
                        self.hit(self.dealer.hand)
                    self.show_all()
                    if self.dealer.hand.value > 21:
                        self.player_wins(bet)
                        break
                    elif self.player.hand.value > self.dealer.hand.value:
                        self.player_wins(bet)
                        break
                    elif self.player.hand.value < self.dealer.hand.value:
                        self.dealer_wins(bet)
                        break
                    else:
                        self.push(bet)
                        break
            if self.player.chips == 0:
                print("You have run out of chips.")
                break
            else:
                play_again = input("Would you like to play again? ")
                if play_again.lower() != "yes":
                    break

if __name__ == "__main__":
    game = Blackjack()