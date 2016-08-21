package algo;

public class RobotMovesCircularOrNot {
	static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;

	public static void main(String[] args) {
		String moves = "GLLG";
System.out.println(checkCircularMove(moves.toCharArray()));

	}

	public static boolean checkCircularMove(char[] moves) {
		int x = 0, y = 0;
		int dir = NORTH;
		for (int i = 0; i < moves.length; i++) {
			char move = moves[i];
			if (move == 'G') {
				if (dir == 0)
					y++;
				else if (dir == 1)
					x++;
				else if (dir == 2)
					y--;
				else
					x--;
			} else if (move == 'R') {
				dir = (dir + 1) % 4;

			} else {
				dir = ((dir - 1) + 4) % 4;
			}

		}
		return ((x==0)&&(y==0));
	}
}
