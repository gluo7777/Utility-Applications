package org.william.apps.file;

import static java.util.Arrays.asList;
import static org.william.apps.file.PostFunction.Factory.logger;
import static org.william.apps.file.PostFunction.Factory.skipper;
import static org.william.apps.file.ReadPredicate.Factory.needsCapitalization;
import static org.william.apps.file.VisitFunction.Factory.capitalizer;
import static org.william.apps.file.VisitFunction.Factory.renameDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class VisitorFactory {

	public static void main(String[] args) {
		List<Path> roots = buildPaths(args);
		roots.stream().forEach(directory -> {
			try {
				System.out.println(directory.resolveSibling("2423423"));
				System.out.println(directory.resolve(Paths.get("C:\\Users\\gluo7\\Downloads\\randomtestdir\\newer folder\\erwerew")));
				renameDirectory(directory, "buttface");
				Files.walkFileTree(directory, buildFileCapitalizer());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		});
	}

	private static List<Path> buildPaths(String[] args) {
		return asList(Paths.get("C:\\Users\\gluo7\\Downloads\\randomtestdir\\new folder"));
	}

	private static CustomFileVisitor buildFileCapitalizer() {
		CustomFileVisitor visitor = new CustomFileVisitor();
		visitor.setPreVisitDirectoryOperation(Operation.newVisitOperation(
				asList(needsCapitalization()),
				asList(capitalizer())
				));
		visitor.setVisitFileOperation(Operation.newVisitOperation(
				asList(needsCapitalization()),
				asList(capitalizer())
				));
		visitor.setVisitFileFailedOperation(Operation.newPostOperation(
				asList(skipper(),logger())
				));
		visitor.setPostVisitDirectoryOperation(Operation.newPostOperation(
				asList(skipper(),logger())
				));
		return visitor;
	}

}
