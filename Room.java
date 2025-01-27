import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private String longDescription;
    private Item item;
    
    private HashMap<String, Room> exits;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        this.longDescription = longDescription;
        this.item = item;
        
        exits = new HashMap<>();
    }
    
    public Room getExit(String exit){
        return exits.get(exit);
    }
    
    public String getExitString(){
        String exitString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit: keys){
            exitString += " " + exit;
        }
        return exitString;
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room next) 
    {
        if("North" == direction) {
            //northExit = north;
            exits.put("North", next);
        }
        if("East" == direction) {
            //eastExit = east;
            exits.put("East", next);
        }
        if("South" == direction) {
            //southExit = south;
            exits.put("South", next);
        }
        if("West" == direction) {
            //westExit = west;
            exits.put("West", next);
        }
        if("Up" == direction) {
            //westExit = west;
            exits.put("West", next);
        }
        if("Down" == direction) {
            //westExit = west;
            exits.put("West", next);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getLongDescription()
    {
        return longDescription;
    }
    
    public Item itemInRoom(){
        return item;
    }

}
