package org.william.apps.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherExample {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("\\d+");
		String input = "afdaf3424fvdsfsf12313  _\n\n\t\t3445dfsf";
		Matcher m = p.matcher(input);
		while(m.find()) {
			System.out.printf("start %d end %d%n",m.start(),m.end());
		}
	}

}
