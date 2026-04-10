// Adam Saleh
// This program makes a bwoling manager where you can manage members of a bowling team

import java.util.*;
import java.io.*;

public class BowlingManager {

	public static void main(String[] args) throws IOException {
		System.out.println("---------- BOWLING TEAM MANAGER ----------");
		System.out.println("This program helps manage a bowling team");
		System.out.println();

		BowlingTeam team = new BowlingTeam(); // makes the team object
		Scanner fileScanner = null; // makes the file reader that read the bowler text file

		fileScanner = new Scanner(new File("bowlers.txt"));
		readTeamData(fileScanner, team);

		PrintWriter reportWriter = new PrintWriter(new FileWriter("bowling_reports.txt", true));
		Scanner userInput = new Scanner(System.in); // makes a scanner to read user input

		int choice;
		do {
			displayMenu();
			choice = getValidChoice(userInput);

			if (choice == 1) {
				updateBowlerData(userInput, team);
			} else if (choice == 2) {
				outputBowlerReport(userInput, team, reportWriter);
			} else if (choice == 3) {
				addNewBowler(userInput, team);
			} else if (choice == 4) {
				deleteBowler(userInput, team);
			} else if (choice == 5) {
				team.outputTeamReport(reportWriter);
			} else if (choice == 6) {
				System.out.println("Thank you for using Bowling Team Manager!");
			}
		} while (choice != 6);

		if (fileScanner != null) {
			fileScanner.close();
		}
		userInput.close();
		reportWriter.close();
	}

	private static void readTeamData(Scanner fileScanner, BowlingTeam team) {
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim(); // reading line by line
			if (line.isEmpty()) continue;

			Scanner lineScanner = new Scanner(line);
			if (lineScanner.hasNext()) {
				String firstName = lineScanner.next();
				String lastName = lineScanner.next();
				String fullName = firstName + " " + lastName;

				int year = lineScanner.nextInt();
				String school = lineScanner.next().replace("_", " ");
				int age = lineScanner.nextInt();

				ArrayList<Integer> scores = new ArrayList<Integer>();
				while (lineScanner.hasNextInt()) {
					scores.add(lineScanner.nextInt());
				}

				Bowler bowler = new Bowler(fullName, year, school, age, scores);
				team.addBowler(bowler);
			}
			lineScanner.close();
		}
	}

	private static void displayMenu() {
		System.out.println("\n---------- BOWLING TEAM MENU ----------");
		System.out.println("1. Update data for a particular bowler");
		System.out.println("2. Output a report for a particular bowler");
		System.out.println("3. Add a new bowler to the team");
		System.out.println("4. Delete a bowler from the team");
		System.out.println("5. Output a team report");
		System.out.println("6. Exit");
		System.out.print("Enter your choice (1-6): ");
	}

	private static int getValidChoice(Scanner input) {
		int choice;
		while (true) {
			try {
				choice = input.nextInt();
				input.nextLine(); // consume newline
				if (choice >= 1 && choice <= 6) {
					return choice;
				} else {
					System.out.print("Invalid choice. Please enter 1-6: ");
				}
			} catch (InputMismatchException e) {
				System.out.print("Invalid input. Please enter a number 1-6: ");
				input.nextLine(); // consume invalid input
			}
		}
	}

	private static void updateBowlerData(Scanner input, BowlingTeam team) {
		System.out.print("Enter bowler's name: ");
		String name = input.nextLine();

		Bowler bowler = findBowlerByName(team, name);
		if (bowler == null) {
			System.out.println("Bowler not found.");
			return;
		}

		System.out.println("What would you like to update?");
		System.out.println("1. Add a score");
		System.out.println("2. Delete a score");
		System.out.println("3. Update name");
		System.out.println("4. Update year in school");
		System.out.println("5. Update school");
		System.out.println("6. Update age");
		System.out.print("Enter choice: ");

		int updateChoice = input.nextInt();
		input.nextLine();

		if (updateChoice == 1) {
			System.out.print("Enter new score: ");
			int newScore = input.nextInt();
			bowler.addScore(newScore);
			System.out.println("Score added successfully!");
		} else if (updateChoice == 2) {
			System.out.print("Enter score to delete: ");
			int scoreToDelete = input.nextInt();
			if (bowler.deleteScore(scoreToDelete)) {
				System.out.println("Score deleted successfully!");
			} else {
				System.out.println("Score not found.");
			}
		} else if (updateChoice == 3) {
			System.out.print("Enter new name: ");
			String newName = input.nextLine();
			bowler.updateName(newName);
			System.out.println("Name updated successfully!");
		} else if (updateChoice == 4) {
			System.out.print("Enter new year in school: ");
			int newYear = input.nextInt();
			bowler.updateYearInSchool(newYear);
			System.out.println("Year updated successfully!");
		} else if (updateChoice == 5) {
			System.out.print("Enter new school: ");
			String newSchool = input.nextLine();
			bowler.updateSchool(newSchool);
			System.out.println("School updated successfully!");
		} else if (updateChoice == 6) {
			System.out.print("Enter new age: ");
			int newAge = input.nextInt();
			bowler.updateAge(newAge);
			System.out.println("Age updated successfully!");
		} else {
			System.out.println("Invalid choice.");
		}
	}

	private static void outputBowlerReport(Scanner input, BowlingTeam team, PrintWriter writer) {
		System.out.print("Enter bowler's name: ");
		String name = input.nextLine();
		team.outputBowlerReport(name, writer);
	}

	private static void addNewBowler(Scanner input, BowlingTeam team) {
		System.out.print("Enter bowler's full name: ");
		String name = input.nextLine();

		System.out.print("Enter year in school (9-12): ");
		int year = input.nextInt();
		input.nextLine();

		System.out.print("Enter school name: ");
		String school = input.nextLine();

		System.out.print("Enter age: ");
		int age = input.nextInt();

		System.out.print("How many scores to enter? ");
		int numScores = input.nextInt();

		ArrayList<Integer> scores = new ArrayList<Integer>();
		for (int i = 0; i < numScores; i++) {
			System.out.print("Enter score " + (i + 1) + ": ");
			scores.add(input.nextInt());
		}

		Bowler newBowler = new Bowler(name, year, school, age, scores);
		team.addBowler(newBowler);
		System.out.println("Bowler added successfully!");
	}

	private static void deleteBowler(Scanner input, BowlingTeam team) {
		System.out.print("Enter name of bowler to delete: ");
		String name = input.nextLine();

		if (team.deleteBowler(name)) {
			System.out.println("Bowler deleted successfully!");
		} else {
			System.out.println("Bowler not found.");
		}
	}

	private static Bowler findBowlerByName(BowlingTeam team, String name) {
		for (Bowler bowler : team.getTeam()) {
			if (bowler.getName().equalsIgnoreCase(name)) {
				return bowler;
			}
		}
		return null;
	}
}
