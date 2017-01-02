/*
 * File: Boo.java
 * Author: Anthony Karalekas
 * Help: CP
 * Extension 2
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
 * Extension 2! This creates another predator called Boo
 * Boo can never die and he will eventually populate the whole landscape!
 * This extension makes use of both another class/predator
 * AND it uses another route of simulation to make this happen
 */


/*
 * This is the another predator class
 * The Boo steals the prey, DragonBalls and consumes ninjas and gifts
 */
public class Boo extends Cell{
	protected int category;
	protected int health;
	
	//constructor
	public Boo(double x, double y){
		super(x, y);
		this.category = 4;
		this.health = 10;
	
	}
	//category method...honestly unnecessary 
	public int getCategory(){
		return this.category;
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

	//updateState for Boo predator
	//
	public void updateState(Landscape scape){
        ArrayList<Cell> neighbors = scape.getNeighbors(this);
		Random rand = new Random();
        int percentage = rand.nextInt(100);
        int found = 0;
		
		//for items that are a neighbor to Boo
		for (Cell item : neighbors){
			//if the item is a dragonball, gift, or ninja...consume!
			if(item instanceof DragonBall || item instanceof Gift || item instanceof Ninja){
				this.health ++;
				scape.removeAgent(item);
				found ++;
				if (this.health > 20){
					scape.addAgent( new Boo( 
			                	rand.nextDouble() *scape.getWidth() , 
			               	 rand.nextDouble() *scape.getHeight()));
				}
			}
		}
		
		//moves the boo if there are no neihgbors to eat
		//but checks to make sure it doesnt move within a castle radius
		if(found == 0){
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
			if (this.health == 0){
				scape.removeAgent(this);	
			}		
		}
		//moves the boo with a 10% chance
		if (percentage <= 10){
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
		if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 10 * 10)){
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
		Image img1 = Toolkit.getDefaultToolkit().getImage("Boo.png");
		g.drawImage(img1, x-24,  y-24, null);
		
		return;
	}
	
	//main test code
	public static void main(String[] args) throws InterruptedException{
	Landscape scape = new Landscape(150, 100);
	LandscapeDisplay display = new LandscapeDisplay(scape, 4);
	Random gen = new Random();

	for(int i=0;i< 100;i++) {
						scape.addAgent( new Boo( gen.nextDouble()*scape.getWidth(), 
													gen.nextDouble()*scape.getHeight() ) );
				}
	for(int i=0;i<20;i++) {
				scape.advance();
				display.update();
				Thread.sleep( 250 );
				}
	
	}


}