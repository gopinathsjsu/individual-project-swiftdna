package processors;
import java.util.ArrayList;
import java.util.Set;

import storage.InternalDatabase;

public class CardDetailsProcessor extends FileHandler {
    private String filePath;

    public CardDetailsProcessor(String filePath) {
        this.filePath = filePath;
    }

    public void saveCardDetails() {
        ArrayList<String> fileContent = super.getFileContent(this.filePath);
        for (String line : fileContent) {
			String[] item = line.split(",");
			// System.out.println(item[0]);
            InternalDatabase idb = InternalDatabase.getInstance();
            // Store card details
            idb.getCardDetails().add(item[0]);
		}
    }

    public void printCardDetails() {
        InternalDatabase idb = InternalDatabase.getInstance();
        Set<String> cards = idb.getCardDetails();
        System.out.println(cards);
    }
}
