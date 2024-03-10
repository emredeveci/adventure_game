public class Shop extends NormalLoc{

    public Shop(Player player){
        super(player, "Shop");
    }

    @Override
    public boolean onLocation(){
        System.out.println("-----------Welcome to the best shop in town.-----------");
        System.out.println("1 - Browse Weapons");
        System.out.println("2 - Browse Armor");
        System.out.println("3 - Exit the shop.");
        System.out.print("Your choice: ");
        int selection = Location.scanner.nextInt();
        while (selection < 1 || selection > 3){
            System.out.print("You have made an invalid choice. Please try again: ");
        }
        switch(selection){
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                break;
            case 3:
                System.out.println("See you next time, wanderer.");
                return false;

        }
        return true;
    }

    public void printWeapon() {
        System.out.println("----------Weapons----------");
        for (Weapon w: Weapon.weapons()){
            System.out.println(w.getId()+ " - " + w.getName() + " <Cost: " + w.getCost() + " , Damage: " + w.getDamage() + ">");
        }
    }

    public void buyWeapon() {
        System.out.println("Choose the weapon you want to purchase: ");
        int selection = scanner.nextInt();
        while (selection < 1 || selection > Weapon.weapons().length){
            System.out.println("You have made an invalid choice. Please try again: ");
            selection = scanner.nextInt();
        }

        Weapon selectedWeapon = Weapon.getWeaponObjByID(selection);
        if(selectedWeapon != null){
            if(selectedWeapon.getCost() > this.getPlayer().getGold()){
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

    public void printArmor(){
        System.out.println("Armor");
    }
}
