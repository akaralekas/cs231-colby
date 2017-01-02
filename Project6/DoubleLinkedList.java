/*
 * File: DoubleLinkedList.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Oct. 28, 2015
 * Assignment: Lab 5
 */
  
/*
 *
 */
 
import java.util.*;

//now a doubly linked list for project 5
//can move both forwards and backwards across the list
public class DoubleLinkedList<T> implements Iterable<T>{
	private Node head;
	private int numNodes;
	private Node tail;
	
	//constructor
	public DoubleLinkedList(){
		this.head = null;
		this.numNodes = 0;
	}
	
	public void clear(){
		this.head = null;
		this.numNodes = 0;
		this.tail = null;
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
	
	//removes and returns the first element from the list
	public T pop(){
		if(this.head != null){
			T item = this.head.data;
			this.head = this.head.next;
			return item;
		}
		
		return null;
	}
	//returns the head
	public Node getHead(){
		return this.head;	
	}
	
	//get the data of the head
	public T getHeadData(){
		return this.head.data;
	}
	
	//adds item to the beginning of list
	public void add(T item){
		Node newNode = new Node(this.head, item, null);
		if(this.size() == 1){
			this.tail = newNode;
		}
		this.head = newNode;
		if ( newNode.getNext() == null) {
			return;
		}
		else {
			newNode.getNext().setPrev(newNode);
		}	
	}
	
	//adds item to the end of list
	public void append(T item){
		Node newNode = new Node(null, item, this.tail);
		if(this.size() == 0){
			this.head = newNode;
		}
		this.tail = newNode;
		if ( newNode.getPrev() == null) {
			return;
		}
		else {
			newNode.getPrev().setNext(newNode);	
		}	
	}
	
	//Project 5--changed from boolean to a void
	//worked with Steve Parrott CS231 classmate
	public void remove(T item) {
			Node temp = this.head;
			while (temp.next.next != null) {
				if (temp.next.data == item){
					temp.setNext(temp.next.next);
					temp.next.setPrev(temp);
				}	
				else{
					temp = temp.next;
				}
			}
			if (temp.next.next == null){
				if(temp.next.data == item ){
					temp.setNext(null);
				}
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
		return new LLIterator(this.head, this.tail);
	}
	
	private class LLIterator implements Iterator<T>{
		private Node nextNode;
		private Node prevNode;
	
		public LLIterator(Node head, Node tail){
			this.nextNode = head;
			this.prevNode = tail;
		}
		
		public boolean hasNext(){
			return this.nextNode != null;
		}
		
		//Project 5 DoubleLinkedList
		public boolean hasPrev(){
			return this.prevNode != null;
		}
	
		public T next(){
		if (nextNode == null){
			return null;
		}
		T result = nextNode.data;
		nextNode = nextNode.next;
		return result;
		}
		
		//added for project 5
		public T prev(){
		if (prevNode == null){
			return null;
		}
		T result = prevNode.data;
		prevNode = prevNode.prev;
		return result;
		}
		
		public void remove(){}
	
	}



	private class Node{
		private Node next;
		private T data;
		private Node prev;

		public Node(Node next, T data, Node prev){
			this.data = data;
			this.next = next;
			this.prev = prev;
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
		//added for 5
		public void setPrev(Node n) {
			this.prev = n;
		}
		//added for project 5	
		public Node getPrev() {
			return this.prev;
		}	

	}
	

public static void main(String[] args) {
		
		DoubleLinkedList<Integer> llist = new DoubleLinkedList<Integer>();
		
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


