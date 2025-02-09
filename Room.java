import java.util.ArrayList;
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
    private ArrayList<Item> invRoom = new ArrayList<>();
    private Player player = new Player();
    
    private HashMap<String, Room> exits;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String longDescription) 
    {
        this.description = description;
        this.longDescription = longDescription;
        
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
        if("North".equals(direction)) {
            //northExit = north;
            exits.put("North", next);
        }
        if("East".equals(direction)) {
            //eastExit = east;
            exits.put("East", next);
        }
        if("South".equals(direction)) {
            //southExit = south;
            exits.put("South", next);
        }
        if("West".equals(direction)) {
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

    public void addItem(Item item){
        invRoom.add(item);
    }

    public void listAllItems(){
        for(int i = 0; i < invRoom.size(); i++){
            System.out.println(invRoom.get(i).getDescription());
        }
    }

    public Item getItemInRoom(int index){        
        Item control = invRoom.get(index);
        player.getItem(control);
        invRoom.remove(index);
        return control;
    }
    
    public boolean thereAreItems(){
        return !invRoom.isEmpty();
    }
}
