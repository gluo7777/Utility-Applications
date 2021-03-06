package org.william.apps.utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static boolean renameFile(Path oldFile, String newFileName) throws IOException {
		Path newFile = oldFile.resolveSibling(newFileName);
		Files.move(oldFile, newFile, StandardCopyOption.ATOMIC_MOVE);
		return Files.exists(newFile);
	}

	public static Path copyFile(Path oldFile, Path target) throws IOException {
		return Files.copy(oldFile, target.resolve(oldFile.getFileName()));
	}

	public static void deleteFile(Path oldFile) throws IOException {
		Files.deleteIfExists(oldFile);
	}
	
	public static void createFilesInDirectory(Path dir, boolean continueOnFailure, String...files) {
		for(String file: files) {
			try {
				Files.createFile(dir.resolve(file));
			} catch (IOException e) {
				if(continueOnFailure)
					continue;
				else
					throw new RuntimeException(e);
			}
		}
	}
	
	public static void createFilesInDirectory(Path dir, boolean continueOnFailure, Path...files) {
		for(Path file: files) {
			try {
				Files.createFile(dir.resolve(file));
			} catch (IOException e) {
				if(continueOnFailure)
					continue;
				else
					throw new RuntimeException(e);
			}
		}
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

	/**
	 * 
	 * @param file to search and replace
	 * @param findRegex 
	 * @param replaceRegex
	 * @return number of matched & modified character sequences
	 * @throws IOException
	 */
	public static int findReplaceInFile(Path file, String findRegex, String replaceRegex) throws IOException {
		Pattern findPattern = Pattern.compile(findRegex, Pattern.MULTILINE);
		String newFileContents = FileUtils.readFileToString(file.toFile());
		Matcher matcher = findPattern.matcher(newFileContents);
		StringBuffer buf = new StringBuffer();
		int count = 0;
		while (matcher.find()) {
			count++;
			matcher.appendReplacement(buf, replaceRegex);
		}
		newFileContents = matcher.appendTail(buf).toString();
		FileUtils.writeStringToFile(file.toFile(), newFileContents);
		return count;
	}

	public static Path pathToCopy(Path file, String append) throws IOException {
		String tempFileName = file.getFileName().toString().concat(append);
		return file.resolveSibling(tempFileName);
	}

	public static String simpleFileName(Path file) {
		return file.getFileName().toString();
	}
}
