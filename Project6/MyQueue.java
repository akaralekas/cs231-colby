/*
 * File: MyQueue.java
 * Author: Anthony Karalekas
 * Help: CP
 * Worked with: Steven Parrott
 * Date: Nov. 1, 2015
 * Assignment: Lab 6
 */
  
import java.util.*;

public class MyQueue<T> implements Iterable<T>{
     protected DoubleLinkedList list;
     protected int cap;
	
	//constructor
    public MyQueue(int cap){
          list = new DoubleLinkedList<T>();
          this.cap = cap;
     }
	//returns size of the linked list
    public int size(){
          return this.list.size();
     }
	
	//return true if empty
	public boolean isEmpty(){
          return this.list.size() == 0;
     }
	
	//add to end of linked list
    public boolean add(T item){
		this.list.append(item);
		return true;
	}
	//add to end of linked list
    public boolean offer(T item){
     	if(this.list.size()< this.cap){
     		this.list.append(item);
     		return true;
     	}
     	else{
     		System.out.println("Sorry, no more room in this queue");
     		return false;
     	}
     }
	
	//remove from end of linked list
	public T remove(){
		return (T) this.list.pop();
	}	
    //remove from end of linked list
	public T poll(){
		return (T) this.list.pop();
	}
     
    //return data of head
    public T element(){
     	return (T) this.list.getHeadData();	
     }
     
    //return data of head
    public T peek(){
    	if(this.list.size() > 0){
    		return (T) this.list.getHeadData();
    	}
    	else{
    		return null;
    	}
     }
     
     //get the front of the list
    public T front(){
        return (T) this.list.get(0);
     }
     
	public Iterator<T> iterator(){
		return list.iterator();
	}	
	
	//to String
	public String toString(){
		ArrayList<T> arraylist = list.toArrayList();
		return arraylist.toString();
	}
    
    //main test code
    public static void main( String[] args){
		MyQueue<Integer> queue = new MyQueue<Integer>(4);
		for (int i = 0; i < 5; i++){
			queue.offer(i);
			System.out.println(queue.toString());
		}
		queue.remove();

		System.out.println(queue.peek());
		System.out.println(queue.poll());
	}
     
}