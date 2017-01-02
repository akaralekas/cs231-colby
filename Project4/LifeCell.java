/*
 * File: LifeCell.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Oct. 18, 2015
 * Assignment: Project 4
 */
  

// imports
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

/*
 * LifeCell that will demonstrate a new type of visual
 * New rules in updateState
 */
public class LifeCell extends Cell{
	int lives;
	Landscape scape;
	
	//constructor
	public LifeCell( double x, double y, int lives, Landscape scape){
		super(x, y);
		this.lives = lives;
		this.scape = scape;
	}

	//NEW Project 4
	//UpdateState method with new rules to demonstrate life cell
	public void updateState(ArrayList<Cell> neighbors) {
		for( Cell c : neighbors){
			int numNeigh = scape.getNeighbors(c).size();
			
			// give it 3 lives
			if( numNeigh >= 3){
				this.lives = 3;
			}
			
			//add a new cell to the agent nearby
			if( numNeigh  == 5 || numNeigh  == 6){
				LifeCell n = new LifeCell(this.getX(), this.getY(), 3, scape);
				scape.addAgent(n);
			}
			
			//subtract a live or remove the cell
			if( numNeigh  < 3){
				this.lives -= 1;
				if(this.lives == 0){
					scape.removeAgent(this);
				}
			}
			
			//move with a 10%
			if( numNeigh  >=3 && numNeigh <=5){
				Random rand = new Random();
				int percentage = rand.nextInt(100);
					if (percentage == 10) {
						this.x += randomInRange(-5, 5);
						this.y += randomInRange(-5, 5);	
						if(this.x < 0 || this.x > scape.getCols() 
							|| this.y < 0 || this.y > scape.getRows()){
							scape.removeAgent(c);						
						}		
					}
			}
			
			//move the cell!
			else if( numNeigh <3 || numNeigh >5){
				this.x += randomInRange(-5, 5);
				this.y += randomInRange(-5, 5);	
				if(this.x < 0 || this.x > scape.getCols() 
							|| this.y < 0 || this.y > scape.getRows()){
							scape.removeAgent(c);						
				}	
			}
		}
	}

    //same isNeighbor method!
     public boolean isNeighbor(Cell cell){
    	if((Math.pow(x - cell.getX(), 2) + (Math.pow(y - cell.getY(), 2)) <= 2* 2)){
			return true;
			
		}
		else{

			return false;
		}
    }
    
    //same draw method from ClumpingCell
    public void draw(Graphics g, int x0, int y0, int scale){
    	int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		
		g.setColor(new Color(0.2f, 0.6f, 0.3f));
		g.fillRect(x, y, scale, scale);
		
		return;
    }
    
    
    
    //main test function for LifeCell
	public static void main(String[] args) {
		Landscape landscape = new Landscape(400, 225);
		Random gen = new Random();
		
		int iterations = Integer.parseInt(args[0]);
		
		for(int i = 0; i< 1000; i++){
				landscape.addAgent( new LifeCell(gen.nextDouble()*landscape.getCols(),
							gen.nextDouble()*landscape.getRows(), 3 , landscape));
		}
		
		LandscapeDisplay display = new LandscapeDisplay(landscape,2);
		
		for(int i = 0; i<iterations; i++){
			String n = "";
			if(Integer.toString(i).length() < Integer.toString(iterations).length()){
				for(int j = 0; j < (Integer.toString(iterations).length() - Integer.toString(i).length()); j++){
					n += "0";
				}
			}
			n += i + ".png";
			display.saveImage(n);
			landscape.advance();
			System.out.println(i);
			display.update();
		}
	
	}
}