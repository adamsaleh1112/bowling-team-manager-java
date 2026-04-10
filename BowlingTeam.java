import java.util.ArrayList;
import java.io.PrintWriter;
// represents the bowling team which holds it's members
// this class allows user to edit the list of bowlers in the team

public class BowlingTeam {
	private ArrayList<Bowler> team; // team list of members

	public BowlingTeam() {
		team = new ArrayList<>();
	}

	public BowlingTeam(ArrayList<Bowler> bowlers) {
		team = new ArrayList<>(bowlers);
	}

	public ArrayList<Bowler> getTeam() {
		return team;
	}

	public int getTeamSize() {
		return team.size();
	}

	public void addBowler(Bowler b) {
		if (team.size() < 15) {
			team.add(b);
		} else {
			System.out.println("The bowling team is full! Can't add any more bowlers.");
		}
	}

	public boolean deleteBowler(String name) {
		for (int i = 0; i < team.size(); i++) {
			if (team.get(i).getName().equals(name)) {
				team.remove(i);
				return true;
			}
		}
		return false;
	}

	public void outputBowlerReport(String name, PrintWriter writer) {
		for (Bowler bowler : team) {
			if (bowler.getName().equals(name)) {
				String report = bowler.toString();
				System.out.println("\n" + report);
				writer.println(report);
				writer.flush();
				System.out.println("Report saved to bowling_reports.txt");
				return;
			}
		}
		System.out.println("Bowler not found.");
	}

	public void outputTeamReport(PrintWriter writer) {
		if (team.isEmpty()) {
			System.out.println("No bowlers on the team yet.");
			return;
		}

		String header = "\n---------- COMPLETE TEAM REPORT ----------";
		System.out.println(header);
		writer.println(header);

		for (Bowler bowler : team) {
			String report = bowler.toString();
			System.out.println("\n" + report);
			writer.println(report);
		}

		String footer = "\n---------- END OF TEAM REPORT ----------";
		System.out.println(footer);
		writer.println(footer);
		writer.flush();
		System.out.println("Team report saved.");
	}
}