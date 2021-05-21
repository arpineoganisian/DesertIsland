package desertIsland.inventoryTest;

import desertIsland.item.Item;
import desertIsland.item.Moveable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    @Test
    @DisplayName("проверка на добавление item")
    public void testAdd() throws Exception {
        Inventory inventory = new Inventory();
        Item item = new Item("Name", "Description", Moveable.MOBILE);
        inventory.add(item);
        assertEquals(1, inventory.getItems().size());
    }

}
