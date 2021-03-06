package algo.bits;

public class AddTwoBinaryStrings {
	public String addBinary(String a, String b) {
		if (a.length() == 0)
			return b;
		if (b.length() == 0)
			return a;
		int i = a.length() - 1;
		int j = b.length() - 1;
		boolean carry = false;
		StringBuilder ans = new StringBuilder();
		while (i >= 0 || j >= 0) {
			if (i >= 0 && j < 0) {
				char c = a.charAt(i);
				if (carry) {
					ans.append(c == '0' ? '1' : '0');
					carry = (c == '1');
				} else
					ans.append(c);
			} else if (j >= 0 && i < 0) {
				char c = b.charAt(j);
				if (carry) {
					ans.append(c == '0' ? '1' : '0');
					carry = (c == '1');
				} else
					ans.append(c);
			} else {
				char c1 = a.charAt(i);
				char c2 = b.charAt(j);
				if (carry) {
					if (c1 == c2) {
						ans.append(carry ? '1' : '0');
						carry = (c1 == '1');
					} else {// c1!=c2
						ans.append(carry ? '0' : '1');
						// carry=carry;
					}
				} else {
					ans.append(c1 == c2 ? '0' : '1');
					carry = (c1 == '1') && (c2 == '1');
				}

			}
			i--;
			j--;
		}
		if (carry)//missing this statement always...!!!!!
			ans.append('1');
		return ans.reverse().toString();
	}
}
