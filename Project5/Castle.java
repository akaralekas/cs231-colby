/*
 * File: Castle.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 28, 2015
 * Assignment: Project 5
 */
  

// imports

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
/*
 * Categories 0 = Ninja, 1 = DragonBall, 2 = GiftBox, 3 = Castle
 *
 *received category help and idea from roommate and CS major Aaron Liu
 *[ended up not using the categories!]
 *he helped me understand each individual class and each individual updateStat
 *also received help from CP Majgaard
 *worked alongside Steven Parrott CS231 classmate
 */




/*
 * This is the obstacle class
 * The castle creates an obstacle for the Ninjas!
 */
 
public class Castle extends Cell{
	public int category;
	
	
	//constructor
	public Castle(double x, double y){
		super(x, y);
		this.category = 3;
	
	}
	
	
	
	//Worked with Steven Parrott in my CS 231 Class
	//Stack Overflow and Steve Parrott
	//basically this method is used to generate random number within a range
	public double randomInRange(double min, double max) {
        Random r = new Random();
        double range = max - min;
        double scaled = r.nextDouble() * range;
        double shifted = scaled + min;
        return shifted;
    }
	
	
	//no need for update state in castle
	public void updateState(Landscape scape){}
	
	
	// isNeighbor is a boolean that returns true if the argument cell is within some radius of this cell
	public boolean isNeighbor(Cell cell){
		if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 2 * 2)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//draw method using graphics
	// now using drawImage for extension 1
	//received help from cp!
    public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		Image img1 = Toolkit.getDefaultToolkit().getImage("Castle.png");
		
		g.setColor(new Color(1f, 0f, 0f));
		g.fillOval(x -60,y -60,120,120);
		g.drawImage(img1, x-16,  y-16, null);
		return;
	}
	
	//main test code
	public static void main(String[] args) throws InterruptedException{
	Landscape scape = new Landscape(150, 100);
	LandscapeDisplay display = new LandscapeDisplay(scape, 4);
	Random gen = new Random();

	for(int i=0;i< 20;i++) {
						scape.addAgent( new Castle( gen.nextDouble()*scape.getWidth(), 
													gen.nextDouble()*scape.getHeight() ) );
				}
	for(int i=0;i<20;i++) {
				scape.advance();
				display.update();
				Thread.sleep( 250 );
				}
	
	}

}