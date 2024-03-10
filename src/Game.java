import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);

    public void start(){

        System.out.println("Welcome to the Realm of the Wizards!");
//        System.out.println("What should we call you, wanderer?");
//        System.out.print("Your name: ");
//        String playerName = scanner.nextLine();
        Player player = new Player("Drazecros");
        System.out.println(player.getName() + "! I wonder if you are the hero the legends speak of...");
        System.out.println("To start your journey, you need to choose your character.");
        player.selectChar();

        Location location = null;

        while(true){
            player.printStatus();
            System.out.println();
            System.out.println("You can go to: ");
            System.out.println("1 - Tavern");
            System.out.println("2 - Shop");
            System.out.println("Please enter where you want to go: ");
            int selectedLoc = scanner.nextInt();
            switch (selectedLoc) {
                case 1:
                    location = new Tavern(player);
                    break;
                case 2:
                    location = new Shop(player);
                    break;
                default:
                    location = new Tavern(player);
            }

            if(!location.onLocation()){
                System.out.println("You died. We shall meet in another life...");
                break;
            }
        }

    }
}

//Illusionist can create visions to trick enemies into attacking them, quantum fragments to increase the power of their visions
//Enchanter can empower weapons to increase attacks, star dust to increase the power of their enchantments and execute enemies below 15% health if they hit a certain count
//Shade can terrify opponents and prevent them from attacking (depending on the frost percentage during their attack roll), soul shards to increase the chance to nullify enemy attacks or decrease the potency of upcoming attacks
//Psychic can make opponents attack each other or themselves (if only 1), gray matter to increase their mind control over others

//at the end of a fight, three possibilities of drops: all gold, all particles, or half gold + half particles (or they can choose?).
