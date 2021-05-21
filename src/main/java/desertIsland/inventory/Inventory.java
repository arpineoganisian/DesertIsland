package desertIsland.inventory;

import desertIsland.item.Item;
import java.util.ArrayList;
import java.util.Optional;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        if (Optional.ofNullable(item).isPresent()) {
            items.add(item);
        }
    }

    public void addAll(ArrayList<Item> items) {
        if (Optional.ofNullable(items).isPresent()) {
            this.items.addAll(items);
        }
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