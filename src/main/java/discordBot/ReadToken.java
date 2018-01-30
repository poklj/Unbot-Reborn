package discordBot;

import java.io.*;

public class ReadToken {
	public String readToken() {
		String Filename = null;
		// Grab File From Config Relative to Jar file
		try {
			Filename = new File("./config/token.txt").getCanonicalPath();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		String line = null;

		try {
			// Neatly package up the Read, We really don't need to Do much more then this
			FileReader fileReader = new FileReader(Filename);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			line = bufferedReader.readLine();
			System.out.println(line);

			bufferedReader.close();
			try {
				if (line == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("No Token in token file: Please Add token to token file");
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (IOException ex1) {

			ex1.printStackTrace();
		}
		return line;

	}
}
