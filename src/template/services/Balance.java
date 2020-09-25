package template.services;

import template.exceptions.CoinException;
import template.models.Coin;

import java.util.HashMap;
import java.util.Map;

public class Balance {

    private final Coin penny = new Coin(19.05f, 2.500f, 1.52f, 0.01f);
    private final Coin nickel = new Coin(21.21f, 5.000f, 1.95f, 0.05f);
    private final Coin dime = new Coin(17.91f, 2.268f, 1.75f, 0.10f);
    private final Coin quarter = new Coin(24.26f, 5.670f, 1.75f, 0.25f);
    private final Coin half = new Coin(30.61f, 11.340f, 2.15f, 0.50f);
    private final Map<Coin, Integer> moneyMap;
    private float balance = 0;

    public Balance() {
        this.moneyMap = new HashMap<>();
        this.add(nickel, 10);
        this.add(dime, 10);
        this.add(quarter, 10);
        this.add(half, 10);
    }

    public Map<Coin, Integer> getMoneyMap() {
        return this.moneyMap;
    }

    public float getBalance() {
        return this.balance;
    }

    public void insertCoin(Coin coin) {
        this.balance += coin.getValue();
        add(coin, 1);
        System.out.printf("%.2f\n", this.balance);
    }

    public void add(Coin coin, int quantity) {
        if (coin.getValue() != penny.getValue()) {
            if (!this.moneyMap.containsKey(coin)) {
                this.moneyMap.put(coin, quantity);
            } else {
                int currentQuantity = moneyMap.get(coin);
                this.moneyMap.replace(coin, currentQuantity + quantity);
            }
        } else {
            System.out.println("Sorry, we don't accept this type of coins\n");
        }
    }

    public void removeCoin(Coin coin, int amount) throws CoinException {
        int currentQuantity = this.moneyMap.get(coin);
        if (currentQuantity > 0) {
            this.moneyMap.replace(coin, currentQuantity - amount);
        }
        String coinName = coinValueToName(coin);
        System.out.println("Dropped " + coin.getValue() * amount + "$ in " + coinName);
    }

    public float getTotalBalance() {
        float balance = 0;
        for (Coin coin : this.moneyMap.keySet()) {
            int quantity = this.moneyMap.get(coin);
            balance += coin.getValue() * quantity;
        }
        return balance;
    }

    public void returnChange(float change) throws CoinException {
        int amount, amountOfHalves, amountOfQuarters, amountOfDimes, amountOfNickels;
        amount = Math.round(change * 100);
        amountOfHalves = amount / 50 % 50;
        amount = amount - amountOfHalves * 50;
        amountOfQuarters = (amount - amountOfHalves) / 25 % 25;
        amount = amount - amountOfQuarters * 25;
        amountOfDimes = amount / 10 % 10;
        amount = amount - amountOfDimes * 10;
        amountOfNickels = amount / 5 % 5;

        System.out.println("Returning change: ");
        removeCoin(half, amountOfHalves);
        removeCoin(quarter, amountOfQuarters);
        removeCoin(dime, amountOfDimes);
        removeCoin(nickel, amountOfNickels);
    }

    private String coinValueToName(Coin coin) throws CoinException {
        return switch (Math.round(coin.getValue() * 100)) {
            case 5 -> "Nickel";
            case 10 -> "Dime";
            case 25 -> "Quarter";
            case 50 -> "Half";
            default -> throw new CoinException("Wrong coin\n");
        };
    }
}
