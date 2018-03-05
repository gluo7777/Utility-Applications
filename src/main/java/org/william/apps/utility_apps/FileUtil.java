package org.william.apps.utility_apps;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public enum FileUtil {
	INSTANCE;

	public static boolean renameFile(Path oldFile, String newFileName) throws IOException {
		Path newFile = oldFile.resolveSibling(newFileName);
		Files.move(oldFile, newFile, StandardCopyOption.ATOMIC_MOVE);
		return Files.exists(newFile);
	}

	public static Path copyFile(Path oldFile, Path target) throws IOException {
		return Files.copy(oldFile, target.resolve(oldFile.getFileName()), StandardCopyOption.ATOMIC_MOVE);
	}

	public static void copyDirectory(Path oldDir, Path target, boolean deleteAfter) throws IOException {
		Path targetRootDir = copyFile(oldDir, target);
		if (folderNotEmpty(targetRootDir)) {
			FileVisitor<Path> copier = new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					return copyAndDelete(deleteAfter, targetRootDir, file);
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc != null) {
						exc.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
					return copyAndDelete(deleteAfter, targetRootDir, dir);
				}

				public FileVisitResult copyAndDelete(boolean deleteAfter, Path newDir, Path file) throws IOException {
					copyFile(file, newDir);
					if (deleteAfter)
						Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
			};
			Files.walkFileTree(oldDir, copier);
		}
	}

	public static boolean folderNotEmpty(Path newDir) {
		return Files.exists(newDir) && newDir.toFile().list().length > 0;
	}
}
