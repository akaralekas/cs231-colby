/*
 * File: PassengerGroup.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Nov. 9, 2015
 * Assignment: Project 7
 */
  
/*
 *
 */
 
import java.util.*;
import java.awt.*;
import java.util.Comparator;

 
/*
 * PassengerGroup class
 */  
public class PassengerGroup extends MyPriorityQueue<Passenger> implements Iterable<Passenger> {
	
	//constructor
	public PassengerGroup(int max){
		super(max, new Passenger.MaxFloor());
	}
	
	//useMaxFloors
	public void useMaxFloors(){
		setComparator(new Passenger.MaxFloor());
	}
	
	//useMinFloors
	public void useMinFloors(){
		setComparator(new Passenger.MinFloor());
	}
	
    public String toString(){
        return ("Passengers: " + this.getSize());
    }
    
    /*public static void main(String[] args){
    	PassengerGroup group = new PassengerGroup(4);
    	group.add(new Passenger(1, 3));
    	group.add(new Passenger(2, 3));
    	group.useMaxFloors();
    	group.useMinFloors();
    	System.out.print(group);
    }*/
}
