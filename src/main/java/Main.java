import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //инициализация предметов
        Item item1 = new Item("Сell phone", "Watch the charge level...", Moveable.MOBILE);
        Item item2 = new Item("Beach volleyball", "His name is Wilson", Moveable.MOBILE);
        Item item3 = new Item("Mint", "So aromatic", Moveable.STATIONARY);
        Item item4 = new Item("Lime", "Ripe and sore... And what am I supposed to do with it?", Moveable.STATIONARY);
        Item item5 = new Item("Shipwreck", "I wish I could build a new one...", Moveable.STATIONARY);
        Item item6 = new Item("Banana leaf", "Looks something that I can use for constructing a hut", Moveable.STATIONARY);
        Item item7 = new Item("Coconut", "Need something to crack it", Moveable.STATIONARY);

        // инициализация локаций
        Location location1 = new Location("Beach",
                "Описание домика");
        Location location2 = new Location("Беседка",
                "Описание беседки.");
        Location location3 = new Location("Огород",
                "Описание огорода.");
        Location location4 = new Location("Речка",
                "Описание речки.");

        // инициализация игрока
        System.out.print("Insert player name: ");
        Player player = new Player(reader.readLine(), location1, 50);

        // инициализация инвенторя у игрока и на локациях
        player.getInventory().addAll(new ArrayList<Item>(Arrays.asList(item1)));
        location1.getInventory().addAll(new ArrayList<Item>(Arrays.asList(item2)));
        location2.getInventory().addAll(new ArrayList<Item>(Arrays.asList(item3)));
        location3.getInventory().addAll(new ArrayList<Item>(Arrays.asList(item4)));
        location4.getInventory().addAll(new ArrayList<Item>(Arrays.asList(item5)));

        // инициализация связей для локаций
        location1.setDirections(location2, Direction.EAST);
        location2.setDirections(location1, Direction.WEST);
        location2.setDirections(location3, Direction.SOUTH);
        location3.setDirections(location2, Direction.NORTH);
        location3.setDirections(location4, Direction.WEST);
        location4.setDirections(location3, Direction.EAST);
        location4.setDirections(location1, Direction.NORTH);
        location1.setDirections(location4, Direction.SOUTH);

        System.out.println(" ----------------------------------------------------------------------\n" +
                "                                КОМАНДЫ                              \n" +
                " ----------------------------------------------------------------------\n" +
                " look around                     --> осмотреться на местности\n" +
                " go west / east / south / north  --> переместиться на другую локацию\n" +
                " inventory                       --> список доступного инвенторя игрока\n" +
                " pick up [item name]             --> поместить предмет в свой инвентарь\n" +
                " drop off [item name]            --> убрать предмет из своего инвентаря\n" +
                " info [item name]                --> описание предмета\n" +
                " commands                        --> полный список команд\n" +
                " exit                            --> выход из игры\n" +
                " ----------------------------------------------------------------------");

        while (true) {
            String message = reader.readLine().toLowerCase().replaceAll("^\\s+", "");
            if(message.length() >= 11 && message.compareTo("look around") == 0) {
                player.lookAround();
            }
            else if(message.length() >= 2 && message.substring(0, 2).compareTo("go") == 0) {
                player.go(message.substring(2));
            }
            else if(message.length() >= 9 && message.substring(0, 9).compareTo("inventory") == 0) {
                System.out.println("~ You've got:");
                player.getInventory().show();
            }
            else if(message.length() >= 4 && message.substring(0, 4).compareTo("exit") == 0)  {
                System.exit(0);
            }
            else if (message.length() >= 7 && message.substring(0, 7).compareTo("pick up") == 0) {
                player.pickUp(message.substring(7));
            }
            else if (message.length() >= 8 && message.substring(0, 8).compareTo("drop off") == 0) {
                player.dropOff(message.substring(8).trim());
            }
            else if (message.length() >= 4 && message.substring(0, 4).compareTo("info") == 0) {
                player.showItemDescription(message.substring(4).trim());
            }
            else {
                System.out.println("There is no such command");
            }


        }

    }
}

//создать еще одну строку для названий айтемов в родительном падеже?