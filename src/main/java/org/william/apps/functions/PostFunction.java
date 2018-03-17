package org.william.apps.functions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;

import org.william.apps.utils.FileUtil;

public interface PostFunction extends FileFunction<IOException> {
	static class Factory {

		public static PostFunction skipper() {
			return (p, i) -> FileVisitResult.CONTINUE;
		}

		/**
		 * Does not delete old directory
		 * @param function
		 * @return
		 */
		public static PostFunction directoryNameTransformer(StringFunction function) {
			return new PostFunction() {
				@Override
				public FileVisitResult apply(Path dir, IOException u) {
					String oldName = dir.getFileName().toString();
					try {
						Path newDir = dir.resolveSibling(function.apply(oldName));
						FileUtil.copyDirectory(dir, newDir, true);
					} catch (IOException e) {
						return defaultFileVisitErrorHandling(e);
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}

		public static PostFunction directoryDeleter() {
			return new PostFunction() {
				@Override
				public FileVisitResult apply(Path file, IOException u) {
					try {
						FileUtil.deleteFile(file);
					} catch (IOException e) {
						return defaultFileVisitErrorHandling(e);
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}
	}
}
