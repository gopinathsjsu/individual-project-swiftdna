package processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.InternalDatabase;

public class OrderProcessor extends FileHandler {
    private String filePath;
    ArrayList<List<String>> orderDetails = new ArrayList<List<String>>();

    public OrderProcessor(String filePath) {
        this.filePath = filePath;
    }

    public int getInt(String value) {
        return Integer.parseInt(value);
    }

    public boolean isOrderValid() {
        ArrayList<String> fileContent = super.getFileContent(this.filePath);
        InternalDatabase idb = InternalDatabase.getInstance();
        boolean result = true;
        String errorMessage = "";
        Integer orderTotal = 0;
        String cardNumber = "";
        HashMap<String, Integer> capTracker = new HashMap<String, Integer>();
        for (String line : fileContent) {
            ArrayList<String> currentItemList = new ArrayList<String>();
            String[] item = line.split(",");
            // Item - item[0] && Qty - item[1]
            String itemName = item[0];
            String stockQty = idb.getQuantities().get(item[0]);
            String itemCategory = idb.getCategories().get(item[0]);
            Integer capQtyLimit = idb.getCapConfig().get(itemCategory);
            Integer currentCapQty = capTracker.get(itemCategory);
            if (currentCapQty == null) {
                capTracker.put(itemCategory, 0);
                currentCapQty = 0;
            }
            String itemQty = item[1];
            if (getInt(stockQty) < getInt(itemQty)) {
                result = false;
                errorMessage = "Stocks are not available for " + itemName + "\n";
                errorMessage += "Please correct quantities.";
                super.logError(errorMessage);
                break;
            }
            if ((currentCapQty + getInt(itemQty)) > capQtyLimit) {
                result = false;
                errorMessage = "Cap for " + itemCategory + " is exceeded!";
                super.logError(errorMessage);
                break;
            }
            // Add qty to cap tracker
            capTracker.put(itemCategory, currentCapQty + getInt(itemQty));
            // Get item price
            String stockItemPrice = idb.getPrices().get(item[0]);
            Integer itemTotal = getInt(itemQty) * getInt(stockItemPrice);
            orderTotal += itemTotal;
            currentItemList.add(itemName);
            currentItemList.add(itemQty);
            currentItemList.add(itemTotal.toString());
            // Card Number
            if (item.length > 2 && item[2] != null) {
                cardNumber = item[2];
            }
            this.orderDetails.add(currentItemList);
        }
        if (!idb.getCardDetails().contains(cardNumber)) {
            idb.getCardDetails().add(cardNumber);
        }
        int itemListIndex = 0;
        for(List<String> orderLine: this.orderDetails) {
            if (itemListIndex == 0) {
                orderLine.add(orderTotal.toString());
            }
            itemListIndex++;
        }
        return result;
    }
    
    public void printReceipt(String outputFilePath) {
        // Clear error file if it had any contents previously
        super.logError("");
        super.writeFile(outputFilePath, this.orderDetails);
    }
}
