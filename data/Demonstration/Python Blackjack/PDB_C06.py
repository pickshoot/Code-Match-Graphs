import random

class Blackjack:
    def __init__(self):
        self.deck = self.createDeck()
        self.playerHand = []
        self.dealerHand = []
        self.playerScore = 0
        self.dealerScore = 0

    def createDeck(self):
        ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
        suits = ['Spades', 'Clubs', 'Diamonds', 'Hearts']
        deck = []
        for suit in suits:
            for rank in ranks:
                deck.append(rank + ' of ' + suit)
        random.shuffle(deck)
        return deck

    def dealCards(self):
        self.playerHand.append(self.deck.pop())
        self.dealerHand.append(self.deck.pop())
        self.playerHand.append(self.deck.pop())
        self.dealerHand.append(self.deck.pop())

    def calculateScore(self, hand):
        score = 0
        numAces = 0
        for card in hand:
            rank = card.split()[0]
            if rank == 'Ace':
                numAces += 1
                score += 11
            elif rank in ['Jack', 'Queen', 'King']:
                score += 10
            else:
                score += int(rank)
        while score > 21 and numAces > 0:
            score -= 10
            numAces -= 1
        return score

    def printHands(self, showDealerHand):
        print('Player hand:', self.playerHand, '(', self.playerScore, ')')
        if showDealerHand:
            print('Dealer hand:', self.dealerHand, '(', self.dealerScore, ')')
        else:
            print('Dealer hand:', [self.dealerHand[0], 'Face down'])

    def playerTurn(self):
        while True:
            self.printHands(True)
            action = input('Do you want to hit or stand? ')
            if action.lower() == 'hit':
                self.playerHand.append(self.deck.pop())
                self.playerScore = self.calculateScore(self.playerHand)
                if self.playerScore > 21:
                    print('Player busts!')
                    return False
            elif action.lower() == 'stand':
                return True
            else:
                print('Invalid action. Please try again.')

    def dealerTurn(self):
        while self.dealerScore < 17:
            self.dealerHand.append(self.deck.pop())
            self.dealerScore = self.calculateScore(self.dealerHand)
        if self.dealerScore > 21:
            print('Dealer busts!')
            return False
        else:
            return True

    def play(self):
        self.deck = self.createDeck()
        self.playerHand = []
        self.dealerHand = []
        self.playerScore = 0
        self.dealerScore = 0
        self.dealCards()
        self.playerScore = self.calculateScore(self.playerHand)
        self.dealerScore = self.calculateScore(self.dealerHand)
        self.printHands(False)
        if self.playerScore == 21:
            print('Player has blackjack!')
            return
        while True:
            if not self.playerTurn():
                return
            if not self.dealerTurn():
                return
            self.printHands(True)
            if self.dealerScore >= self.playerScore:
                print('Dealer wins!')
                return
            else:
                print('Player wins!')
                return

game = Blackjack()
game.play()
