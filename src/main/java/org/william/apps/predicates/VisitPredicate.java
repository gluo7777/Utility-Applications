package org.william.apps.predicates;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.william.apps.utils.FileUtil;

public interface VisitPredicate extends FilePredicate<BasicFileAttributes> {
	static class Factory implements FilePredicate.Factory {
		public static VisitPredicate fileNameChecker(StringPredicate predicate) {
			return new VisitPredicate() {

				@Override
				public boolean test(Path t, BasicFileAttributes u) {
					return predicate.test(FileUtil.simpleFileName(t));
				}

			};
		}
	}
}
