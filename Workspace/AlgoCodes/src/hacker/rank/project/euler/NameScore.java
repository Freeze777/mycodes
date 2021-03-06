package hacker.rank.project.euler;
import java.util.Scanner;
import java.util.TreeMap;

public class NameScore {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		TreeMap<String, Integer> nameScore = new TreeMap<String, Integer>();

		while (tst > 0) {
			String name = sc.next();
			int score = 0;

			for (int i = 0; i < name.length(); i++)
				score += (name.charAt(i) - 'A' + 1);

			nameScore.put(name, score);
			tst--;
		}
		int i = 1;
		for (String name : nameScore.keySet()) {
			nameScore.put(name, nameScore.get(name) * i);
			i++;
		}

		tst = sc.nextInt();

		while (tst > 0) {
			System.out.println(nameScore.get(sc.next()));
			tst--;
		}
	}
}
