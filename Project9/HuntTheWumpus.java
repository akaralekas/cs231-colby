/*
 * File: HuntTheWumpus.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */

//imports
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * HuntTheWumpus class 
 */
public class HuntTheWumpus {
    private Landscape scape;
    private LandscapeDisplay display;
    private Graph graph;
    private Hunter hunter;
    private Wumpus wumpus;
    private int rooms;
	// controls whether the simulation is playing or exiting
	private enum PlayState { PLAY, STOP }
	private PlayState state;
    //iteration field
    private int iteration;
    
    
	//constructor
	//calls generateMap and setupUI
	//build the graph, insert the vertices, Hunter, and Wumpus into the Landscape
    public HuntTheWumpus(int rooms){
        this.rooms = rooms;
        //calls generateGame method defined below
        generateGame(rooms);
        //calls setupUI method defined below
        setupUI();
        display.update();
        state = PlayState.PLAY;
        
    }
    
	//configures the user interface for the game
    public void setupUI(){
        Control control = new Control();
        this.display.addKeyListener(control);
        this.display.setFocusable(true);
        this.display.requestFocus();
    }
    
	//Generates a map of interconnected rooms (Vertex) for the game
	//build the graph, insert the vertices, Hunter, and Wumpus into the Landscape
    public void generateGame(int rooms){
        scape = new Landscape(20,25);
        graph = new Graph();
        display = new LandscapeDisplay(scape, 41);
        //start at center of landscape
        Vertex startLocation = new Vertex(12,9);
        //create hunter and place on the vertex location
        hunter = new Hunter(startLocation);
        //set the hunter to be visible. the wumpus will not be visible
        startLocation.setVisible(true);
        //add the hunter to the landscape
        scape.addAgent(hunter);
        
        //this method recursively adds rooms to the scape
        this.addRooms(startLocation, rooms);
        for(Vertex v: graph.getVertices()){
            scape.addAgent(v);
        }
        //add a wumpus to a random square on graphy
        wumpus = new Wumpus(graph.randomVertex());
        //wumpus is invisible
        wumpus.setVisible(false);
        scape.addAgent(wumpus); 
        //set cost
        graph.shortestPath(wumpus.getHome());
        display.update();
    }
    
    /**
     * Received help from CP Majgaard with this method
     * this method will populate the landscape with rooms
     * to do this, each direction has specific x and y values
     * then those x and y values are used to check the next room
     * if there is already a room, do not add new one but connect it
     * if not, add a new one and connect it
     */
    public void addRooms(Vertex v, int rooms){
        int y = 0;
        int x = 0;
        //get a random direction of the next room
        Direction dir = Direction.getRandomDirection(); 
        
        //each direction has a corresponding x and y value 
        //that corresponds what direction to move on the graph
        if (dir == Direction.NORTH){
			x = 0;
			y = -1;
		}
		else if (dir == Direction.SOUTH){
			x = 0;
			y = 1;
		}
		else if (dir == Direction.WEST){
			x = -1;
			y = 0;
		}
		else if (dir == Direction.EAST){
			x = 1;
			y = 0;
		}
        
        //if there is not enough rooms in the graph
        if(graph.vertexCount() < rooms){
            //and if there is already a room at the given coordinates
            if(graph.hasVertexAt(v.getX() + x , v.getY() + y)){
                Vertex n = graph.getVertexAt(v.getX() + x , v.getY() + y);
                 //Connect
                graph.addEdge(v, dir, n);
                //call this method recursively 
                this.addRooms(n, rooms);
            }
            //if there isn't already a room and within bounds of window
            else if((v.getX() + x > 0) && (v.getX() + x) < 25 && (v.getY() + y) > 0 && (v.getY() + y) < 20){ 
                Vertex n = new Vertex(v.getX() + x , v.getY() + y);
                graph.addEdge(v, dir, n);
                this.addRooms(n, rooms);
            }  
            //if trying to generate new vertex outside, keep trying until generating inside
            else this.addRooms(v, rooms);
            
        }
    }
	
	//iteration method
	public void iterate(){
		this.iteration++;

		if (this.state == PlayState.PLAY){
		// update the landscape, display
			this.scape.advance();
			this.display.repaint();
		}
	}	

