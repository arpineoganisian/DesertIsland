package desertIsland.player;

import desertIsland.GameUtils;
import desertIsland.inventory.Inventory;
import desertIsland.item.CellPhone;
import desertIsland.item.Combo;
import desertIsland.item.Item;
import desertIsland.item.Moveable;
import desertIsland.location.Direction;
import desertIsland.location.Location;

import java.util.List;
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

    public String getName() { return name; }
    public Location getLocation() { return location; }
    public Inventory getInventory() { return inventory; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

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
        Optional<Item> optional = Optional.ofNullable(GameUtils.findItem(location.getInventory(), string));
        if (optional.isPresent()) {
            Item item = optional.get();
            if (item.getMoveable() == Moveable.STATIONARY) {
                System.out.println("I very much doubt I can pick it up");
                return;
            }
            inventory.getItems().add(item);
            location.pickUp(item);
            System.out.println("~ You've got:");
            getInventory().show();
        }
        else {
            System.out.println("There is no such item at the current location");
        }
    }

    public void dropOff(String string) {
        Optional<Item> optional = Optional.ofNullable(GameUtils.findItem(inventory, string));
        if(optional.isPresent()) {
            Item item = optional.get();
            inventory.getItems().remove(item);
            location.putOn(item);
            System.out.println("~ At this location: ");
            location.getInventory().show();
        }
        else {
            System.out.println("There is no such item in " + name + "'s inventory");
        }
    }

    public void showItemDescription(String string) {
        if (string.equals("")) {
            System.out.println("Nope. Specify the item name first");
            return;
        }
        Optional<Item> optional = Optional.ofNullable(GameUtils.findItem(location.getInventory(), string));
        if (optional.isEmpty()) {
            optional = Optional.ofNullable(GameUtils.findItem(inventory, string));
            if (optional.isEmpty()) {
                System.out.println("There is no such item in " + name + "'s inventory or at the current location");
                return;
            }
        }
        if (optional.isPresent()) {
            Item item = optional.get();
            System.out.println(item.getDescription());
        }
    }

    public void useItem(String string, CellPhone phone) {
        if (string.equals("")) {
            System.out.println("Nope. Specify the item name first");
            return;
        }
        if (string.equals("phone")) {
            phone.use(this, this.location);
            return;
        }
        Optional<Item> optional = Optional.ofNullable(GameUtils.findItem(location.getInventory(), string));
        if (optional.isPresent()) {
            Item item = optional.get();
                item.use(this, this.location);
        }
        else {
            optional = Optional.ofNullable(GameUtils.findItem(getInventory(), string));
            if (optional.isPresent()) {
                Item item = optional.get();
                item.use(this, this.location);
            }
            else {
                System.out.println("There is no such item in " + name + "'s inventory or at the current location");
            }
        }
    }
    public void makeCombo (List<Combo> comboList, String string) {
        String[] word = string.split("\\s+");
        if (word.length != 2) {
            System.out.println("No, no, no. Specify 2 space-separated item name first");
            return;
        }
        if ((Optional.ofNullable(GameUtils.findItem(location.getInventory(), word[0])).isPresent()
                || Optional.ofNullable(GameUtils.findItem(getInventory(), word[0])).isPresent())
                && (Optional.ofNullable(GameUtils.findItem(location.getInventory(), word[1])).isPresent()
                || Optional.ofNullable(GameUtils.findItem(getInventory(), word[1])).isPresent())) {
            for (Combo c : comboList) {
                if(c.getObject().getName().toLowerCase().equals(word[0])
                        && c.getSubject().getName().toLowerCase().equals(word[1])) {
                    if (c.getResult().getMoveable() == Moveable.MOBILE) {
                        getInventory().add(c.getResult());
                        System.out.println("Great! You've made up " + c.getResult().getName());
                        System.out.println("~ You've got:");
                        getInventory().show();
                    }
                    else {
                        getLocation().getInventory().add(c.getResult());
                        System.out.println("Great! You've made up " + c.getResult().getName());
                        System.out.println("~ At this location: ");
                        getLocation().getInventory().show();
                    }
                    return;
                }
            }
            System.out.println("There is nothing that can be made up of " + word[0].toUpperCase() + " and " + word[1].toUpperCase());
        }
        else {
            System.out.println("There is no such item in " + name + "'s inventory or at the current location");
        }
    }
}
