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
        if (selection.equalsIgnoreCase("F") && combat(enemyCount)) {
            System.out.println("You won the fight.");
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You died.");
            return false;
        }
        return true;
    }

    public boolean combat(int enemyCount) {

        for (int i = 1; i <= enemyCount; i++) {
            this.getEnemy().setHealth(this.getEnemy().getBeginningHealth());
            playerStats();
            System.out.println();
            enemyStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getEnemy().getHealth() > 0) {
                System.out.println("(H)it or (E)scape");
                String selectCombat = scanner.nextLine();
                if (selectCombat.equalsIgnoreCase("H")) {
                    System.out.println("You landed a hit.");
                    this.getEnemy().setHealth(this.getEnemy().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getEnemy().getHealth() > 0) {
                        System.out.println();
                        System.out.println("The opponent attacked you.");
                        int enemyDamage = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getDefense();
                        if (enemyDamage < 0) {
                            enemyDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getEnemy().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You have slain your opponent.");
                System.out.println("You have earned " + this.getEnemy().getReward() + " gold.");
                this.getPlayer().setGold(this.getPlayer().getGold() + this.getEnemy().getReward());
                System.out.println("Your balance is: " + this.getPlayer().getGold());
            } else {
                return false;
            }
        }

        return true;
    }

    public void afterHit() {
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getEnemy().getName() + " health: " + this.getEnemy().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Your current stats: ");
        System.out.println("--------------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Defense: " + this.getPlayer().getInventory().getArmor().getDefense());
        System.out.println("Gold: " + this.getPlayer().getGold());
    }

    public void enemyStats(int i) {
        System.out.println(this.getEnemy().getName() + " number #" + i);
        System.out.println(this.getEnemy().getName() + " stats");
        System.out.println("--------------------------");
        System.out.println("Health: " + this.getEnemy().getHealth());
        System.out.println("Damage: " + this.getEnemy().getDamage());
        System.out.println("Reward: " + this.getEnemy().getReward());
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