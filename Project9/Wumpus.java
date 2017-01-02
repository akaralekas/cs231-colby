/*
 * File: Wumpus.java
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
 * Wumpus class extends cell
 * the wumpus doe not move
 * only visible if it eats or get eaten by hunter
 */
public class Wumpus extends Cell{
    private Vertex home;
    private boolean visibility;
    private boolean victory;
    
    //constructor for super
    public Wumpus(double x, double y) {
        super(x, y);
    }
    
    //constructor
    public Wumpus(Vertex vert){
        this(vert.getX(), vert.getY());
        this.home = vert;
    }
    
	//get the current home location of the wumpus
    public Vertex getHome(){
        return home;
    }
    
	//return whether or not the wumpus is visible
	//true == is visible and false == not visible
    public boolean isVisible(){
        return this.visibility;
    }
    
    //Sets the visibility of the Wumpus
    public void setVisible(boolean visible){
        this.visibility = visible;
    }

    //updateState
    @Override
    public void updateState(Landscape scape) {
        throw new UnsupportedOperationException("Not supported."); 
    }

	//draw method draws the wumpus as an image.png
    public void draw(Graphics g, int x, int y, int scale) {
        if(this.visibility){
            Image img1 = Toolkit.getDefaultToolkit().getImage("AgentSmith.png");
            g.drawImage(img1,this.getCol() * scale, this.getRow() * scale, null);
        }   
    }
    
}