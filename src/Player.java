import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int beginningHealth;
    private int gold;
    private String charName;
    private String name;
    private Inventory inventory;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> completedAreas;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {

        CharType[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("------------------------------------------------------");

        for (CharType charType : charList) {
            System.out.println("ID : " + charType.getId() +
                    "\t Character: " + charType.getName() +
                    "\t Damage: " + charType.getDamage() +
                    "\t Health: " + charType.getHealth() +
                    "\t Gold: " + charType.getGold());
        }

        System.out.println("------------------------------------------------------");
        System.out.print("Enter your choice: ");
        int selectedChar = scanner.nextInt();
        switch (selectedChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println(this.getName() + ", the " + this.getCharName() + "! Shall your quests and battles lead you to glory and fame. Good luck!");
    }

    public void initPlayer(CharType gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setGold(gameChar.getGold());
        this.setCharName(gameChar.getName());
        this.setBeginningHealth(gameChar.getHealth());
        this.completedAreas = new ArrayList<String>();
    }

    public void printStatus() {
        System.out.println("Weapon: " + this.getInventory().getWeapon().getName() +
                ", Armor: " + this.getInventory().getArmor().getName() +
                ", Defense: " + this.getInventory().getArmor().getDefense() +
                ", Attack: " + this.getDamage() +
                ", Health: " + this.getHealth() +
                ", Gold: " + this.getGold());
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getBeginningHealth() {
        return beginningHealth;
    }

    public void setBeginningHealth(int beginningHealth) {
        this.beginningHealth = beginningHealth;
    }

    public ArrayList<String> getCompletedAreas() {
        return completedAreas;
    }

    public void setCompletedAreas(String completedMission) {
        this.completedAreas.add(completedMission);
    }
}
