package org.william.apps.file;

import java.nio.file.FileVisitResult;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import org.william.apps.utility_apps.Util;

public class FileVisitorBuilder {
	
	public static void main(String[] args) {
		ReadFunction.Builder.buildCollector(null);
	}

	private Set<Option> options;
	private Set<String> filesToIgnore;
	private Set<String> directoriesToIgnore;
	private FileOperation<ReadFunction> preVisitDirectoryOperations, visitFileOperations;
	private FileOperation<PostFunction> visitFailedOperations, postVisitDirectoryOperations;
	
	public final FileVisitorBuilder setOptions(Option...options) {
		this.options = Util.INST.arrayToSet(options);
		return this;
	}

	public final FileVisitorBuilder setFilesToIgnore(String...filesToIgnore) {
		this.filesToIgnore = Util.INST.arrayToSet(filesToIgnore);
		return this;
	}

	public final FileVisitorBuilder setDirectoriesToIgnore(String...directoriesToIgnore) {
		this.directoriesToIgnore = Util.INST.arrayToSet(directoriesToIgnore);
		return this;
	}
	
	private static class FileOperation<T extends BiFunction<?, ?, FileVisitResult>>{
		List<T> operations;
		FileVisitResult finalResult;
	}
}
