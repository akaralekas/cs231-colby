/*
 * File: Simulation.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 20, 2015
 * Assignment: Project 1
 */
 
//imports ArrayList method
import java.util.ArrayList;
 
/*
 * Models a standard dice test with an arbitrary number of sides
 */
public class Simulation{
	
	//main function
	public static void main( String[] args ){
		Blackjack b1 = new Blackjack();
		// create multiple integers
		int score;
		int pushes = 0;
		int dealerWins = 0;
		int playerWins = 0;
		//run 1000 simulations
		for( int i = 0; i<1000; i++ ){
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
		//print out the totals
		System.out.println("Total number of Pushes =" + " " + pushes);
		System.out.println("Total number of Player Wins =" + " " + playerWins);
		System.out.println("Total number of Dealer Wins =" + " " + dealerWins);
		
		//print out the percentages
		System.out.println("Percentage of Pushes =" + " " + (pushes/1000.0));
		System.out.println("Percentage of Player Wins =" + " " + (playerWins/1000.0));
		System.out.println("Percentage of Dealer Wins =" + " " + (dealerWins/1000.0));
		
		
	}
	
}
	
	