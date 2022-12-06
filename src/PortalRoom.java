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
import processing.core.PImage;

/**
 * Child class of Room. PortalRoom is a type of room that will transport the entering player into an
 * adjacent room. A dragon cannot enter this type of room
 * 
 * @author Cole Bielby
 *
 */
public class PortalRoom extends Room {

  private Random randGen; // random number generator for location picking
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE =
      "The space distortion teleported you to another room!\n";
  private static PImage portalImage; // image of a portal to be shown in all portal rooms

  /**
   * Constructor for a PortalRoom object. Initializes all instance data fields.
   * 
   * @param ID          the ID that this PortalRoom should have
   * @param description the verbal description this PortalRoom should have
   * @param image       the image that should be used as a background when drawing this PortalRoom
   */
  public PortalRoom(int ID, String description, PImage image) {
    super(ID, description, image);
    randGen = new Random();
  }

  /**
   * Getter for PORTAL_WARNING
   * 
   * @return the string for warning about a portal being nearby
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;
  }

  /**
   * Getter for TELEPORT_MESSAGE
   * 
   * @return the string for letting the player know they were teleported
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE;
  }

  /**
   * Picks an adjacent room at random for the player to teleport into.
   * 
   * @return The room that player should immediately be moved to
   */
  public Room getTeleportLocation() {
    // Generates index that is valid for adjacent rooms arraylist
    int randomIndex = randGen.nextInt(this.getAdjacentRooms().size());
    return this.getAdjacentRooms().get(randomIndex);
  }

  /**
   * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
   * and the portal image.
   * 
   */
  @Override
  public void draw() {
    super.draw();
    processing.image(portalImage, 325, 225);
  }

  /**
   * Sets the portal image for the PortalRoom class.
   * 
   * @param portalImageParam the image to represent the portal
   */
  public static void setPortalImage(PImage portalImageParam) {
    portalImage = portalImageParam;
  }
}
