/*
 * File: Landscape.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 18, 2015
 * Assignment: Project 6
 */
  
//imports
 import java.util.ArrayList;
 import java.util.Random;
 
/*
 * models a landscape -- contains a linked list of cells
 */
public class Landscape{
	//height of landscape
	private double height;
	//width of landscape
	private double width;
	//linked list of cells
	protected DoubleLinkedList<Cell> landscape;
	//New for Project 6 -- Task 4
	protected DoubleLinkedList<Customer> customers;
	

	/*
	 * creates a linked-list of cells landscape 
 	*/
	public Landscape(int width, int height){
		this.landscape = new DoubleLinkedList<Cell>();
		//set values based on parameter data
		this.customers = new DoubleLinkedList<Customer>();
		this.width = width;
		this.height = height;		
	}
	
	// reset method that clears the customers in the landscape
	public void reset(){
		this.customers.clear();	
	}
	
	//returns the height as rounded integer
	public int getRows(){
		return (int) this.height;
	
	}
	//returns the width as rounded integer
	public int getCols(){
		return (int) this.width;
	}
	
	//return height as double value
	public double getHeight(){
		return this.height;
	}
	
	//return height as double value
	public double getWidth(){
		return this.width;
	}
	
	
	//adds an agent to the Landscape
	//ADDs customers to separate list--task 4 project 6
	public void addAgent(Cell a){
		if(a instanceof Customer){
			this.customers.add((Customer)a);
		}
		landscape.add(a);
	}
	
	//remove agent from the Landscape
	// Project 4
	public void removeAgent(Cell a){
		landscape.remove(a);
	}
	
	//creates an ArrayList of cells on the landscape
	public ArrayList<Cell> getAgents(){
		return this.landscape.toArrayList();
	}
	

	
	//Worked together with Steve Parrot from CS 231
	//Received help from TA Mike
	//creates a 2d array of string objects
	public String toString() {
        String[][] slist = new String[this.getRows()][this.getCols()];
        //loops through a sets all string to empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                slist[i][j]  = " " ;
            }
        }
        //for each loop of arraylist of cells
        for(Cell x: this.getAgents()){
        	//this makes sure all values are within in the index
        	if((x.getRow() < height && x.getRow() >=0) && (x.getCol() < width && x.getCol() >=0)) {
        		slist[x.getRow()][x.getCol()] = x.toString();
        	}
        }
        //final string concatenates all of the strings in the array grid
        String finalStr = new String();
        for( int i = 0; i < slist.length; i++){
        	for(int j= 0; j<slist[i].length; j++){
        		finalStr += slist[i][j];
        	}
        	finalStr += "\n";
        }
        return finalStr; 
    }
    
    //new getNeighbors method for Project 6, Task 4
    //worked with Steven Parrott
    public ArrayList<CheckoutAgent> getCheckoutAgents(){
    	ArrayList<CheckoutAgent> shoppers = new ArrayList<CheckoutAgent>();
    	for(Cell z : this.landscape){
    		if(z instanceof CheckoutAgent){
    			shoppers.add((CheckoutAgent)z);
    		}	
    	}
    	return shoppers;
    }
    
      //method that returns list of Cells
    public ArrayList<Cell> getNeighbors(Cell qcell){
    	ArrayList<Cell> neighbors = new ArrayList<Cell>();
    	for(Cell z : this.landscape){
    		if(qcell.isNeighbor(z) == true){
    			neighbors.add(z);
    		}	
    	}
    	return neighbors;
    }
    
    //gets a shuffled list of cells and call updateState on all
    public void advance(){
		landscape.toShuffledList();
		for(Cell x : landscape){
			x.updateState(this);
		}
    }
    
      //created this method with help from CP Majgaard
	//use this method to check if any objects are within a certain radius of a castle
	//this is important because the other cells cannot move through a castle
    public boolean inRadius(double cellX, double cellY, 
							double castleX, double castleY, double radius){
							
		double distanceSq = Math.pow(cellX - castleX, 2) +
							 (Math.pow(cellY - castleY, 2));
		
		if(distanceSq <= radius * radius){
			return true;
		}	    
		
		return false;
		
	}

    
    //main test function
	/*public static void main(String[] args) {
		int rows = 30;
		int cols = 70;
		int N = 300;
		Landscape scape = new Landscape(cols, rows);
		Random gen = new Random();
		
		//task 5 uses args to decide type of cell
		if( args.length != 1 && args.length != 2){
			System.out.println("You need either 1 or 2 command line args");
			System.out.println("If you want a CatCell use 2 args");
			System.out.println("If you want a normal Cell use 1 arg");
			return;
		}
		//Help from Bruce on command line args TASK 5
		for(int i=0; i < N; i++) {
		//in args is length 1 use a normal cell
			if(args.length == 1){
				scape.addAgent( new Spawner( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1)) );
			}/*
			//if args is length 2 use a categorized cell
			else if(args.length == 2){
				scape.addAgent( new ClumpingCell( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1), (int) gen.nextInt(2) ));
			}
			
		}
 
		System.out.println( "\nLandscape:\n" + scape );
		//iterations and call advance method
		for(int i=0; i < 200; i++) {
			scape.advance();
			System.out.printf("Iteration %d:\n", i);
			System.out.println( scape );
		}
	}*/

}
