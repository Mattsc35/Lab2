// Cisc 181 - 012
// Matthews Curtinhas
// Jesse Rinaldi
// Chris Cornwell
// Sam Paleen
// Ben Gause 

package poker;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Arrays;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand(){
		cards = new ArrayList<Card>();
	}
	
	public int getHandSize(){
		return cards.size();
	}
	
	public Card getCard(int i){
		if (i < 0 || i >= cards.size() || cards.size() == 0)
			return null;
		
		
		return cards.get(i);
	}

	public void addCard(Card c)
	{
		 cards.add(c);
	}
	
	public void removeCard(int index)
	{
		if (index < 0 || index >= cards.size() || cards.size() == 0)
			return;
		
	
		cards.remove(index);		
	}
	
	public void clearHand()
	{
		cards = new ArrayList<Card>();
	}

	public static PokerHandRank judge(Hand h)
	{
		h.sort();
		
		if(isRoyalFlush(h))
			return PokerHandRank.ROYALFLUSH;
		if(isStraightFlush(h))
			return PokerHandRank.STRAIGHTFLUSH;
		if(isFourOfAKind(h)) 
			return PokerHandRank.FOUROFAKIND;
		if(isFullHouse(h)) 
			return PokerHandRank.FULLHOUSE;
		if(isFlush(h)) 
			return PokerHandRank.FLUSH;
		if(isStraight(h)) 
			return PokerHandRank.STRAIGHT;
		if(isThreeOfAKind(h))
			return PokerHandRank.THREEOFAKIND;
		if(isTwoPair(h))
			return PokerHandRank.TWOPAIR;
		if(isOnePair(h))
			return PokerHandRank.ONEPAIR;
		
		return PokerHandRank.HIGHCARD;
	}
	
	public static String judgeAsString(Hand h){
		PokerHandRank handRank = Hand.judge(h);
		
		if(handRank.equals(PokerHandRank.ROYALFLUSH))
			return "Royal Flush";
		if(handRank.equals(PokerHandRank.STRAIGHTFLUSH))
			return "Straight Flush";
		if(handRank.equals(PokerHandRank.FOUROFAKIND))
			return "Four of a Kind";
		if(handRank.equals(PokerHandRank.FULLHOUSE))
			return "Full House";
		if(handRank.equals(PokerHandRank.FLUSH))
			return "Flush";
		if(handRank.equals(PokerHandRank.STRAIGHT))
			return "Straight";
		if(handRank.equals(PokerHandRank.THREEOFAKIND))
			return "Three of a Kind";
		if(handRank.equals(PokerHandRank.TWOPAIR))
			return "Two Pair";
		if(handRank.equals(PokerHandRank.ONEPAIR))
			return "One Pair";
	
		return "High Card";
	}

	public static Hand judge(Hand[] handArray){
		//Returns the highest hand in the array, in case of a tie the latter card is the winner
		Hand highHand = handArray[0];
		
		for(int i = 1; i < handArray.length; i++){
				highHand = compareTwoHands(highHand, handArray[i]);
				if(highHand == null)
					highHand = handArray[i];
		}
				
		return highHand;	
	}
	
	private static Hand compareTwoHands(Hand hand1, Hand hand2){
		if (Hand.judge(hand1).ordinal() < Hand.judge(hand2).ordinal())
			return hand1;
		if (Hand.judge(hand2).ordinal() < Hand.judge(hand1).ordinal())
			return hand2;
		
		if(Hand.judge(hand1).equals(PokerHandRank.ROYALFLUSH))
			return royalFlushTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.STRAIGHTFLUSH))
			return straightFlushTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.FOUROFAKIND))
			return fourofAKindTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.FULLHOUSE))
			return fullHouseTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.FLUSH))
			return flushTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.STRAIGHT))
			return straightTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.THREEOFAKIND))
			return threeOfAKindtieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.TWOPAIR))
			return twoPairTieBreaker(hand1, hand2);
		if(Hand.judge(hand1).equals(PokerHandRank.ONEPAIR))
			return onePairTieBreaker(hand1, hand2);
		
		return highCardTieBreak(hand1, hand2);
	}
	
	private static boolean isRoyalFlush(Hand h){
		return (Hand.isStraightFlush(h) && Hand.isFlush(h) && 
				h.getCard(0).getRank().equals(Rank.TEN) &&  h.getCard(4).getRank().equals(Rank.ACE));
	}
	private static boolean isStraightFlush(Hand h){
		return (Hand.isStraight(h) && Hand.isFlush(h));
	}
	private static boolean isFourOfAKind(Hand h){
		
		for(int i =0; i < 2; i++)
			if(h.getCard(i).getRank().equals(h.getCard(i+1).getRank()) && 
					h.getCard(i).getRank().equals(h.getCard(i+2).getRank()) &&
					h.getCard(i).getRank().equals(h.getCard(i+3).getRank()))
				return true;
		
		
		return false;
	}
	private static boolean isFullHouse(Hand h){
		if((h.getCard(0).getRank().equals(h.getCard(1).getRank()) && h.getCard(0).getRank().equals(h.getCard(2).getRank())) &&
				h.getCard(3).getRank().equals(h.getCard(4).getRank()) && !h.getCard(0).getRank().equals(h.getCard(4).getRank()))
			return true;
		if((h.getCard(4).getRank().equals(h.getCard(3).getRank()) && h.getCard(4).getRank().equals(h.getCard(2).getRank())) &&
				h.getCard(0).getRank().equals(h.getCard(1).getRank()) && !h.getCard(0).getRank().equals(h.getCard(4).getRank()))
			return true;
		
		return false;
	}
	private static boolean isFlush(Hand h){
		Suit firstCardSuit = h.getCard(0).getSuit();
		
		for(int i = 1; i < h.getHandSize(); i++)
			if (!firstCardSuit.equals(h.getCard(i).getSuit()))
				return false;
			
		return true;
	}
	private static boolean isStraight(Hand h){
		//Check for low ace straight
		if(h.getCard(0).getRank().equals(Rank.TWO) && 
				h.getCard(1).getRank().equals(Rank.THREE) && 
				h.getCard(2).getRank().equals(Rank.FOUR) &&
				h.getCard(3).getRank().equals(Rank.FIVE) && 
				h.getCard(4).getRank().equals(Rank.ACE))
			return true;
	
		int nextNeededRank = h.getCard(0).getRank().getValue() + 1;
		for(int i = 1; i < h.getHandSize(); i++){
			if (nextNeededRank == h.getCard(i).getRank().getValue())
				nextNeededRank++;
			else
				return false;
		}
		
		return true;
	}
	private static boolean isThreeOfAKind(Hand h){
		for(int i =0; i < 3; i++)
			if(h.getCard(i).getRank().equals(h.getCard(i+1).getRank()) && 
					h.getCard(i).getRank().equals(h.getCard(i+2).getRank()))
				return true;
		
		return false;
	}
	private static boolean isTwoPair(Hand h){
		boolean onePairFound = false;
		Rank foundPairRank = null;
		
		for(int i = 0; i < h.getHandSize() - 1; i++)
			if(h.getCard(i).getRank().equals(h.getCard(i+1).getRank()))
				if(!onePairFound){
					onePairFound = true;
					foundPairRank = h.getCard(i).getRank();
				}
				else if(!foundPairRank.equals(h.getCard(i).getRank()))
					return true;
		
		return false;
	}
	private static boolean isOnePair(Hand h){
		for(int i = 0; i < 4; i++)
			if(h.getCard(i).getRank().equals(h.getCard(i+1).getRank()))
				return true;
		
		return false;
	}

	//Tie breakers in the event two cards are judged the same, all functions return null in case of true tie.
	private static Hand royalFlushTieBreaker(Hand hand1, Hand hand2){
		return null;
	}
	private static Hand straightFlushTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = hand1.getCard(hand1.getHandSize()-1).getRank().getValue();
		int hand2HighestRank = hand2.getCard(hand2.getHandSize()-1).getRank().getValue();
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		else
			return null; 
	}
	private static Hand fourofAKindTieBreaker(Hand hand1, Hand hand2){

		int hand1HighestRank = hand1.getCard(2).getRank().getValue();
		int hand2HighestRank = hand2.getCard(2).getRank().getValue();
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		else
			return null; 
	}
	private static Hand fullHouseTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = hand1.getCard(2).getRank().getValue();
		int hand2HighestRank = hand2.getCard(2).getRank().getValue();
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		else
			return null; 
	}
	private static Hand flushTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank, hand2HighestRank;
		
		for(int i = hand1.getHandSize()-1; i >= 0; i--){
			hand1HighestRank = hand1.getCard(i).getRank().getValue();
			hand2HighestRank = hand2.getCard(i).getRank().getValue();
			if(hand1HighestRank > hand2HighestRank)
				return hand1;
			else if(hand2HighestRank > hand1HighestRank)
				return hand2;
		}
		
		return null; 
	}
	private static Hand straightTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = hand1.getCard(hand1.getHandSize()-1).getRank().getValue();
		int hand2HighestRank = hand2.getCard(hand2.getHandSize()-1).getRank().getValue();
		
		if(hand1HighestRank == Rank.ACE.getValue() || hand2HighestRank == Rank.ACE.getValue()){
				hand1HighestRank = hand1.getCard(0).getRank().getValue();
				hand2HighestRank = hand2.getCard(0).getRank().getValue();
		}		
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		else
			return null; 
	}
	private static Hand threeOfAKindtieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = hand1.getCard(2).getRank().getValue();
		int hand2HighestRank = hand2.getCard(2).getRank().getValue();
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		else
			return null; 
	}
	private static Hand twoPairTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = 9999; 
		int hand2HighestRank = 9999; 
		
		//Check high pairs
		for(int i = hand1.getHandSize()-1; i > 0; i--)
			if(hand1.getCard(i).getRank().getValue() == hand1.getCard(i-1).getRank().getValue()){
				hand1HighestRank = hand1.getCard(i).getRank().getValue();
				break;
			}
		for(int i = hand2.getHandSize()-1; i > 0; i--)
			if(hand2.getCard(i).getRank().getValue() == hand2.getCard(i-1).getRank().getValue()){
				hand2HighestRank = hand2.getCard(i).getRank().getValue();
				break;
			}
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		
		int highPairRank = hand1HighestRank;
		
		//Check low pairs
		for(int i = hand1.getHandSize()-1; i > 0; i--)
			if(hand1.getCard(i).getRank().getValue() == hand1.getCard(i-1).getRank().getValue() &&  
			hand1HighestRank != hand1.getCard(i).getRank().getValue()){
				hand1HighestRank = hand1.getCard(i).getRank().getValue();
				break;
			}
		for(int i = hand2.getHandSize()-1; i > 0; i--)
			if(hand2.getCard(i).getRank().getValue() == hand2.getCard(i-1).getRank().getValue() &&  
			hand2HighestRank != hand2.getCard(i).getRank().getValue()){
				hand2HighestRank = hand2.getCard(i).getRank().getValue();
				break;
			}
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		
		int lowPairRank = hand1HighestRank;
		
		//Check side card
		for(int i = hand1.getHandSize()-1; i > 0; i--)
			if(hand1.getCard(i).getRank().getValue() != highPairRank &&  
			hand1.getCard(i).getRank().getValue() != lowPairRank){
				hand1HighestRank = hand1.getCard(i).getRank().getValue();
				break;
			}
		for(int i = hand2.getHandSize()-1; i > 0; i--)
			if(hand2.getCard(i).getRank().getValue() != highPairRank &&  
			hand2.getCard(i).getRank().getValue() != lowPairRank){
				hand2HighestRank = hand2.getCard(i).getRank().getValue();
				break;
			}
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		
		return null;
	}
	private static Hand onePairTieBreaker(Hand hand1, Hand hand2){
		int hand1HighestRank = 9999; 
		int hand2HighestRank = 9999; 
		
		//Check pair
		for(int i = hand1.getHandSize()-1; i > 0; i--)
			if(hand1.getCard(i).getRank().getValue() == hand1.getCard(i-1).getRank().getValue()){
				hand1HighestRank = hand1.getCard(i).getRank().getValue();
				break;
			}
		for(int i = hand2.getHandSize()-1; i > 0; i--)
			if(hand2.getCard(i).getRank().getValue() == hand2.getCard(i-1).getRank().getValue()){
				hand2HighestRank = hand2.getCard(i).getRank().getValue();
				break;
			}
		
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		
		int pairRank = hand1HighestRank;
		
		//Check side cards;
		int hand1HighSideCardIndex = 4;
		int hand2HighSideCardIndex = 4;
		while(hand1HighSideCardIndex != 0 && hand2HighSideCardIndex != 0){
			for(int i = hand1HighSideCardIndex; i >= 0; i--)
				if(hand1.getCard(i).getRank().getValue() != pairRank){
					hand1HighestRank = hand1.getCard(i).getRank().getValue();
					hand1HighSideCardIndex = i;
					break;
				}
			for(int i = hand2HighSideCardIndex; i >= 0; i--)
				if(hand2.getCard(i).getRank().getValue() != pairRank){
					hand2HighestRank = hand2.getCard(i).getRank().getValue();
					hand2HighSideCardIndex = i;
					break;
				}

			if(hand1HighestRank > hand2HighestRank)
				return hand1;
			else if(hand2HighestRank > hand1HighestRank)
				return hand2;

		}
		if(hand1HighestRank > hand2HighestRank)
			return hand1;
		else if(hand2HighestRank > hand1HighestRank)
			return hand2;
		return null; 
	}
	private static Hand highCardTieBreak(Hand hand1, Hand hand2){
		int hand1HighestRank, hand2HighestRank;
		
		for(int i = hand1.getHandSize()-1; i >= 0; i--){
			hand1HighestRank = hand1.getCard(i).getRank().getValue();
			hand2HighestRank = hand2.getCard(i).getRank().getValue();
			if(hand1HighestRank > hand2HighestRank)
				return hand1;
			else if(hand2HighestRank > hand1HighestRank)
				return hand2;
		}
		
		return null; 
	}
	
	private void sort(){
		Collections.sort(cards);
	}

	public String toString() {
		String returnString = "";
		for(int i = 0; i < cards.size(); i++){
			returnString += cards.get(i).toString() + "\n";
		}
		return returnString;
	}
}
