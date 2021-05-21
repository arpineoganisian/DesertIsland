package desertIsland.inventoryTest;

import desertIsland.item.Item;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void addAll(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void show() {
        if (items.isEmpty()) {
            System.out.println("  | Nothing's here |");
        }
        else {
        System.out.print("  | ");
            for (Item i : items) {
                System.out.print(i.getName() + " | ");
            }
            System.out.println();
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}