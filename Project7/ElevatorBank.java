/*
 * File: ElevatorBank.java
 * Edited By: Anthony Karalekas
 * Help:
 * Date: Nov. 13, 2015
 * Assignment: Lab 7 and Project 7
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a collection of elevators that operate independently.  The
 * bank maintains a list of all the elevators in the bank; an individual
 * elevator can be accessed by ElevatorBank.getElevator(index).
 * <p>
 * The bank also maintains queues of passengers on each floor that have requested
 * to travel to a different floor.  There are two queues for each floor,
 * one for passengers traveling to a higher floor and one for passengers
 * traveling to a lower floor.
 * 
 * @author bseastwo
 */
 
 /*
  * File provided by CS231 Professors
  * Edited by Tony Karalekas
  */
 
public class ElevatorBank
{
	private Elevator[] elevators;
	private Queue[] upQueues;
	private Queue[] downQueues;
	
	/**
	 * Initializes an elevator bank with the given number of elevators
	 * and floors.
	 * @param lifts the number of elevators
	 * @param floors the number of floors
	 */
	public ElevatorBank(int lifts, int floors, int capacity)
	{
		// initialize all the elevators
		this.elevators = new Elevator[lifts];
		for (int i = 0; i < lifts; i++)
		{
			this.elevators[i] = new Elevator(this, capacity);
		}
		
		// initialize all the queues on each floor
		this.upQueues = new Queue[floors];
		this.downQueues = new Queue[floors];
		for (int i = 0; i < floors; i++)
		{
			this.upQueues[i] = new LinkedList();
			this.downQueues[i] = new LinkedList();
		}
	}
	
	public int getFloorCount() { return this.upQueues == null ? 0 : this.upQueues.length; }
	public int getElevatorCount() { return this.elevators == null ? 0 : this.elevators.length; }
	
	/**
	 * Gets the elevator with the given index.
	 * @param index the elevator index
	 * @return the elevator, or null if the index is invalid
	 */
	public Elevator getElevator(int index)
	{
		Elevator lift = null;
		if (index >= 0 && index < this.getElevatorCount())
			lift = this.elevators[index];
		return lift;
	}
	
	/**
	 * Determines whether a given floor index is valid for this bank.
	 * @param floor the floor index
	 * @return true if the floor is valid, false otherwise
	 */
	private boolean isFloorValid(int floor)
	{
		return floor >= 0 && floor < this.getFloorCount();
	}
		
	/**
	 * Adds a passenger to the bank by placing them in the proper queue
	 * on their start floor.  This method checks to make sure the
	 * passenger has valid start and end floors for this elevator bank.
	 * 
	 * @param user the passenger to add
	 */
	public void addPassenger(Passenger user)
	{
		if (this.isFloorValid(user.getStartFloor()) &&
			this.isFloorValid(user.getTargetFloor()) &&
			user.getStartFloor() != user.getTargetFloor())
		{
			if (user.getStartFloor() < user.getTargetFloor())
			{
				this.upQueues[user.getStartFloor()].offer(user);
			}
			else
			{
				this.downQueues[user.getStartFloor()].offer(user);
			}
		}
		else
		{
			System.err.println("ElevatorBank.addPassenger is rejecting a naughty passenger: " + user);
		}
	}

	/**
	 * Returns a reference to the passenger that is closest to the given
	 * elevator in the direction it is traveling. If the elevator is idle, this
	 * is the nearest passenger traveling in either direction. If the elevator
	 * is moving in a direction, this is the closest passenger in that
	 * direction.
	 * 
	 * @param lift the elevator to consider
	 * @return the closest passenger or null if a suitable one does not exist.
	 */
	public Passenger getNearestPassenger(Elevator lift)
	{	
		// find all nearby passengers
		Passenger[] nearby = new Passenger[4];
		
		// find nearest passengers above
		for (int i = lift.getCurrentFloor(); i < this.getFloorCount(); i++)
		{
			if (nearby[0] == null && this.upQueues[i].size() > 0)
				nearby[0] = (Passenger) this.upQueues[i].peek();
			if (nearby[1] == null && this.downQueues[i].size() > 0)
				nearby[1] = (Passenger) this.downQueues[i].peek();
		}
		
		// find nearest passengers below
		for (int i = lift.getCurrentFloor(); i >= 0; i--)
		{
			if (nearby[2] == null && this.upQueues[i].size() > 0)
				nearby[2] = (Passenger) this.upQueues[i].peek();
			if (nearby[3] == null && this.downQueues[i].size() > 0)
				nearby[3] = (Passenger) this.downQueues[i].peek();
		}
		
		// if we're already moving, we ony need to consider one passenger
		if (lift.getDirection() == Elevator.Direction.UP)
			return nearby[0];
		
		if (lift.getDirection() == Elevator.Direction.DOWN)
			return nearby[3];
		
		// Direction is IDLE...find any nearest passenger.
		Passenger best = null;
		int bestDist = 0;
		for (int i = 0; i < nearby.length; i++)
		{
			if (nearby[i] != null && best == null)
			{
				// found first nearby passenger
				best = nearby[i];
				bestDist = Math.abs(lift.getCurrentFloor() - best.getStartFloor());
			}
			else if (nearby[i] != null)
			{
				// found another nearby passenger; are they closer?
				int dist = Math.abs(lift.getCurrentFloor() - nearby[i].getStartFloor());
				if (dist < bestDist)
				{
					best = nearby[i];
					bestDist = dist;
				}
			}
		}
		
		return best;
	}
	
