/*
 * File: BirdID.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Steven Parrott
 * Date: Nov. 22, 2015
 * Assignment: Project 8
 */
 
//imports
import java.awt.Graphics;
 
public class BirdID {
 	//root and tracker node
	private BTNode root;
    private BTNode current;
 	
 	//constructor (interaction friendly) -- help from CP
	public BirdID(String instruction, String aChoice, String bChoice){
    	this.root = new BTNode(instruction);
        root.setA(new BTNode(aChoice));
        root.setB(new BTNode(bChoice));
        current = root;
	}
     
    //constructor 
    public BirdID(BTNode r){     
    	this.root = r;
    	current = root;
     }
    
	//restarts the ID
    public void restartID(){
        current = root;
    } 
     
   //return true if the current node is the leaf
   //the leaf is the end of the tree
    public boolean isLeaf(){
        return !this.current.isInstruction();
    }    
     
	//return the instructions of the current node
    public String getCurrent(){
        return this.current.getInstruction();
    }
    
    //returns the current aChoice button
    public String getCurrentA(){
    	return this.current.getAChoice();
    }
    
    //returns the current bChoice button
    public String getCurrentB(){
    	return this.current.getBChoice();
    }
    
 	
 	//returns the instructions of the aChoice button
 	//if you choose aChoice, this will navigate to the next set of instructions
    public String getA(){
        
        String message = this.current.getA().getInstruction();
       
        //if the current aChoice returns null instructions then you have reached the leaf
        if(!this.current.getA().isInstruction()){
            current = current.getA();
            //return the list of birds you might have seen
            //this is the message of the final BTNode
            return "<html>You saw one of these birds: " + message + ".</html>";
        }
        current = current.getA();
        return message;
    }
 	
 	//returns the instructions of the bChoice button
 	//if you choose aChoice, this will navigate to the next set of instructions
    public String getB(){
        
        String message = this.current.getB().getInstruction();
       
        //if the current bChoice returns null instructions then you have reached the leaf
        if(!this.current.getB().isInstruction()){
            current = current.getB();
            //return the list of birds you might have seen
            //this is the message of the final BTNode
            return "<html>You saw one of these birds: " + message + ".</html>";
        }
        current = current.getB();
        return message;
    }
      
 }
 