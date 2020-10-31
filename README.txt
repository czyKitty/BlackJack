=== Assignment 2 ===
Contributor: Zhuyun Chen
Email: zchen98@bu.edu
BU ID: U38119774

========================
=== Overview ===
========================

This submission implements the Black Jack game.

To compile, run command: javac *.java
To start the game, run command: java BlackJack
To play the game, follow instructions on screen.

Game rule:
As gambler, you start by enter your name and total amount of chips you have.
You will get 2 cards at beginning, place a bet, and take one of this action:
1.Hit:draw another card from deck
2.Stand:stop draw cards and wait for dealer to finish round
3.Split(only appear when you have two cards of same rank and enough chips):split cards to two hands, place the same amount of chip for new hand, and draw one card for each hand.
4.Double up: double the bet, hit, and stand immediately.
The game end when you don't have any remaining chip after a round.
You may choose to stop play at end of a round.

As human dealer, you start by enter your name and dear cards (though you can only choose to the beginning sequence). There's not much thing you can do, so just follow instructions.

== Player ==
Class represents single player, has general properties such as name and hands (represent the hands a player have), but can be extended for more specific setting.

== Gambler ==
Class represents gamblers in Black Jack game, who has bunch of chips and can take action during the game based on rules.

== Dealer ==
Class represents dealer in Black Jack game, who deal cards and can take action during the game based on rules.

== Hand ==
Class represents a hand of cards, follow the rule of black jack (must have a bet place on it, has a total value, can no longer be played after stand or burst, etc.)

== Deck ==
Class represents the card deck, has general properties that all decks have (can be shuffled, cleaned up, etc.) and can be extends to different type of decks.

== BJDeck ==
Subclass of Deck which represents deck used in Black Jack (contains 52 poker card without jokers).

== Card ==
Class represents a poker card, which has a suit and a rank.

== Game ==
Class represents a game with abstract methods, can be extend to different types of games.

== BJGame ==
Class represents a Black Jack game.

== BlackJack ==
Main class to play the black jack game, specified for for the game.
