package algo;

import java.util.Random;

class Password {
	private String _password = "";

	// Constructor that generates a password
	public Password(int length) {
		Random generator = new Random();
		for (int i = 0; i < length; ++i) {
			this._password = this._password
					+ (char) (generator.nextInt(26) + 97);
		}

	}

	public String get_password() {
		return _password;
	}

	// Method that compares a given string to the password
	public boolean isPassword(String st) {
		return st.equals(this._password);
	}
}

public class PasswordCracker {
	public static void main(String[] args) {
		int n=6;
		Password p = new Password(n);
		System.out.println(p.get_password());
		System.out.println(findPassword(p, "",n));
	}

	private static boolean findPassword(Password p, String pswd, int length) {

		if (length == pswd.length()) {
			if (p.isPassword(pswd))
				System.out.println(pswd);
			return p.isPassword(pswd);

		}

		String alpha = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < alpha.length(); i++) {
			if (findPassword(p, pswd + alpha.charAt(i), length)) 
				return true;
		}
		return p.isPassword(pswd);
	}

}
