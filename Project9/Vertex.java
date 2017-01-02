/*
 * File: Vertex.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */



import java.util.*;
import java.awt.*;

/*
 * Vertex class extends cell
 */
public class Vertex extends Cell implements Comparable<Vertex>{
	
	private HashMap<Direction, Vertex> edges;
	private Direction direction;
	protected int cost;
	private boolean marked;
	private boolean visible;

	//constructor
	public Vertex(double x, double y){
		super(x,y);
		this.edges = new HashMap<Direction, Vertex>();
		this.direction = Direction.NORTH;
		this.cost = 0;
		this.marked = false;
		this.visible = false;
	}

	//direction method that switches to opposite direction
	public static Direction opposite(Direction d){
		if (d == Direction.NORTH){
			return Direction.SOUTH;
		}
		else if (d == Direction.SOUTH){
			return Direction.NORTH;
		}
		else if (d == Direction.WEST){
			return Direction.EAST;
		}
		else if (d == Direction.EAST){
			return Direction.WEST;
		}
		else{ 
			System.out.println("error");
			return d;
		}
	}

//visible accessor methods
	public boolean getVisible(){
		return this.visible;
	}

	public void setVisible(boolean visibility){
		this.visible = visibility;
	}

//cost accessor methods
	public int getCost(){
		return this.cost;
	}
	
	public void setCost(int i){
		this.cost = i;
	}

//marked accessor methods
	public boolean getMarked(){
		return this.marked;
	}

	public void setMarked(boolean marked){
		this.marked = marked;
	}

//vertex class specific methods
//======================================================================
	public void connect(Vertex other, Direction dir){
		this.edges.put(dir, other);
	}
	
	//Gets neighbor relating to the given direction
	public Vertex getNeighbor(Direction dir){
		return this.edges.get(dir);
	}
	
	//getNeighborsValue returns a collection of all the vertices connected to this vertex
	public Collection<Vertex> getNeighbors(){
		Collection c = this.edges.values();
		return c;
	}
	
	//compareTo class
	public int compareTo(Vertex other){
		if(this.cost < other.cost)
			return -1;
		else if(this.cost == other.cost)
			return 0;
		else if (this.cost > other.cost)
			return 1;
		return 0;	
	}	

	public String toString(){
		String string = "The Number of Neighbors is:" + this.edges.size();
		string += "The Cost is:  " + this.cost;
		string += "The Marked is:  " + this.marked;
		string += "Visibility:  " + this.visible;
		string += "Position: " + this.x + "," + this.y;
		return string;
	}
	
	
	//draw method given in the project instructions
	public void draw(Graphics g, int x0, int y0, int scale) {
		if (!this.visible) return;
		
		int xpos = x0 + (int) this.x*scale;
		int ypos = y0 + (int) this.y*scale;
		int border = 2;
		int half = scale / 2;
		int eighth = scale / 8;
		int sixteenth = scale / 16;
		
		// draw rectangle for the walls of the cave
		if (this.cost <= 2)
			// wumpus is nearby
			g.setColor(Color.red);
		else
			// wumpus is not nearby
			g.setColor(Color.white);
			
		g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
		
		// draw doorways as boxes
		g.setColor(Color.blue);
		if (this.edges.containsKey(Direction.NORTH))
			g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
		if (this.edges.containsKey(Direction.SOUTH))
			g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
				  eighth, eighth + sixteenth);
		if (this.edges.containsKey(Direction.WEST))
			g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
		if (this.edges.containsKey(Direction.EAST))
			g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
				  eighth + sixteenth, eighth);
	}
	
	//update state
    @Override
    public void updateState(Landscape scape) {
        throw new UnsupportedOperationException("Not supported");
    }

	//main test method
	public static void main(String args[]){

		Vertex v1 = new Vertex(20,20);
		Vertex v2 = new Vertex(50,50);

		v1.setCost(2);
		v2.setCost(6);
		v1.setMarked(true);
		v2.setMarked(false);

		System.out.println("v1:"+v1.getCost() + " " + v1.getMarked());
		System.out.println("v2:"+v2.getCost() + " " + v2.getMarked());
		System.out.println("Print opposite NORTH is " + opposite(Direction.NORTH));
	}
}










