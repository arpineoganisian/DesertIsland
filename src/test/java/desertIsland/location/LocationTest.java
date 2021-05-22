package desertIsland.location;

import desertIsland.item.Item;
import desertIsland.item.Moveable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    Location location1 = new Location("Name1", "Description1");
    Location location2 = new Location("Name2", "Description2");
    Item item = new Item("Name", "Description", Moveable.MOBILE);

    @Test
    @DisplayName("(setDirections) добавление в хэшмапу")
    public void testSetDirections() throws Exception {
        location1.setDirections(location2, Direction.EAST);
        assertEquals(1, location1.getDirections().size());
    }

    @Test
    @DisplayName("(setDirections) добавление в хэшмапу: Location - null")
    public void testSetDirectionsNullAsLoc() throws Exception {
        location1.setDirections(null, Direction.EAST);
        assertEquals(0, location1.getDirections().size());
    }

    @Test
    @DisplayName("(setDirections) добавление в хэшмапу: Direction - null")
    public void testSetDirectionsNullAsDir() throws Exception {
        location1.setDirections(location2, null);
        assertEquals(0, location1.getDirections().size());
    }

    @Test
    @DisplayName("(checkDirection) проверка Location по заданному Direction")
    public void testCheckCorrectDirection() throws Exception {
        location1.setDirections(location2, Direction.EAST);
        assertEquals(location2, location1.checkDirection(Direction.EAST));
    }

    @Test
    @DisplayName("(checkDirection) проверка Location по заданному Direction")
    public void testCheckWrongDirection() throws Exception {
        location1.setDirections(location2, Direction.EAST);
        assertEquals(null, location1.checkDirection(Direction.SOUTH));
    }

    @Test
    @DisplayName("(putOn) проверка с Item")
    public void testPutOn() {
        location1.putOn(item);
        assertEquals(1, location1.getInventory().getItems().size());
    }

    @Test
    @DisplayName("(putOn) проверка c null")
    public void testPutOnNull() {
        location1.putOn(null);
        assertEquals(0, location1.getInventory().getItems().size());
    }

    @Test
    @DisplayName("(pickUp) проверка с Item")
    public void testPickUp() {
        location1.getInventory().getItems().add(item);
        location1.pickUp(item);
        assertEquals(0, location1.getInventory().getItems().size());
    }

    @Test
    @DisplayName("(pickUp) проверка c null")
    public void testPickUpNull() {
        location1.getInventory().getItems().add(item);
        location1.pickUp(null);
        assertEquals(1, location1.getInventory().getItems().size());
    }
}
