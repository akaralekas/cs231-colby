/*
 * File: Cell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 4, 2015
 * Assignment: Project 3
 */
  

// imports
import java.util.ArrayList;
import java.util.Random;

/*
 * models a cell that stores it own location within 
 */
public class Cell{
	//y location value of cell
	protected double y0;
	//x location value of cell
	//used protected to give inherited class the ability to be manipulate
	//Help from Bruce!
	protected double x0;
	
	/*
 	* creates a cell objects with given location
 	*/
	public Cell(double x0, double y0){	
		this.x0 = x0;
		this.y0 = y0;
		
	}
	//fetches x location
	public double getX(){
		return this.x0;
	}
	
	//get column
	public int getCol(){
		return (int)Math.round(this.x0);
	}
	
	//fetches y location
	public double getY(){
		return this.y0;
	}
	
	//get row
	public int getRow(){
		return (int)Math.round(this.y0);
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
	
	//updateState method that follows rules
	//if numNeigh > 3 move +/-5 with 1% chance
	//else move +/-5
	//Help and collaboration with Steve Parrot
    public void updateState(ArrayList<Cell> neighbors) {
        int numNeigh = neighbors.size();
        Random rand = new Random();
        if (numNeigh > 3) {
            int percentage = rand.nextInt(100);
            //this completes the if condition 1% of the times
            if (percentage == 0) {
                this.x0 += randomInRange(-5, 5);
                this.y0 += randomInRange(-5, 5);
            }
        }
        else {
                this.x0 += randomInRange(-5, 5);
                this.y0 += randomInRange(-5, 5);
        }
    }
    
	//main test method
	public static void main(String[] args) {
		Cell cell1 = new Cell(4.4, 3.6 );
		Cell cell2 = new Cell(2.1, 4.5 );

		System.out.printf( "cell1: %.2f %.2f %d %d\n", 
		cell1.getX(), cell1.getY(), 
		cell1.getCol(), cell1.getRow() );

		System.out.printf( "cell2: %.2f %.2f %d %d\n", 
		cell2.getX(), cell2.getY(), 
		cell2.getCol(), cell2.getRow() );
	}
}