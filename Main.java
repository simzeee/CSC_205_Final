import java.util.Scanner;

public class Main {

	final static String author = "John Sims"; // change this to your name/MEID

	public static void main (String[] args) {

		Scanner scnr = new Scanner(System.in);
		String  menuOption;
		char    selectOption;

		do {
			System.out.println("\nWelcome to the CSC205 final project of " + author);

			System.out.println("\n");
			System.out.println("1. Select the Suitor.");
			System.out.println("2. Escape the Haunted House.");
			System.out.println("3. Sentiment Analysis.");
			System.out.println("\nQ. Quit.");

			do {
				System.out.print("\nYour option ==>");
				menuOption = scnr.nextLine();
			} while (menuOption.length()<1);

			switch (menuOption.charAt(0)) {

				case '1':
				   // select the suitor
				   Suitor.start(scnr);
				   break;

				case '2':
				   // escape the haunted house
				   HauntedHouse.start(scnr);
				   break;

				case '3':
				   // sentiment analysis
				   Sentiment.start(scnr);
				   break;
			}

		} while (menuOption.toUpperCase().charAt(0) != 'Q');
	}

    public static String getAuthor() {
        return author;
    }
}




