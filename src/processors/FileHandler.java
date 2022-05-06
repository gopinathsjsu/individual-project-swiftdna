package processors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public static ArrayList<String> getFileContent(String path) {
        ArrayList<String> fileContent = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(path).toFile()));
			String buffer = "";
			boolean firstLineExists = true;
			while ((buffer = bufferedReader.readLine()) != null) {
				if (firstLineExists) {
					firstLineExists = false;
					continue;
				}
				fileContent.add(buffer);
			}
		} catch (Exception e) {
            System.out.println(e);
		}
        return fileContent;
	}

	public static void writeFile(String path, ArrayList<List<String>> data) {
		try {
			FileWriter writer = new FileWriter(path);
			String collect = "";
			// Headers
			collect += "Item,Quantity,Price,TotalPrice\n";
			for (List<String> orderLine: data) {
				collect += (String) orderLine.stream().collect(Collectors.joining(",")) + "\n";
			}

			writer.write(collect);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public static void logError(String data) {
		try {
			Path currentRelativePath = Paths.get("output/error.txt");
			String fileAbsolutePath = currentRelativePath.toAbsolutePath().toString();
			FileWriter writer = new FileWriter(fileAbsolutePath);
			if (data.length() > 0) {
				writer.write("Error: \n");
			}
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
}