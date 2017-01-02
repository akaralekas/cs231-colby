/*
 * File: Simulation.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 4, 2015
 * Assignment: Project 3
 */
 
 //imports
import java.util.Random;

/*
 * models a simulation class
 */
public class Simulation{
	//initialize
	private int type;
	private int iters;
	private int print;
	private int rows;
	private int cols;
	private int agents;
	
	//constructor
	public Simulation(){
		this.type = type;
		this.iters = iters;
		this.print = print;
		this.rows = rows;
		this.cols = cols;
		this.agents = agents;
	}

	//main test function
	public static void main(String[] args) {
		//first check that there are enough command line arguments
		if( args.length != 6){
			System.out.println("You need 6 command line arguments!");
			System.out.println("The command line arguments should follow this format:");
			System.out.println("java Simulation type iters rows cols agents print");
			System.out.println("IMPORTANT:");
			System.out.println("Input 0 for type Cell, Input 1 for type CategorizedCell");
			return;
		}
		//assign everything to command line args
		int type = Integer.parseInt(args[0]);
		int iters = Integer.parseInt(args[1]);
		int rows = Integer.parseInt(args[2]);
		int cols = Integer.parseInt(args[3]);
		int agents = Integer.parseInt(args[4]);
		int print = Integer.parseInt(args[5]);
		Landscape scape = new Landscape(cols, rows);
		Random gen = new Random();
		
		//if the type arg is 0 create a cell
		for(int i=0; i < agents; i++) {
			if(type == 0){
				scape.addAgent( new Cell( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1)) );
			}
			//Comment for task 7
			//if the type are is 1 create a CatCell
// 			else if(type == 1){
// 				scape.addAgent( new CategorizedCell( 
// 			                gen.nextFloat() * (cols-1) , 
// 			                gen.nextFloat() * (rows-1), (int) gen.nextInt(2) ));
// 			}
			//Uncomment for task 7
			//This basically adds another category to the mix
			else if(type == 1){
				scape.addAgent( new CategorizedCell( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1), (int) gen.nextInt(3) ));
			}
		}

		System.out.println( "\nLandscape:\n" + scape );
		
		for(int i=0; i < iters; i++) {
			scape.advance();
			//Help from CP. Use modulo to determine how many iterations to print out
			//For example, 10 % 5(print) leaves no remainder
			//So that will print every 5 iterations
			if(i == 0 || i % print == 0){
				System.out.printf("Iteration %d:\n", i);
				System.out.println( scape );
			}	
		}
	}
	
}