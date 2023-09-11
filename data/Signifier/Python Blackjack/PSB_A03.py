import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value
        
    def __repr__(self):
        return f"{self.value} of {self.suit}"
    
class Deck:
    def __init__(self):
        self.cards = [Card(suit, value) for suit in ["Spades", "Clubs", "Hearts", "Diamonds"] for value in range(1, 14)]
        
    def shuffle(self):
        if len(self.cards) > 1:
            random.shuffle(self.cards)
            
    def deal_card(self):
        if len(self.cards) > 1:
            return self.cards.pop(0)
        
class Hand:
    def __init__(self):
        self.cards = []
        
    def add_card(self, card):
        self.cards.append(card)
        
    def get_value(self):
        value = 0
        has_ace = False
        for card in self.cards:
            if card.value > 10:
                value += 10
            elif card.value == 1:
                has_ace = True
                value += 11
            else:
                value += card.value
                
        if has_ace and value > 21:
            value -= 10
            
        return value
    
    def __repr__(self):
        return str(self.cards)
    
class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        
    def deal_cards(self):
        self.player_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        
    def player_turn(self):
        while True:
            print(f"Your hand: {self.player_hand}")
            player_value = self.player_hand.get_value()
            print(f"Your hand is worth {player_value}")
            if player_value > 21:
                print("You busted! Dealer wins.")
                return False
            elif player_value == 21:
                print("Blackjack! You win!")
                return True
            else:
                hit = input("Do you want to hit? (y/n) ")
                if hit.lower() == "y":
                    self.player_hand.add_card(self.deck.deal_card())
                else:
                    return None
                
    def dealer_turn(self):
        while True:
            print(f"Dealer's hand: {self.dealer_hand}")
            dealer_value = self.dealer_hand.get_value()
            print(f"Dealer's hand is worth {dealer_value}")
            if dealer_value > 21:
                print("Dealer busted! You win!")
                return True
            elif dealer_value >= 17:
                return None
            else:
                self.dealer_hand.add_card(self.deck.deal_card())
                
    def determine_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if player_value > 21:
            return False
        elif dealer_value > 21:
            return True
        elif player_value == dealer_value:
            print("It's a tie!")
            return None
        elif player_value > dealer_value:
            return True
        else:
            return False
        
    def play_game(self):
        self.deal_cards()
        game_over = self.player_turn()
        if not game_over:
            return
        self.dealer_turn()
        winner = self.determine_winner()
        if winner is None:
            return
        elif winner:
            print("Congratulations! You win!")
        else:
            print("Sorry, you lose.")

        
def main():
    print("Welcome to Blackjack!")
    while True:
        game = Blackjack()
        game.play_game()
        play_again = input("Do you want to play again? (y/n) ")
        if play_again.lower() != "y":
            break
    print("Thanks for playing!")

if __name__ == "__main__":
    main()