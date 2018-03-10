package org.william.apps.file;

import org.junit.After;
import org.junit.Before;
import org.william.apps.experiment.testutils.FileTestUtil;

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
