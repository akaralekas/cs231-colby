/*
 * File: CheckoutSimulation.java
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
 * simulation of checkout landscape
 */
public class CheckoutSimulation{
	
	//Worked with Steven Parrot in my CS 231 Class
	//Stack Overflow and Steve Parrot
	//basically this method is used to generate random number within a range
	public static double randomInRange(double min, double max) {
        Random r = new Random();
        double range = max - min;
        double scaled = r.nextDouble() * range;
        double shifted = scaled + min;
        return shifted;
    }
    
	public static void main(String[] args) throws InterruptedException{
		Landscape scape = new Landscape(400, 225);
		int iterations = Integer.parseInt(args[0]);
		
        //add 1 spawner at a random location
		double spawnCellX = randomInRange(0,1)*scape.getWidth();
	    double spawnCellY = randomInRange(0,1)*scape.getHeight();
		scape.addAgent(new Spawner(spawnCellX, spawnCellY));
		
		//add checkout agents
		for(int i=0;i<22;i++) {
		    double cellX = i*20+30;
		    double cellY = 200;
			scape.addAgent(new CheckoutAgent(cellX, cellY));
		}

		LandscapeDisplay display = new LandscapeDisplay(scape, 2);
		
		
        //run the simulation 200 times.
		//received help from CP
		//for all the iterations make a .png file and save it
		//and then advance
		for(int i=0;i<iterations;i++) {
			String n = "";
			if(Integer.toString(i).length() < Integer.toString(iterations).length()){
				for(int j = 0; j < (Integer.toString(iterations).length() - Integer.toString(i).length()); j++){
					n += "0";
				}
			}	
			n += i + ".png";
			scape.advance();
			display.update();
			Thread.sleep( 100 );
			System.out.println("Iteration" + i);
			display.saveImage(n);
		}	
		


	

//Task 7-- Received Help from Roommate Aaron Liu and worked with CP and Steven Parrott
//Calculates the mean and the standard devations and prints it
	ArrayList<CheckoutAgent> cashiers = scape.getCheckoutAgents();
	ArrayList<Integer> waitTimes = new ArrayList<Integer>();
	for(CheckoutAgent item: cashiers){
		for(Integer time: item.waitTimes){
			waitTimes.add(time);
		}
	}
		
	int mean = 0;
	for( Integer time: waitTimes){
		mean += time;
	}
	mean = mean/waitTimes.size();
	
	ArrayList<Integer> deviations = new ArrayList<Integer>();
	int deviation = 0;
	for(Integer time: waitTimes){
		deviation = (int)Math.pow((time-mean), 2);
		deviations.add(deviation);
	}
	int total = 0;
	for(Integer	number: deviations){
		total += number;
	}
	total = total/waitTimes.size();
	deviation = (int)Math.sqrt(total);	
		
	System.out.println(waitTimes);		
	System.out.println("Mean: " + mean);
	System.out.println("Std:  " + deviation);
	}	
}
	