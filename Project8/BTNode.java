/*
* File: BTNode.java
* Author: Anthony Karalekas
* Help: CP Majgaard
* Worked with: Steven Parrott
* Date: Nov. 22, 2015
* Assignment: Project 8
*/


//BTNode class with children nodes, choices, and an instruction/question
public class BTNode{
	BTNode A, B;
	String aChoice, bChoice;
	String instruction;
	
	 //constructor (interaction-friendly) -- help from CP
	 public BTNode(String instruction){
	 	this.A = null;
	 	this.B = null;
	 	this.aChoice = null;
	 	this.bChoice = null;
	 	this.instruction = instruction;
	 }
	
	//constructor 
	public BTNode(String r, String aC, String bC, BTNode a, BTNode b){
		this.instruction = r;
   		this.A = a;
   		this.B = b;
   		this.aChoice = aC;
	 	this.bChoice = bC;
	}
	
	//set the A child node
	public void setA(BTNode x){
	   	this.A = x;
	}

	
	//sets the B child node
	public void setB(BTNode x){
	   	this.B = x;
	}
	
	//set the A choice string
	public void setAChoice(String x){
	   	this.aChoice = x;
	}

	
	//sets the B choice string
	public void setBChoice(String x){
	   	this.bChoice = x;
	}
	
	//retrieves the left or A child node
	public BTNode getA(){
	   	return A;
	}
	
	
	//retrieves the right or A child node
	public BTNode getB(){
	   	return B;
	}
	
	//set the A choice string
	public String getAChoice(){
	   	return aChoice;
	}

	
	//sets the B choice string
	public String getBChoice(){
	   	return bChoice;
	}
	
	//sets instructions to the node
	public void setInstruction(String instruction){
	   	this.instruction = instruction;
	}
	
	//gets the instructions from the node
	public String getInstruction(){
	   	return this.instruction;
	}

	//return true if this is the instruction
	public boolean isInstruction(){
	   if (this.A == null && this.B == null)
		   return false;
	   else
		   return true;
	}
}