package desertIsland.inventoryTest;

import desertIsland.inventory.Inventory;
import desertIsland.item.Item;
import desertIsland.item.Moveable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    @Test
    @DisplayName("(add) добавление предмета в инвентарь: Item")
    public void testAddItem() throws Exception {
        Inventory inventory = new Inventory();
        Item item = new Item("Name", "Description", Moveable.MOBILE);
        inventory.add(item);
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    @DisplayName("(add) добавление предмета в инвентарь: null")
    public void testAddNull() throws Exception {
        Inventory inventory = new Inventory();
        Item item = null;
        inventory.add(item);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(addAll) добавление списка в инвентарь: ArrayList")
    public void testAddAllList() throws Exception {
        Inventory inventory = new Inventory();
        Item item1 = new Item("Name", "Description", Moveable.MOBILE);
        Item item2 = new Item("Name", "Description", Moveable.MOBILE);
        ArrayList<Item> itemList = new ArrayList<>(List.of(item1, item2));
        inventory.addAll(itemList);
        assertEquals(2, inventory.getItems().size());
    }

    @Test
    @DisplayName("(addAll) добавление списка в инвентарь: null")
    public void testAddAllNull() throws Exception {
        Inventory inventory = new Inventory();
        ArrayList<Item> itemList = null;
        inventory.addAll(itemList);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(remove) удаление предмета из инвентаря: Item")
    public void testRemoveItem() throws Exception {
        Inventory inventory = new Inventory();
        Item item = new Item("Name", "Description", Moveable.MOBILE);
        inventory.add(item);
        inventory.remove(item);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(remove) удаление предмета из инвентаря: null")
    public void testRemoveNull() throws Exception {
        Inventory inventory = new Inventory();
        Item item1 = new Item("Name", "Description", Moveable.MOBILE);
        Item item2 = null;
        inventory.add(item1);
        inventory.remove(item2);
        assertEquals(1, inventory.getItems().size());
    }
}
