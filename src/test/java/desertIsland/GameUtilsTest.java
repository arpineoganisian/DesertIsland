package desertIsland;

import desertIsland.inventory.Inventory;
import desertIsland.item.Item;
import desertIsland.item.Moveable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameUtilsTest {

    Inventory inventory = new Inventory();
    Item item1 = new Item("Name", "Description", Moveable.MOBILE);

    @Test
    @DisplayName("(findItem) проверка существующего Item")
    public void testFindItemCorrect() {
        inventory.add(item1);
        assertEquals(item1, GameUtils.findItem(inventory, "name"));
    }

    @Test
    @DisplayName("(findItem) проверка несуществующего Item")
    public void testFindItemWrong() {
        inventory.add(item1);
        assertEquals(null, GameUtils.findItem(inventory, "hehe"));
    }

    @Test
    @DisplayName("(findItem) проверка null")
    public void testFindItemNull() {
        inventory.add(item1);
        assertEquals(null, GameUtils.findItem(inventory, null));
    }

}
