import java.util.ArrayList;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private ArrayList<String> loot;

    public Inventory() {
        this.weapon = new Weapon("Fist", -1, 0, 0);
        this.armor = new Armor(-1, "Cloth Armor", 0, 0);
        this.loot = new ArrayList<String>();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public ArrayList<String> getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot.add(loot);
    }
}
