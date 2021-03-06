package algo.string;

import java.util.HashMap;
import java.util.Map;
/*Amazon interview question*/
public class WordPatternII {
	static final String BLUE="blue",GREEN="green",RED="red";
	
	public static void main(String[] args) {
		
		String pattern = "xyxzy";
		String s = "redblueredgreenblue";
		Map<String, Character> map = new HashMap<String, Character>();
		boolean ans = true;
		int offset = 0;
		for (int i = 0; i < pattern.length() && ans; i++) {
			char c = s.charAt(offset);
			switch (c) {
			case 'r':
				if (!map.containsKey(RED))
					map.put(RED, pattern.charAt(i));
				else {
					ans = ans && (map.get(RED) == pattern.charAt(i));
				}
				offset += 3;
				break;
			case 'g':
				if (!map.containsKey(GREEN))
					map.put(GREEN, pattern.charAt(i));
				else {
					ans = ans && (map.get(GREEN) == pattern.charAt(i));
				}
				offset += 5;
				break;
			case 'b':
				if (!map.containsKey(BLUE))
					map.put(BLUE, pattern.charAt(i));
				else {
					ans = ans && (map.get(BLUE) == pattern.charAt(i));
				}
				offset += 4;
				break;
			}

		}
		System.out.println(ans);
	}

}
