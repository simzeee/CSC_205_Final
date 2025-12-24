import java.util.Scanner;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

public class Sentiment {

	public static void start (Scanner scnr) {

		System.out.println("\n");
		System.out.println("Sentiment Analysis.");
		System.out.println();

		// Load positive and negative word lists
		HashSet<String> positiveWords = new HashSet<>();
		HashSet<String> negativeWords = new HashSet<>();

		// Load positive words
		int positiveCount = loadWordList("positive.txt", positiveWords);
		if (positiveCount > 0) {
			System.out.println(positiveCount + " Positive Words Successfully Loaded.");
		} else {
			System.out.println("Error: Could not load positive.txt");
			return;
		}

		// Load negative words
		int negativeCount = loadWordList("negative.txt", negativeWords);
		if (negativeCount > 0) {
			System.out.println(negativeCount + " Negative Words Successfully Loaded.");
		} else {
			System.out.println("Error: Could not load negative.txt");
			return;
		}

		// Loop to analyze multiple files
		boolean continueAnalysis = true;

		while (continueAnalysis) {
			System.out.println();
			System.out.print("Enter the name of the text file to perform sentiment analysis:  ");
			String fileName = scnr.nextLine().trim();

			// Analyze the file
			analyzeFile(fileName, positiveWords, negativeWords);

			// Ask if user wants to analyze another file
			String response = "";
			boolean validResponse = false;

			while (!validResponse) {
				System.out.print("Would you like to analyze another file Y/N? ");
				response = scnr.nextLine().trim().toUpperCase();

				if (response.equals("Y") || response.equals("YES")) {
					validResponse = true;
					continueAnalysis = true;
				} else if (response.equals("N") || response.equals("NO")) {
					validResponse = true;
					continueAnalysis = false;
				} else {
					System.out.println("Error: Please enter Y or N.");
				}
			}
		}
	}

	// Load words from a file into a HashSet, ignoring comment lines
	private static int loadWordList(String filename, HashSet<String> wordSet) {
		try {
			File file = new File(filename);
			Scanner fileScanner = new Scanner(file);

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine().trim();

				// Skip empty lines and comment lines (starting with ;)
				if (!line.isEmpty() && !line.startsWith(";")) {
					wordSet.add(line.toLowerCase());
				}
			}

			fileScanner.close();
			return wordSet.size();

		} catch (FileNotFoundException e) {
			return 0;
		}
	}

	// Analyze a text file for sentiment
	private static void analyzeFile(String filename, HashSet<String> positiveWords, HashSet<String> negativeWords) {
		try {
			File file = new File(filename);
			Scanner fileScanner = new Scanner(file);

			int totalWords = 0;
			int posWords = 0;
			int negWords = 0;

			// Read and analyze each word in the file
			while (fileScanner.hasNext()) {
				String word = fileScanner.next().toLowerCase();

				// Remove punctuation from the word
				word = word.replaceAll("[^a-z]", "");

				if (!word.isEmpty()) {
					totalWords++;

					if (positiveWords.contains(word)) {
						posWords++;
					} else if (negativeWords.contains(word)) {
						negWords++;
					}
				}
			}

			fileScanner.close();

			// Calculate percentages
			double posPercent = 0.0;
			double negPercent = 0.0;

			if (totalWords > 0) {
				posPercent = (double) posWords / totalWords;
				negPercent = (double) negWords / totalWords;
			}

			// Determine sentiment
			String sentiment;
			if (posPercent - negPercent >= 0.05) {
				sentiment = "positive";
			} else if (negPercent - posPercent >= 0.05) {
				sentiment = "negative";
			} else {
				sentiment = "neutral";
			}

			// Display report
			System.out.println();
			System.out.println("Sentiment Report for " + filename + ":");
			System.out.println("There were " + posWords + " positive words, " + negWords +
			                   " negative words and " + totalWords + " total words.");
			System.out.println("That's " + Math.round(posPercent * 100) + "% positive and " +
			                   Math.round(negPercent * 100) + "% negative.  Overall the file's sentiment was " +
			                   sentiment + ".");

		} catch (FileNotFoundException e) {
			System.out.println("Error: File '" + filename + "' not found.");
		}
	}
}




