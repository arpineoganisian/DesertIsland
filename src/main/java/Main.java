import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        //приветственное сообщение
        System.out.println("\n ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "   Your cruise liner was shipwrecked...\n" +
                "   You are on the beach in the middle of nowhere, desperately trying to find your way back home...\n" +
                " ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //инициализация предметов
        CellPhone item1 = new CellPhone("Phone", "Watch the charge level...", Moveable.MOBILE, 30);
        Item item2 = new UsableItem("Wilson", "Beach volleyball, nice dude", Moveable.MOBILE,
                10, "It's nice to finally talk to someone");
        Item item3 = new Item("Coconut", "Need something to crack it", Moveable.MOBILE);
        Item item4 = new Item("Shipwreck", "I wish I could build a new one...", Moveable.STATIONARY);
        Item item5 = new Item("Mint", "So aromatic, but tastes not good", Moveable.MOBILE);
        Item item6 = new UsableItem("Lime", "Ripe and sore... And what am I supposed to do with it?", Moveable.MOBILE,
                -5, ".");
        Item item7 = new Item("Banana leaf", "Looks like something that I can use for constructing a hut. As a roof. But I need foundation though.", Moveable.MOBILE);
        Item item8 = new UsableItem("Source of freshwater", "Thank God! I will not die of thirst", Moveable.STATIONARY,
                10, ".");
        Item item9 = new UsableItem("Stone", "Thank God! I will not die of thirst", Moveable.STATIONARY,
                10, ".");
        Item item10 = new Item("Fishes", "A shoal of fish. But I can't catch one with my bare hands", Moveable.STATIONARY);
        Item item11 = new Item("Sticks", "Those cane be useful", Moveable.MOBILE);


        // инициализация локаций
        Location location1 = new Location("Beach",
                "Upon the sunny beach, upon the rising gold, my eyes listen to the light as it plays upon seawater.\n" +
                        "  Boundless ocean in the north and scaring jungle in the south.");
        Location location2 = new Location("Jungle",
                "The jungle is a chorus greens that sings the hymn of creation's soul.\n" +
                        "  Is that caves behind the bushes in the west?");
        Location location3 = new Location("Caves",
                "There's cave lake on the north. I wonder where this lake leads to?");
        Location location4 = new Location("Mountain",
                "Maybe it's a good place to try to make a call");
        Location location5 = new Location("Ocean",
                "If only I had a boat...");

        // инициализация игрока
        System.out.print("Insert player name: ");
        Player player = new Player(reader.readLine(), location1, 20);

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

        Game.showCommands();

        while (true) {
            System.out.print(" --> ");
            String message = reader.readLine().toLowerCase().replaceAll("^\\s+", "").replaceAll("\\s+$", "");
            if(message.length() >= 11 && message.compareTo("look around") == 0) {
                player.lookAround();
            }
            else if(message.length() >= 2 && message.substring(0, 2).compareTo("go") == 0) {
                player.go(message.substring(2).replaceAll("^\\s+", ""));
            }
            else if(message.length() >= 9 && message.substring(0, 9).compareTo("inventory") == 0) {
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
            else if(message.length() >= 6 && message.compareTo("health") == 0)  {
                System.out.println("~ " + player.getName() + "'s health:\n  " + player.getHealth() + "%");
            }
            else if (message.length() >= 8 && message.compareTo("commands") == 0) {
                Game.showCommands();
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

//создать еще одну строку для названий айтемов в родительном падеже?