package template.models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<Item, Integer> itemMap;

    public Inventory() {
        this.itemMap = new HashMap<>();
    }

    public Map<Item, Integer> getItemMap() {
        return itemMap;
    }

    public void add(Item item, int quantity) {
        if (!itemMap.containsKey(item)) {
            itemMap.put(item, quantity);
        } else {
            int currentQuantity = itemMap.get(item);
            itemMap.replace(item, currentQuantity + quantity);
        }
    }

    public void remove(Item item) {
        int currentQuantity = itemMap.get(item);
        if (currentQuantity > 0) {
            itemMap.replace(item, currentQuantity - 1);
        } else {
            System.out.println("Out of stock");
        }
    }
}
