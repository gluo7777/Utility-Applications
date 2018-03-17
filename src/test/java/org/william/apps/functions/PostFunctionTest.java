package org.william.apps.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.william.apps.util.FileTest;
import org.william.apps.util.FileTestUtil;
import org.william.apps.utils.FileUtil;

public class PostFunctionTest extends FileTest {

	@Test
	public void test_skipper() {
		assertEquals(FileVisitResult.CONTINUE, PostFunction.Factory.skipper().apply(null, null));
	}

	@Test
	public void test_directoryNameTransformer() throws IOException {
		Path someDir = Files.createDirectories(FileTestUtil.getTestDirPath().resolve("someDir"));
		PostFunction directoryNameTransformer = PostFunction.Factory
				.directoryNameTransformer(StringFunction.Factory.createCapitalizer());
		FileUtil.createFilesInDirectory(someDir, true, "file1", "fileA", "file2", "fileB");
		directoryNameTransformer.apply(someDir, null);
		assertTrue(Files.exists(someDir));
		assertEquals(4, someDir.toFile().listFiles().length);
		Path newDir = FileTestUtil.getTestDirPath().resolve("SomeDir");
		assertTrue(Files.exists(newDir));
		assertEquals(4, newDir.toFile().listFiles().length);
	}

	@Test
	public void test_fileDeleter() throws IOException {
		Path someDir = Files.createDirectories(FileTestUtil.getTestDirPath().resolve("someDir"));
		FileUtil.createFilesInDirectory(someDir, true, "file1", "fileA", "file2", "fileB");
		PostFunction directoryDeleter = PostFunction.Factory.directoryDeleter();
		directoryDeleter.apply(someDir, null);
		assertFalse(Files.exists(someDir));
	}

}
