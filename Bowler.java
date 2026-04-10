import java.util.ArrayList;
// this class represents each bowler, with variables and methods being traits of the bowler

public class Bowler { // all variables are self explanatory
	private String nameOfBowler; // name of bowler
	private int yearInSchool; // grade (9-12) of the bowler
	private String nameOfSchool; // name of school of bowler
	private int age; // age of bowler
	private ArrayList<Integer> scores; // list of scores of bowler

	public Bowler() {
		nameOfBowler = "";
		yearInSchool = 0;
		nameOfSchool = "";
		age = 0;
		scores = new ArrayList<>();
	}

	public Bowler(String name, int year, String school, int bowlerAge, ArrayList<Integer> bowlerScores) {
		nameOfBowler = name;
		yearInSchool = year;
		nameOfSchool = school;
		age = bowlerAge;
		scores = new ArrayList<>(bowlerScores);
	}

	public Bowler(String name, int year, String school, int bowlerAge) {
		nameOfBowler = name;
		yearInSchool = year;
		nameOfSchool = school;
		age = bowlerAge;
		scores = new ArrayList<>();
	}

	public String getName() {
		return nameOfBowler;
	}

	public void updateName(String n) {
		nameOfBowler = n;
	}

	public void updateYearInSchool(int y) {
		yearInSchool = y;
	}

	public void updateSchool(String s) {
		nameOfSchool = s;
	}

	public void updateAge(int a) {
		age = a;
	}

	public void addScore(int s) {
		scores.add(s);
	}

	public boolean deleteScore(int score) {
		Integer scoreObj = Integer.valueOf(score);
		return scores.remove(scoreObj);
	}

	public double averageBowlers() {
		if (scores.isEmpty()) {
			return 0.0;
		}
		int total = 0;
		for (int s : scores) {
			total += s;
		}
		return total / (double)(scores.size());
	}

	public String bowlerRating() {
		double avg = averageBowlers();
		if (avg < 157) {
			return "minimal";
		} else if (avg < 180) {
			return "basic";
		} else if (avg < 235) {
			return "proficient";
		} else {
			return "advanced";
		}
	}

	public String isVarsity() {
		String rating = bowlerRating();
		if (rating.equals("minimal") || rating.equals("basic")) {
			return "JV";
		} else {
			return "Varsity";
		}
	}

	public int aboveTwoHundred() {
		int c = 0;
		for (int s : scores) {
			if (s > 200) {
				c++;
			}
		}
		return c;
	}

	public int aboveThreeHundred() {
		int c = 0;
		for (int s : scores) {
			if (s == 300) {
				c++;
			}
		}
		return c;
	}

	public String toString() {
		return "Name: " + nameOfBowler +
		       "\nSchool Year: " + yearInSchool +
		       "\nSchool: " + nameOfSchool +
		       "\nAge: " + age +
		       "\nScores: " + scores +
		       "\nAverage: " + averageBowlers() +
		       "\nRating: " + bowlerRating() +
		       "\nJV or Varsity: " + isVarsity() +
		       "\nGames over 200: " + aboveTwoHundred() +
		       "\nGames over 300: " + aboveThreeHundred();
	}
}