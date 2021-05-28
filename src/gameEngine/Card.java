package gameEngine;

import javafx.scene.image.Image;

public class Card {
	int rank;
	int suit;
	Image cardImage;
	final static String[] cardSuits = { "club", "diamond", "heart", "spade" };
	final static String imgUrl = "/images/";

	public Card(int card) {
		setRank(card / 10);
		setSuit(card % 10);
		String url = (imgUrl + cardSuits[suit] + "_" + Integer.toString(rank) + ".png");
		setCardImage(new Image(url));
	}

	public Image getCardImage() {
		return cardImage;
	}

	public void setCardImage(Image cardImage) {
		this.cardImage = cardImage;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

}
