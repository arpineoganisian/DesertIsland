public class CellPhone extends Item {

    private int chargeLevel;
    public CellPhone(String name, String description, Moveable moveable, int chargeLevel) {
        super(name, description, moveable, 0);
        this.chargeLevel = chargeLevel;
    }

    public void use(Location location) {
        if (chargeLevel <= 0) {
            System.out.println("Your phone's dead. Charge level: 0%");
            return;
        }
        if (location.getName().equals("Mountains")) {
            System.out.println("You did it!\n" +
                    "Rescue services have traced your signal. They are already on their way!");
        }
        else {
            System.out.println("No signal");
            chargeLevel -= 10;
            System.out.println("Charge level: " + chargeLevel + "%");
        }
    }
}
