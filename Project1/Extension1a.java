/*
 * File: Extension1.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 20, 2015
 * Assignment: Project 1
 * Contents: Extension number 5. Standard deviation of simulations
 */
 
//imports ArrayList method
import java.util.ArrayList;
 
/*
 * Models a standard dice test with an arbitrary number of sides
 */
public class Extension1a{

	public static void main( String[] args ){
		Blackjack b1 = new Blackjack();
		// create multiple integers
		int score;
		int pushes = 0;
		int totalPushes = 0;
		int dealerWins = 0;
		int totalDealer = 0;
		int playerWins = 0;
		int totalPlayer = 0;
		for( int i = 0; i<10; i++ ){
			for( int j =0; j <100; j++){
				score = b1.Game();
				if(score == 0){
					pushes += 1;
				}
				else if(score == 1){
					playerWins += 1;
				}
				else if(score == -1){
					dealerWins += 1;
				}
			}
			// totals all the averages
			totalPushes += pushes;
			totalDealer += dealerWins;
			totalPlayer += playerWins;
				
		}
		//print out average totals
		System.out.println("Total number of Pushes =" + " " + totalPushes);
		System.out.println("Total number of Player Wins =" + " " + totalDealer);
		System.out.println("Total number of Dealer Wins =" + " " + totalPlayer);
		
		//print out average of averages
		System.out.println("Average # of Pushes =" + " " + (totalPushes/10.0));
		System.out.println("Average # of Player Wins =" + " " + (totalPlayer/10.0));
		System.out.println("Average # of Dealer Wins =" + " " + (totalDealer/10.0));	
		
	}
	
}
	
	