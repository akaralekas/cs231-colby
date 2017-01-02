/*
 * File: Elevator.java
 * Edited by: Anthony Karalekas
 * Help:
 * Date: Nov. 13, 2015
 * Assignment: Lab 7 and Project 7
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.Queue;
import java.awt.*;

/**
 * Represents an elevator in an elevator simulation.  The elevator travels
 * between floors picking up and dropping off passengers.  It maintains
 * a priority queue of passengers riding in the elevator.
 * 
 * 
 * @author bseastwo
 * @author bmaxwell
 */
 
 /*
  * File provided by CS231 Professors
  * Edited by Tony Karalekas
  */
 
 
public class Elevator extends Cell
{	
	/**
	 * Represents the current traveling state of an elevator.  An elevator
	 * can be moving up or down or be idle.
	 */
	public enum Direction { DOWN, IDLE, UP }
	
	// the penalty incurred from stopping at a floor
	public static int DOOR_TIME = 5;
	// the maximum number of floors one can move at a time
	public static int FLOOR_DMAX = 1;

	private int currentFloor;
	private Direction direction;
	private PassengerGroup passengers;
	private ElevatorBank bank;
	private int waitTime;
	
	/**
	 * Create an elevator in an elevator bank with a maximum capacity.
	 * 
	 * @param bank the elevator bank this elevator belongs to
	 * @param capacity the maximum number of passengers in this elevator
	 */
	public Elevator(ElevatorBank bank, int capacity)
	{
		super(5, 5);
		this.bank = bank;
		this.passengers = new PassengerGroup(capacity);
		
		this.currentFloor = 0;
		this.direction = Direction.IDLE;
		this.waitTime = 0;
	}

	public int getCurrentFloor() { return this.currentFloor; }
	public void setCurrentFloor(int floor) { this.currentFloor = floor; }
	
	/**
	 * Gets the Direction the elevator is traveling.
	 * @return the direction
	 */
	public Direction getDirection() { return this.direction; }
	
	/**
	 * Sets the direction the elevator is traveling. This modifies
	 * the ordering used on the passengers riding the elevator, and
	 * so should only be called when the elevator is empty.
	 * 
	 * @param dir the new direction for the elevator
	 */
	public void setDirection(Direction dir) 
	{ 
		this.direction = dir;
		if (this.direction == Direction.UP)
		{
			this.passengers.useMinFloors();
		}
		else if (this.direction == Direction.DOWN)
		{
			this.passengers.useMaxFloors();
		}
	}
	
	/**
	 * Returns a string representation of this elevator.  This includes
	 * the elevator's current floor, direction, number of passengers,
	 * current wait time before the doors close, and the passenger with
	 * highest priority.  For example:
	 * <p>
	 * <tt>Elevator ( 0,    UP):  2 pass,  0 wait, Passenger: [ 0 ->  3,   0]</tt>
	 */
	public String toString()
	{
		return String.format(
			"Elevator (%2d, %5s): %2d pass, %2d wait, %s", 
			this.currentFloor, this.direction, this.passengers.getSize(), this.waitTime, this.passengers.getNext());
	}
	
	/**
	 * Default implementation for handling the case where the doors
	 * open on a particular floor.
	 * <p>
	 * Note that the direction for this elevator should be set before
	 * this function is called, otherwise we risk not ordering passengers
	 * properly as they board.
	 * <p>
	 * First, let as many passengers off at this floor as want to get off,
	 * then let anyone on who wants to travel in the same direction as
	 * the elevator.
	 */
	private void openDoors()
	{
		assert (this.direction != Direction.IDLE) : 
			"Elevator should decide what direction to go before opening doors.";
		
		// incur the penalty for opening the doors
		this.waitTime = Elevator.DOOR_TIME;
		
		// let passengers off, if anyone has reached their destination
		while (!this.passengers.isEmpty() &&
			this.getCurrentFloor() == this.passengers.getNext().getTargetFloor())
		{
			Passenger p = this.passengers.remove();
			p.setOnboard(false);
			Dispatch.instance().consumePassenger(p, this);
		}
		
		// see if there are passengers waiting to get on
		Queue floorQueue = this.bank.getFloorQueue(this);
		Passenger nextWaiter = (Passenger) floorQueue.peek();
		while (nextWaiter != null &&
			!this.passengers.isFull())
		{
			Passenger p = (Passenger) floorQueue.poll();
			p.setOnboard(true);
			this.passengers.add(p);
			nextWaiter = (Passenger) floorQueue.peek();
		}
	}
	
