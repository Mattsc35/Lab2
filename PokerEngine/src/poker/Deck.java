// Cisc 181 - 012
// Matthews Curtinhas
// Jesse Rinaldi
// Chris Cornwell
// Sam Paleen
// Ben Gause 

package poker;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck(){
		cards = new ArrayList<Card>();
		
		for(int i =  0; i < 4; i++){
			Suit suit = Suit.values()[i];
			for(int j = 0; j < 13; j++ ){
				Rank rank = Rank.values()[j];
				Card c = new Card(suit, rank);
				cards.add(c);
			}
		}
		shuffle();
	}

	private void shuffle(){
		Collections.shuffle(cards);
	}
	
	public Card draw(){
		return cards.remove(cards.size() - 1);
	}
	
	public int cardsLeft(){
		return cards.size();
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0; i < cards.size(); i++){
			returnString += cards.get(i).toString() + "\n";
		}
		return returnString;	
	}
}
