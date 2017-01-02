/*
 * File: Graph.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */


import java.util.*;
import java.awt.*;

//graph class
public class Graph{
	private ArrayList<Vertex> vertices;

	//constructor
	public Graph(){
		 this.vertices = new ArrayList<Vertex>();
	}

	//returns the number of vertices
	public int vertexCount(){
		return vertices.size();
	}
	
    //Gets an list of the vertices in the graph
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }
    
    /**
     * Needed this method for addRooms method
     * Help from CP
     * Checks if a vertex with the given coordinates exists
     * Whether there is a vertex as the given coordinates
     */
    public boolean hasVertexAt(double x, double y){
        for(Vertex v: vertices){
            if(v.getX() == x && v.getY() == y){
                return true;
            }
        }
        
        return false;
    }
    
     /**
     * Needed this method for addRooms method
     * Help from CP
     * Gets the vertex at the given coordinates
     */
    public Vertex getVertexAt(double x, double y){
        for(Vertex v: vertices){
            if(v.getX() == x && v.getY() == y){
                return v;
            }
        }
        
        return null; //If used correctly, should not happen
    }
    
    //returns a random vertex
    public Vertex randomVertex(){
        int gen = new Random().nextInt(vertices.size());
        
        while(gen == 0){ //While it isn't the origin
            gen = new Random().nextInt(vertices.size());
        }
        
        return vertices.get(gen);
    }
    
	//add edges to the vertices
	public void addEdge(Vertex v1, Direction dir, Vertex v2){
	
		v1.connect(v2, dir);
		v2.connect(v1, Vertex.opposite(dir));
		if (!vertices.contains(v1)){
			vertices.add(v1);
		}
		if (!vertices.contains(v2)){
			vertices.add(v2);
		}
	}

	//shortestPath method
	public void shortestPath(Vertex v0){

		// Given: a graph G and starting vertex v0 in G
		// Initialize all vertices in G to be unmarked and have infinite cost	
		for (Vertex v: this.vertices){
			v.setMarked(false);
			v.setCost(Integer.MAX_VALUE);
		}

		// Create a priority queue, q, that orders vertices by lowest cost
		PriorityQueue<Vertex> vertQueue = new PriorityQueue();
		// Set the cost of v0 to 0 and add it to q
		v0.setCost(0);
		vertQueue.add(v0);
		
		// while q is not empty:
		while (vertQueue.size() != 0){
		// 	let v be the vertex in q with lowest cost
		// 	remove v from q
			Vertex v = vertQueue.poll();
		// 	mark v as visited		
			v.setMarked(true);
		// 	for each vertex w that neighbors v:
		// 		if w is not marked and v.cost + 1 < w.cost:
			for (Vertex w : v.getNeighbors()){
				if (w.getMarked()== false && v.getCost()+1 < w.getCost()){
		// 			w.cost = v.cost + 1
					w.setCost(v.getCost()+1);
		// 			add w to q
					vertQueue.add(w);
				}
			}
		}
		// Output: the cost of each vertex v in G is the shortest distance from v0 to v.
	}

		public static void main(String[] args){
			Vertex v1 = new Vertex(10,10);
			Vertex v2 = new Vertex(10,30);
			Vertex v3 = new Vertex(30,10);
			Graph g = new Graph();

			g.addEdge(v1, Direction.EAST, v2);
			g.addEdge(v1, Direction.SOUTH, v3);
			System.out.println("Vertices Count:" + g.vertexCount());		

			g.shortestPath(v3);

			System.out.println("Cost of v1:" + v1.getCost());
			System.out.println("Cost of v2:" + v2.getCost());


		}
}

