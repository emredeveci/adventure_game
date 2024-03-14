public class Enemy {
    private int id;
    private int damage;
    private int health;
    private String name;
    private int reward;
    private int beginningHealth;

    public Enemy(int id, String name, int damage, int health, int reward) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.beginningHealth = health;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getBeginningHealth() {
        return beginningHealth;
    }

    public void setBeginningHealth(int beginningHealth) {
        this.beginningHealth = beginningHealth;
    }
}
