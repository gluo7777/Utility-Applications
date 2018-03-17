package org.william.apps.functions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

import org.william.apps.utils.FileUtil;

public interface VisitFunction extends FileFunction<BasicFileAttributes>{
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
		
	}
}