    //this method controls the simulation in response to key presses
    private class Control extends KeyAdapter implements KeyListener{
    	//this field(shootState) was made to assist with Firing the arrow
    	//it is a boolean value... if true, then fire the arrow
    	//if false then simply move the 
    	private boolean shootState = false;
		public void keyTyped(KeyEvent e) { 
			
            //if we are playing
            if(state == PlayState.PLAY){
                    
				/*
				 *WASD movement controls
				 */
				 
				//W moves the hunter North
				if (("" + e.getKeyChar()).equalsIgnoreCase("w")){
					Vertex v = hunter.getLocation().getNeighbor(Direction.NORTH);
					//if shootState is false then simply move the hunter 
					if( shootState == false){
						if(v != null){
							//move the hunter north one a tile
							hunter.setLocation(v);
							//set the visibility of the tile to true because its the hunter
							v.setVisible(true);
							display.update();
							//if hunter moves into the same vertex as the wumpus
							if(v == wumpus.getHome()){
								//winAction is false and hunter dies!
								endGame(false);
							}
						}
					}
					//else if shootState is true then shoot an arrow  
					else if( shootState == true){
						if(v == wumpus.getHome()){
                            //we win and run end game sequence
                            endGame(true);
                        }
                        else{
                        	//we lose and run end game sequence 
                        	endGame(false);
                        }
					}
				}
				
				//A moves the hunter West			
				else if (("" + e.getKeyChar()).equalsIgnoreCase("a")){
					Vertex v = hunter.getLocation().getNeighbor(Direction.WEST);
					if( shootState == false){
						if(v != null){
							hunter.setLocation(v);
							v.setVisible(true);
							display.update();
							if(v == wumpus.getHome()){
								endGame(false);
							}
						}
					}
					else if( shootState == true){
						if(v == wumpus.getHome()){
                            endGame(true); 
                        }
                        else{
                        	endGame(false);
                        }
					}
				}
				
				//S moves the hunter South		
				else if (("" + e.getKeyChar()).equalsIgnoreCase("s")){
					Vertex v = hunter.getLocation().getNeighbor(Direction.SOUTH);
					if( shootState == false){
						if(v != null){
							hunter.setLocation(v);
							v.setVisible(true);
							display.update();
							if(v == wumpus.getHome()){
								endGame(false);
							}
						}
					}
					else if( shootState == true){
						if(v == wumpus.getHome()){
                            endGame(true);
                        }
                        else{
                        	endGame(false);
                        }
					}
				}
				
			 	//D moves the hunter East	
				else if (("" + e.getKeyChar()).equalsIgnoreCase("d")){
					Vertex v = hunter.getLocation().getNeighbor(Direction.EAST);
					if( shootState == false){
						if(v != null){
							hunter.setLocation(v);
							v.setVisible(true);
							display.update();
							if(v == wumpus.getHome()){
								endGame(false);
							}
						}
					}
					else if( shootState == true){
						if(v == wumpus.getHome()){
                            endGame(true);
                        }
                        else{
                        	endGame(false);
                        }
					}
				}
				
				/*
				 *Firing controls
				 *Space bar sets shootState to true and fires an arrow
				 * this allows me to toggle the arrow
				 * if i press space, then the arrow is drawn waiting for WASD direction
				 * then pressing space again will un-draw the arrow
				 */
				if(e.getKeyChar() == KeyEvent.VK_SPACE){
					this.shootState = !this.shootState;
					display.toggleShooter();
					display.update();
				}
                        
            }	
		}
    }
  
  	//ends the game and restarts
  	//received help from CP 
  	//triggers the correct response depending on who wins
    public void endGame(boolean win){
        //if hunter wins!
        if(win == true){
            //stop keyboard input
            state = PlayState.STOP;
            //make the wumpus visible
            wumpus.getHome().setVisible(true);
            wumpus.setVisible(true);
            
            //Generate an option panel with an correct message
            final ImageIcon icon = new ImageIcon("Neo.png");
            //JOptionPane pauses code until button is pressed
            JOptionPane.showMessageDialog(null, "Neo killed Agent Smith.", "He truly is the ONE!", JOptionPane.INFORMATION_MESSAGE, icon);
			//throw out game and kill window           
            display.dispose(); 
            //create totally new game and graph
            this.generateGame(this.rooms); 
            this.setupUI();
            //play again!
            state = PlayState.PLAY;
            display.update();

        }
        //if the wumpus prevails and the hunter loses...
        else{
            //stop keyboard input
            state = PlayState.STOP;
            //Generate an option panel with an correct message
            final ImageIcon icon = new ImageIcon("AgentSmith.png");
            JOptionPane.showMessageDialog(null,"You die in the Matrix, you die in real life!" , "Agent Smith has killed Neo.", JOptionPane.INFORMATION_MESSAGE, icon);
            //throw out game and kill window 
            display.dispose();
            //create totally new game and graph
            this.generateGame(this.rooms);
            this.setupUI();
            //play again!
            state = PlayState.PLAY;
            display.update();
            
            
        }
    }
    
    public static void main(String[] args){
       HuntTheWumpus HTW = new HuntTheWumpus(100);
    }
}