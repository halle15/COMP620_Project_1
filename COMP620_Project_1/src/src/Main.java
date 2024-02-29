package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean running = true;

		while (running == true) {

			InputReader r = new InputReader();
			ArrayList<Integer> input;

			Scanner s = new Scanner(System.in);
			System.out.println("Input a filename to test.");
			String filename = s.next();

			try {
				input = r.readFile(filename);
			} catch (IllegalArgumentException e) {
				System.out.println("Failed! Input a valid filename!");
				continue;
			}


			SheepFinder sheepFinder;
			
			try {
				sheepFinder = new SheepFinder(input);
			} catch (IllegalArgumentException e) {
				System.out.println("Failed! " + e.getMessage());
				continue;
			}
			
			System.out.println("Please input the value of sheep you would like to find.");

			int sheep = s.nextInt();

			String answer = sheepFinder.findSheep(sheep);
			if (!answer.equals("NF")) {
				System.out.println("The sheep " + sheep + " is located at " + answer);
			} else {
				System.out.println("Sheep not found!");
			}
			System.out.println("Would you like to continue?");

			answer = s.next();

			if (answer.contains("n")) {
				break;
			}
		}
	}

}
