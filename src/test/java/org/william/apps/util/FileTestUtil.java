package org.william.apps.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileTestUtil {
	private final static String TEST_DIR = "\\src\\test\\resources\\fileutil";
	private final static Map<String, Path> files = new ConcurrentHashMap<>();

	public static Path createFile(Path file) throws IOException {
		if(file.getRoot() == null) {
			Path newFile = Files.createFile(getTestDirPath().resolve(file));
			files.put(file.getFileName().toString(), newFile);
			return newFile;
		} else
			throw new IOException(String.format("%s has root.", file.toString()));
	}
	
	public static Path createFile(String file) throws IOException {
		return createFile(Paths.get(file));
	}
	
	public static Path getFile(String name) {
		return files.get(name);
	}

	public static final Path createTestDir() throws IOException {
		Path p = getTestDirPath();
		if (Files.exists(p) && !Files.isDirectory(p)) {
			throw new IOException(String.format("%s is not a directory.", p.toString()));
		} else {
			Files.createDirectories(p.getParent());
			Files.createDirectory(p);
		}
		return p;
	}

	public static Path getTestDirPath() {
		Path p = Paths.get(System.getProperty("user.dir"), TEST_DIR);
		return p;
	}

	public static final void deleteTestDir() throws IOException {
		if(Files.exists(getTestDirPath()))
			FileUtils.deleteDirectory(getTestDirPath().toFile());
	}
	
	public static void resetTestDir() throws IOException {
		deleteTestDir();
		createTestDir();
	}

	public static class FileTestUtilTest{
		
		@Before
		public void setUp() throws Exception {
			resetTestDir();
		}

		@After
		public void tearDown() throws Exception {
			deleteTestDir();
		}
		
		@Test
		public void test_getTestDir() throws IOException {
			Path p = getTestDirPath();
			assertEquals(System.getProperty("user.dir").concat(TEST_DIR), p.toString());
		}
		
		@Test
		public void test_reset() throws IOException {
			deleteTestDir();
			Path p = getTestDirPath();
			assertFalse(Files.exists(p));
			createTestDir();
			assertTrue(Files.exists(p));
			deleteTestDir();
			assertFalse(Files.exists(p));
			
			resetTestDir();
			p = createFile(Paths.get("my_folder"));
			assertTrue(Files.exists(p));
		}
	}
}
