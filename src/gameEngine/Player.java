package gameEngine;

public class Player {
	GameManager game;
	Card[] deck = new Card[6]; // maximum of 6 cards on deck
	int amountOfCards = 0;
	boolean hasAce = false;
	int totalValue = 0;

	public Player(GameManager game) {
		setGame(game);
	}

	public void hit() {
		deck[amountOfCards] = game.drawCard();
		amountOfCards++;
		calculateValue();
	}

	public void calculateValue() { // returns total value of cards on deck
		int totalValue = 0;
		for (int i = 0; i < amountOfCards; i++) {
			int tmp = deck[i].getRank();
			if (tmp > 10)
				tmp = 10;
			if (tmp == 1) {
				hasAce = true;
				tmp += 10;
			}

			totalValue += tmp;
		}
		if (hasAce && totalValue > 21)
			totalValue -= 10;

		this.totalValue = totalValue;
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	public Card[] getDeck() {
		return deck;
	}

	public void setDeck(Card[] deck) {
		this.deck = deck;
	}

	public int getAmountOfCards() {
		return amountOfCards;
	}

	public void setAmountOfCards(int amountOfCards) {
		this.amountOfCards = amountOfCards;
	}

	public boolean isHasAce() {
		return hasAce;
	}

	public void setHasAce(boolean hasAce) {
		this.hasAce = hasAce;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

}
