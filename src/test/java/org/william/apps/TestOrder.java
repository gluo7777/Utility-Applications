package org.william.apps;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.william.apps.experiment.testutils.FileTestUtil;
import org.william.apps.file.FileUtilTest;

@RunWith(Suite.class)
@SuiteClasses({})
public class TestOrder {
	
	@RunWith(Suite.class)
	@SuiteClasses({FileTestUtil.FileTestUtilTest.class})
	public static class First{
		
	}
	
	@RunWith(Suite.class)
	@SuiteClasses({FileUtilTest.class})
	public static class Second{
		
	}
}