		/**
	 * Determines what an elevator does when it is empty.
	 * <p>
	 * If the elevator is currently at a floor where it can pick up
	 * passengers, do so. The elevator turns around at the bottom and
	 * top floors. Otherwise, the elevator continues in the direction
	 * it was headed.
	 * <p>
	 * Returns a negative number to move down, positive number to move up,
	 * and 0 to stay put.
	 *
	 * @return an int that determines what direction to move
	 */
	private int emptyRule(){
		int move = 0;

		// save current direction so we can continue if we do not
		// pick up any passengers on this floor
		Direction dir = this.getDirection();

		// find the closest waiting passenger in either direction
		this.direction = Direction.IDLE;
		Passenger nextWaiting = this.bank.getNearestPassenger(this);
		if (nextWaiting != null &&
			this.getCurrentFloor() == nextWaiting.getStartFloor()){
			
			// get passengers if we are on their floor
			// decide on our direction before opening door
			if (this.getCurrentFloor() < nextWaiting.getTargetFloor())
				this.setDirection(Direction.UP);
			else
				this.setDirection(Direction.DOWN);

			// pick up passengers from current floor
			this.openDoors();
		}
		else if (this.getCurrentFloor() == 0 &&
				dir != Direction.UP)
		{
			// switch directions at the bottom of the bank
			this.direction = Direction.UP;

		}
		else if (this.getCurrentFloor() == this.bank.getFloorCount()-1 &&
				dir != Direction.DOWN){
			// switch directions at the top of the bank
			this.direction = Direction.DOWN;
			
		}
        //Task 2 Work
        //worked with Steve Parrot and Brendan Doyle
        else{
        	//checks to see if someone is waiting
            if (nextWaiting != null){
            //if someone is waiting
            	//then, take the start floor for the person waiting
            	//and subtract the current floor and get difference
                int difference = nextWaiting.getStartFloor() - this.getCurrentFloor();
                //if difference is < 0 move down
                if(difference < 0){
                    this.direction = Direction.DOWN;
                }
                //move up
                else{this.direction = Direction.UP;
                }
            }
        }

		// move in the direction we are headed
		move = this.getDirection() == Direction.DOWN ? -Elevator.FLOOR_DMAX : +Elevator.FLOOR_DMAX;

		return move;
	}

	/**
	 * Determines what an elevator does when it is not empty.
	 * <p>
	 * If the current floor is one where passengers need to be let off,
	 * the elevator opens its doors. Otherwise, the elevator continues in
	 * the direction it was headed.
	 * <p>
	 * Returns a negative number to move down, positive number to move up,
	 * and 0 to stay put.
	 *
	 * @return an int that indicates what direction to move
	 */
	private int nonEmptyRule(){
		int move = 0;

		// we have passengers; find the one with highest priority
		Passenger nextOff = this.passengers.getNext();

                
		// see if someone wants to get off the elevator here
		if (this.getCurrentFloor() == nextOff.getTargetFloor()){
			this.openDoors();
		}
		
		
		// Task 2 worked with Steven Parrot and Brendan Doyle
		// Check to see if anyone is waiting
		// Only add passengers if there is more than one open space
		// But, do NOT stop for just one or two person
		else if(!this.bank.getFloorQueue(this).isEmpty() && !this.passengers.isFull()
				//only add people if there is less than 6 people on the elevator
				&& this.passengers.getSize() < 6){
			this.openDoors();
		}

		// move in the direction we are headed
		move = this.getDirection() == Direction.UP ? +Elevator.FLOOR_DMAX : -Elevator.FLOOR_DMAX;

		return move;
	}

	/**
	/**
	 * Updates the elevator by dropping off or picking up passengers at
	 * the current floor, if needed, or moving one floor in either
	 * direction.
	 */
	public void updateState(Landscape scape)
	{
		int move = 0;
		
		// Choose one of two behaviours, based on whether the elevator
		// is empty or has passengers.
		if (this.passengers.isEmpty())
		{
			move = this.emptyRule();
		}
		else
		{
			move = this.nonEmptyRule();
		}

		// See if we can move
		if (this.waitTime > 0)
		{
			// we have to wait for the doors to close--count down
			this.waitTime--;
		}
		else
		{
			// we are free to move--move
			this.currentFloor += move;
			
			// make sure we didn't jump the tracks
			this.currentFloor = Math.max(this.currentFloor, 0);
			this.currentFloor = Math.min(this.currentFloor, this.bank.getFloorCount() - 1);
		}
		
		// elevators never die
		return;
	}

	/**
	 * Draws an elevator as a stack of floors.  Each floor is a rectangle
	 * labeled with the floor number.  The elevator carriage is drawn as
	 * a solid rectangle on the elevator's current floor.  If the elevator
	 * has passengers, the carriage has a filled in circle within it.
	 */
	public void draw(Graphics g, int x0, int y0, int scale)
	{
		// draw each floor separately
		for (int floor = 0; floor < this.bank.getFloorCount(); floor++)
		{
				int ypos = y0 + (int)(y + (this.bank.getFloorCount() - floor)) * scale;
			g.setColor(Color.black);
			
			// draw a box for the floor and label it
			g.drawRect(x0 + (int)x*scale, ypos, 3 * scale, scale);
			g.drawString("" + floor, x0 + (int)(x + 1)*scale, ypos + scale - 1);
			
			// draw the elevator carriage on the proper floor
			if (floor == this.getCurrentFloor())
			{
				g.setColor(Color.orange);
				g.fillRect(x0 + (int)(x + 1)*scale, ypos, 2 * scale, scale);
				
				// fill the carriage if there's a passenger
				if (!this.passengers.isEmpty())
				{
					
					//EXTENSION 2
					Image img = Toolkit.getDefaultToolkit().getImage("BusinessMan.png");
					g.drawImage(img,(int) (x0 + (x + 1) * scale), ypos, scale-1, scale-1, null);
				
				}
				
				//EXTENSION 1
				//Writes in the number of passengers on each elevator at a given time
				g.setColor(Color.red);
				g.drawString(Integer.toString(this.passengers.getSize()),(int)(x0 + (x+2)*scale),(ypos+15));

			}
		}
	}
	
	/**
	 * Tests the elevator class by creating a new elevator and adding a
	 * few passengers.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		Elevator lift = new Elevator(null, 10);
		System.out.println("Init => " + lift);
		
		lift.setDirection(Direction.UP);
		lift.passengers.add(new Passenger(0, 3));
		lift.passengers.add(new Passenger(0, 4));
		System.out.println("Adds => " + lift);
	}

}
