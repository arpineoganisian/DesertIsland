import java.util.Locale;
import java.util.Optional;

public class Player {
    private String name;
    private Location location;
    private Inventory inventory;
    private int health;

    public Player(String name, Location location, int health) {
        this.name = name;
        this.location = location;
        this.inventory = new Inventory();
        this.health = health;
    }

    public void lookAround() {
        System.out.print("~ Current location: " + location.getName() + "\n  "
                + location.getDesctiption() + "\n~ At this location: \n");
        location.getInventory().show();
    }

    public void go(String string) {

        // парс заданного направления в команде
        Direction temp = null;
        if (string.trim().compareTo("west") == 0)
            temp = Direction.WEST;
        else if (string.trim().compareTo("east") == 0)
            temp = Direction.EAST;
        else if (string.trim().compareTo("north") == 0)
            temp = Direction.NORTH;
        else if (string.trim().compareTo("south") == 0)
            temp = Direction.SOUTH;
        else
            System.out.println("There is no such command");

        // если по ключу-направлению нет локации, локация остается той же
        Optional<Location> optional = Optional.ofNullable(location.checkDirection(temp));
        this.location = optional.orElse(this.location);

        if (optional.isPresent()) { lookAround(); }
        else { System.out.println("There is nothing in this direction. Go in the other one"); }
    }

    public void pickUp(String string) {
        for (Item i:location.getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                inventory.getItems().add(i);
                location.pickUp(i);
                break;
            }
        }
    }

    public void dropOff(String string) {
        for(Item i:inventory.getItems()) {
            if (i.getName().toLowerCase().equals(string)) {
                inventory.getItems().remove(i);
                location.putOn(i);
                break;
            }
        }
    }

    public void showItemDescription(String string) {
        for (Item i:location.getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                System.out.println(i.getDescription());
                break;
            }
        }
        for (Item i:getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                System.out.println(i.getDescription());
                break;
            }
        }
        System.out.println("There is no such item in " + name + "'s inventory or at the current location" );
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setHealth(int healthChange) {
        this.health = this.health - healthChange;
        if (health > 0) {
            System.out.println("~ " + this.name + "'s health:\n  " + this.health);
        }
        if (health <= 0) {
            System.out.println("~ " + this.name + "'s health:\n  0");
            System.out.println("OOPS! " + this.name + "is dead :(");
            System.exit(0);
        }
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

}
