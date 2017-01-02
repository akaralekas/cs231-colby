/*
 * File: Simulation.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 18, 2015
 * Assignment: Project 4
 */
  
//imports
 import java.util.ArrayList;
 import java.util.Random;
 
/*
 * models a landscape -- contains a linked list of cells
 */
public class Simulation{
	
	public static void main(String[] args) throws InterruptedException{
		Landscape scape = new Landscape(dimensionW, dimensionH);
		Random gen = new Random();
		//command line args rules
		if( args.length != 6){
			System.out.println("You need 6 command line arguments!");
			System.out.println("The command line arguments should follow this format:");
			System.out.println("java Simulation iterations type sleep numCells landscapeWidth landscapeHeight");
			System.out.println("IMPORTANT:");
			System.out.println("Input 1 for type PreferenceCell, Input 2 for type ClumpingCell, Input 3 for type LifeCell");
			return;
		}
		//command line args
		int iterations = Integer.parseInt(args[0]);
		int type = Integer.parseInt(args[1]);
		int sleep = Integer.parseInt(args[2]);
		int numCells = Integer.parseInt(args[3]);
		int dimensionW = Integer.parseInt(args[4]);
		int dimensionH = Integer.parseInt(args[5]);
		
		//for the given number of cells make one of three types of cells
		for(int i = 0; i< numCells; i++){
			if(type == 1){
				//here we make an even amount of all three categories of PreferenceCell
				scape.addAgent( new PreferenceCell(gen.nextDouble()*scape.getCols(),
							gen.nextDouble()*scape.getRows(), 0));
							
				scape.addAgent( new PreferenceCell(gen.nextDouble()*scape.getCols(),
							gen.nextDouble()*scape.getRows(), 1));
							
				scape.addAgent( new PreferenceCell(gen.nextDouble()*scape.getCols(),
							gen.nextDouble()*scape.getRows(), 2));
				}
			//For clumping cells
			else if(type == 2){
				scape.addAgent( new ClumpingCell(gen.nextDouble()*scape.getCols(),
							gen.nextDouble()*scape.getRows()));
			}
			//For LifeCells
			else if(type == 3){
				scape.addAgent( new LifeCell(gen.nextDouble()*scape.getCols(),
							gen.nextDouble()*scape.getRows(), 3 , scape));
			}
		}
		
		//make a display
		LandscapeDisplay display = new LandscapeDisplay(scape,2);
		
		//received help from CP
		//for all the iterations make a .png file and save it
		//and then advance
		for(int i = 0; i<iterations; i++){
			String n = "";
			if(Integer.toString(i).length() < Integer.toString(iterations).length()){
				for(int j = 0; j < (Integer.toString(iterations).length() - Integer.toString(i).length()); j++){
					n += "0";
				}
			}
			n += i + ".png";
			display.saveImage(n);
			scape.advance();
			System.out.println(i);
			display.update();
			Thread.sleep( 100 );
		}
	
	
	
	}


}