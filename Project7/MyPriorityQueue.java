/*
 * File: MyPriorityQueue.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Nov. 9, 2015
 * Assignment: Lab 7 and Project 7
 */
  
/*
 *
 */
 
import java.util.*;
import java.util.Comparator;

 
/*
 * Creates a priority queue that holds the highest priority at the head
 */  
public class MyPriorityQueue<T> implements Iterable<T>{
	private Node head;
	private int count;
	private int maxsize;
	private Comparator<T> comp;
	
	
	//constructor for PriorityQueue
	public MyPriorityQueue(int maxsize, Comparator<T> comp){
		this.maxsize = maxsize;
		this.comp = comp;
		this.head = head;
		this.count = 0;
	}
	
	//comparator 
	public class IntComparator implements Comparator<Integer> {
    	public int compare(Integer o1, Integer o2) {
        	return o1 - o2;
    	}
	}
	
	
	
	//boolean add method--worked with CS231 student Steve Parrott
	//adds a node to priority queue in the right location
	//uses compare method from Comparator class
	public boolean add(T item){
		Node current = this.head;
		
		if (this.getSize() < this.maxsize){
				if (this.head == null) {
				   this.head = new Node(null, item);
				   return true;
				}
				if (this.comp.compare(this.head.data, item ) < 0) {
				   this.head = new Node(current, item);
				   return true;
				}
				while (current.next != null) {
				   if (this.comp.compare(current.next.data, item) < 0) {
						   current.setNext(new Node(current.next, item));
						   return true;
				   }
				   current = current.next;
				}
				current.setNext(new Node(null, item));
				return true;
				}
		else{
			return false;
		}
	}
	
	
	//clears the queue
	public void clear(){
		this.head = null;
		this.count = 0;
	}
	
	//gets the next node in the queue
	public T getNext(){
		if( this.head == null){
			return null;
		}	
		return this.head.data;	
	}
	
	
	//returns the size of the queue
	public int getSize(){
		int count = 0;
		for(T x : this){
			count ++;
		}
		return count;
	}
	
	//sets the size the count field of the queue
	public void setSize(int i){
		this.count = i;
	}
	
	//returns true if the head is nul AKA the queue is empty
	public boolean isEmpty(){
		return this.head == null;
	}
	
	//returns true if the size of the queue has reached its maxsize
	public boolean isFull(){
		return this.getSize() == this.maxsize;
	}
	
	//removes an element from the queue
	public T remove(){
		if(this.head == null){
			System.out.println("Queue is empty");
			return null;
		}
		else{
			Node old = this.head;
			this.head = this.head.next;
			return old.data;
		}
	}
	
	//returns a organized string of the items in the priorityQueue
	public String toString(){
		String string = "";
		for (T item: this){
			string += item + ", ";
		}
		return string;	
	}
	
	
	//converts queue to an ArrayList
	public ArrayList<T> toArrayList(){
		ArrayList<T> arrlist = new ArrayList<T>();
		for(T x : this){
			arrlist.add(x);
		}
		return arrlist;
	}
	
	
	//received help from Brendan Doyle CS231 Classmate
	public void setComparator( Comparator<T> comparator){
		this.comp = comparator;
		ArrayList<T> temp = new ArrayList<T>();
		this.clear();
		for( T t : temp){
			this.add(t);
		}
		
	}
	

//=========================================================
/*
 * These are methods from my LinkedList class that I do not need
 * Rather than delete them, I just placed them down here.
 *
 */

	//Homework
	//returns true if one of more elements in the linked list have the value of String s
	//returns false for any other condition
	public boolean contains(T s){
		int total = 0;
		//loops through all nodes and uses a counter to record all with string s as data
		Node index = this.head;
		//loops through all the nodes until it reaches the indexed node
		for(int x=0; x < this.count; x++){
			if(index.data == s){
				total ++;
			}
			index = index.next;
			
		}
		return total > 0;
	}
	
	public ArrayList<T> toShuffledList(){
		ArrayList<T> shuffle = new ArrayList<T>();
		ArrayList<T> source = this.toArrayList();
		// assistance from CP and stack-overflow 
		// created a new deck and randomly added that deck to old emptied deck
		Random generator = new Random(System.currentTimeMillis());
		int z = source.size();
		for( int i = 0; i < z; i++){
			shuffle.add(source.remove(generator.nextInt(source.size())));
		}
		return shuffle;
	}
	
//====================================================================	
//====================================================================
//====================================================================

/*
 * Now Iterator and Node classes!
 */
 	
 	//Iterator Class
	private class MyPriorityQueueIterator implements Iterator<T>{
		private Node nextNode;
	
		public MyPriorityQueueIterator(Node head){
			this.nextNode = head;
		}
		
		public boolean hasNext(){
			return this.nextNode != null;
		}
	
		public T next(){
			if (nextNode == null){
				return null;
			}	
			T result = nextNode.data;
			nextNode = nextNode.next;
			return result;
		}
		
		public void remove(){}
	
	}


	//iterator method
	public Iterator<T> iterator(){
		return new MyPriorityQueueIterator(this.head);
	}
	
	
	
	//Node class
	private class Node{
		private Node next;
		private T data;

		public Node(Node next, T data){
			this.data = data;
			this.next = next;
		}
	
		public T getData(){
			return this.data;
		}
	
		public void setNext(Node n){
			this.next = n;
		}
	
		public Node getNext(){
			return this.next;
		}

	}
	
	//Comparator class that tests Integers
	public static class Comp<Integer extends Comparable<Integer>> implements Comparator<Integer> {
			public int compare( Integer a, Integer b ) {
					return a.compareTo( b );
			}
	}
	
	
	//main test function
	public static void main(String[] args) {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<Integer>(30, new Comp<Integer>());
		
		queue.add(6);
		queue.add(10);
		queue.add(15);
		queue.add(1);
		
		System.out.printf("\nAfter setup %d\n",queue.getSize());
		for (Integer item: queue){
			System.out.printf("\nThing: %d",item);
		}
	}
}



