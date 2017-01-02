/*
 * File: Landscape.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Sept. 28, 2015
 * Assignment: Project 2
 */
  
//imports
 import java.util.ArrayList;
 
/*
 * models a landscape -- 2-d array grid of cells
 */
public class Landscape{
	//a 2-d array grid object of cells
	Cell[][] grid;
	//total number of rows in the grid
	private int rows;
	//total number of columns in the grid
	private int cols;

	/*
	 * creates a 2-d array grid object of cells
 	*/
	public Landscape(int rows, int cols){
		//initializes a new 2-d grid array list of data type cell with [rows][cols]
		grid = new Cell[rows][cols];
		//loop through each part of the grid and creates a new Cell
		for( int i = 0; i < rows; i++){
			for( int j = 0; j < cols; j++){
				//each cell gets its location values and it given an initial "dead" status
				grid[i][j] = new Cell(i, j, false);
			}
		}
		//set values based on parameter data
		this.rows = rows;
		this.cols = cols;		
	}
	
	// reset method that resets all the cells to dead(false) status
	public void reset(){
		for( int i = 0; i < rows; i++){
			for( int j = 0; j < cols; j++){
				this.grid[i][j].setAlive(false);
			}
		}		
	}
	
	//returns the number of rows
	public int getRows(){
		return rows;
	
	}
	//returns the number of columns
	public int getCols(){
		return cols;
	}
	
	//fetches a cell at a given location
	public Cell getCell(int row, int col){
		return this.grid[row][col];
	}
	
	//this toString method creates a string or textual representation of the grid
	public String toString(){
	String textrep = "";
	
		for( int i = 0; i < rows; i++){
			for( int j = 0; j < cols; j++){
				//append each part of the grid to the textrep
				textrep += this.grid[i][j].toString();
			}
			//start a new line for each row
			textrep += "\n";
		}
		//finally return the textrep string
		return textrep;
	}
	
	//very important method
	//creates an ArrayList of cell neighbors surrounding a specific cell
	public ArrayList<Cell> getNeighbors(int row, int col){
	ArrayList<Cell> references = new ArrayList<Cell>();
		//started with the top left corner case
		if(row == 0){
			if(col ==0){
				//add the three surrounding cells to the list
				references.add(this.grid[0][1]);
				references.add( this.grid[1][0]);
				references.add(this.grid[1][1]);
			}
			//top right corner case
			else if(col == cols-1){
				//add the three surrounding cells to the list
				references.add(this.grid[0][col-1]);
				references.add(this.grid[1][col]);
				references.add(this.grid[1][col-1]);
			}
			//this else represents all of the cells in the top row 
			//that are not one of the two corners
			else{
				for( int i = row; i <= row+1; i++){
					for(int j = col -1; j <= col +1; j++ ){
						if(i == row && j == col){}
						else{
							//add the five surrounding cells to the list
							references.add(this.grid[i][j]);
						}
					}
				}	
			}
		}
	
		//now we are focusing on the bottom of the grid
		else if(row == rows -1){
			//firs we start with the bottom left corner case
			if(col ==0){
				//add the three surrounding cells to the list
				references.add(this.grid[row-1][0]);
				references.add(this.grid[row][1]);
				references.add(this.grid[row-1][1]);
			}
			//then the bottom right case
			else if(col == cols-1){
				//add the three surrounding cells to the list
				references.add(this.grid[row][col-1]);
				references.add(this.grid[row-1][col]);
				references.add(this.grid[row-1][col-1]);	
			}
			//and finally all of the cells in the bottom besides the two corner cells
			else{
				for( int i = row-1; i <= row; i++){
					for(int j = col -1; j <= col +1; j++ ){
						if(i == row && j == col){}
						else{
							//add the five surrounding cells to the list
							references.add(this.grid[i][j]);
						}
					}
				}	
				
			}	
		}
	
		//this else if takes care of the far left column besides the two corners(top & bottom)
		else if(col == 0){
			for( int i = row-1; i <= row +1; i++){
				for(int j = col; j <= col +1; j++ ){
					if(i == row && j == col){}
					else{
						//add the five surrounding cells to the list
						references.add(this.grid[i][j]);
					}
				}
			}	
		}
		//this else if takes care of the far right column besides the two corners(top & bottom)
		else if(col == cols -1){
			for( int i = row-1; i <= row +1; i++){
				for(int j = col-1; j <= col; j++ ){
					if(i == row && j == col){}
					else{
						//add the five surrounding cells to the list
						references.add(this.grid[i][j]);
					}
				}
			}	
		}
		
		//and finally, this last condition takes care of all the rest of the cells
		//these cells should all have eight neighbors
		else{
			for( int i = row-1; i <= row +1; i++){
				for(int j = col-1; j <= col +1; j++ ){
					if(i == row && j == col){}
					else{
						////add the eight surrounding cells to the list
						references.add(this.grid[i][j]);
					}
				}
			}	
		}
		//return the list of neighbors
		return references;
	}
	//Task 5 of the project!
	//I decided to create a new constructor method to create a true copy of the grid
	public Landscape(Landscape scape){
		//this assigns the new landscape all the same values as the old
		this.rows = scape.getRows();
		this.cols = scape.getCols();
		this.grid = new Cell[this.rows][this.cols];
		for( int i = 0; i < rows; i++){
			for( int j = 0; j < cols; j++){
				grid[i][j] = new Cell(i, j, scape.getCell(i, j).isAlive());
			}
		}
	}
	
	//this method reads through the boolean cells of the original grid
	//then it updates the state of each cell in the new landscape 
	//depending on the status of neighbors in the original landscape
	public void advance(){
		Landscape temp = new Landscape(this);
		for( int i = 0; i < this.getRows(); i++){
			for( int j = 0; j < this.getCols(); j++){
				//gets the list of neighbors
				ArrayList<Cell> neighbors = this.getNeighbors(i,j);
				//updates the alive state of the cells 
				temp.getCell(i, j).updateState(neighbors);
			}		
		}
		//points the original grid at the temporary landscape object
		this.grid = temp.grid;
	}
}