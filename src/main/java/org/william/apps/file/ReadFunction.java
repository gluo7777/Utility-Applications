package org.william.apps.file;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.function.BiFunction;

public interface ReadFunction extends BiFunction<Path, BasicFileAttributes, FileVisitResult> {
	static class Builder{
		public static <E> ReadFunction buildCollector(Collection<E> c) {
			return new ReadFunction() {
				
				@Override
				public FileVisitResult apply(Path t, BasicFileAttributes u) {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
	}
}
