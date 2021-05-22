package desertIsland.inventory;

import desertIsland.item.Item;
import desertIsland.item.Moveable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {

    Inventory inventory = new Inventory();
    Item item1 = new Item("Name1", "Description1", Moveable.MOBILE);
    Item item2 = new Item("Name2", "Description2", Moveable.MOBILE);

    @Test
    @DisplayName("(add) добавление предмета в инвентарь: Item")
    public void testAddItem() throws Exception {
        inventory.add(item1);
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    @DisplayName("(add) добавление предмета в инвентарь: null")
    public void testAddNull() throws Exception {
        inventory.add(null);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(addAll) добавление списка в инвентарь: ArrayList")
    public void testAddAllList() throws Exception {
        ArrayList<Item> itemList = new ArrayList<>(List.of(item1, item2));
        inventory.addAll(itemList);
        assertEquals(2, inventory.getItems().size());
    }

    @Test
    @DisplayName("(addAll) добавление списка в инвентарь: null")
    public void testAddAllNull() throws Exception {
        inventory.addAll(null);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(remove) удаление предмета из инвентаря: Item")
    public void testRemoveItem() throws Exception {
        inventory.add(item1);
        inventory.remove(item1);
        assertEquals(0, inventory.getItems().size());
    }

    @Test
    @DisplayName("(remove) удаление предмета из инвентаря: null")
    public void testRemoveNull() throws Exception {
        inventory.add(item1);
        inventory.remove(null);
        assertEquals(1, inventory.getItems().size());
    }
}
