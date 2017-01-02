/*
 * File: Passenger.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Nov. 9, 2015
 * Assignment: Project 7
 */
  
/*
 *
 */
 
import java.util.*;
import java.util.Comparator;
import java.awt.*;

 
/*
 * Passenger class extends Cell
 */  
public class Passenger extends Cell{
	private boolean active;
	private boolean onboard;
	private final int startFloor;
	private final int targetFloor;
	private int waitTime;
	
	public Passenger(int start, int target){
		super(0, 0);
		this.startFloor = start;
		this.targetFloor = target;	
		this.waitTime = 0;		
	}
	
	//accessor for startFloor
	public int getStartFloor(){
		return this.startFloor;
	}
	
	//accessor for targetFloor
	public int getTargetFloor(){
		return this.targetFloor;
	}
	
	//accessor for waitTime
	public int getWaitTime(){
		return this.waitTime;
	}
	
	//accessor for active
	public boolean isActive(){
		return this.active;
	}
	
	//modifier for active
	public void setActive(boolean active){
		this.active = active;
	}
	
	//accessor for onboard
	public boolean isOnboard(){
		return this.onboard;
	}
	
	//modifier for onboard
	public void setOnboard(boolean onboard){
		System.out.println("I am Onboard!");
		this.onboard = onboard;
	}
	
	
	//Prints out a string of start and target floors
    public String toString(){
        String s = "Starting Floor:" + this.startFloor + "\n Target Floor:"
                + this.targetFloor;
        return s;
    }
	
	
	//increments waitTime
	public void updateState(Landscape scape){
		this.waitTime ++;
		return;
	}
	
	
    //Draws a Circle of size scale
	public void draw(Graphics g, int x1, int y1, int scale) {
		int x = x1 + (int)(this.getX() * scale);
		int y = y1 + (int)(this.getY() * scale);
		
		
		//EXTENSION 2
		Image img = Toolkit.getDefaultToolkit().getImage("BusinessMan.png");
		g.drawImage(img,x, y, scale-1, scale-1, null);
		return;

	}
	
		
	//nested class MaxFloor
	public static class MaxFloor implements Comparator<Passenger>{
    	
    	//compare method
    	public int compare( Passenger A, Passenger B ){
    		if(A.getTargetFloor() > B.getTargetFloor()){
    			return 1;
    		}
    		else if(A.getTargetFloor() == B.getTargetFloor()){
    			return 0;
    		}
    		else{
    			return -1;
    		}
    	}
    }
    
    //nested class MinFloor
    public static class MinFloor implements Comparator<Passenger>{
    	
    	//compare method
    	public int compare( Passenger A, Passenger B ){
    		if(A.getTargetFloor() > B.getTargetFloor()){
    			return -1;
    		}
    		else if(A.getTargetFloor() == B.getTargetFloor()){
    			return 0;
    		}
    		else{
    			return 1;
    		}
    	}
    }

}