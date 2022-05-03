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

            // System.out.println("Enter the path for the Cards data: ");
            // String cardDetailsFilePath = userInput.nextLine();
            String cardDetailsFilePath = "/Users/prashanth/workspace/blackpanther/202/BillingProject/resources/card_details.csv";
            CardDetailsProcessor cardDetailsProcessor = new CardDetailsProcessor(cardDetailsFilePath);
            cardDetailsProcessor.saveCardDetails();
            System.out.println("Card Details saved successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
