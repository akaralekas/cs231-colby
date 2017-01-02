/*
 * File: PreferenceCell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 18, 2015
 * Assignment: Project 4
 */
  

// imports
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

/*
 * Preference Cell class that extends Cell
 * will have three categorized rules
 */
public class PreferenceCell extends Cell{
	private int cat;
	
	//constructor calls super--inheritance 
	public PreferenceCell( double x, double y, int cat){
		super(x, y);
		this.cat = cat;
	}
	
	//returns the category value
	public int getCategory(){
		return this.cat;
	}
	
	
	//updateState method for PreferenceCell
	//follows new rules using counters of same and different categories
	public void updateState(ArrayList<Cell> neighbors){
		int catSame = 0;
		int catDiff = 0;
		for( Cell c : neighbors){
			//use instance of to find categorized cells in neighbors
			if( c instanceof PreferenceCell){
				//help from CP Majgaard--forced type cast
				if( ((PreferenceCell)c).getCategory() == this.getCategory()){
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
		
		//this else contains the original categorizedcell updatestate
		//if num of same > num of different cells
		//move +/-5 at 1% of the time
		Random rand = new Random();
		if (catSame > catDiff) {
			int percentage = rand.nextInt(100);
			if (percentage == 0) {
				this.x += randomInRange(-5, 5);
				this.y += randomInRange(-5, 5);
				}
			}
			//else move +/-5 all the time
		else {
				this.x += randomInRange(-5, 5);
				this.y += randomInRange(-5, 5);
			}
    	}
    
    //same isNeighbor from Clumping
    public boolean isNeighbor(Cell cell){
    	if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 10* 10)){
			return true;
		}
		else{
			return false;
		}
    }
    
    //new draw method with three possible categories
    public void draw(Graphics g, int x0, int y0, int scale){
    	int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
    	
    	//if cat == 0
    	//draw a red rectangle
    	if( this.cat == 0){
    		g.setColor(new Color(1f, 0f, 0f));
			g.fillRect(x, y, scale * 2, scale * 2);
		
			return;
    	}
    	//if cat == 1
    	//draw a green rectangle
    	// green triangle arc for extension
    	else if( this.cat == 1){
    		g.setColor(new Color(0f, 1f, 0f));
			g.fillArc(x, y, scale * 4, scale * 4, 0, 90);
		
			return;
		}
		//if cat == 2
    	//draw a blue rectangle
    	// blue circle oval for extension
    	else if( this.cat == 2){
    		g.setColor(new Color(0f, 0f, 1f));
			g.fillOval(x, y, scale * 2, scale * 2);
		
			return;
		
		}
    }



//main test function for PreferenceCEll
	public static void main(String[] args) {
		PreferenceCell cell1 = new PreferenceCell(4.4, 3.6, 0);
		PreferenceCell cell2 = new PreferenceCell(2.1, 4.5, 1 );

		System.out.printf( "cell1: %.2f %.2f %d %d %d\n", 
		cell1.getX(), cell1.getY(), cell1.getCategory(), 
		cell1.getCol(), cell1.getRow());

		System.out.printf( "cell2: %.2f %.2f %d %d %d\n", 
		cell2.getX(), cell2.getY(), cell2.getCategory(),
		cell2.getCol(), cell2.getRow() );
	}
	
}