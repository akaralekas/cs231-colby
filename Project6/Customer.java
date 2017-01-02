/*
 * File: Customer.java
 * Author: Anthony Karalekas
 * Help: CP
 * Date: Nov. 1, 2015
 * Assignment: Project 6
 */
  
//imports
 import java.awt.*;
 import java.util.*;
 
/*
 *Customer class extends cell
 *represents customer in checkout line
 */
public class Customer extends Cell{
	
	private int phase;
	private int timeUntilSelected;
	private int items;
	private int strategy;
	protected int timeWaited;
	
	//constructor
	//calls super on cell
	public Customer(double x, double y, int items, int strategy){
		super(x, y);
		this.items = items;
		this.strategy = strategy;
		this.phase = 0;
		this.timeUntilSelected = 0;
		this.timeWaited = 0;	
		
		//Strategy one will take 1 step to find random checkout-counter
		if( this.strategy == 1){
			this.timeUntilSelected = 1;
		}	
		//Strategy two will take 2 steps to chose between two random checkout-counter
		if( this.strategy == 2){
			this.timeUntilSelected = 2;	
		}
		//Strategy 3 will take 4 steps to chose between all checkout-counters
		if( this.strategy == 3){
			this.timeUntilSelected = 4;
		}		
	}
	//every time something is bought and checked-out, remove 1 item from total items
	public void buyItem(){
		this.items -= 1;
	}
	
	//returns number of items customer has left in shopping cart
	public int getNumItems(){
		return this.items;
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
	
	
	/**
	* Changes the State of the customer in the landscape
	* once the waiting time is over and time until selected is 0
	* run whatever strategy is given!
	* each strategy has different update rules
	* Removes itself when it has 0 items
	*/

	public void updateState( Landscape scape ) {
		ArrayList<CheckoutAgent> cashiers = scape.getCheckoutAgents();
		//if the customer is now up in the line to checkout
		if ( this.timeUntilSelected == 0){
			//if we are using the first strategy
			if (strategy == 1){
				//pick a random cash register to checkout in
				double register = randomInRange(0, cashiers.size());		
				cashiers.get((int)register).addCustomer(this);
				this.timeWaited += 1;
			}	
			//if we using the second strategy
			else if (strategy == 2){
				double register1 = randomInRange(0, cashiers.size());
				double register2 = randomInRange(0, cashiers.size());	
				if( cashiers.get((int)register1).line.size() > 
							cashiers.get((int)register2).line.size()){
					cashiers.get((int)register2).addCustomer(this);
				}
				else{
					cashiers.get((int)register1).addCustomer(this);
				}	
				this.timeWaited += 2;
			}	
			//if we are using the third strategy
			//looks at every register and then chooses the smallest line
			else if (strategy == 3){
				if(cashiers.size() == 0){
					return;
				}	
				int index = 0;
				for ( int i = 0; i < cashiers.size(); i++){
					//if the i line is shorter than the index line-- make i the new index
					if(cashiers.get(i).line.size() < cashiers.get(index).line.size()){
						index = i;
					}
				}			
				cashiers.get(index).addCustomer(this);
				this.timeWaited += 4;
			}	
		}	
		this.timeUntilSelected --;
				
	}

	//draw method using graphics
	// now using drawImage for extension 1
	//received help from cp!
    public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale) - 5;
		Image img1 = Toolkit.getDefaultToolkit().getImage("Cart1.png");
		switch(this.strategy){
			//Extension 2! Different carts for different strategy!
			//Help from CP Majgaard with with SWITCH implementation
			case 1: img1 = Toolkit.getDefaultToolkit().getImage("Cart1.png");
					break;
			case 2: img1 = Toolkit.getDefaultToolkit().getImage("Cart2.png");
					break;
			case 3: img1 = Toolkit.getDefaultToolkit().getImage("Cart3.png");
					break;
			default: img1 = Toolkit.getDefaultToolkit().getImage("Cart1.png");
					 break;
		} 
		
		g.drawImage(img1, x,  y-30, null);
		
		return;
	}  
    
    public static void main(String[] args) throws InterruptedException{
		Landscape scape = new Landscape(225, 400);
		Random generator = new Random();
		LandscapeDisplay display = new LandscapeDisplay(scape, 4);
		for(int i=0;i<50;i++) {
		    double cellX = generator.nextDouble()*scape.getWidth();
		    double cellY = generator.nextDouble()*scape.getWidth();
			scape.addAgent(new Customer(cellX, cellY, 10, 1));
		}
	}	
}    