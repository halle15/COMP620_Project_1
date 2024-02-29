package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

	ArrayList<Integer> output;

	public InputReader() {
		this.output = new ArrayList<Integer>();
	}
	
	/**
	 *  @param filePath The filepath to extract the list from
	 *  @return Returns the ArrayList of integers found in the file
	 *  @throws FileNotFoundException Throws exception if the given filepath is not found
	 * 
	 */
	public ArrayList<Integer> readFile(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int number = Integer.parseInt(scanner.nextLine());
                output.add(number);
            }
        } catch (FileNotFoundException e) {
        	throw new IllegalArgumentException();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return output;
	}
	
}
