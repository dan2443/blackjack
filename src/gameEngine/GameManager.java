package gameEngine;

import java.util.Random;

public class GameManager {
	// first cell represents the amount of the cards in the pack
	// if there is 2 digits, first digit represents the card, second digit
	// represents the type of the card
	// if there is 3 digits, first 2 digits represent the card, third digit
	// represents the type of the card
	int[] packOfCards = { 52, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33, 40, 41, 42, 43, 50, 51, 52, 53, 60, 61,
			62, 63, 70, 71, 72, 73, 80, 81, 82, 83, 90, 91, 92, 93, 100, 101, 102, 103, 110, 111, 112, 113, 120, 121,
			122, 123, 130, 131, 132, 133 };

	Player player;
	Player dealer;

	public void init() { // initializing the board
		player = new Player(this);
		dealer = new Player(this);
		shuffleDeck();
	}

	public void shuffleDeck() {
		Random rnd = new Random();
		for (int j = 0; j < 3; j++) { // doing the shuffle 3 times
			// swapping random index with i
			for (int i = 1; i < packOfCards.length - 1; i++) {
				int index = rnd.nextInt(packOfCards.length - 1) + 1;
				int tmp = packOfCards[index];
				packOfCards[index] = packOfCards[i];
				packOfCards[i] = tmp;
			}
		}
	}

	public Card drawCard() {
		int cardInt = packOfCards[packOfCards[0]];
		packOfCards[0]--;
		Card card = new Card(cardInt);
		return card;
	}

	// true if player has bigger value then dealer
	public boolean isWon(Player player1, Player player2) {
		return player1.getTotalValue() > player2.getTotalValue();
	}

	// true if player has a value bigger then 21;
	public boolean isLost(Player player1) {
		return player1.getTotalValue() > 21;
	}

	public Player getPlayer() {
		return player;
	}

	public Player getDealer() {
		return dealer;
	}

}
