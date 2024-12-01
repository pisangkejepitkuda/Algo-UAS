import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static class KingsManagementSystem {
        private String name; //samueldidi
        private int hp;
        private int attackPower;

        public KingsManagementSystem(String name, int hp, int attackPower) {
            this.name = name;
            this.hp = hp;
            this.attackPower = attackPower;
        }

        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public int getAttackPower() {
            return attackPower;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public void setAttackPower(int attackPower) {
            this.attackPower = attackPower;
        }

        public void takeDamage(int damage) {
            hp -= damage;
            if (hp < 0) hp = 0;
        }

        public boolean isDefeated() {
            return hp <= 0;
        }

        @Override
        public String toString() {
            return name + " (HP: " + hp + ", Attack Power: " + attackPower + ")";
        }
    }

    private static ArrayList<KingsManagementSystem> kingsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n--- Kings adi Management System Game Menu ---");
            System.out.println("1. Create Kings");
            System.out.println("2. Update Kings Stats");
            System.out.println("3. Delete Kings Stats");
            System.out.println("4. View All Kings");
            System.out.println("5. Start Battle");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createKings();
                    break;
                case 2:
                    updateKings();
                    break;
                case 3:
                    deleteKings();
                    break;
                case 4:
                    viewAllKings();
                    break;
                case 5:
                    startBattle();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the game.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    // Method Create
    public static void createKings() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter HP: ");
        int hp = scanner.nextInt();
        System.out.print("Enter Attack Power: ");
        int attackPower = scanner.nextInt();

        KingsManagementSystem newKings = new KingsManagementSystem(name, hp, attackPower);
        kingsList.add(newKings);
        System.out.println("Kings created successfully.");
    }

    // Method Update
    public static void updateKings() {
        System.out.print("Enter the index of the Kings to update: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < kingsList.size()) {
            KingsManagementSystem skibidi = kingsList.get(index);
            System.out.print("Enter new HP for " + skibidi.getName() + ": ");
            int newHp = scanner.nextInt();
            System.out.print("Enter new Attack Power for " + skibidi.getName() + ": ");
            int newAttackPower = scanner.nextInt();

            skibidi.setHp(newHp);
            skibidi.setAttackPower(newAttackPower);
            System.out.println("Kings updated successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Method Delete
    public static void deleteKings() {
        System.out.print("Enter the index of the Kings to delete: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < kingsList.size()) {
            kingsList.remove(index);
            System.out.println("Kings deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Method View All
    public static void viewAllKings() {
        if (kingsList.isEmpty()) {
            System.out.println("No Kings available.");
        } else {
            System.out.println("\n--- List of Kings ---");
            for (int i = 0; i < kingsList.size(); i++) {
                System.out.println(i + ". " + kingsList.get(i));
            }
        }
    }

    // Method Start Battle
    public static void startBattle() {
        if (kingsList.size() < 2) {
            System.out.println("Need at least 2 kings to start a battle.");
            return;
        }

        System.out.print("Enter the index of the first kings to battle: ");
        int index1 = scanner.nextInt();
        System.out.print("Enter the index of the second kings to battle: ");
        int index2 = scanner.nextInt();

        if (index1 >= 0 && index1 < kingsList.size() && index2 >= 0 && index2 < kingsList.size() && index1 != index2) {
            KingsManagementSystem skibidi1 = kingsList.get(index1);
            KingsManagementSystem skibidi2 = kingsList.get(index2);

            System.out.println("\nStarting battle between " + skibidi1.getName() + " and " + skibidi2.getName() + "!");
            while (!skibidi1.isDefeated() && !skibidi2.isDefeated()) {
                skibidi1.takeDamage(skibidi2.getAttackPower());
                System.out.println(skibidi2.getName() + " attacks " + skibidi1.getName() + "! " + skibidi1.getName() + " HP: " + skibidi1.getHp());

                if (skibidi1.isDefeated()) {
                    System.out.println(skibidi1.getName() + " has been defeated! " + skibidi2.getName() + " wins!");
                    break;
                }

                skibidi2.takeDamage(skibidi1.getAttackPower());
                System.out.println(skibidi1.getName() + " attacks " + skibidi2.getName() + "! " + skibidi2.getName() + " HP: " + skibidi2.getHp());

                if (skibidi2.isDefeated()) {
                    System.out.println(skibidi2.getName() + " has been defeated! " + skibidi1.getName() + " wins!");
                    break;
                }
            }
        } else {
            System.out.println("Invalid indices for battle.");
        }
    }
}