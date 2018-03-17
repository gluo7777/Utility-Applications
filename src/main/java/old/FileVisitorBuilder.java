package old;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import org.william.apps.functions.PostFunction;
import org.william.apps.functions.VisitFunction;
import org.william.apps.functions.VisitFunction.Factory;
import org.william.apps.utils.BaseUtil;

public class FileVisitorBuilder {

	public static void main(String[] args) {
//		VisitFunction.Factory.buildCollector(null, null);
	}

	private Set<Option> options;
	private Set<String> filesToIgnore;
	private Set<String> directoriesToIgnore;
//	private FileOperation<VisitFunction> preVisitDirectoryOperations, visitFileOperations;
	private FileOperation<PostFunction> visitFailedOperations, postVisitDirectoryOperations;

	public final FileVisitorBuilder setOptions(Option... options) {
		this.options = BaseUtil.INST.arrayToSet(options);
		return this;
	}

	public final FileVisitorBuilder setFilesToIgnore(String... filesToIgnore) {
		this.filesToIgnore = BaseUtil.INST.arrayToSet(filesToIgnore);
		return this;
	}

	public final FileVisitorBuilder setDirectoriesToIgnore(String... directoriesToIgnore) {
		this.directoriesToIgnore = BaseUtil.INST.arrayToSet(directoriesToIgnore);
		return this;
	}

//	public final FileVisitorBuilder setPreVisitDirectoryOperations(FileOperation<VisitFunction> preVisitDirectoryOperations) {
////		this.preVisitDirectoryOperations = preVisitDirectoryOperations;
//		return this;
//	}

//	public final FileVisitorBuilder setVisitFileOperations(FileOperation<VisitFunction> visitFileOperations) {
////		this.visitFileOperations = visitFileOperations;
//		return this;
//	}

	public final FileVisitorBuilder setVisitFailedOperations(FileOperation<PostFunction> visitFailedOperations) {
		this.visitFailedOperations = visitFailedOperations;
		return this;
	}

	public final FileVisitorBuilder setPostVisitDirectoryOperations(FileOperation<PostFunction> postVisitDirectoryOperations) {
		this.postVisitDirectoryOperations = postVisitDirectoryOperations;
		return this;
	}
	
	public final FileVisitor<Path> build() {
		return new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//				for(VisitFunction event: preVisitDirectoryOperations.operations) {
//					
//				}
				return null;
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
		};
	}

	private static class FileOperation<T extends BiFunction<?, ?, FileVisitResult>> {
		List<T> operations;
		FileVisitResult finalResult;
	}
}
