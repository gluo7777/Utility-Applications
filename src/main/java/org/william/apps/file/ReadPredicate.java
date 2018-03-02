package org.william.apps.file;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface ReadPredicate extends BiPredicate<Path, BasicFileAttributes> {
	static class Builder {
		public static ReadPredicate buildFileMatcher(Set<String> fileNames) {
			return new ReadPredicate() {

				@Override
				public boolean test(Path t, BasicFileAttributes u) {
					return fileNames.contains(simpleName(t));
				}
			};
		}

		public static ReadPredicate buildRegexMatcher(Set<String> matchRegexes) {
			return new ReadPredicate() {
				List<Pattern> patterns = createRegexSet(matchRegexes);

				@Override
				public boolean test(Path t, BasicFileAttributes u) {
					String fileName = simpleName(t);
					for (Pattern p : patterns)
						if (p.matcher(fileName).matches())
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

		private static String simpleName(Path t) {
			return t.getFileName().toString();
		}

	}
}
