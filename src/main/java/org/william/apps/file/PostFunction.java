package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.function.BiFunction;

public interface PostFunction extends BiFunction<Path, IOException, FileVisitResult> {
	static class Factory {

		public static PostFunction terminator() {
			return (p, i) -> FileVisitResult.TERMINATE;
		}

		public static PostFunction skipper() {
			return (p, i) -> FileVisitResult.CONTINUE;
		}

		public static PostFunction logger() {
			return (p, exc) -> {
				if (exc != null)
					exc.printStackTrace(System.out);
				return FileVisitResult.CONTINUE;
			};
		}
	}
}
