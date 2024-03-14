public class Armor {
    private int id;
    private String name;
    private int defense;
    private int cost;

    public Armor(int id, String name, int defense, int cost) {
        this.id = id;
        this.name = name;
        this.defense = defense;
        this.cost = cost;
    }

    public static Armor[] armors() {
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1, "Hide Armor", 1, 15);
        armorList[1] = new Armor(2, "Leather Armor", 3, 25);
        armorList[2] = new Armor(3, "Chainmail", 5, 40);

        return armorList;
    }

    public static Armor getWeaponObjByID(int id) {
        for (Armor a : Armor.armors()) {
            if (a.getId() == id) {
                return a;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
