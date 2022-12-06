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
 * Child class of Room. TreasureRoom is a room that contains the treasure that the player needs to
 * grab to win the game. The player needs a key to open the treasure
 * 
 * @author Cole Bielby
 *
 */
public class TreasureRoom extends Room {

  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  private static PImage treasureBackground; // the image ALWAYS used for treasure rooms

  /**
   * Constructor for a TresureRoom object and have a description of "In the back of this room, you
   * spot a treasure chest. It is locked...". Intializes all instance data fields.
   * 
   * @param ID the ID to give to this object
   */
  public TreasureRoom(int ID) {
    super(ID, "In the back of this room, you spot a treasure chest. It is locked...",
        treasureBackground);
  }

  /**
   * Getter for TREASURE_WARNING
   * 
   * @return the string for warning about treasure nearby
   */
  public static String getTreasureWarning() {
    return TREASURE_WARNING;
  }

  /**
   * Determines whether or not the player can open the treasure chest in the room.
   * 
   * @param p the Player to check if they can open the chest
   * @return true if the player has the key and is in this TreasureRoom, false otherwise
   */
  public boolean playerCanGrabTreasure(Player p) {
    if (p.hasKey() && (p.getCurrentRoom() instanceof TreasureRoom)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sets the background image for the TreasureRoom class
   * 
   * @param treasureBackground the image to be the background
   */
  public static void setTreasureBackground(PImage treasureBackgroundParam) {
    treasureBackground = treasureBackgroundParam;
  }
}
