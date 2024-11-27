import java.util.*;
/*
 * Class controlling Alice from Lewis Carroll's Alice in wonderland. Can interact with various magical objects around her
 */
public class AliceInWonderland implements Contract {
    private float size;
    private int elevation;
    private ArrayList<String> inventory;
    private int energy;
    private int xcor;
    private int ycor;
    private boolean isOnMap;

    public AliceInWonderland() {
        this.size = 1;
        this.elevation = 0;
        this.energy = 10;
        this.xcor = 0;
        this.ycor = 0;
        this.inventory = new ArrayList<String>();
    }
    /*
     * Adds an item to the player's inventory
     * @param item an item the player picks up
     */
    public void grab(String item) {
        this.inventory.add(item);
        this.energy += -1;
    }
    /*
     * Removes an item from the player's inventory
     * @param item an item in the player's inventory they wish to get rid of
     * @return item the item that was dropped
     */
    public String drop(String item) {
        if (this.inventory.contains(item) == true) {
            this.inventory.remove(item);
            item = item + "has been removed from the inventory";   
        }
        else {
            item = item + "is not in your inventory"; 
        }
        this.energy += -1;
        return item;
    }
    /*
     * Prints the name of an item in the player's inventory
     * @param item an item in the player's inventory that they wish to look at
     */
    public void examine(String item) {
        if (this.inventory.contains(item) == true) {
            System.out.println("It's a " + item);
        }
        else {
            System.out.println("What " + item + " are you talking about? I don't think you have one");
        }
        this.energy += -1;
    }
    /* 
     * If the player has a magical item in their inventory, they will consume it and use it's effect 
     * @param item an item in the player's inventory they wish to use
     */
    public void use(String item) {
        if (item == "Drink me bottle") {
            if (this.inventory.contains(item) == true) {
                shrink();
            }

        }
        else if (item == "Eat me cookie") {
            if (this.inventory.contains(item) == true) {
                grow();
            }
        }
        else if (item == "Caterpillar") {
            if (this.inventory.contains(item) == true) {
            fly(0,0);
            }
        }
        else {
            System.out.println("You have used a " + item);
        }
        this.energy += -1;
    }
        
    /*
     * Transports the player one unit in the direction of their choice
     * @param direction the direction they are travelling
     * @return whether or not they are still on the supported map
     */
    public boolean walk(String direction) {
        if (direction.toLowerCase() == "forwards" ) {
            this.xcor += 1;
            if (this.xcor < 16) {
                this.isOnMap = true;
                this.energy += -1;
                return isOnMap;
            }
            else {
                this.isOnMap = false;
                System.out.println("You fell out of wonderland");
                return this.isOnMap;
            }
        }
        else if (direction.toLowerCase() == "back") {
            this.xcor += -1;
            if (this.xcor > -16) {
                this.isOnMap = true;
                this.energy += -1;
                return this.isOnMap;
            }
            else {
                this.isOnMap = false;
                System.out.println("You fell out of wonderland");
                return this.isOnMap;
            }
        }
        else if (direction.toLowerCase() == "left") {
            this.ycor += 1;
            if (this.ycor < 16) {
                this.isOnMap = true;
                this.energy += -1;
                return this.isOnMap;
            }
            else {
                this.isOnMap = false;
                System.out.println("You fell out of wonderland");
                return this.isOnMap;
            }
        }
        else if (direction.toLowerCase() == "right") {
            this.ycor += -1;
            if (this.ycor > -16) {
                this.isOnMap = true;
                this.energy += -1;
                return isOnMap;
            }
            else {
                isOnMap = false;
                System.out.println("You fell out of wonderland");
                return this.isOnMap;
            }
        }
        else {
            System.out.println("I don't recognize that direction. Please try forwards, back, left, or right");
            return this.isOnMap;
        }
    }
    /*
     * Transports Alice to a specific location on the map, regardless of where she currently is. Lifts her off the ground if she is not already
     * @param x the x coordinate travelled to
     * @param y the y coordinate travelled to
     * @return whether or not they are travelling to a location on the map
     */
    public boolean fly(int x, int y){
        if (x > 16 || x < -16 || y > 16 || y < -16) {
            System.out.println("That location is off the map");
            return false;
        }
        if (elevation == 0) {
            System.out.println("Houston we have lift off");
        }
        this.ycor = y;
        this.xcor = x;
        this.energy += -1;
        return true;
    }
    /*
     * Halves Alice's size
     * @return the size of Alice after performing the action
     */
    public Number shrink() {
        this.size = this.size / 2;
        this.energy += -1;
        return this.size;
    }
    /*
     * Doubles Alice's size
     * @return the size of Alice after performing the action
     */
    public Number grow() {
        this.size = this.size * 2;
        this.energy += -1;
        return this.size;
    }
    /*
     * Restores Alice's energy to the default, 10
     */
    public void rest() {
        this.energy = 10;
    }
    /*
     * Restores all of Alice's stats to the default
     */
    public void undo() {
        this.energy = 10;
        this.inventory.clear();
        this.size = 1;
        this.elevation = 0;
        this.xcor = 0;
        this.ycor = 0;
        this.isOnMap = true;
    }
    /*
     * Returns Alice's energy level
     * @return Energy remaining
     */
    public int getEnergy() {
        return this.energy;
    }
    /*
     * Returns Alice's inventory
     * @Return ArrayList<String> of items in inventory
     */
    public ArrayList<String> getInventory() {
        return this.inventory;
    }
    public float getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        AliceInWonderland alice = new AliceInWonderland();
        alice.grab("Drink me bottle");
        alice.grab("test");
        alice.drop("test");
        alice.examine("Drink me bottle");
        alice.use("Drink me bottle");
        alice.walk("forwards");
        alice.fly(17, 4);
        alice.grow();
        alice.shrink();
        System.out.println(alice.getEnergy());
        alice.rest();
        System.out.println(alice.getEnergy());
        System.out.println(alice.getSize());
        System.out.println(alice.getInventory());
        
    }
}
