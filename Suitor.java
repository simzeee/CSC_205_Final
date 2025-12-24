import java.util.Scanner;

public class Suitor {

	// Node class to represent each suitor in the linked list
	private static class Node {
		int number;
		String name;
		Node next;

		Node(int number, String name) {
			this.number = number;
			this.name = name;
			this.next = null;
		}
	}

	public static void start (Scanner scnr) {
		int numberOfSuitors = 0;
		boolean validInput = false;

		System.out.println("\n");
		System.out.println("Select the Suitor.");

		while (!validInput) {
			System.out.print("\nEnter the number of Suitors:  ");

			if (scnr.hasNextInt()) {
				numberOfSuitors = scnr.nextInt();

				if ((numberOfSuitors > 0)&&(numberOfSuitors <= 50)) {
					validInput = true;
				} else {
					System.out.println("Error: Number of suitors must be between 1 and 50.");
				}
			} else {
				System.out.println("Error: Please enter a valid integer.");
				scnr.nextLine(); // consume the entire invalid line
			}
		}

		scnr.nextLine(); // consume the remaining newline

		// Collect suitor names and build the circular linked list
		Node head = null;
		Node tail = null;

		for (int i = 1; i <= numberOfSuitors; i++) {
			String name = "";
			boolean validName = false;

			while (!validName) {
				System.out.print("Enter name of Suitor #" + i + ":  ");
				name = scnr.nextLine().trim();

				if (!name.isEmpty()) {
					validName = true;
				} else {
					System.out.println("Error: Suitor name cannot be blank. Please enter a valid name.");
				}
			}

			Node newNode = new Node(i, name);

			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
		}

		// Make it circular
		if (tail != null) {
			tail.next = head;
		}

		System.out.println();

		// Perform the elimination process
		Node current = head;
		Node previous = tail; // Start with previous pointing to the last node

		while (current.next != current) { // Continue until only one node remains
			// Count 3 positions (V-a-l)
			for (int count = 0; count < 2; count++) {
				previous = current;
				current = current.next;
			}

			// Eliminate the third suitor
			Node eliminated = current;
			System.out.println("Suitor #" + eliminated.number + ", " + eliminated.name + ", eliminated.");

			// Remove the node from the list
			previous.next = current.next;
			current = current.next;
		}

		// Print the winner
		System.out.println("\nThe correct suitor was #" + current.number + ", " + current.name + ".");
	}
}




