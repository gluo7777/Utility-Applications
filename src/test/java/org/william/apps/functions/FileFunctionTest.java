package org.william.apps.functions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileFunctionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_defaultFileVisitErrorHandling() {
		assertEquals(FileVisitResult.TERMINATE, new FileFunction<Path>() {

			@Override
			public FileVisitResult apply(Path t, Path u) {
				// TODO Auto-generated method stub
				return null;
			}
		}.defaultFileVisitErrorHandling(new IOException("oh no")));
	}

}
