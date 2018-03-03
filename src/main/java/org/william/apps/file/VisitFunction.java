package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.function.BiFunction;

import static org.apache.commons.lang3.StringUtils.capitalize;

public interface VisitFunction<T> extends BiFunction<T, BasicFileAttributes, FileVisitResult> {
	static class Factory {
		public static VisitFunction<Path> collector(Collection<Path> c) {
			return (t, u) -> {
				c.add(t);
				return FileVisitResult.CONTINUE;
			};
		}

		public static VisitFunction<Path> capitalizer() {
			return new VisitFunction<Path>() {
				@Override
				public FileVisitResult apply(Path file, BasicFileAttributes u) {
					String oldName = file.getFileName().toString();
					try {
						if (Files.isDirectory(file, LinkOption.NOFOLLOW_LINKS)) {
							renameDirectory(file, capitalize(oldName));
						} else {
							renameFile(file, capitalize(oldName));
						}
					} catch (IOException e) {
						e.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
					return FileVisitResult.CONTINUE;
				}
			};
		}
		
		public static void renameFile(Path file, String capitalize) throws IOException {
			Files.move(file, file.resolveSibling(capitalize), StandardCopyOption.ATOMIC_MOVE);
		}

		public static void renameDirectory(Path dir, String newName) throws IOException {
			Files.copy(dir, dir.resolveSibling(newName), StandardCopyOption.COPY_ATTRIBUTES);
		}	
	}
}
