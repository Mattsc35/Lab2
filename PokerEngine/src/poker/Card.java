// Cisc 181 - 012
// Matthews Curtinhas
// Jesse Rinaldi
// Chris Cornwell
// Sam Paleen
// Ben Gause 

package poker;

public class Card implements Comparable<Card> {
	private Suit suit;
	private Rank rank;

	private Card(){
		this.rank = null;
		this.suit = null;
	}
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public int compareTo(Card c) {
		return this.getRank().compareTo(c.getRank()); 
	}
	
	@Override
	public String toString() {
		String returnString = "";
		
		if (rank.equals(Rank.ACE))
			returnString += "Ace";
		else if (rank.equals(Rank.TWO))
			returnString += "Two";
		else if (rank.equals(Rank.THREE))
			returnString += "Three";
		else if (rank.equals(Rank.FOUR))
			returnString += "Four";
		else if (rank.equals(Rank.FIVE))
			returnString += "Five";
		else if (rank.equals(Rank.SIX))
			returnString += "Six";
		else if (rank.equals(Rank.SEVEN))
			returnString += "Seven";
		else if (rank.equals(Rank.EIGHT))
			returnString += "Eight";
		else if (rank.equals(Rank.NINE))
			returnString += "Nine";
		else if (rank.equals(Rank.TEN))
			returnString += "Ten";
		else if (rank.equals(Rank.JACK))
			returnString += "Jack";
		else if (rank.equals(Rank.QUEEN))
			returnString += "Queen";
		else if (rank.equals(Rank.KING))
			returnString += "King";
		
		if(suit.equals(Suit.SPADES))
			returnString += " Of Spades";
		else if(suit.equals(Suit.CLUBS))
			returnString += " Of Clubs";
		else if(suit.equals(Suit.DIAMONDS))
			returnString += " Of Diamonds";
		else if(suit.equals(Suit.HEARTS))
			returnString += " Of Hearts";
		
		return returnString;
	}
}

