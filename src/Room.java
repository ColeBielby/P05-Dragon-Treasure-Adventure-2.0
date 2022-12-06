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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * An instantiable class that stores information about a room in a dungeon
 * 
 * @author Cole Bielby
 *
 */
public class Room {
  private String description; // verbal description of the room
  private ArrayList<Room> adjRooms; // list of all rooms directly connect
  private final int ID; // a "unique" identifier for each room
  protected static PApplet processing; // PApplet object which the rooms will use to
  // draw stuff to the GUI
  private PImage image; // stores the image that corresponds to the background of a room

  /**
   * Constructor for a Room object. Initializes all instance data fields
   * 
   * @param ID          the ID that this Room should have
   * @param description the verbal description this Room should have
   * @param image       the image that should be used as a background when drawing this Room.
   */
  public Room(int ID, String description, processing.core.PImage image) {
    this.ID = ID;
    this.description = description;
    this.image = image;
    adjRooms = new ArrayList<Room>();
  }

  /**
   * Getter for ID
   * 
   * @return the ID of this room
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Getter for description
   * 
   * @return the verbal description of this Room
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for the list of adjacentRooms
   * 
   * @return the list of adjacent rooms
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.adjRooms;
  }

  /**
   * Explanation of the purpose of the method
   * 
   * @param processingParam the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(processing.core.PApplet processingParam) {
    processing = processingParam;
  }

  /**
   * Adds the given room to the list of rooms adjacent to this room
   * 
   * @param toAdd the room to be added
   */
  public void addToAdjacentRooms(Room toAdd) {
    adjRooms.add(toAdd);
  }

  /**
   * Checks whether or not the given room is adjacent to this room
   * 
   * @param r the room to check for adjacency
   * @return true if it is adjacent, false otherwise
   */
  public boolean isAdjacent(Room r) {
    boolean isAdj = false;

    // Goes thru adjacent rooms to try to find r
    for (int i = 0; i < adjRooms.size(); ++i) {
      if (r.equals(adjRooms.get(i))) { // If room = r
        isAdj = true;
        break;
      }
    }

    return isAdj;
  }

  /**
   * Overrides Object.equals(). Determines if two objects are equal
   * 
   * @param other the object to check against this Room
   * @return true if other is of type Room and has the same ID, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Room) {
      if (((Room) other).getID() == this.ID) {
        return true;
      }
    }

    return false; // Only reached if not equal
  }

  /**
   * Overrides Object.toString(). Returns a string representation of a Room object
   * 
   * @return Returns a string in the form of "<ID>: <description>\n Adjacent Rooms: <r1's ID> <r2's
   *         ID>" list of adjacent room IDs continues for all rooms adjacent to this Room.
   */
  @Override
  public String toString() {
    String formatString = "";
    // Adds initial parts of the string
    formatString += this.ID + ": " + this.description + "\nAdjacent Rooms: ";
    // Adds the adjacent rooms ID's
    for (int i = 0; i < adjRooms.size(); ++i) {
      formatString += adjRooms.get(i).getID();
      if (i == adjRooms.size() - 1) { //If this is the last index
        // Do nothing
      } else {
        formatString += " "; // Adds space if this isn't the last ID
      }
    }
    
    return formatString;
  }
  
  /**
   * Draws this Room to the window by drawing the background image, a rectangle, and some text
   */
  public void draw() {
    processing.image(image, 0, 0);
    processing.fill(-7028);
    processing.rect(0, 500, 800, 600);
    processing.fill(0);
    processing.text(this.toString(), 300, 525);
  }
}
