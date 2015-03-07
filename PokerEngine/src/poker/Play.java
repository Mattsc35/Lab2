// Cisc 181 - 012
// Matthews Curtinhas
// Jesse Rinaldi
// Chris Cornwell
// Sam Paleen
// Ben Gause 

package poker;
import java.util.Scanner;

public class Play {
	public static void playPoker(){
		final int MAXPLAYERS = 5;
		final int MINPLAYERS = 2;
		int numPlayers = MAXPLAYERS + 1;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of players: ");
		numPlayers = scanner.nextInt();
		
		while(numPlayers > MAXPLAYERS || numPlayers < MINPLAYERS){
			if(numPlayers > MAXPLAYERS)
				System.out.println("Too many players, max players allowed is: " + MAXPLAYERS);
			else if(numPlayers < MINPLAYERS)
				System.out.println("Too few players, minimum players allowed is: " + MINPLAYERS);
				
			numPlayers = scanner.nextInt();
		}
		
		String userInput;
		Deck pokerDeck;
		Hand[] playerHands = new Hand[numPlayers];
		
		while(true){
			pokerDeck = new Deck();
			
			for(int i = 0; i < numPlayers; i++){
				playerHands[i] = new Hand();
				for(int j = 0; j < 5; j++){
					playerHands[i].addCard(pokerDeck.draw());
				}
			}
			
			for(int i = 0; i < numPlayers; i++)
				System.out.println("Player " + i + "'s hand is:\n" + playerHands[i].toString());
			
			for(int i = 0; i < numPlayers; i++)
				System.out.println("Player " + i + " has a " + Hand.judgeAsString(playerHands[i]));
			
			Hand winningHand = Hand.judge(playerHands);
			
			for(int i = 0; i < numPlayers; i++)
				if(playerHands[i].equals(winningHand)){
					System.out.println("\n\nThe winner is player " + i + " with a " + Hand.judgeAsString(playerHands[i]));
					break;
				}
			
			System.out.println("Would you like to quit? (Y/N)");

			userInput = scanner.next();
			
			if (userInput.toLowerCase().equals("yes") || userInput.toLowerCase().equals("y"))
				break;
		}
		
		System.out.println("Thank you for playing!");
		scanner.close();
	}

	public static void main(String[] args) {
		playPoker();
	}
}
