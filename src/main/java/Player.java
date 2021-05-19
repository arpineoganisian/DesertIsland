import java.util.Locale;
import java.util.Optional;

public class Player {
    private String name;
    private Location location;
    private Inventory inventory;
    private int health;
//    CellPhone phone;

    public Player(String name, Location location, int health) {
        this.name = name;
        this.location = location;
        this.inventory = new Inventory();
        this.health = health;
//        this.phone = new CellPhone("Phone", "Watch the charge level...", Moveable.MOBILE, 30);
//        getInventory().add(phone);
    }

    public String getName() { return name; }
    public Location getLocation() { return location; }
    public Inventory getInventory() { return inventory; }

    public void lookAround() {
        System.out.print("~ Current location: " + location.getName() + "\n  "
                + location.getDesctiption() + "\n~ At this location: \n");
        location.getInventory().show();
    }

    public void go(String string) {
        // парс заданного направления в команде
        Direction temp = null;
        if (string.compareTo("west") == 0)
            temp = Direction.WEST;
        else if (string.compareTo("east") == 0)
            temp = Direction.EAST;
        else if (string.compareTo("north") == 0)
            temp = Direction.NORTH;
        else if (string.compareTo("south") == 0)
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
            if (i.getMoveable() == Moveable.STATIONARY) {
                System.out.println("  I very much doubt I can pick it up");
            }
            if(i.getName().toLowerCase().equals(string.trim())) {
                inventory.getItems().add(i);
                location.pickUp(i);
                System.out.println("~ You've got:");
                getInventory().show();
                return;
            }
        }
        System.out.println("There is no such item at the current location");
    }

    public void dropOff(String string) {
        for(Item i:inventory.getItems()) {
            if (i.getName().toLowerCase().equals(string.trim())) {
                inventory.getItems().remove(i);
                location.putOn(i);
                System.out.println("~ At this location: \n");
                location.getInventory().show();
                return;
            }
        }
        System.out.println("There is no such item in " + name + "'s inventory");
    }

    public void showItemDescription(String string) {
        for (Item i:location.getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                System.out.println(i.getDescription());
                return;
            }
        }
        for (Item i:getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                System.out.println(i.getDescription());
                return;
            }
        }
        System.out.println("There is no such item in " + name + "'s inventory or at the current location");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health != 0) {
            this.health += health;
            if (this.health > 0) {
                System.out.println("~ " + this.name + "'s health:\n  " + this.health);
            }
            if (this.health <= 0) {
                System.out.println("~ " + this.name + "'s health:\n  0");
                System.out.println("OOPS! " + this.name + "is dead :(");
                System.exit(0);
            }
        }
        else {
            System.out.println("  Doesn't make any sense. What am I supposed to do with this?\n" +
                    "  Nothing has changed");
        }
    }

    public void useItem(String string, CellPhone phone) {
        if(string.equals("phone")) {
            phone.use(this.location);
            return;
        }
        for (Item i:location.getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                if(i.getMoveable() == Moveable.STATIONARY) {
                    setHealth(i.getEffect());
                }
                else {
                    System.out.println("  You have to put the item " +
                            "in your inventory before you can use it");
                }
            }
        }
        for (Item i:getInventory().getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                setHealth(i.getEffect());
            }
        }
    }



}
