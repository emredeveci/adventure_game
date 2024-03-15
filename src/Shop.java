public class Shop extends NormalLoc {

    public Shop(Player player) {
        super(player, "Shop");
    }

    @Override
    public boolean onLocation() {
        System.out.println("-----------Welcome to the best shop in town.-----------");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Browse Weapons");
            System.out.println("2 - Browse Armor");
            System.out.println("3 - Exit the shop.");
            System.out.print("Your choice: ");
            int selection = Location.scanner.nextInt();
            while (selection < 1 || selection > 3) {
                System.out.print("You have made an invalid choice. Please try again: ");
            }
            switch (selection) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("See you next time, wanderer.");
                    showMenu = false;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("----------Weapons----------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Cost: " + w.getCost() + " , Damage: " + w.getDamage() + ">");
        }
        System.out.println("0 - Exit");
    }

    public void buyWeapon() {
        System.out.println("Choose the weapon you want to purchase: ");
        int selection = scanner.nextInt();

        while (selection < 0 || selection > Weapon.weapons().length) {
            System.out.println("You have made an invalid choice. Please try again: ");
            selection = scanner.nextInt();
        }

        if (selection != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selection);

            if (selectedWeapon != null) {
                if (selectedWeapon.getCost() > this.getPlayer().getGold()) {
                    System.out.println("You do not have enough gold.");
                } else {
                    System.out.println("Your purchase is complete.");
                    int balance = this.getPlayer().getGold() - selectedWeapon.getCost();
                    this.getPlayer().setGold(balance);
                    System.out.println("Your have " + this.getPlayer().getGold() + " gold left.");
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmor() {
        System.out.println("----------Weapons----------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " <Cost: " + a.getCost() + " , Defense: " + a.getDefense() + ">");
        }
        System.out.println("0 - Exit");
    }

    public void buyArmor() {
        System.out.println("Please choose the armor you want: ");

        int selectedArmorID = scanner.nextInt();
        while (selectedArmorID < 0 || selectedArmorID > Armor.armors().length) {
            System.out.println("You have made an invalid choice. Please make a correct choice.");
            selectedArmorID = scanner.nextInt();
        }

        if (selectedArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectedArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getCost() > this.getPlayer().getGold()) {
                    System.out.println("You do not have enough gold.");
                } else {
                    System.out.println("You have purchased: " + selectedArmor.getName());
                    this.getPlayer().setGold(this.getPlayer().getGold() - selectedArmor.getCost());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Your balance is: " + this.getPlayer().getGold());
                }
            }
        }
    }
}
