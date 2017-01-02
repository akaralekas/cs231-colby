/*
 * File: MyPQTest.java
 * Author: Anthony Karalekas
 * Help:
 * Date: Nov. 13, 2015
 * Assignment: Lab 7 and Project 7
 */
  
/*
 * File provided by CS231 Professors
 * Edited by Tony Karalekas
 */

import java.lang.*;
import java.util.*;

/*
 * Class that tests the MyPriorityQueue class
 * Fall 2012
 *
 * @author Bruce Maxwell
 * Edited by Tony Karalekas
 */

public class MyPQTest {

		// Comparator class that tests Integers
		public static class Comp<Integer extends Comparable<Integer>> implements Comparator<Integer> {
				public int compare( Integer a, Integer b ) {
						return a.compareTo( b );
				}
		}

		// Main test function
		public static void main(String[] argv) {
				MyPriorityQueue<Integer> a = new MyPriorityQueue<Integer>(30, new Comp<Integer>() );
				Random gen = new Random();

				a.add(5);
				a.add(20);
				a.add(10);

				// Should be 20, 10, 5
				System.out.printf("\nAfter setup %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

				a.clear();

				// Should be empty
				System.out.printf("\nAfter clearing %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

				for(int i=0;i<10;i++) {
						a.add( new Integer(gen.nextInt( 20 ) ) );
				}

				// Should have 10 integers in order from largest to smallest
				System.out.printf("\nAfter setting %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

				a.remove();
				a.remove();
				a.remove();
				a.remove();

				// Should have 6 integers, with the top 4 removed
				System.out.printf("\nAfter removing the top 4 items: size %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

				int count = a.getSize();
				for(int i=0;i<count;i++) {
						a.remove();
				}

				// Should be empty
				System.out.printf("\nAfter removing the rest: size %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

				a.add( 4 );
				a.add( 15 );
				a.add( 42 );
				a.add( 1 );

				// Should be in order 42, 15, 4, 1
				System.out.printf("\nAfter add 4, 15, 42, 1: size %d\n", a.getSize());
				for(Integer item: a) {
						System.out.printf("thing %d\n", item);
				}

		}
}