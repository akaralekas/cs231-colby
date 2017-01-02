/*
 * File: Hand.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 19, 2015
 * Assignment: Project 1
 */
 
//imports ArrayList method
import java.util.ArrayList;
 
/*
 * Models an hand of cards represented within an ArrayList
 */
public class Hand{
	// creates an ArrayList of <Card> objects called hand
	ArrayList<Card> hand;


	/*
	 * Creates a new hand ArrayList
	 */
	public Hand(){
		this.hand = new ArrayList<Card>();
	}
	
	// clears the contents of the hand ArrayList
	public void reset(){
		this.hand.clear();
	}
	
	// adds a card to the hand ArrayList
	public void add( Card card ){
		this.hand.add(card);
	}
	
	// returns the size of the hand ArrayList. How many cards in the hand
	public int size(){
		return this.hand.size();
	}
	
	// returns a the value of a specific card in the hand ArrayList
	public Card getCard( int i ){
		return this.hand.get(i);
	}
	
	// totals all the of the card values in the hand and returns the sum
	public int getTotalValue(){
		int sum = 0;
		for(int i=0; i < this.hand.size(); i++){
			sum += this.hand.get(i).getValue();
		}
		
		return sum;
	
	}
	
	/*
	 * this returns a string that lists all of the cards with the hand ArrayList
	 */
	public String toString(){
		String handVal = "Hand contents: ";
		// for loop to add the contents to the handVal string separated with commas
		for(int i=0; i < this.hand.size() -1; i++){
			handVal += this.hand.get(i).getValue() + ", ";
		}
		// adds the last one to the String
		handVal += this.hand.get(this.hand.size() -1).getValue();
		return handVal;
	}
	
	// main function that adds ten random cards to a new hand object and prints the contents
	public static void main( String[] args){
		Hand myhand =  new Hand();
		for( int i = 0; i < 10; i++){
			myhand.add(new Card());
		}
		//prints the contents
		System.out.println(myhand.toString());
	
	}





}