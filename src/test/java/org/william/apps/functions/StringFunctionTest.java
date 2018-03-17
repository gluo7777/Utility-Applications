package org.william.apps.functions;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringFunctionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_createCapitalizer() {
		StringFunction capitalizer = StringFunction.Factory.createCapitalizer();
		assertEquals("Erwfwwf",capitalizer.apply("erwfwwf"));
		assertEquals("Erwfwwf",capitalizer.apply("eRwfwwf"));
		assertEquals("0erwfwwf",capitalizer.apply("0eRwfwwf"));
		assertEquals(null,capitalizer.apply(null));
		assertEquals("",capitalizer.apply(""));
	}
	
	@Test
	public void test_createReplacer() {
		StringFunction replacer = StringFunction.Factory.createReplacer("(\\d)(\\d)","$1$1$2$2$2");
		assertEquals("a11222b1",replacer.apply("a12b1"));
	}
	
	@Test
	public void test_createCompositeFunction() {
		StringFunction composite = StringFunction.Factory.createCompositeFunction(StringFunction.Factory.createCapitalizer(),
				StringFunction.Factory.createReplacer("a","b"),
				StringFunction.Factory.createReplacer("(\\d)(\\d)","$1$1$2$2$2"));
		assertEquals("A11222b1",composite.apply("a12a1"));
	}

}
