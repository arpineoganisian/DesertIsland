public class Item {

    private String name;
    private String description;
    private Moveable moveable;
    private int effect;

    public Item(String name, String description, Moveable moveable, int effect) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
        this.effect = effect;
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getEffect() { return effect; }

    public Moveable getMoveable() { return moveable; }

}
