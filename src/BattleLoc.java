import java.util.Objects;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Enemy enemy;
    private String reward;
    private int maxEnemies;
    private Random random = new Random();

    public BattleLoc(Player player, String name, Enemy enemy, String reward, int maxEnemies) {
        super(player, name);
        this.enemy = enemy;
        this.reward = reward;
        this.maxEnemies = maxEnemies;
    }

    @Override
    public boolean onLocation() {

        switch (this.getName()) {
            case "The Caves":
                if (this.getPlayer().getCompletedAreas().contains("The Caves")) {
                    System.out.println("You cannot re-enter this area. It's already been completed.");
                    return true;
                }
                break;
            case "The Forest":
                if (this.getPlayer().getCompletedAreas().contains("The Forest")) {
                    System.out.println("You cannot re-enter this area. It's already been completed.");
                    return true;
                }
                break;
            case "The River":
                if (this.getPlayer().getCompletedAreas().contains("The River")) {
                    System.out.println("You cannot re-enter this area. It's already been completed.");
                    return true;
                }
                break;
        }

        int enemyCount = this.randomEnemyCount();
        if (enemyCount == 1) {
            System.out.println("You are now here: " + this.getName() + ". \nBe careful! There is a " + this.getEnemy().getName() + " here.");
        } else {
            System.out.println("You are now here: " + this.getName() + ". \nBe careful! There are " + enemyCount + " " + this.getEnemy().getName() + "s here.");
        }

        System.out.print("<F>ight or <E>scape: ");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("F") && combat(enemyCount)) {
            System.out.println("You won the fight.");
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
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

            boolean playerAttacksFirst = random.nextBoolean();

            while (this.getPlayer().getHealth() > 0 && this.getEnemy().getHealth() > 0) {
                if (playerAttacksFirst) {
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
                } else {
                    if (this.getEnemy().getHealth() > 0) {
                        System.out.println();
                        System.out.println("The opponent attacked you.");
                        int enemyDamage = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getDefense();
                        if (enemyDamage < 0) {
                            enemyDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
                        afterHit();
                        System.out.println("(H)it or (E)scape");
                        String selectCombat = scanner.nextLine();
                        if (selectCombat.equalsIgnoreCase("H")) {
                            System.out.println("You landed a hit.");
                            this.getEnemy().setHealth(this.getEnemy().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (this.getEnemy().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You have slain your opponent.");

                if (Objects.equals(this.getEnemy().getName(), "Snake")) {
                    String loot = lootGeneration();

                    if (Objects.equals(loot, "Long Sword") || Objects.equals(loot, "Crossbow") || Objects.equals(loot, "Dual Swords")) {
                        this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByName(loot));
                        System.out.println("You have acquired: " + loot);
                    } else if (Objects.equals(loot, "Hide Armor") || Objects.equals(loot, "Chainmail") || Objects.equals(loot, "Leather Armor")) {
                        this.getPlayer().getInventory().setArmor(Armor.getArmorObjByName(loot));
                        System.out.println("You have acquired: " + loot);
                    } else if (Objects.equals(loot, "10") || Objects.equals(loot, "5") || Objects.equals(loot, "1")) {
                        this.getPlayer().setGold(this.getPlayer().getGold() + Integer.parseInt(loot));
                        System.out.println("You have acquired: " + loot + " gold");
                    } else {
                        System.out.println("Bad luck. You have earned nothing.");
                    }

                }
                if (this.getEnemy().getName() != "Snake") {
                    System.out.println("You have earned " + this.getEnemy().getReward() + " gold.");
                    this.getPlayer().setGold(this.getPlayer().getGold() + this.getEnemy().getReward());
                }

                if (i == enemyCount && !Objects.equals(this.getEnemy().getName(), "Snake")) {
                    this.getPlayer().getInventory().setLoot(this.getReward());
                    this.getPlayer().setCompletedAreas(this.getName());
                    System.out.println(this.getReward() + " has been added to your inventory.");
                    System.out.println("Your inventory: " + this.getPlayer().getInventory().getLoot());
                    System.out.println("Your completed areas: " + this.getPlayer().getCompletedAreas());
                }
                System.out.println("Your balance is: " + this.getPlayer().getGold());
            } else {
                return false;
            }
        }

        return true;
    }

    public String lootGeneration() {
        Random random = new Random();
        double probability = random.nextDouble() * 100;
        if (probability < 15) {
            return weaponGeneration();
        } else if (probability < 30) {
            return armorGeneration();
        } else if (probability < 55) {
            return goldGeneration();
        } else {
            return "Nothing";
        }
    }

    public String weaponGeneration() {
        Random random = new Random();
        double probability = random.nextDouble() * 100;
        if (probability < 20) {
            return "Long Sword";
        } else if (probability < 50) {
            return "Crossbow";
        } else {
            return "Dual Swords";
        }
    }

    public String armorGeneration() {
        Random random = new Random();
        double probability = random.nextDouble() * 100;
        if (probability < 20) {
            return "Chainmail";
        } else if (probability < 50) {
            return "Leather Armor";
        } else {
            return "Hide Armor";
        }
    }

    public String goldGeneration() {
        Random random = new Random();
        double probability = random.nextDouble() * 100;
        if (probability < 20) {
            return "10";
        } else if (probability < 50) {
            return "5";
        } else {
            return "1";
        }
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