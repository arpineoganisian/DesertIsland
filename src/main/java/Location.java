import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String desctiption;
    private Inventory inventory;
    private Map<Direction, Location> directions;

    public Location(String name, String desctiption) {
        this.name = name;
        this.desctiption = desctiption;
        this.inventory = new Inventory();
        this.directions = new HashMap<>();
    }

    public String getName() { return name; }
    public Inventory getInventory() { return inventory; }
    public String getDesctiption() { return desctiption; }

    public void setDirections(Location loc, Direction dir) {
        directions.put(dir, loc);
    }

    public Location checkDirection (Direction direction) {
        // возвращаю value (location) по заданному key (direction)
        // ксли такого value по этому key нет, то возвращается null;
        return directions.getOrDefault(direction, null);
    }

    public void putOn(Item item) { inventory.add(item); }
    public void pickUp(Item item) { inventory.remove(item); }
}
