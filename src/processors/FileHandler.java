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
}