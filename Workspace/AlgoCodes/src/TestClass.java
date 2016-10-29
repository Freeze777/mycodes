import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TestClass {

	public static void main(String[] args) {}

}

class Obj implements Comparable<Obj> {
	char c;
	int count;

	Obj(char ch, int c) {
		c = ch;
		count = c;
	}

	@Override
	public int compareTo(Obj o) {
		if (this.count != o.count) {
			return this.count - o.count;
		} else {
			return this.c - o.c;
		}

	}
}