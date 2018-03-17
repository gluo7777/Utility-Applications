package org.william.apps.predicates;

import java.nio.file.Path;
import java.util.function.BiPredicate;

public interface FilePredicate<A> extends BiPredicate<Path, A> {
	public static interface Factory {
		public static <A> FilePredicate<A> negate(FilePredicate<A> predicate) {
			return (fileName, u) -> !predicate.test(fileName, u);
		}

		public static <A> FilePredicate<A> compositeVisitPredicate(@SuppressWarnings("unchecked") FilePredicate<A>... testers) {
			return (s, u) -> {
				for (FilePredicate<A> tester : testers)
					if (!tester.test(s, u))
						return false;
				return true;
			};
		}
	}
}
