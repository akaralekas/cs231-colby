/*
 * File: Deck.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 19, 2015
 * Assignment: Project 1
 */
 
//imports ArrayList method and Random method
import java.util.ArrayList;
import java.util.Random;
 
/*
 * Models a standard 52-card deck of cards
 */
public class Deck{
	
	ArrayList<Card> deck;
	
	/*
 	 * creates deck object
 	 */
	public Deck(){
		this.deck = new ArrayList<Card>();
		this.build();
	}
	// build a deck with 52 cards
	public void build(){
		this.deck.clear();
		// 4 sets of card with values 1-9
		for( int i = 0; i < 4; i++){
			for( int j = 1; j <= 9; j++){
				this.deck.add(new Card(j));
			}
			// 16 cards with value 10
			for(int k = 0; k < 4; k++){
				this.deck.add(new Card(10));
			}
		}
	}
	
	// returns card dealt
	public Card deal(){
		// received assistance from CP
		// setting a pointer to the card dealt so i can return it after its been removed
		return this.pick(0);
	}
	
	// removes card after being dealt
	public Card pick(int i){
		Card forReturn = deck.get(i);
		this.deck.remove(i);
		return forReturn;
	
	}
	// shuffles the deck
	public void shuffle(){
		// assistance from CP and stack-overflow 
		// created a new deck and randomly added that deck to old emptied deck
		Random generator = new Random(System.currentTimeMillis());
		Deck deck2 = new Deck();
		this.deck.clear();
		for( int i = 0; i < 52; i++){
			this.deck.add(deck2.pick(generator.nextInt(deck2.size())));
		}
			
	
	}
	
	// return a string with the contents of the hand of cards
	public String toString(){
		String deckVal = "Deck contents: ";
		
		for(int i=0; i < this.deck.size() -1; i++){
			deckVal += this.deck.get(i).getValue() + ", ";
		}
		deckVal += this.deck.get(this.deck.size() -1).getValue();
		return deckVal;
	}
	
	public int size(){
		return this.deck.size();
	}
	
	// main method to test
	public static void main( String[] args){
		Deck mydeck = new Deck();
		System.out.println(mydeck.toString());
		System.out.println(mydeck.size());
		mydeck.shuffle();
		System.out.println(mydeck.toString());
		System.out.println(mydeck.size());
		
	
	}




}