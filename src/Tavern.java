public class Tavern extends NormalLoc {
    public Tavern(Player player) {
        super(player, "Tavern");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the Tavern. Your health is fully replenished.");
        if (this.getPlayer().getCompletedAreas().contains("The Caves") && this.getPlayer().getCompletedAreas().contains("The River") && this.getPlayer().getCompletedAreas().contains("The Forest")) {
            System.out.println("You have completed the game. Congratulations!");
            this.getPlayer().setWinGame(true);
            return false;
        }
        this.getPlayer().setHealth(this.getPlayer().getBeginningHealth());
        return true;
    }
}
