public class Tavern extends NormalLoc {
    public Tavern(Player player) {
        super(player, "Tavern");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the Tavern. Your health is fully replenished.");
        return true;
    }
}
