package org.william.apps.file;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface ReadPredicate<T> extends BiPredicate<T, BasicFileAttributes> {
	static class Factory {
		public static ReadPredicate<String> negate(ReadPredicate<String> predicate){
			return (fileName, u) -> !predicate.test(fileName, u);
		}
		
		public static ReadPredicate<String> fileMatcher(Set<String> fileNames) {
			return (fileName, u) -> fileNames.contains(fileName);
		}

		public static ReadPredicate<String> regexMatcher(Set<String> matchRegexes) {
			return new ReadPredicate<String>() {
				List<Pattern> patterns = createRegexSet(matchRegexes);
				@Override
				public boolean test(String fileName, BasicFileAttributes u) {
					for (Pattern p : patterns)
						if (p.matcher(fileName).matches())
							return true;
					return false;
				}
			};
		}

		public static ReadPredicate<String> needsCapitalization() {
			return (s, u) -> Character.isLetter(s.charAt(0)) && !Character.isUpperCase(s.charAt(0));
		}

		public static ReadPredicate<String> compositeChecker(
				@SuppressWarnings("unchecked") ReadPredicate<String>... testers) {
			return (s, u) -> {
				for (ReadPredicate<String> tester : testers)
					if (!tester.test(s, u))
						return false;
				return true;
			};
		}

		private static List<Pattern> createRegexSet(Set<String> matchRegexes) {
			return matchRegexes.stream().map(s -> {
				return Pattern.compile(s);
			}).collect(Collectors.toList());
		}

	}
}
