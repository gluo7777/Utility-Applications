package org.william.apps.functions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.function.BiFunction;

public interface FileFunction<A> extends BiFunction<Path, A, FileVisitResult> {
	default FileVisitResult defaultFileVisitErrorHandling(IOException e) {
		e.printStackTrace();
		return FileVisitResult.TERMINATE;
	}
}
