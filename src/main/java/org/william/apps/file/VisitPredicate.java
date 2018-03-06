package org.william.apps.file;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;

import org.william.apps.strings.StringPredicate;

public interface VisitPredicate extends BiPredicate<Path, BasicFileAttributes> {
	static class Factory {
		public static VisitPredicate negate(VisitPredicate predicate){
			return (fileName, u) -> !predicate.test(fileName, u);
		}
		
		public static VisitPredicate fileNameChecker(StringPredicate predicate) {
			return new VisitPredicate() {

				@Override
				public boolean test(Path t, BasicFileAttributes u) {
					return predicate.test(FileUtil.simpleFileName(t));
				}
				
			};
		}

		public static VisitPredicate compositeVisitPredicate(VisitPredicate... testers) {
			return (s, u) -> {
				for (VisitPredicate tester : testers)
					if (!tester.test(s, u))
						return false;
				return true;
			};
		}

	}
}
