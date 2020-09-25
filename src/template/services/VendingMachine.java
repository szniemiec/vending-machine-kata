package template.services;

import template.exceptions.CoinException;
import template.models.Coin;
import template.models.Inventory;
import template.models.Item;

import java.util.Map;

public class VendingMachine {

    private final Balance balance;
    private final Inventory inventory;

    public VendingMachine(Balance balance, Inventory inventory) {
        this.balance = balance;
        this.inventory = inventory;
    }

    public float getBalance() {
        return balance.getBalance();
    }

    public float getTotalBalance() {
        return balance.getTotalBalance();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void listItems() {
        Map<Item, Integer> itemMap = inventory.getItemMap();
        for (Item item : itemMap.keySet()) {
            String name = item.getName();
            double price = item.getPrice();
            System.out.printf(item.getId() + ". " + name.toUpperCase() + "    Price: " +
                    "%.2f" + "    Quantity: " + itemMap.get(item) + "\n", price);
        }
        System.out.println();
    }

    public void acceptCoin(Coin coin) {
        balance.insertCoin(coin);
    }

    public void selectProduct(Item item) throws CoinException {
        if (item != null) {
            float itemValue = item.getPrice();
            if (getBalance() >= itemValue) {
                inventory.remove(item);
                balance.returnChange(getBalance() - itemValue);
                System.out.println("Enjoy!");
            }
        } else {
            System.out.println("No such product");
        }
    }
}