	/**
	 * Gets a queue of passengers waiting to board an elevator.  This queue
	 * contains passengers at the given elevator's current floor who 
	 * would like to travel in the same direction the elevator is traveling.
	 * In other words, this is the queue from which passengers would board
	 * the given elevator if there is room.
	 * <p>
	 * Note that this method prefers the upwards queue when the elevator
	 * direction is idle.
	 * 
	 * @param lift the elevator that is stopped at a floor
	 * @return the queue of passengers or null if the elevator's floor
	 * is invalid
	 */
	public Queue getFloorQueue(Elevator lift)
	{
		Queue queue = null;
		if (this.isFloorValid(lift.getCurrentFloor()))
		{
			// prefer up queue
			queue = this.upQueues[lift.getCurrentFloor()];
			
			// unless we're headed down or we're idling and the up queue is empty
			if (lift.getDirection() == Elevator.Direction.DOWN ||
				(lift.getDirection() == Elevator.Direction.IDLE &&
				queue.size() == 0))
			{
				queue = this.downQueues[lift.getCurrentFloor()];
			}
		}
		else
		{
			System.err.println("ElevatorBank.getFloorQueue got an elevator that found a new floor:" + lift);
		}
		
		return queue;
	}

	/**
	 * Returns a string representation of the elevator bank. This string
	 * contains the number of elevators and floors in the elevator bank as well
	 * as the count of passengers waiting in each queue on each floor. For
	 * example:
	 * <p>
	 * 
	 * <pre>
	 * Bank ( 3 els,   5 fls):
	 *    4:  0 u,  1 d
	 *    3:  1 u,  0 d
	 *    2:  0 u,  1 d
	 *    1:  1 u,  0 d
	 *    0:  0 u,  0 d
	 * </pre>
	 */
	public String toString()
	{
		StringBuilder str = new StringBuilder(
			String.format("Bank (%2d els, %3d fls):\n", this.getElevatorCount(), this.getFloorCount()));
		
		for (int i = this.getFloorCount()-1; i >= 0; i--)
		{
			str.append(String.format("\t%3d: %2d u, %2d d\n", i, this.upQueues[i].size(), this.downQueues[i].size()));
		}
		
		return str.toString();
	}
	
	/**
	 * Tests the elevator bank by creating a new bank, adding
	 * passengers to it, and seeing where an elevator would stop traveling
	 * up and down.
	 * 
	 * @param args not used
	 */
	public static void main(String args[])
	{
		// initialize an elevator bank
		ElevatorBank bank = new ElevatorBank(3, 5, 8);
		System.out.println("Init => " + bank);
		
		// fill the bank with some passengers
		for (int i = 0; i < bank.getFloorCount(); i++)
		{
			if (i % 2 == 0)
				bank.addPassenger(new Passenger(i, i - 1));
			else
				bank.addPassenger(new Passenger(i, i + 1));
		}
		System.out.println("Adds => " + bank);
		
		// get an elevator
		Elevator lift = bank.elevators[0];
		
		// see who the elevator would get going up
		lift.setDirection(Elevator.Direction.UP);
		for (int i = 0; i < bank.getFloorCount(); i++)
		{
			lift.setCurrentFloor(i);
			System.out.println(lift + " => " + bank.getNearestPassenger(lift));
			System.out.println("\tqueue => " + bank.getFloorQueue(lift));
		}
		
		// see who the elevator would get going down
		lift.setDirection(Elevator.Direction.DOWN);
		for (int i = bank.getFloorCount()-1; i >= 0; i--)
		{
			lift.setCurrentFloor(i);
			System.out.println(lift + " => " + bank.getNearestPassenger(lift));
			System.out.println("\tqueue => " + bank.getFloorQueue(lift));
		}
	}
}
