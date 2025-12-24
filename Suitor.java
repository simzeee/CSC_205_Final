import java.util.Scanner;

public class Suitor {

	public static void start (Scanner scnr) {
		int numberOfSuitors = 0;
		boolean validInput = false;

		System.out.println("\n");
		System.out.println("Select the suitor.");

		while (!validInput) {
			System.out.println("Enter the number of Suitors:");

			if (scnr.hasNextInt()) {
				numberOfSuitors = scnr.nextInt();

				if ((numberOfSuitors > 0)&&(numberOfSuitors < 100)) {
					validInput = true;
				} else {
					System.out.println("Error: Number of suitors must be greater than 0 and less than 100.");
				}
			} else {
				System.out.println("Error: Please enter a valid integer.");
				scnr.nextLine(); // consume the entire invalid line
			}
		}

		scnr.nextLine(); // consume the remaining newline

	}
}




