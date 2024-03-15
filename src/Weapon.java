import java.util.Objects;

public class Weapon {
    private String name;
    private int id;
    private int damage;
    private int cost;

    public Weapon(String name, int id, int damage, int cost) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.cost = cost;
    }

    public static Weapon[] weapons() {
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon("Dual Swords", 1, 3, 25);
        weaponList[1] = new Weapon("Crossbow", 2, 4, 10);
        weaponList[2] = new Weapon("Long Sword", 3, 5, 25);
        return weaponList;
    }

    public static Weapon getWeaponObjByID(int id) {
        for (Weapon w : Weapon.weapons()) {
            if (w.getId() == id) {
                return w;
            }
        }
        return null;
    }

    public static Weapon getWeaponObjByName(String name) {
        for (Weapon w : Weapon.weapons()) {
            if (Objects.equals(w.getName(), name)) {
                return w;
            }
        }
        return null;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
