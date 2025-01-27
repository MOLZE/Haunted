/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

 public class Game 
 {
     private Parser parser;
     private Room currentRoom;
         
     /**
      * Create the game and initialise its internal map.
      */
     public Game() 
     {
         createRooms();
         parser = new Parser();
     }
     
     /**
      * Create all the rooms and link their exits together.
      */
    private void createRooms()
      {
         Room  garden, corridor, room, kitchen, garage, bathroom, basement, livingRoom;
         Item doll, knife, candle, portrait, lighter, cross;
         
         // Create the items
         doll = new Item("Old doll, it has a creepy face, mostly because of its age!");
         knife = new Item("Old rusted knife, perfect for stabing someone!");
         candle = new Item("Normal plain candle, it does not even have a scent!");
         portrait = new Item("A portrait of a young lady, wonder who she might be...");
         lighter = new Item("Old rusted lighter, it's a miracle it still works!");
         cross = new Item("Gold plated cross, beautiful, wonder if this is gonna be useful!");
         
         
          // create the rooms
         garden = new Room("at the House entrance", 
         "The house stands silent, its sagging roof and dark windows watching like hollow eyes. \nIvy crawls up its cracked stone walls, and the overgrown garden swallows the path to the door. \nAn unsettling quiet hangs in the air, broken only by the soft creak of the iron gate.\n"
         ,null);
         corridor = new Room("at the main hall", 
         "The air inside is cold, thick with the scent of dust and decay. \nThe main hall stretches before you, dimly lit by the pale light filtering through broken windows. \nFaded wallpaper peels from the walls, revealing dark stains beneath. \nThe floor creaks with every step, as if the house itself is groaning in protest.\n"
         , portrait);
         room = new Room("at the sleeping room" ,
         "The room is still, almost unnaturally so, as if time has stopped here. \nThe bed lies untouched, its once-ornate frame now chipped and weathered, with moth-eaten sheets barely clinging to it. \nThe air feels thick, as though it’s holding its breath.\n "
         , doll);
         garage = new Room("at the garage",
         "The garage is suffocatingly dark, the only light coming from a small, grimy window high on the wall. \nDust hangs in the air, thick and unmoving. Old tools lie scattered across a workbench, their edges rusted, forgotten. \nA car, covered in a tattered sheet, sits in the corner, its outline faint but imposing.\n"
         , lighter);
         kitchen = new Room("at the kitchen", 
         "The stove is cold, its once-white tiles now stained and cracked. \nA faded, rotting smell lingers in the air, mixing with the metallic scent of rusting appliances.\n"
         , knife);
         livingRoom = new Room("at the living room", 
         "The furniture is shrouded in dust, the once-cozy space now a graveyard of forgotten belongings. \nA cold draft sweeps through, and the curtains sway, though no wind touches them.\n"
         , candle);
         bathroom = new Room("at the toilet", 
         "The sink is cracked, water pooling in the corners as the mirror reflects nothing but emptiness. \nFaint, yellow stains mark the walls, as if the room has been forgotten for decades.\n"
         , cross);
         basement = new Room("at the basement", 
         "The stairs creak underfoot, leading to a cold, shadow-filled space. \nThe air is thick, musty, and the faint sound of dripping water echoes through the room.\n"
         , null);
 
          // initialise room exits
          garden.setExit("North", corridor);
          garden.setExit("East", corridor);
          corridor.setExit("North", livingRoom);
          corridor.setExit("East", garage);
          corridor.setExit("South", garden);
          corridor.setExit("West", room);
          room.setExit("East", corridor);
          garage.setExit("North",kitchen);
          garage.setExit("West",corridor);
          kitchen.setExit("South", garage);
          kitchen.setExit("West", livingRoom);
          livingRoom.setExit("North", bathroom);
          livingRoom.setExit("East", kitchen);
          livingRoom.setExit("South", corridor);
          livingRoom.setExit("West", basement);
          basement.setExit("East", livingRoom);
          bathroom.setExit("South", livingRoom);
  
          currentRoom = garden;  // start game outside
      }
 
     /**
      *  Main play routine.  Loops until end of play.
      */
     public void play() 
     {            
         printWelcome();
 
         // Enter the main command loop.  Here we repeatedly read commands and
         // execute them until the game is over.
                 
         boolean finished = false;
         while (! finished) {
             Command command = parser.getCommand();
             finished = processCommand(command);
         }
         System.out.println("Thank you for playing.  Good bye.");
     }
 
     /**
      * Print out the opening message for the player.
      */
     private void printWelcome()
     {
         System.out.println();
         System.out.println("Welcome to Haunted!");
         System.out.println("Haunted is a new, incredibly boring adventure game.");
         System.out.println("In this game your objective is to wander around and colect 6 items scatered around the house, good luck avoiding the Ghost");
         System.out.println("Type 'help' if you need help.");
         System.out.println();
         printLocationInfo();
     }
 
     /**
      * Given a command, process (that is: execute) the command.
      * @param command The command to be processed.
      * @return true If the command ends the game, false otherwise.
      */
     private boolean processCommand(Command command) 
     {
         boolean wantToQuit = false;
 
         if(command.isUnknown()) {
             System.out.println("I don't know what you mean...");
             return false;
         }
 
         String commandWord = command.getCommandWord();
         if (commandWord.equals("help")) {
             printHelp();
         }
         else if (commandWord.equals("go")) {
             goRoom(command);
         }
         else if (commandWord.equals("quit")) {
             wantToQuit = quit(command);
         }
         else if (commandWord.equals("look")){
             look();
         }
 
         return wantToQuit;
     }
 
     // implementations of user commands:
 
     /**
      * Print out some help information.
      * Here we print some stupid, cryptic message and a list of the 
      * command words.
      */
     private void printHelp() 
     {
         System.out.println("You are lost. You are alone. You wander");
         System.out.println("around at the university.");
         System.out.println();
         System.out.println("Your command words are:");
         parser.showCommands();
     }
 
     /** 
      * Try to go in one direction. If there is an exit, enter
      * the new room, otherwise print an error message.
      */
     private void goRoom(Command command) 
     {
         if(!command.hasSecondWord()) {
             // if there is no second word, we don't know where to go...
             System.out.println("Go where?");
             return;
         }
 
         String direction = command.getSecondWord();
 
         // Try to leave current room.
         Room nextRoom = null;
         nextRoom = currentRoom.getExit(direction);
         currentRoom = nextRoom;
         if (nextRoom == null) {
             System.out.println("There is no door!");
         }
         else {
             printLocationInfo();
         }
     }
     
     private void printLocationInfo(){
         System.out.println("You are " + currentRoom.getDescription());
         System.out.println(currentRoom.getExitString());
         System.out.println();
     }
     
     /** 
      * "Quit" was entered. Check the rest of the command to see
      * whether we really quit the game.
      * @return true, if this command quits the game, false otherwise.
      */
     private boolean quit(Command command) 
     {
         if(command.hasSecondWord()) {
             System.out.println("Quit what?");
             return false;
         }
         else {
             return true;  // signal that we want to quit
         }
     }
     
     private void look(){
         System.out.println(currentRoom.getLongDescription());
         printLocationInfo();
     }
     
     private void get(){
         if(currentRoom.itemInRoom() != null){
             System.out.print("You get a " + currentRoom.itemInRoom().getDescription());
         }
     }
 }
 