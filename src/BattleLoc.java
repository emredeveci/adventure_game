import java.util.Random;

public abstract class BattleLoc extends Location {
    private Enemy enemy;
    private String reward;
    private int maxEnemies;

    public BattleLoc(Player player, String name, Enemy enemy, String reward, int maxEnemies) {
        super(player, name);
        this.enemy = enemy;
        this.reward = reward;
        this.maxEnemies = maxEnemies;
    }

    @Override
    public boolean onLocation() {
        int enemyCount = this.randomEnemyCount();
        if (enemyCount == 1) {
            System.out.println("You are now here: " + this.getName() + ". \nBe careful! There is a " + this.getEnemy().getName() + " here.");
        } else {
            System.out.println("You are now here: " + this.getName() + ". \nBe careful! There are " + enemyCount + " " + this.getEnemy().getName() + "s here.");
        }

        System.out.print("<F>ight or <E>scape");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("F")) {
            //fight
        }
        return true;
    }

    public int randomEnemyCount() {
        Random r = new Random();
        return r.nextInt(this.getMaxEnemies()) + 1;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

    public void setMaxEnemies(int maxEnemies) {
        this.maxEnemies = maxEnemies;
    }
}