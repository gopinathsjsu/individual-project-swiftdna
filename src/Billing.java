import java.util.Scanner;
import processors.InventoryProcessor;
import storage.InternalDatabase;

public class Billing {
    public static void main(String[] args) throws Exception {
        try {
            // Load app configs
            InternalDatabase idb = InternalDatabase.getInstance();
            idb.loadConfigs();

            // Scanner userInput = new Scanner(System.in);
            // Get the user input file path for stock inventory
			// System.out.println("Enter the path for the Stock data: ");
            // String inventoryFilePath = userInput.nextLine();
            String inventoryFilePath = "/Users/prashanth/workspace/blackpanther/202/BillingProject/resources/inventory_items.csv";
            InventoryProcessor inventoryProcessor = new InventoryProcessor(inventoryFilePath);
            inventoryProcessor.saveInventories();
            System.out.println("Inventories saved successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
