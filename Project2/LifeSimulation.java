/*
 * File: LifeSimulation.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Date: Sept. 28, 2015
 * Assignment: Project 2
 */

//imports 
import java.util.Random;

 
/*
 * models a simulation of Conways game of life
 */
public class LifeSimulation{
	Random random;
	Landscape game;
	//number of iterations
	private int numIter = 0;
	//number of rows and cols
	private int rows, cols;
	
	//class that creates a life simulation
	public LifeSimulation(int rows, int cols){
		this.random = new Random();
		game = new Landscape(rows, cols);
		game.reset();
		this.rows = rows;
		this.cols = cols;
	}
	//creates a random double and compares it to the double given parameter
	public boolean getRandLife( double density ){
		return random.nextDouble() <= density;
	}
	
	//this method loops through all the cells and randomly sets status values for each cell
	//uses the density to determine proportions of living to dead
	public void initializeRandom( double density ){
		for( int i = 0; i < rows; i++){
			for( int j = 0; j < cols; j++){
				game.getCell(i,j).setAlive(getRandLife(density));
			}
		}
	}
	//returns toString() function from Landscape.java
	public String toString(){
		return this.game.toString();
	}
	
	//simulation method that takes a number of simulations and slows down the terminal
	public void simulate( int n ) throws InterruptedException{
		System.out.println(this.game.toString());
		for(int i = 0; i < n; i++){
			Thread.sleep(750);
			this.game.advance();
			System.out.println(this.game.toString());
			}
	}
	
		//main function that runs my game of life simulation
	public static void main(String[] args)throws InterruptedException{
	
		/** received help from Jack W. **/
		// EXTENSION 1
		if( args.length == 0){
			System.out.println("Need command line arguments!");
			System.out.println("Command line should look as follows:");
			System.out.println("java LifeSimulation <numRows> <numCols> <numIters> <numDense>");
			System.exit(0);
		}
		else if(args.length < 4) {
			System.out.println("Need command line arguments!");
			System.out.println("Command line should look as follows:");
			System.out.println("java LifeSimulation <numRows> <numCols> <numIters> <numDense>");
			System.exit(0);
		}
		//command line arguments! for extension 2
		int numRows = Integer.parseInt(args[0]);
		int numCols = Integer.parseInt(args[1]);
		int numIters = Integer.parseInt(args[2]);
		double numDense = Double.parseDouble(args[3]);
	
		//main code that creates a new simulation
		LifeSimulation game = new LifeSimulation(numRows,numCols);
		//initializes a game of life with a specific life density
		game.initializeRandom(numDense);
		//runs the simulation a number of times
		game.simulate(numIters);
	
	}
	
}