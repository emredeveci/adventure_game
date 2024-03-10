import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int gold;
    private String charName;
    private String name;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public void selectChar() {

        CharType[] charList = {new Samurai(), new Archer(), new Knight()};

        for (CharType charType : charList) {
            System.out.println("ID : " + charType.getId() +
                    "\t Character: " + charType.getName() +
                    "\t Damage: " + charType.getDamage() +
                    "\t Health: " + charType.getHealth() +
                    "\t Gold: " + charType.getGold());
        }

        System.out.println("-----------------");
        System.out.print("Enter your choice: ");
        int selectedChar = scanner.nextInt();
        switch (selectedChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Knight());
                break;
            case 3:
                initPlayer(new Archer());
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
}
