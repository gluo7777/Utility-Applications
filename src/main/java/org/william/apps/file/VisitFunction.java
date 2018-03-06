package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.function.BiFunction;

import org.william.apps.strings.StringFunction;

public interface VisitFunction extends BiFunction<Path, BasicFileAttributes, FileVisitResult> {
	static class Factory {
		public static VisitFunction collector(Collection<Path> c) {
			return (t, u) -> {
				c.add(t);
				return FileVisitResult.CONTINUE;
			};
		}
		
		public static VisitFunction fileNameTransformer(StringFunction function) {
			return new VisitFunction() {

				@Override
				public FileVisitResult apply(Path file, BasicFileAttributes u) {
					String oldName = file.getFileName().toString();
					try {
						FileUtil.renameFile(file, function.apply(oldName));
					} catch (IOException e) {
						return defaultFileVisitErrorHandling(e);
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}
		
		public static VisitFunction directoryNameTransformer(StringFunction function) {
			return new VisitFunction() {
				@Override
				public FileVisitResult apply(Path dir, BasicFileAttributes u) {
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
		
		public static VisitFunction fileCopier(Path targetDirectory) {
			return new VisitFunction() {
				@Override
				public FileVisitResult apply(Path file, BasicFileAttributes u) {
					try {
						FileUtil.copyFile(file, targetDirectory);
					} catch (IOException e) {
						return defaultFileVisitErrorHandling(e);
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}
		
		public static VisitFunction fileDeleter() {
			return new VisitFunction() {
				@Override
				public FileVisitResult apply(Path file, BasicFileAttributes u) {
					try {
						FileUtil.deleteFile(file);
					} catch (IOException e) {
						return defaultFileVisitErrorHandling(e);
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}

		public static FileVisitResult defaultFileVisitErrorHandling(IOException e) {
			e.printStackTrace();
			return FileVisitResult.TERMINATE;
		}
		
	}
}
