package desertIsland;

import desertIsland.item.*;
import desertIsland.location.Direction;
import desertIsland.location.Location;
import desertIsland.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        GameUtils.showWelcomeMessage();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //инициализация предметов
        CellPhone item1 = new CellPhone("Phone", "Watch the charge level...", Moveable.MOBILE, 30);
        Item item2 = new UsableItem("Wilson", "Beach volleyball, nice dude", Moveable.MOBILE,
                5, "It's nice to finally talk to someone");
        Item item3 = new Item("Coconut", "Need something to crack it", Moveable.MOBILE);
        Item item4 = new Item("Shipwreck", "I wish I could build a new one...", Moveable.STATIONARY);
        Item item5 = new UsableItem("Mint", "So aromatic", Moveable.MOBILE,
                -5, "Tastes disgusting!");
        Item item6 = new UsableItem("Lime", "Ripe and sore... Should I eat it?", Moveable.MOBILE,
                -5, "Now I'm even more thirsty!");
        Item item7 = new Item("Leaf", "Banana leaf looks like something that I can use for constructing a hut. As a roof. But I need foundation though.", Moveable.MOBILE);
        Item item8 = new UsableItem("Source of freshwater", "Thank God! I will not die of thirst", Moveable.STATIONARY,
                5, "Water is life");
        Item item9 = new Item("Stone", "This can be useful", Moveable.MOBILE);
        Item item10 = new UsableItem("Fish", "A shoal of fish. But I can't catch one with my bare hands", Moveable.STATIONARY,
                -10, "Ouch! I got bit");
        Item item11 = new Item("Stick", "Those can be useful", Moveable.MOBILE);

        // инициализация предметов-результатов комбо
        Item item12 = new UsableItem("Coconut meat", "Calories: 283, protein: 3 grams, carbs: 10 grams, Fat: 27 grams.", Moveable.MOBILE,
                10, "Really quite filling");
        Item item13 = new UsableItem("Mojito virgin", "Let's grab a drink!", Moveable.MOBILE,
                -5, "Rum is missing, I don't like it");
        Item item14 = new UsableItem("Hut", "Finally, I'll get some sleep.", Moveable.STATIONARY,
                -20, "The hut was poorly constructed and collapsed in the middle of my dream");
        Item item15 = new UsableItem("Sushi", "I don't know if it's a good idea to eat raw fish, but I'm so hungry!", Moveable.MOBILE,
                10, "Really quite filling");

        // инициализация комбо для
        Combo combo1 = new Combo(item3, item9, item12);
        Combo combo2 = new Combo(item6, item5, item13 );
        Combo combo3 = new Combo(item4, item7, item14);
        Combo combo4 = new Combo(item10, item11, item15);
        // инициализация комбо для того, чтобы порядок объект-субъект не имел значение
        Combo combo5 = new Combo(item9, item3, item12);
        Combo combo6 = new Combo(item5, item6, item13 );
        Combo combo7 = new Combo(item7, item4, item14);
        Combo combo8 = new Combo(item11, item10, item15);

        List<Combo> comboList1 = new ArrayList<>(List.of(combo1, combo2, combo3, combo4, combo5, combo6, combo7, combo8));

        // инициализация локаций
        Location location1 = new Location("Beach",
                "Upon the sunny beach, upon the rising gold, my eyes listen to the light as it plays upon seawater.\n" +
                        "  > Ocean in the north\n" +
                        "  > Jungle in the south");
        Location location2 = new Location("Jungle",
                "The jungle is a chorus greens that sings the hymn of creation's soul.\n" +
                        "  Is that caves behind the bushes in the west?\n" +
                        "  > Beach in the north\n" +
                        "  > Caves in the west");
        Location location3 = new Location("Caves",
                "There's cave lake on the north. I wonder where this lake leads to?\n" +
                        "  > Jungle in the east\n" +
                        "  > Mountain in the north");
        Location location4 = new Location("Mountain",
                "Maybe it's a good place to try to make a call...\n" +
                        "  > Caves are in the south");
        Location location5 = new Location("Ocean",
                "If only I had a boat...\n" +
                        "  > Beach's in the south");

        // инициализация игрока
        System.out.print("Insert player name: ");
        Player player = new Player(reader.readLine(), location1, 10);

        // инициализация инвенторя у игрока и на локациях
        player.getInventory().add(item1);
        location1.getInventory().addAll(new ArrayList<Item>(List.of(item2, item3, item4)));
        location2.getInventory().addAll(new ArrayList<Item>(List.of(item5, item6, item7, item11)));
        location3.getInventory().addAll(new ArrayList<Item>(List.of(item8)));
        location4.getInventory().addAll(new ArrayList<Item>(List.of(item9)));
        location5.getInventory().addAll(new ArrayList<Item>(List.of(item10)));

        // инициализация связей для локаций
        location1.setDirections(location2, Direction.SOUTH);
        location2.setDirections(location1, Direction.NORTH);
        location2.setDirections(location3, Direction.WEST);
        location3.setDirections(location2, Direction.EAST);
        location3.setDirections(location4, Direction.NORTH);
        location4.setDirections(location3, Direction.SOUTH);
        location5.setDirections(location1, Direction.SOUTH);
        location1.setDirections(location5, Direction.NORTH);

        GameUtils.showCommands();

        while (true) {
            System.out.print(" --> ");
            String message = reader.readLine().toLowerCase().replaceAll("^\\s+", "").replaceAll("\\s+$", "");
            if(message.length() >= 11 && message.compareTo("look around") == 0) {
                player.lookAround();
            }
            else if(message.length() >= 2 && message.substring(0, 2).compareTo("go") == 0) {
                player.go(message.substring(2).replaceAll("^\\s+", ""));
            }
            else if(message.length() >= 9 && message.substring(0, 9).compareTo("desertIsland/inventory") == 0) {
                System.out.println("~ You've got:");
                player.getInventory().show();
            }
            else if (message.length() >= 7 && message.substring(0, 7).compareTo("pick up") == 0) {
                player.pickUp(message.substring(7).replaceAll("^\\s+", ""));
            }
            else if (message.length() >= 8 && message.substring(0, 8).compareTo("drop off") == 0) {
                player.dropOff(message.substring(8).replaceAll("^\\s+", ""));
            }
            else if (message.length() >= 4 && message.substring(0,4).compareTo("info") == 0) {
                player.showItemDescription(message.substring(4).replaceAll("^\\s+", ""));
            }
            else if (message.length() >= 3 && message.substring(0,3).compareTo("use") == 0) {
                player.useItem(message.substring(3).replaceAll("^\\s+", ""), item1);
            }
            else if (message.length() >= 5 && message.substring(0,5).compareTo("combo") == 0) {
                player.makeCombo(comboList1, message.substring(5).replaceAll("^\\s+", ""));
            }
            else if(message.length() >= 6 && message.compareTo("health") == 0)  {
                System.out.println("~ " + player.getName() + "'s health:\n  " + player.getHealth() + "%");
            }
            else if (message.length() >= 8 && message.compareTo("commands") == 0) {
                GameUtils.showCommands();
            }
            else if(message.length() >= 4 && message.compareTo("exit") == 0)  {
                System.exit(0);
            }
            else {
                System.out.println("There is no such command");
            }
        }
    }
}