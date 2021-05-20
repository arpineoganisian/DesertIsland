public class Game {

    public static void showCommands() {
        System.out.println(" -----------------------------------------------------------------------------\n" +
                "                               COMMANDS\n" +
                " -----------------------------------------------------------------------------\n" +
                " look around                            --> осмотреться на местности\n" +
                " go west / east / south / north         --> переместиться на другую локацию\n" +
                " inventory                              --> список доступного инвенторя игрока\n" +
                " pick up [item name]                    --> поместить предмет в свой инвентарь\n" +
                " drop off [item name]                   --> убрать предмет из своего инвентаря\n" +
                " info [item name]                       --> описание предмета\n" +
                " use [item name]                        --> использовать предмет\n" +
                " combo [item name] [another item name]  --> создать что-то из двух предметов\n" +
                " health                                 --> текущее состояние игрока\n" +
                " commands                               --> полный список команд\n" +
                " exit                                   --> выход из игры\n" +
                " -----------------------------------------------------------------------------");
    }

    public static Item findItem(Inventory inventory, String string) {
        for(Item i : inventory.getItems()) {
            if(i.getName().toLowerCase().equals(string)) {
                return i;
            }
        }
        return null;
    }
}
