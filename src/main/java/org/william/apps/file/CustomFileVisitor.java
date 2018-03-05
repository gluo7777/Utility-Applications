package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class CustomFileVisitor extends SimpleFileVisitor<Path> {

	private Operation preVisitDirectoryOperation;
	private Operation visitFileOperation;
	private Operation visitFileFailedOperation;
	private Operation postVisitDirectoryOperation;

	public final Operation getPreVisitDirectoryOperation() {
		return preVisitDirectoryOperation;
	}

	public final Operation getVisitFileOperation() {
		return visitFileOperation;
	}

	public final Operation getVisitFileFailedOperation() {
		return visitFileFailedOperation;
	}

	public final Operation getPostVisitDirectoryOperation() {
		return postVisitDirectoryOperation;
	}

	public final void setPreVisitDirectoryOperation(Operation preVisitDirectoryOperation) {
		this.preVisitDirectoryOperation = preVisitDirectoryOperation;
	}

	public final void setVisitFileOperation(Operation visitFileOperation) {
		this.visitFileOperation = visitFileOperation;
	}

	public final void setVisitFileFailedOperation(Operation visitFileFailedOperation) {
		this.visitFileFailedOperation = visitFileFailedOperation;
	}

	public final void setPostVisitDirectoryOperation(Operation postVisitDirectoryOperation) {
		this.postVisitDirectoryOperation = postVisitDirectoryOperation;
	}

	private FileVisitResult visit(Path dir, BasicFileAttributes attrs, String name,
			List<ReadPredicate<String>> predicates, List<VisitFunction<Path>> functions) {
		if (predicates.size() > functions.size())
			throw new RuntimeException();
		int i = 0;
		FileVisitResult result = FileVisitResult.TERMINATE;
		while (i < predicates.size()) {
			if (!predicates.get(i).test(name, attrs))
				return FileVisitResult.SKIP_SUBTREE;
			if (!(result = nextEvent(dir, attrs, functions, i)).equals(FileVisitResult.CONTINUE))
				return result;
			i++;
		}
		while (i < functions.size()) {
			if (!(result = nextEvent(dir, attrs, functions, i)).equals(FileVisitResult.CONTINUE))
				return result;
			i++;
		}
		return result;
	}

	private FileVisitResult nextEvent(Path dir, BasicFileAttributes attrs, List<VisitFunction<Path>> functions, int i) {
		return functions.get(i).apply(dir, attrs);
	}

	private FileVisitResult after(Path file, IOException exc, List<PostFunction> functions) {
		FileVisitResult result = FileVisitResult.TERMINATE;
		for (PostFunction function : functions) {
			result = function.apply(file, exc);
			if (!result.equals(FileVisitResult.CONTINUE))
				break;
		}
		return result;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		String name = simpleFileName(dir);
		List<ReadPredicate<String>> predicates = this.preVisitDirectoryOperation.getPredicates();
		List<VisitFunction<Path>> functions = this.preVisitDirectoryOperation.getVisitFunctions();
		return visit(dir, attrs, name, predicates, functions);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		String name = simpleFileName(file);
		List<ReadPredicate<String>> predicates = this.visitFileOperation.getPredicates();
		List<VisitFunction<Path>> functions = this.visitFileOperation.getVisitFunctions();
		return visit(file, attrs, name, predicates, functions);
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return after(file, exc, this.visitFileFailedOperation.getPostFunctions());
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return after(dir, exc, this.postVisitDirectoryOperation.getPostFunctions());
	}

	private String simpleFileName(Path file) {
		return file.getFileName().toString();
	}
}
