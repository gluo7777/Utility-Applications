package org.william.apps.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.william.apps.util.FileTestUtil;
import org.william.apps.util.FileUtilTest;

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
