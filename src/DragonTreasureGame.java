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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class sets up the game to be played and actually starts the game
 * 
 * @author Cole Bielby
 *
 */
public class DragonTreasureGame extends PApplet {

  private ArrayList<Room> roomList;
  private File roomInfo;
  private File mapInfo;
  private ArrayList<Character> characters;
  private boolean isDragonTurn;
  private int gameState;


  /**
   * Sets the window of the game to 800 x 600
   * 
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Sets up the window and processing of the game
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text
    isDragonTurn = false; // False so player moves first in the game
    gameState = 0; // 0 means continue, 1 means you won, 2 means you lost
    roomList = new ArrayList<Room>(); // Initializes the roomList arraylist
    characters = new ArrayList<Character>(); // Initializes the characters arraylist
    Room.setProcessing(this); // Sets the rooms processing to this object
    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");
    TreasureRoom.setTreasureBackground(this.loadImage("images/treasure.jpg")); // Loads treasure img
    PortalRoom.setPortalImage(this.loadImage("images/portal.png")); // Loads portal img
    this.loadRoomInfo();
    this.loadMap();
    this.loadCharacters(); // Loads the characters into the characters arraylist
  }


  /**
   * Draws the room of the player and ensures various parts of game logic run correctly (such as if
   * a dragon/treasure/portal is nearby and if the player wins/loses).
   * 
   */
  public void draw() {
    int indexOfPlayer = 0;
    int indexOfDragon = 0;
    int indexOfKeyHolder = 0;
    // a) Draws the room of the player
    for (int i = 0; i < characters.size(); ++i) {
      if (characters.get(i) instanceof Player) {
        characters.get(i).getCurrentRoom().draw();
        indexOfPlayer = i; // Stores index of player in characters arraylist
      }
      if (characters.get(i) instanceof Dragon) {
        indexOfDragon = i; // Stores index of dragon in characters arraylist
      }
      if (characters.get(i).getLabel().equals("KEYHOLDER")) {
        indexOfKeyHolder = i; // Stores index of key holder in characters arraylist
      }
    }

    // b) Checks if dragon is nearby
    if (((Player) characters.get(indexOfPlayer))
        .isDragonNearby((Dragon) characters.get(indexOfDragon))) {
      System.out.println(Dragon.getDragonWarning());
    }
    // Checks if Treasure is nearby
    if (((Player) characters.get(indexOfPlayer)).isTreasureNearby()) {
      System.out.println(TreasureRoom.getTreasureWarning());
    }
    // Checks if portal is nearby
    if (((Player) characters.get(indexOfPlayer)).isPortalNearby()) {
      System.out.println(PortalRoom.getPortalWarning());
    }

    // c) Checks if player can grab key
    if (((Player) characters.get(indexOfPlayer)).getCurrentRoom()
        .equals(characters.get(indexOfKeyHolder).getCurrentRoom())) {
      if (!((Player) characters.get(indexOfPlayer)).hasKey()) {
        ((Player) characters.get(indexOfPlayer)).obtainKey(); // Gives player key
        System.out.println("KEY OBTAINED");
      }
    }

    // d) Checks if player should teleport (and teleports them if applicable)
    /*
     * if (((Player) characters.get(indexOfPlayer)).getCurrentRoom() instanceof PortalRoom) { //
     * Changes room to random room according to PortalRoom method ((Player)
     * characters.get(indexOfPlayer)) .changeRoom(((PortalRoom) ((Player)
     * characters.get(indexOfPlayer)).getCurrentRoom()) .getTeleportLocation());
     */ // Old redundant code

    if (((Player) characters.get(indexOfPlayer)).teleport()) {
      System.out.println(PortalRoom.getTeleportMessage());
    }

    // e) If it's dragons turn and game continues, change its room
    if (gameState == 0 && isDragonTurn) {
      ((Dragon) characters.get(indexOfDragon))
          .changeRoom(((Dragon) characters.get(indexOfDragon)).pickRoom());
      isDragonTurn = false;
    }

    // f) If character and dragon in same room
    if (characters.get(indexOfPlayer).getCurrentRoom()
        .equals(characters.get(indexOfDragon).getCurrentRoom())) {
      if (gameState == 0) {
        System.out.println(Dragon.getDragonEncounter());
        System.out.println("You Lose!");
      }
      gameState = 2;

    }

    // If character in treasure room (with key)
    if (characters.get(indexOfPlayer).getCurrentRoom() instanceof TreasureRoom) {
      // If they can grab key
      if (((TreasureRoom) characters.get(indexOfPlayer).getCurrentRoom())
          .playerCanGrabTreasure((Player) characters.get(indexOfPlayer))) {
        if (gameState == 0) {
          System.out.println("You Won!");
        }
        gameState = 1;
      }
    }
  }

  /**
   * Loads in room info using the file stored in roomInfo
   * 
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      // scanner to read from file
      fileReader = new Scanner(roomInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        // parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); // get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); // get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); // close scanner regardless of what happened for security reasons :)
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   * 
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      // scanner to read from file
      fileReader = new Scanner(mapInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {

        // parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); // get the room we need to update info for adjacent rooms

        // add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally { // close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   * 
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  /**
   * Adds all the characters to the characters ArrayList
   * 
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }

  /**
   * Uses the key the user presses to move them (if it's a valid move)
   * 
   */
  @Override
  public void keyPressed() {
    int indexOfPlayer = 0;
    for (int i = 0; i < characters.size(); ++i) {
      if (characters.get(i) instanceof Player) {
        characters.get(i).getCurrentRoom().draw();
        indexOfPlayer = i; // Stores index of player in characters arraylist
      }
    }
    // If the game is still going
    if (gameState == 0) {
      int keyPressed = Integer.parseInt(String.valueOf(this.key));
      for (int i = 0; i < characters.get(indexOfPlayer).getAdjacentRooms().size(); ++i) {
        if (characters.get(indexOfPlayer).getAdjacentRooms().get(i).getID() == keyPressed) {
          ((Player) characters.get(indexOfPlayer)) // Change room to valid room
              .changeRoom(characters.get(indexOfPlayer).getAdjacentRooms().get(i));
          isDragonTurn = true; // Dragon can move once player moves
        }
      }
      if (isDragonTurn == false) { // If the change was not successful
        System.out.println("Pick a valid room");
      }
    }
  }

  /**
   * Main method that begins the game
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame");

  }

}
