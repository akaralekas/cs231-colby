/*
 * File: LinkedList.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Sept. 28, 2015
 * Assignment: Lab 3
 */
  
/*
 *
 */
 
import java.util.*;

 
public class LinkedList<T> implements Iterable<T>{
	private Node head;
	private int numNodes;
	private Node tail;
	
	public LinkedList(){
		this.head = null;
		this.numNodes = 0;
	}
	
	public void clear(){
		this.head = null;
		this.numNodes = 0;
	}
	
	public int size(){
		int numNodes = 0;
		Node current = this.head;
		while(current != null){
			numNodes ++;
			current = current.next;
		}
		return numNodes;
	}
	
	public void add(T item){
		Node newNode = new Node(this.head, item);
		newNode.setNext(this.head);
		this.head = newNode;
	}
	
	//Project 4--help from Jay Moore
	public boolean remove(T item){
		if(this.head.data == item){
			head = head.next;
			return true;
		}
		else{
			Node index = this.head;
			//loops through all the nodes until it reaches the indexed node
			while(index.next != null){
				if(index.next.data == item){	
					index.next = index.next.next;
					return true;
				}
			index = index.next;
			}
			return false;
		}
	}
	
	//Homework
	//get method that returns the string data within a node of specific index
	public T get(int i){
		Node index = this.head;
		//loops through all the nodes until it reaches the indexed node
		for(int x=0; x < i; x++){
			index = index.next;
		}
		//return the data of the indexed list
		return index.data;
	}
	
	//Homework
	//returns true if one of more elements in the linked list have the value of String s
	//returns false for any other condition
	public boolean contains(T s){
		int total = 0;
		//loops through all nodes and uses a counter to record all with string s as data
		Node index = this.head;
		//loops through all the nodes until it reaches the indexed node
		for(int x=0; x < this.size(); x++){
			if(index.data == s){
				total ++;
			}
			index = index.next;
			
		}
		return total > 0;
	}
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> arrlist = new ArrayList<T>();
		Node index = this.head;
		//loops through all the nodes until it reaches the indexed node
		for(int x=0; x < this.size(); x++){
			arrlist.add(index.data);
			index = index.next;
			
		}
		return arrlist;
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
	
	
	
	
	public Iterator<T> iterator(){
		return new LLIterator(this.head);
	}
	
	private class LLIterator implements Iterator<T>{
		private Node nextNode;
	
		public LLIterator(Node head){
			this.nextNode = head;
		}
		
		public boolean hasNext(){
			return this.nextNode != null;
		}
	
		public T next(){
		T result = nextNode.data;
		nextNode = nextNode.next;
		return result;
		}
		
		public void remove(){}
	
	}



	private class Node{
		private Node next;
		private T data;
		private Node previous;

		public Node(Node next, T data){
			this.data = data;
			this.next = next;
		}
	
		public T getThing(){
			return this.data;
		}
	
		public void setNext(Node n){
			this.next = n;
		}
	
		public Node getNext(){
			return this.next;
		}

	}
	

public static void main(String[] args) {
		
		LinkedList<Integer> llist = new LinkedList<Integer>();
		
		llist.add(5);
		llist.add(10);
		llist.add(20);
		
		System.out.printf("\nAfter setup %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		llist.clear();
		
		System.out.printf("\nAfter clearing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		for (int i=0; i<20; i+=2) {
			llist.add(i);
		}
		
		System.out.printf("\nAfter setting %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		ArrayList<Integer> alist = llist.toArrayList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}						
	
		alist = llist.toShuffledList();	
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}
	}

}


