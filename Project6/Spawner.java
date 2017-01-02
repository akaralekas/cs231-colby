/*
 * File: Spawner.java
 * Author: Anthony Karalekas
 * Help: CP
 * Worked with: Steven Parrott
 * Date: Nov. 1, 2015
 * Assignment: Project 6
 */
  
//imports
 import java.awt.*;
 import java.util.*;
 
/*
 * spawner class extends cell class
 */
public class Spawner extends Cell{
	
	/**
	* Constructor
	*/	
	public Spawner(double x, double y){
		super(x, y);
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

	//adds 10 customers to landscape
	public void updateState( Landscape scape ) {
		for(int i = 0; i < 10; i++){
			double items = randomInRange(1,3);
			double strategy = randomInRange(1,4);
			double cellX = randomInRange(0,100)*scape.getWidth();
			double cellY = 100;
			scape.addAgent(new Customer(cellX, cellY, (int) items, (int) strategy));
		}												

	}
	
	//draw method using graphics
	//received help from cp!
    public void draw(Graphics g, int x0, int y0, int scale){}
    
    
  	public static void main(String[] args) throws InterruptedException{
		Landscape scape = new Landscape(225, 400);
		Random generator = new Random();
		LandscapeDisplay display = new LandscapeDisplay(scape, 4);
        //add 1 Spawner at random locations.
		for(int i=0;i<1;i++) {
		    double cellX = generator.nextDouble()*scape.getWidth();
		    double cellY = generator.nextDouble()*scape.getHeight();
			scape.addAgent(new Spawner(cellX, cellY));
		}
	}
		
}    