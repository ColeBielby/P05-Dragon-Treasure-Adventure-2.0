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

import java.util.ArrayList;

/**
 * An instantiable class that represents a character within the dungeon. Data is stored on the
 * characters current room and an explanation of the character
 * 
 * @author Cole Bielby
 *
 */
public class Character {

  private Room currentRoom; // current room the character is in
  private String label; // a label giving a basic description of the character

  /**
   * Constructor for a Character object. Initializes all instance fields.
   * 
   * @param currentRoom the room that the Character is located in
   * @param label       a descriptive label of this Character
   * @throws IllegalArgumentException If currentRoom is null
   */
  public Character(Room currentRoom, String label) throws IllegalArgumentException {
    if (currentRoom == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }
    this.currentRoom = currentRoom;
    this.label = label;
  }

  /**
   * Getter for the current room of this Character
   * 
   * @return the room where the character is
   */
  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  /**
   * Getter for the label of this Character.
   * 
   * @return this Character's descriptive label
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * Gets the list of rooms adjacent to this Character.
   * 
   * @return an ArrayList of rooms adjacent to this character
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.currentRoom.getAdjacentRooms();
  }

  /**
   * Sets the current room to the one given
   * 
   * @param newRoom the room that should become the current room
   */
  public void setCurrentRoom(Room newRoom) {
    this.currentRoom = newRoom;
  }
}
