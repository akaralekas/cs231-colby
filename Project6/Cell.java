/*
 * File: Cell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 28, 2015
 * Assignment: Project 6
 */
  

// imports
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

/*
 * models a cell that stores it own location within 
 */
public abstract class Cell{
	//y location value of cell
	protected double y;
	//x location value of cell
	//used protected to give inherited class the ability to be manipulate
	//Help from Bruce!
	protected double x;
	
	/*
 	* creates a cell objects with given location
 	*/
	public Cell(double x, double y){	
		this.x = x;
		this.y  = y;
		
	}
	//fetches x location
	public double getX(){
		return this.x;
	}
	
	//get column
	public int getCol(){
		return (int)Math.round(this.x);
	}
	
	//fetches y location
	public double getY(){
		return this.y;
	}
	
	//get row
	public int getRow(){
		return (int)Math.round(this.y);
	}

	//to string method that returns a " . "
	public String toString(){
		return "0";
	}
	
	//Worked with Steven Parrot in my CS 231 Class
	//Stack Overflow and Steve Parrot
	//basically this method is used to generate random number within a range
	public double randomInRange(double min, double max) {
        Random r = new Random();
        double range = max - min;
        double scaled = r.nextDouble() * range;
        double shifted = scaled + min;
        return shifted;
    }
    
    
	// isNeighbor is a boolean that returns true if the argument cell is within some radius of this cell
	public boolean isNeighbor(Cell cell){
		if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 2 * 2)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Project 4
    //10/18/15---NOW abstract class for Project4
    public abstract void updateState(Landscape scape);
    
    
    public abstract void draw(Graphics g, int x, int y, int scale);
    
    
	//main test method
	/*public static void main(String[] args) throws InterruptedException{
	
		//Customer cell1 = new Customer(4.4, 3.6 );
		//Customer cell2 = new Customer(2.1, 4.5 );

		System.out.printf( "cell1: %.2f %.2f %d %d\n", 
		cell1.getX(), cell1.getY(), 
		cell1.getCol(), cell1.getRow() );

		System.out.printf( "cell2: %.2f %.2f %d %d\n", 
		cell2.getX(), cell2.getY(), 
		cell2.getCol(), cell2.getRow() );
		
	}*/
}