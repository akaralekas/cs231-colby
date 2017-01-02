/*
 * File: CheckoutAgent.java
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
 * checkout agent extends cell
 */
public class CheckoutAgent extends Cell{
	
	protected MyQueue<Customer> line;
	protected ArrayList<Integer> waitTimes;
	
	//constructor
	//calls super on cell	
	public CheckoutAgent(double x, double y){
		super(x, y);
		this.line = new MyQueue<Customer>(2000);
		this.waitTimes = new ArrayList<Integer>();
	}
	
	
	public void addCustomer(Customer customer){
		this.line.add(customer);
	}
	
	
	/*
	 * updateState of the customers in checkout line
	 * Decreases items of customer until done checking out
	 * Moves line forward and removes done customers
	 */
	public void updateState( Landscape scape ) {
		//if the line has no customer, exit program
		if( this.line.peek() == null){
			return;
		}
		//else, update the Items
		else{
			if( this.line.peek().getNumItems() > 0){
				this.line.peek().buyItem();
			}
			//if no more items left, total time is added and customer leaves the checkout!
			else{
				this.waitTimes.add(line.peek().timeWaited);
				scape.removeAgent(line.peek());
				this.line.remove();
				//move the customers up!
				int newY = 5;
				for(Customer c: this.line){
					c.x = this.getX();
					c.y = this.getY() - newY;
					newY += 7;
				}
			}
		}
		
		//add time step to waiting customers
		for (Customer shopper: this.line){
			shopper.timeWaited += 1;
		}	
	}				
	
	//draw method using graphics
	//received help from cp!
    public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		Image img1 = Toolkit.getDefaultToolkit().getImage("Register.png");
		g.drawImage(img1, x-8,  y-8, null);
		
		return;
	}
	//main test code
  	public static void main(String[] args) throws InterruptedException{
		Landscape scape = new Landscape(225, 400);
		LandscapeDisplay display = new LandscapeDisplay(scape, 4);
		Random gen = new Random();
		//add checkout agents
		for(int i=0;i<22;i++) {
		    double cellX = gen.nextDouble()*scape.getWidth();
		    double cellY = gen.nextDouble()*scape.getHeight();
			scape.addAgent(new CheckoutAgent(cellX, cellY));
		}
	}
		
				

}    