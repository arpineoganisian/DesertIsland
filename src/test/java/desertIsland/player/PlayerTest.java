package desertIsland.player;

import desertIsland.location.Direction;
import desertIsland.location.Location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    Location locCurrent = new Location("Current", "Description");
    Location locNew = new Location("New", "Description");
    Player player = new Player("Name", locCurrent, 30);

    @Test
    @DisplayName("(go) проверка существующего направления")
    public void testGoCorrect() {
        locCurrent.setDirections(locNew, Direction.WEST);
        player.go("west");
        assertEquals(locNew, player.getLocation());
    }

    @Test
    @DisplayName("(go) проверка несуществующего направления")
    public void testGoWrong() {
        locCurrent.setDirections(locNew, Direction.WEST);
        player.go("");
        assertEquals(locCurrent, player.getLocation());
    }

    @Test
    @DisplayName("(go) проверка на null")
    public void testGoNull() {
        locCurrent.setDirections(locNew, Direction.WEST);
        player.go(null);
        assertEquals(locCurrent, player.getLocation());
    }
}
