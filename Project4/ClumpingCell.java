/*
 * File: ClumpingCell.java
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
 * creates a ClumpingCell class
 * child class of Cell
 */
public class ClumpingCell extends Cell{

	public ClumpingCell(double x, double y){
		//super inherits from cell
		super(x,y);	
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
	
	//still has clumping behavior
    //10/18/15---NOW abstract class for Project4
    public void updateState(ArrayList<Cell> neighbors) {
        int numNeigh = neighbors.size();
        Random rand = new Random();
        if (numNeigh > 3) {
            int percentage = rand.nextInt(100);
            //this completes the if condition 1% of the times
            if (percentage == 0) {
                this.x += randomInRange(-3, 3);
                this.y += randomInRange(-3, 3);
            }
        }
        else {
                this.x += randomInRange(-3, 3);
                this.y += randomInRange(-3, 3);
        }
    }

	// isNeighbor is a boolean that returns true if the argument cell is within some radius of this cell
	public boolean isNeighbor(Cell cell){
		if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 3 * 3)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//draw method using graphics
	// draws a rectangle with a specific color
    public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		
		g.setColor(new Color(0.2f, 0.6f, 0.3f));
		g.fillRect(x, y, scale, scale);
		
		return;
	}
	
	
	 //main test function for ClumpingCell
	public static void main(String[] args) {
		Landscape landscape = new Landscape(400, 225);
		Random gen = new Random();
		
		int iterations = Integer.parseInt(args[0]);
		
		for(int i = 0; i< 1000; i++){
				landscape.addAgent( new ClumpingCell(gen.nextDouble()*landscape.getCols(),
							gen.nextDouble()*landscape.getRows()));
		}
		
		LandscapeDisplay display = new LandscapeDisplay(landscape,2);
		
		for(int i = 0; i<iterations; i++){
			String n = "";
			if(Integer.toString(i).length() < Integer.toString(iterations).length()){
				for(int j = 0; j < (Integer.toString(iterations).length() - Integer.toString(i).length()); j++){
					n += "0";
				}
			}
			n += i + ".png";
			display.saveImage(n);
			landscape.advance();
			System.out.println(i);
			display.update();
		}
	
	}


}


