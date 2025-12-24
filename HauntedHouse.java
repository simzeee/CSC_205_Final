import java.util.Scanner;

public class HauntedHouse {

	// Node class to represent each room in the haunted house
	private static class Node {
		String roomName;
		Node north;
		Node south;
		Node east;
		Node west;

		Node(String roomName) {
			this.roomName = roomName;
			this.north = null;
			this.south = null;
			this.east = null;
			this.west = null;
		}
	}

	public static void start (Scanner scnr) {

		System.out.println("\n");
		System.out.println("Escape the Haunted House.");

		// Create all room nodes
		Node roomA = new Node("A");
		Node roomB = new Node("B");
		Node roomC = new Node("C");
		Node roomD = new Node("D");
		Node roomE = new Node("E");
		Node roomF = new Node("F");
		Node roomG = new Node("G");
		Node roomH = new Node("H");
		Node roomI = new Node("I");
		Node roomJ = new Node("J");
		Node roomK = new Node("K");
		Node roomL = new Node("L");

		// Build the graph by connecting rooms
		// Room A connections
		roomA.east = roomB;
		roomA.south = roomE;

		// Room B connections
		roomB.west = roomA;
		roomB.east = roomC;
		roomB.south = roomF;

		// Room C connections
		roomC.west = roomB;
		roomC.east = roomD;

		// Room D connections
		roomD.west = roomC;
		roomD.south = roomH;

		// Room E connections
		roomE.north = roomA;
		roomE.south = roomI;

		// Room F connections
		roomF.north = roomB;
		roomF.east = roomG;

		// Room G connections
		roomG.west = roomF;
		roomG.south = roomK;

		// Room H connections
		roomH.north = roomD;
		roomH.south = roomL;

		// Room I connections
		roomI.north = roomE;
		roomI.east = roomJ;

		// Room J connections
		roomJ.west = roomI;

		// Room K connections
		roomK.north = roomG;
		roomK.east = roomL;

		// Room L connections (EXIT)
		roomL.west = roomK;
		roomL.north = roomH;

		// Start the navigation from room A
		Node currentRoom = roomA;
		boolean escaped = false;

		while (!escaped) {
			// Display current room and available directions
			System.out.print("\nYou are in room " + currentRoom.roomName + " of the Haunted House.");

			// Check if this is the exit
			if (currentRoom.roomName.equals("L")) {
				System.out.println("  You made it out alive!");
				escaped = true;
				break;
			}

			// Show available directions
			System.out.print("  You can go");
			boolean firstDirection = true;

			if (currentRoom.north != null) {
				System.out.print(" north");
				firstDirection = false;
			}
			if (currentRoom.south != null) {
				if (!firstDirection) System.out.print(",");
				System.out.print(" south");
				firstDirection = false;
			}
			if (currentRoom.east != null) {
				if (!firstDirection) System.out.print(",");
				System.out.print(" east");
				firstDirection = false;
			}
			if (currentRoom.west != null) {
				if (!firstDirection) System.out.print(",");
				System.out.print(" west");
			}
			System.out.println(".");

			// Get user input
			String direction = scnr.nextLine().trim().toUpperCase();

			// Process the direction
			boolean validMove = false;

			if (direction.equals("N") || direction.equals("NORTH")) {
				if (currentRoom.north != null) {
					currentRoom = currentRoom.north;
					validMove = true;
				}
			} else if (direction.equals("S") || direction.equals("SOUTH")) {
				if (currentRoom.south != null) {
					currentRoom = currentRoom.south;
					validMove = true;
				}
			} else if (direction.equals("E") || direction.equals("EAST")) {
				if (currentRoom.east != null) {
					currentRoom = currentRoom.east;
					validMove = true;
				}
			} else if (direction.equals("W") || direction.equals("WEST")) {
				if (currentRoom.west != null) {
					currentRoom = currentRoom.west;
					validMove = true;
				}
			}

			if (!validMove && !direction.isEmpty()) {
				System.out.println("You can't go in that direction!");
			}
		}
	}
}




