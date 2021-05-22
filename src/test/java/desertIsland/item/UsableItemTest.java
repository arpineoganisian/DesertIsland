package desertIsland.item;

import desertIsland.location.Location;
import desertIsland.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsableItemTest {

    Location location = new Location("Name", "Description");
    Player player = new Player("Name", location, 10);
    UsableItem posItem = new UsableItem("Name", "Description", Moveable.MOBILE, 5, "Positive");
    UsableItem negItem = new UsableItem("Name", "Description", Moveable.MOBILE, -5, "Negative");

    @Test
    @DisplayName("(testUse) проверка положительного эффекта")
    public void testUsePosImpact() {
        posItem.use(player, location);
        assertEquals(15, player.getHealth());
    }

    @Test
    @DisplayName("(testUse) проверка отрицательного эффекта")
    public void testUseNegImpact() {
        negItem.use(player, location);
        assertEquals(5, player.getHealth());
    }
}
