/*
 * File: Hunter.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */


//imports
import java.util.*;
import java.awt.*;

/*
 * Hunter class extends cell
 */
public class Hunter extends Cell{
	public Vertex location;
	
	//constructor for super
	public Hunter(double x, double y){
		super(x, y);
	}
	
	//constructor
	public Hunter(Vertex vert){
        this(vert.getX(), vert.getY());
        this.location = vert;
    }
    
 	//get the current location of hunter
    public Vertex getLocation(){
        return location;
    }

    public void updateState(Landscape scape) {
       throw new UnsupportedOperationException("Not supported.");
    }
	
	//sets the current vertex location of the hunter
    public void setLocation(Vertex current){
        location = current;
        this.setPosition(current.getX(), current.getY());
    }
    
    //draw method uses image .png instead of colors
    @Override
    public void draw(Graphics g, int x, int y, int scale) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("Neo.png");
        g.drawImage(img1,this.getCol()*scale +5, this.getRow()*scale +5, null);
    }
    
	//draw method draws the wumpus as an image.png
    public void enableShooter(Graphics g){
    	Image img1 = Toolkit.getDefaultToolkit().getImage("Gun.png");
        g.drawImage(img1, 20, 0, null);
    
    }


}