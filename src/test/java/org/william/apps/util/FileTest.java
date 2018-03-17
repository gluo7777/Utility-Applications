package org.william.apps.util;

import org.junit.After;
import org.junit.Before;

public abstract class FileTest {
	@Before
	public final void setUp() throws Exception {
		FileTestUtil.resetTestDir();
	}

	@After
	public final void tearDown() throws Exception {
		FileTestUtil.deleteTestDir();
	}
}
