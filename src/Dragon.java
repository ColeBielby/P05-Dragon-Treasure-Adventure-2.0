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

import java.util.Random;

/**
 * Child class of Character. The dragon can kill the player character and randomly moves to an
 * adjacent room every turn
 * 
 * @author Cole Bielby
 *
 */
public class Dragon extends Character implements Moveable {

  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

  /**
   * Constructor for a Dragon object. Initializes all instance fields. The label should be "DRAGON"
   * by default.
   * 
   * @param currentRoom the room that the Dragon starts in
   * @throws IllegalArgumentException with a descriptive message if currentRoom is not a
   *                                  TreasureRoom
   */
  public Dragon(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "DRAGON");

    // Checks if currentRoom is TreasureRoom (only possible starting location for a Dragon)
    if (!(currentRoom instanceof TreasureRoom)) {
      throw new IllegalArgumentException("The dragon must start in a TreasureRoom");
    }

    randGen = new Random();
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a
   * PortalRoom.
   * 
   * @param destination the room to check if the dragon can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (destination instanceof PortalRoom) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Moves the Dragon to the destination room.
   * 
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    boolean isInAdjRoom = false;

    if (!this.canMoveTo(destination)) { // If this dragon cannot move to the room
      return false;
    }

    for (int i = 0; i < this.getAdjacentRooms().size(); ++i) {
      if (destination.equals(this.getAdjacentRooms().get(i))) { // If dest. is in adj rooms
        isInAdjRoom = true;
      }
    }

    if (!isInAdjRoom) {
      return false;
    }

    // Only if destination is not a portal room and is in the dragon's adj rooms
    super.setCurrentRoom(destination);
    return true; // Only when dragon actually moves rooms
  }

  /**
   * Picks randomly ONCE an adjacent room to move into.
   * 
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom() {
    boolean validMove = false;
    while (!validMove) {
      // Picks random room from index 0 to size of adjRooms - 1
      int randIndex = randGen.nextInt(this.getAdjacentRooms().size());
      if (this.canMoveTo(this.getAdjacentRooms().get(randIndex))) {
        return this.getAdjacentRooms().get(randIndex);
      }
    }
    Room unusedRoom = new Room(-1, "unused", null); // This code should never be reached
    return unusedRoom;
  }

  /**
   * Getter for DRAGON_WARNING
   * 
   * @return the string for warning about a dragon being nearby
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;
  }

  /**
   * Getter for DRAGON_ENCOUNTER.
   * 
   * @return the string for letting the player know they ran into the dragon
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER;
  }
}
