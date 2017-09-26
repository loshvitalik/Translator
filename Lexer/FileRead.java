import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
	public String[] strings;
	
	public FileRead(String name) {
		BufferedReader reader;
		try {
				ArrayList <String> strings = new ArrayList<>();
				File file = new File(name);
				FileReader fr = new FileReader(file);
				reader = new BufferedReader(fr);
				String line = reader.readLine();
				while (line != null) {
					strings.add(line);
			 		line = reader.readLine();
				}
				this.strings = strings.toArray(new String[strings.size()]);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
