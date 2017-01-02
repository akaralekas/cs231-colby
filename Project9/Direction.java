/*
 * File: Direction.java (enum Class)
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */

import java.util.Random;


public enum Direction{NORTH, SOUTH, EAST, WEST;

  	//this method returns a randomly selected direction
    public static Direction getRandomDirection() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
};