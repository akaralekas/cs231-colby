/*
 * File: Landscape.java
 * Edited by: Anthony Karalekas
 * Help:
 * Date: Nov. 13, 2015
 * Assignment: Lab 7 and Project 7
 */


// Bruce Maxwell
// CS 231 Fall 2012
// Project 7
//


/*
 * File provided by CS231 Professors
 * Edited by Tony Karalekas
 */

import java.util.*;

public class Landscape {
		private double width;  // change to doubles
		private double height;
		private MyLinkedList<Cell> agents;

		public Landscape(int rows, int cols) {
				agents = new MyLinkedList<Cell>();
				height = (double)rows;
				width = (double)cols;
		}

		// add a constructor for a double size
		public Landscape(double rows, double cols) {
				agents = new MyLinkedList<Cell>();
				height = (double)rows;
				width = (double)cols;
		}

		public void reset() {
				// get rid of all of the agents
				agents.clear();
		}

		// modify to round
		public int getRows() {
				return (int)(height + 0.5);
		}

		// add method
		public double getHeight() {
				return height;
		}

		// modify to round
		public int getCols() {
				return (int)(width + 0.5);
		}

		// add method
		public double getWidth() {
				return width;
		}

		public void addAgent(Cell a) {
				agents.add(a);
		}

		public void removeAgent( Cell a ) {
				agents.remove(a);
		}

		public ArrayList<Cell> getAgents() {
				return agents.toArrayList();
		}

		public String toString() {
				ArrayList<String> s = new ArrayList<String>();

				for(int i=0;i<height;i++) {
						for(int j=0;j<width;j++) {
								s.add(" ");
						}
						s.add("\n");
				}

				for( Cell item: agents ) {
						int r = item.getRow();
						int c = item.getCol();

						if(r >= 0 && r < height && c >= 0 && c < width ) {
								int index = r * (this.getCols() + 1) + c;
								s.set( index, item.toString() );
						}
				}

				String sout = "";
				for( String a: s ) {
						sout += a;
				}

				return sout;
		}

		public void advance() {
				// put the agents in random oder
				ArrayList<Cell> items = agents.toShuffledList();

				// update the state of each agent
				for(Cell item: items ) {
						item.updateState( this );
				}
		}
		
		public static void main(String argv[]) {
				int rows = 30;
				int cols = 70;
				int N = 300;
				Landscape scape = new Landscape(rows, cols);
				Random gen = new Random();

				
		}

};
