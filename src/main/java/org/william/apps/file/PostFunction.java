package org.william.apps.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.function.BiFunction;

public interface PostFunction extends BiFunction<Path, IOException, FileVisitResult> {
	
}
