package storage;

import java.util.HashMap;
import java.util.HashSet;

public class InternalDatabase {
    private static InternalDatabase instance;
    private HashMap<String, String> categories = new HashMap<String, String>();
    private HashMap<String, String> quantities = new HashMap<String, String>();
    private HashMap<String, String> prices = new HashMap<String, String>();
    private HashSet<String> card_details = new HashSet<String>();
    private HashMap<String, Integer> qtyCapConfig = new HashMap<String, Integer>();

    private InternalDatabase() {}

    public static InternalDatabase getInstance() {
        if (instance == null) {
            instance = new InternalDatabase();
        }
        return instance;
    }

    public HashMap<String, String> getCategories() {
        return categories;
    }

    public HashMap<String, String> getQuantities() {
        return quantities;
    }

    public HashMap<String, String> getPrices() {
        return prices;
    }

    public HashSet<String> getCardDetails() {
        return card_details;
    }

    public HashMap<String, Integer> getCapConfig() {
        return qtyCapConfig;
    }

    public void loadConfigs() {
        qtyCapConfig.put("Essentials", 3);
        qtyCapConfig.put("Luxury", 4);
        qtyCapConfig.put("Misc", 6);
    }
}