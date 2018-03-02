package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SkipFileVisitor extends SimpleFileVisitor<Path> {

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		return super.preVisitDirectory(dir, attrs);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		return super.visitFile(file, attrs);
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		return super.visitFileFailed(file, exc);
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		return super.postVisitDirectory(dir, exc);
	}
	
}
