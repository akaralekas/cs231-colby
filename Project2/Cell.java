/*
 * File: Cell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Sept. 25, 2015
 * Assignment: Project 2
 */
  

// imports
import java.util.ArrayList;

/*
 * models a cell of data within a 2-d array grid
 */
public class Cell{
	//current value of the row in grid
	private int row;
	//current value of the col in grid
	private int col;
	//status of cells life... true=alive, false=dead
	private boolean status;
	
	/*
 	* creates a cell objects with given number of cols, rows, and life status
 	*/
	public Cell(int row, int col, boolean life){	
		//these all set values based on parameter inputs
		this.row = row;
		this.col = col;
		this.status = life;
	}
	
	//set the position of the cell within in the grid with parameters row and col
	public void setPosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	//sets the status of the cell with boolean parameter
	public void setAlive(boolean life){
		this.status = life;
	}
	
	//fetches the row value the cell is located within
	public int getRow(){
		return this.row;
	}
	
	//fetches the col value the cell is located within
	public int getCol(){
		return this.col;
	}
	
	//fetches the boolean dead or alive status of the current cell
	public boolean isAlive(){
		return this.status;
	}
	
	//this method converts the boolean life status of the cell into a string
	public String toString(){
		//if the cell is alive print out ""
		if(this.status == true){
			return " 🐹 ";
		}
		//if the cell is dead print out ""
		else{
			return " 🌲 ";
		}
	}
	
	//reads through a list of surrounding neighbors of a cell
	//keeps a counter on the number of alive neighbors
	//updates the state of the current cell depending on status and status of neighbors
	public void updateState( ArrayList<Cell> neighbors ){
		//counter for number of alive neighbors
		int numAlive = 0;
		//this for loop loops through the neighbor list and updates the numAlive counter
		for( int i = 0; i < neighbors.size(); i++){
			if(neighbors.get(i).isAlive() == true){
			 	numAlive +=1;
			 }
		}
		//if the current cell is alive and it has 2 or 3 alive neighbors, keep it alive
		if(this.isAlive() == true && numAlive == 2 || numAlive == 3){
			this.setAlive(true);
		}	
		//if the current cell is dead and it has 3 alive neighbors, make it alive!
		else if(this.isAlive() == false && numAlive == 3){
			this.setAlive(true);
		}
		//for any other condition of the current cell, make it a dead cell
		else{
			this.setAlive(false);
		}	
	}
}