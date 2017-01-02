/*
 * File: CategorizedCell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 4, 2015
 * Assignment: Project 3
 */
  
  
//imports
 import java.util.ArrayList;
 import java.util.Random;
 
/*
 * new class that extends Cell but contains a category -- CategorizedCell
 */
public class CategorizedCell extends Cell{
	private int cat;
	
	//constructor calls super--inheritance 
	public CategorizedCell( double x0, double y0, int cat){
		super(x0, y0);
		this.cat = cat;
	}
	
	//returns the category value
	public int getCategory(){
		return this.cat;
	}
	
	//returns string to indicate the category
	public String toString(){
		String catStr = new String();
		if(this.cat == 0){
			catStr +=  " 0 ";
		}
		else if(this.cat == 1){
			catStr += " 1 ";
		}
		else if(this.cat == 2){
			catStr += " 2 ";
		}
		return catStr;
	}
	
	//updateState method for CategorizedCell
	//follows new rules using counters of same and different categories
	public void updateState(ArrayList<Cell> neighbors){
		int catSame = 0;
		int catDiff = 0;
		for( Cell c : neighbors){
			//use instance of to find categorized cells in neighbors
			if( c instanceof CategorizedCell){
				//help from CP Majgaard--forced type cast
				if( ((CategorizedCell)c).getCategory() == this.getCategory()){
					catSame ++;
				}
				else{
					catDiff++;
				}
			}
			//this is if the cell doesn't have a category
			else{
				catDiff ++;
			}
		}
		//Task 7--if the category value = 2
		//This category type will unclump! instead of clumping 
		if(this.getCategory() == 2){
			Random rand = new Random();
			//if num of same is less than num of different cells
			if (catSame < catDiff) {
				int percentage = rand.nextInt(100);
				// 20% of the time
				if (percentage >= 79) {
					this.x0 += randomInRange(-5, 5);
					this.y0 += randomInRange(-5, 5);
				}
			}
			//else move +/-5 all the time
			else {
					this.x0 += randomInRange(-5, 5);
					this.y0 += randomInRange(-5, 5);
			}
		}
		//this else contains the original categorizedcell updatestate
		//if num of same > num of different cells
		//move +/-5 at 1% of the time
		else{
			Random rand = new Random();
			if (catSame > catDiff) {
				int percentage = rand.nextInt(100);
				if (percentage == 0) {
					this.x0 += randomInRange(-5, 5);
					this.y0 += randomInRange(-5, 5);
				}
			}
			//else move +/-5 all the time
			else {
					this.x0 += randomInRange(-5, 5);
					this.y0 += randomInRange(-5, 5);
			}
    	}
	}
	
	
	//main test function 
	public static void main(String[] args) {
		CategorizedCell cell1 = new CategorizedCell(4.4, 3.6, 0);
		CategorizedCell cell2 = new CategorizedCell(2.1, 4.5, 1 );

		System.out.printf( "cell1: %.2f %.2f %d %d %d\n", 
		cell1.getX(), cell1.getY(), cell1.getCategory(), 
		cell1.getCol(), cell1.getRow());

		System.out.printf( "cell2: %.2f %.2f %d %d %d\n", 
		cell2.getX(), cell2.getY(), cell2.getCategory(),
		cell2.getCol(), cell2.getRow() );
	}
	
}
