/*
 * File: Blackjack.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 20, 2015
 * Assignment: Project 1
 */
 
//imports ArrayList method
import java.util.ArrayList;
 
/*
 * Blackjack class with Deck, playerHand, and dealerHand objects
 */
public class Blackjack{
	private Deck deck;
	private Hand playerHand;
	private Hand dealerHand;
	private int scoreBoard;
	
	// Blackjack class that resets and deals cards
	public Blackjack(){
		this.reset(true);	
		this.deal();
	}
	
	// reset the deck of cards, hands, and shuffles
	public void reset( boolean newDeck ){
		if( newDeck = true){
			deck = new Deck();
			playerHand = new Hand();
			dealerHand = new Hand();
			this.deck.build();
			this.deck.shuffle();
		}
	}
	//deals two cards to player and then two cards to dealer
	public void deal(){
		for( int i = 0; i <2; i++){
			playerHand.add(deck.deal());
			dealerHand.add(deck.deal());
		}	
	}
	
	// reports on the status of the game with contents of each players hand in string form
	public String toString(){
		String playerStatus = "Player" + " " + playerHand.toString() + ": "
			+ this.playerHand.getTotalValue(); ;
		String dealerStatus = "Dealer" + " " + dealerHand.toString() + ": "
			+ this.dealerHand.getTotalValue();
		return playerStatus + "\n" + dealerStatus;
	}
	
	// this is the players turn. the player will draw if value of hand <16
	// we use booleans here for when the value in >21 and if value>16 but<21
	public boolean playerTurn(){
		while(this.playerHand.getTotalValue() <16){
			//System.out.println("Player wants to hit");
			this.playerHand.add(deck.deal());
		}
		if( this.playerHand.getTotalValue() > 21 ){
			return false;
		}
		else{
			return true;
		}
	}
	// this is the dealers turn. the dealer will draw if value of hand <17
	// we use booleans here for when the value in >21 and if value>17 but<21
	public boolean dealerTurn(){
		while(this.dealerHand.getTotalValue() <17){
			//System.out.println("Dealer wants to hit");
			this.dealerHand.add(deck.deal());
		}
		if( this.dealerHand.getTotalValue() > 21 ){
			return false;
		}
		else{
			return true;
		}	
	}
	
	// this gets the score of a single simulation of Blackjack
	public int getScore(){
		this.scoreBoard = 0;
		if ( this.dealerHand.getTotalValue() == this.playerHand.getTotalValue()){
			this.scoreBoard += 0;
		}
		else if(( this.dealerHand.getTotalValue() > this.playerHand.getTotalValue()) 
			&& (this.dealerHand.getTotalValue()<=21)){
			this.scoreBoard -= 1;
		}	
		else if(( this.playerHand.getTotalValue() > this.dealerHand.getTotalValue()) 
			&& (this.playerHand.getTotalValue()<=21)){
			this.scoreBoard += 1;
		}	
		
		else if( this.playerHand.getTotalValue() > 21){
			this.scoreBoard -= 1;
		}
		
		else if( this.dealerHand.getTotalValue() > 21){
			this.scoreBoard += 1;
		}
		
		return this.scoreBoard;
	}
	//used for the simulation class
	//runs the game and compiles the score
	public int Game(){
		this.reset(true);
		this.deal();
		this.playerTurn();
		this.dealerTurn();
		int result = 0;
		if ( this.dealerHand.getTotalValue() == this.playerHand.getTotalValue()){
			result += 0;
		}
		else if(( this.dealerHand.getTotalValue() > this.playerHand.getTotalValue()) 
			&& (this.dealerHand.getTotalValue()<=21)){
			result -= 1;
		}	
		else if(( this.playerHand.getTotalValue() > this.dealerHand.getTotalValue()) 
			&& (this.playerHand.getTotalValue()<=21)){
			result += 1;
		}	
		
		else if( this.playerHand.getTotalValue() > 21){
			result -= 1;
		}
		
		else if( this.dealerHand.getTotalValue() > 21){
			result += 1;
		}
		
		return result;
	}
	//main function
	public static void main( String[] args ){
		// creates game of Blackjack
		Blackjack b = new Blackjack();
		System.out.println("Player is dealt" + ": " + b.playerHand.getTotalValue());
		System.out.println("Dealer is dealt" + ": " + b.dealerHand.getTotalValue());
		System.out.println(b.toString());
		b.playerTurn();
		// if player doesn't bust
		if( b.playerTurn() == true ){
			System.out.println("Player will stay with" + ": "
				+ b.playerHand.getTotalValue());
				
			b.dealerTurn();
			
			// if dealer doesnt bust
			if( b.dealerTurn() == true){
				System.out.println("Dealer will stay with" + ": "
					+ b.dealerHand.getTotalValue());
				
				// if the values are equal	
				if ( b.dealerHand.getTotalValue() == b.playerHand.getTotalValue()){
					System.out.println("It is a PUSH!");
					System.out.println(b.toString());
				}
				
				//if dealer is greater than player
				else if ( b.dealerHand.getTotalValue() > b.playerHand.getTotalValue()){
					System.out.println("Game is over! Dealer wins with hand of" + ": "
						+b.dealerHand.getTotalValue());
					System.out.println(b.toString());
				}
				
				// if player is greater than dealer
				else{
					System.out.println("Game is over! Player wins with hand of" + ": "
						+b.playerHand.getTotalValue());
					System.out.println(b.toString());
				}
			}
			// if the dealer busts
			if( b.dealerTurn() == false){
				System.out.println("Game is over! Dealer has busted and player wins!");
				System.out.println(b.toString());
			}
			
		// if the player busts	
		}
		if( b.playerTurn() == false){
			System.out.println("Game is over! Player has busted and dealer wins!");
			System.out.println(b.toString());
		}
		System.out.println("The scoreboard now reads" + ": " + b.getScore());
		
		// this makes sure that the deck shuffles when it gets to 20 cards left
		if( b.deck.size() < 20){
			b.deck.shuffle();
		}
		
	}
}