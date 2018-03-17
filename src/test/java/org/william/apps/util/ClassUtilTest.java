package org.william.apps.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class ClassUtilTest {

	static class TestPojo {
		int id;
		String f;
		LocalDate dob;

		public final int getId() {
			return id;
		}

		public final void setId(int id) {
			this.id = id;
		}

		public final String getF() {
			return f;
		}

		public final void setF(String f) {
			this.f = f;
		}

		public final LocalDate getDob() {
			return dob;
		}

		public final void setDob(LocalDate dob) {
			this.dob = dob;
		}
	}

	static class DecimalWrapper{
		BigDecimal amount,amount2;
		Integer id;
	}
	
	private TestPojo pojo;

	@Before
	public void setUp() {
		pojo = new TestPojo();
		pojo.id = 2;
		pojo.f = "Will";
	}

	public static void main(String[] args) {
		DecimalWrapper dw = new DecimalWrapper();
		dw.amount = BigDecimal.valueOf(2.2).setScale(2);
		dw.amount2 = BigDecimal.valueOf(2.2).setScale(2);
		String json = new Gson().toJson(dw);
		dw = new Gson().fromJson(json, dw.getClass());
		System.out.println(dw);
	}

	@Test
	public void testValueMap() {
//		Util.base.calcRunTime(() -> Util.clazz.getValueMap(pojo));
//		Util.base.calcRunTime(() -> Util.clazz.getValueMap(pojo));
//		Map<String, Object> values = Util.clazz.getValueMap(pojo);
		// assertThat(values.size(), equalTo(4s));
//		System.out.println(values.toString());
	}

	@Test
	public void testPrintPojo() {
		System.out.println(Arrays.toString(pojo.getClass().getFields()));
//		Util.clazz.printInfoAboutType(pojo);
	}
}
