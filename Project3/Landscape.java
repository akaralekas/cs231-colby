/*
 * File: Landscape.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 4, 2015
 * Assignment: Project 3
 */
  
//imports
 import java.util.ArrayList;
 import java.util.Random;
 
/*
 * models a landscape -- contains a linked list of cells
 */
public class Landscape{
	//height of landscape
	private int height;
	//width of landscape
	private int width;
	//linked list of cells
	LinkedList<Cell> landscape;
	

	/*
	 * creates a linked-list of cells landscape 
 	*/
	public Landscape(int width, int height){
		this.landscape = new LinkedList<Cell>();
		//set values based on parameter data
		this.width = width;
		this.height = height;		
	}
	
	// reset method that clears the entire landscape of agents
	public void reset(){
		this.landscape.clear();	
	}
	
	//returns the height
	public int getRows(){
		return this.height;
	
	}
	//returns the width
	public int getCols(){
		return this.width;
	}
	
	//adds an agent to the Landscape
	public void addAgent(Cell a){
		this.landscape.add(a);
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
    
    //method that returns list of Cells within given radius of location x,y
    public ArrayList<Cell> getNeighbors(double x0, double y0, double radius){
    	ArrayList<Cell> neighbors = new ArrayList<Cell>();
    	for(Cell z : landscape){
    		if(Math.pow(x0 - z.getX(), 2) + Math.pow(y0 - z.getY(), 2) <= (radius * radius)){
    			neighbors.add(z);
    		}	
    	}
    	return neighbors;
    }
    
    //gets a shuffled list of cells and call updateState on all
    public void advance(){
		landscape.toShuffledList();
		for(Cell x : landscape){
			x.updateState(getNeighbors(x.getX(), x.getY(), 2));
		}
    }
    
    //main test function
	public static void main(String[] args) {
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
				scape.addAgent( new Cell( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1)) );
			}
			//if args is length 2 use a categorized cell
			else if(args.length == 2){
				scape.addAgent( new CategorizedCell( 
			                gen.nextFloat() * (cols-1) , 
			                gen.nextFloat() * (rows-1), (int) gen.nextInt(2) ));
			}
		}

		System.out.println( "\nLandscape:\n" + scape );
		//iterations and call advance method
		for(int i=0; i < 50; i++) {
			scape.advance();
			System.out.printf("Iteration %d:\n", i);
			System.out.println( scape );
		}
	}

}
