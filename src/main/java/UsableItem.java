public class UsableItem extends Item{

    private String message;
    private int effect;

    public UsableItem(String name, String description, Moveable moveable, int effect, String message) {
        super(name, description, moveable);
        this.effect = effect;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getEffect() {
        return effect;
    }

    @Override
    public void use(Player player, Location location) {
        player.setHealth(player.getHealth() + this.effect);
        System.out.println(this.message);
        if (player.getHealth() > 0) {
            System.out.println("~ " + player.getName() + "'s health:\n  " + player.getHealth());
        }
        if (player.getHealth() <= 0) {
            System.out.println("~ " + player.getName() + "'s health:\n  0");
            System.out.println("OOPS! " + player.getName() + "is dead :(");
            System.exit(0);
        }
    }
}
