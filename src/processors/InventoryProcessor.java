package processors;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import storage.InternalDatabase;

public class InventoryProcessor extends FileHandler {
    private String filePath;

    public InventoryProcessor(String filePath) {
        this.filePath = filePath;
    }

    public void saveInventories() {
        ArrayList<String> fileContent = super.getFileContent(this.filePath);
        for (String line : fileContent) {
			String[] item = line.split(",");
			// System.out.println(item[0]);
            InternalDatabase idb = InternalDatabase.getInstance();
            // Store category
            idb.getCategories().put(item[0], item[1]);
            // Store Quantity
            idb.getQuantities().put(item[0], item[2]);
            // Store Price
            idb.getPrices().put(item[0], item[3]);
		}
    }

    // public void showPrices() {
    //     InternalDatabase idb = InternalDatabase.getInstance();
    //     HashMap<String, String> priceMap = idb.getPrices();
    //     System.out.println(priceMap);
    // }
}
