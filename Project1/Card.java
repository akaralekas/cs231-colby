/*
 * File: Card.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 19, 2015
 * Assignment: Project 1
 */
  
/*
 * Models a card with an arbitrary card face value
 */
public class Card{
	// the current card number value
	private int cardValue;
	
	/*
	 * Creates a card object with a random value 
	 */
	public Card(){
		// gives card random value between 1-10
		this.cardValue = (int) ((Math.random()*10)+1);
	}

	/*
	 * creates a card with a given number value
	 */
	public Card( int v ){
		// gives card value of int v parameter
		this.cardValue = v;
	}
	
	// fetches the value of the Card and returns the value
	public int getValue(){
		return this.cardValue;

	}

	/*
	 * Main function that creates a new card and prints the value
	 */
	public static void main(String[] args){
		Card card = new Card();
		System.out.println(card.getValue());

	}


}