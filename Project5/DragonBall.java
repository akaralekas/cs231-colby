/*
 * File: DragonBall.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 28, 2015
 * Assignment: Project 4
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
 *he helped me understand each individual class and each individual updateStat
 *also received help from CP Majgaard
 *worked alongside Steven Parrott CS231 classmate
 */


/*
 * This is the prey class
 * The DragonBalls are prey for the Ninjas!
 */
 
public class DragonBall extends Cell{
	public int category;
	
	//constructor
	public DragonBall(double x, double y){
		super(x, y);
		this.category = 1;
	
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
    
    //worked with CP Majgaard
    //created this method with help from CP Majgaard
	//use this method to check if any objects are within a certain radius of a castle
	//this is important because the other cells cannot move through a castle
	public boolean inRadius(double ninjaX, double ninjaY, 
							double castleX, double castleY, double radius){
							
		double distanceSq = Math.pow(ninjaX - castleX, 2) +
							 (Math.pow(ninjaY - castleY, 2));
		
		if(distanceSq <= radius * radius){
			return true;
		}	    
		
		return false;
		
	}
    
    //updateState method for a DragonBall
	public void updateState(Landscape scape){
        ArrayList<Cell> neighbors = scape.getNeighbors(this);
		Random rand = new Random();
        int percentage = rand.nextInt(100);
        int found = 0;
		
		//dragonballs have a 30% chance of moving
		//first we check to see if the new location of the dragon ball
		//    will interfere with a castles radius
		//if the dragonball is going to move into a castles radius, we dont move
		if (percentage <= 30){
			ArrayList<Castle> castles = scape.getCastles();
			double randX = randomInRange(-5, 5);
			double randY = randomInRange(-5, 5);
			boolean flag = false;
			for(Castle c : castles){
				if(inRadius(this.x + randX, 
							this.y + randY, c.getX(), c.getY(), 15)){
					flag = true;
				}
			}		
			
			if(flag == true){
				return;
			}
			else{
				this.x += randX;
            	this.y += randY;	
            }
		}	
			
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
	
	//draw method using graphics
	// now using drawImage for extension 1
	//received help from cp!
    public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		Image img1 = Toolkit.getDefaultToolkit().getImage("DragonBall.png");
		g.drawImage(img1, x-8,  y-8, null);
		
		return;
	}
	
	//main test method
	public static void main(String[] args) throws InterruptedException{
	Landscape scape = new Landscape(150, 100);
	LandscapeDisplay display = new LandscapeDisplay(scape, 4);
	Random gen = new Random();

	for(int i=0;i< 100;i++) {
						scape.addAgent( new DragonBall( gen.nextDouble()*scape.getWidth(), 
													gen.nextDouble()*scape.getHeight() ) );
				}
	for(int i=0;i<20;i++) {
				scape.advance();
				display.update();
				Thread.sleep( 250 );
				}
	
	}

}