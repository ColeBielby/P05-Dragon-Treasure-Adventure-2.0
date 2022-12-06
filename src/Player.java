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

/**
 * Child class of Character. Represents the player character in the dungeon
 * 
 * @author Cole Bielby
 *
 */
public class Player extends Character implements Moveable {

  private boolean hasKey;

  /**
   * Constructor for player object. The label should be "PLAYER" and not have a key by default.
   * 
   * @param currentRoom the room that the player should start in
   * @throws IllegalArgumentException if currentRoom is not an instance of StartRoom
   */
  public Player(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "PLAYER");
    if (!(currentRoom instanceof StartRoom)) {
      throw new IllegalArgumentException("Player must be initialized with a start room");
    }
    hasKey = false;
  }

  /**
   * Determines if the player has the key.
   * 
   * @return true if the player has the key, false otherwise
   */
  public boolean hasKey() {
    return hasKey;
  }

  /**
   * Gives player the key
   * 
   */
  public void obtainKey() {
    hasKey = true;
  }

  /**
   * Moves the Player to the destination room.
   * 
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    if (this.canMoveTo(destination)) {
      this.setCurrentRoom(destination);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the player can move to the given destination. A valid move is the destination is a
   * room adjacent to the player.
   * 
   * @param destination the room to check if the player can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (destination.isAdjacent(this.getCurrentRoom())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the player needs to teleport and move them if needed.
   * 
   * @return true if a teleport occurred, false otherwise
   */
  public boolean teleport() {
    if (this.getCurrentRoom() instanceof PortalRoom) {
      Room toTeleportTo = ((PortalRoom) this.getCurrentRoom()).getTeleportLocation();
      this.changeRoom(toTeleportTo);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is in
   * one of the adjacent rooms.
   * 
   * @param d the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d) {
    for (int i = 0; i < this.getAdjacentRooms().size(); ++i) {
      // If an adjacent room is also the dragons current room
      if (this.getAdjacentRooms().get(i) == d.getCurrentRoom()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether or not a portal room is nearby. A portal room is considered nearby if it is
   * one of the adjacent rooms.
   * 
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby() {
    for (int i = 0; i < this.getAdjacentRooms().size(); ++i) {
      if (this.getAdjacentRooms().get(i) instanceof PortalRoom) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether or not the treasure room is nearby. The treasure room is considered nearby
   * if it is one of the adjacent rooms.
   * 
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby() {
    for (int i = 0; i < this.getAdjacentRooms().size(); ++i) {
      if (this.getAdjacentRooms().get(i) instanceof TreasureRoom) {
        return true;
      }
    }
    return false;
  }

}
