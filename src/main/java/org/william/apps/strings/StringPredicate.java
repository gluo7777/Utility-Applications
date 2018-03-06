package org.william.apps.strings;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface StringPredicate extends Predicate<String> {
	static class Factory {
		public static StringPredicate needsCapitalization() {
			return (s) -> Character.isLetter(s.charAt(0)) && !Character.isUpperCase(s.charAt(0));
		}
		
		public static StringPredicate matcher(Set<String> names) {
			return (name) -> names.contains(name);
		}
		
		public static StringPredicate regexMatcher(Set<String> matchRegexes) {
			return new StringPredicate() {
				List<Pattern> patterns = createRegexSet(matchRegexes);
				@Override
				public boolean test(String name) {
					for (Pattern p : patterns)
						if (p.matcher(name).matches())
							return true;
					return false;
				}
			};
		}
		
		private static List<Pattern> createRegexSet(Set<String> matchRegexes) {
			return matchRegexes.stream().map(s -> {
				return Pattern.compile(s);
			}).collect(Collectors.toList());
		}
	}
}
