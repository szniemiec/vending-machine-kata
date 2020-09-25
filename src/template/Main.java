package template;

import template.exceptions.CoinException;
import template.models.Coin;
import template.models.Inventory;
import template.models.Item;
import template.services.Balance;
import template.services.Input;
import template.services.VendingMachine;

public class Main {

    public static void main(String[] args) throws CoinException {
        Item cola = new Item(1, "cola", 1.00f);
        Item chips = new Item(2, "chips", 0.50f);
        Item candy = new Item(3, "candy", 0.60f);

        Balance balance = new Balance();

        Inventory inventory = new Inventory();

        inventory.add(cola, 10);
        inventory.add(chips, 10);
        inventory.add(candy, 10);

        VendingMachine vendingMachine = new VendingMachine(balance, inventory);

        System.out.println(vendingMachine.getTotalBalance());

        Input input = new Input();

        boolean flag = true;
        while (flag) {
            switch (input.getIntInputWithMessage("Welcome!\n" +
                    "What do you want to do?\n" +
                    "1. Buy item\n" +
                    "2. Insert coins\n" +
                    "3. Exit\n")) {
                case 1 -> buyProcess(vendingMachine, input);
                case 2 -> insertCoins(vendingMachine, input);
                case 3 -> flag = false;
                default -> System.out.println("Wrong operation\n");
            }
        }
    }

    private static void buyProcess(VendingMachine vendingMachine, Input input) throws CoinException {
        boolean flag = true;
        while (flag) {
            vendingMachine.listItems();
            switch (input.getIntInputWithMessage("1. Choose item\n" +
                    "2. Check balance\n" +
                    "3. Exit\n")) {
                case 1 -> {
                    Item item = chooseItem(vendingMachine.getInventory(), chooseId(input));
                    vendingMachine.selectProduct(item);
                }
                case 2 -> System.out.printf("%.2f\n", vendingMachine.getBalance());
                case 3 -> flag = false;
                default -> System.out.println("Wrong operation\n");
            }
        }
    }

    private static void insertCoins(VendingMachine vendingMachine, Input input) {
        Coin penny = new Coin(19.05f, 2.500f, 1.52f, 0.01f);
        Coin nickel = new Coin(21.21f, 5.000f, 1.95f, 0.05f);
        Coin dime = new Coin(17.91f, 2.268f, 1.75f, 0.10f);
        Coin quarter = new Coin(24.26f, 5.670f, 1.75f, 0.25f);
        Coin half = new Coin(30.61f, 11.340f, 2.15f, 0.50f);

        System.out.printf("%.2f\n", vendingMachine.getBalance());
        boolean flag = true;
        while (flag) {
            switch (input.getIntInputWithMessage("Select coin to insert:\n" +
                    "1. Penny\n" +
                    "2. Nickel\n" +
                    "3. Dime\n" +
                    "4. Quarter\n" +
                    "5. Half\n" +
                    "6. Exit")) {
                case 1 -> vendingMachine.acceptCoin(penny);
                case 2 -> vendingMachine.acceptCoin(nickel);
                case 3 -> vendingMachine.acceptCoin(dime);
                case 4 -> vendingMachine.acceptCoin(quarter);
                case 5 -> vendingMachine.acceptCoin(half);
                case 6 -> flag = false;
                default -> System.out.println("Wrong operation\n");
            }
        }
    }

    private static int chooseId(Input input) {
        return input.getIntInputWithMessage("Enter id: ");
    }

    private static Item chooseItem(Inventory inventory, int id) {
        for (Item item : inventory.getItemMap().keySet()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
