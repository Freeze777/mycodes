package hacker.rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AnagramIsFromAPalindrome {

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        String inputString = myScan.nextLine();
       
        String ans="YES";
        Map<Character,Integer> countMap =new HashMap<Character, Integer>();
        for (int i = 0; i <inputString.length(); i++) {
        	char ch = inputString.charAt(i);
			if (countMap.containsKey(ch))
				countMap.put(ch, countMap.get(ch) + 1);
			else
				countMap.put(ch, 1);
		}
        int countOfOdds=0;
        for (Integer value : countMap.values()) {
            if (countOfOdds>1) {
				ans="NO";
            	break;
			}
        	if(value%2==1)
            	countOfOdds++;
        }
        
        // Assign ans a value of YES or NO, depending on whether or not inputString satisfies the required condition
        System.out.println(ans);
        myScan.close();
    }
}