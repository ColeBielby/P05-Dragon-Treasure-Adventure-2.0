//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 New Dragon Treasure Adventure
// Course: CS 300 Fall 2022
//
// Author: Cole Bielby
// Email: cbielby@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;

/**
 * Child class of Room, StartRoom functions as the first room of the dungeon (the room the player
 * start in)
 * 
 * @author Cole Bielby
 *
 */
public class StartRoom extends Room {

  /**
   * Constructor for StartRoom. Utilizes the Room constructor with custom text
   * 
   * @param ID    The unique ID of the room
   * @param image The background image of the room
   */
  public StartRoom(int ID, PImage image) {
    super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
  }

}
