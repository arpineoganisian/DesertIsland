public class CellPhone extends Item {

    private int chargeLevel;

    public CellPhone(String name, String description, Moveable moveable, int chargeLevel) {
        super(name, description, moveable);
        this.chargeLevel = chargeLevel;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nCharge level: " + chargeLevel + "%";
    }

    @Override
    public void use(Player player, Location location) {
        if (chargeLevel <= 0) {
            System.out.println("Your phone's dead. Charge level: 0%");
            return;
        }
        if (location.getName().equals("Mountain")) {
            System.out.println("You did it!\n" +
                    "Rescue services have traced your signal. They are already on their way!");
            System.exit(0);
        }
        else {
            System.out.println("No signal");
            chargeLevel -= 10;
            System.out.println("Charge level: " + chargeLevel + "%");
        }
    }
}
