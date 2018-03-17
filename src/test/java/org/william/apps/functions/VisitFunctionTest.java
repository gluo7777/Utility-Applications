package org.william.apps.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.william.apps.util.FileTest;
import org.william.apps.util.FileTestUtil;

public class VisitFunctionTest extends FileTest {

	@Test
	public void test_collector() throws IOException {
		Set<Path> files = new LinkedHashSet<>();
		VisitFunction collector = VisitFunction.Factory.collector(files);
		collector.apply(Paths.get("file1"), null);
		collector.apply(Paths.get("file2"), null);
		collector.apply(Paths.get("file3"), null);
		assertEquals(3, files.size());
	}

	@Test
	public void test_fileNameTransformer() throws IOException {
		VisitFunction fileNameTransformer = VisitFunction.Factory
				.fileNameTransformer(StringFunction.Factory.createReplacer("\\d", "#"));
		Path p = Files.createFile(FileTestUtil.getTestDirPath().resolve("fileA1B2C3"));
		fileNameTransformer.apply(p, null);
		assertFalse(Files.exists(p));
		assertTrue(Files.exists(FileTestUtil.getTestDirPath().resolve("fileA#B#C#")));
	}
	
	@Test
	public void test_fileCopier() throws IOException {
		Path file = FileTestUtil.createFile("file1");
		Path dir = Files.createDirectories(FileTestUtil.getTestDirPath().resolve("testdir2"));
		VisitFunction fileCopier = VisitFunction.Factory
				.fileCopier(dir);
		fileCopier.apply(file, null);
		assertTrue(Files.exists(file));
		assertTrue(Files.exists(dir.resolve(file.getFileName())));
	}
	
	@Test
	public void test_fileDeleter() throws IOException {
		Path file = FileTestUtil.createFile("file1");
		VisitFunction fileDeleter = VisitFunction.Factory
				.fileDeleter();
		fileDeleter.apply(file, null);
		assertFalse(Files.exists(file));
	}

}
