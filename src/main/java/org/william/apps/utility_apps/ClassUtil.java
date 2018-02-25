package org.william.apps.utility_apps;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public enum ClassUtil {
	INSTANCE;

	private ClassRecord record = ClassRecord.INSTANCE;

	public Map<String, Object> getValueMap(Object pojo) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		setRecord(pojo);
		for (Field field : record.getFields()) {
			try {
				field.setAccessible(true);
				valueMap.put(field.getName(), field.get(pojo));
			} catch (IllegalArgumentException | IllegalAccessException e) {
//				Util.base.log(this, e.toString());
			}
		}
		return valueMap;
	}

	public Map<String, Method> getMethodMap(Object pojo) {
		Map<String, Method> methodMap = new HashMap<String, Method>();
		setRecord(pojo);
		for (Method m : pojo.getClass().getDeclaredMethods()) {
			if (!m.getReturnType().equals(void.class)) {
				try {
					methodMap.put(m.getName().substring(3), m);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		return methodMap;
	}

	protected void setRecord(Object pojo) {
		if (!pojo.getClass().equals(record.getPreviousType())) {
			record.setPreviousType(pojo.getClass());
			record.setFields(pojo.getClass().getDeclaredFields());
		}
	}

	/**
	 * should print out fields of the pojo in a hierarchical format (call
	 * recursively)
	 * 
	 * @param pojo
	 */
	public <T> void printInfoAboutType(Object pojo) {
		if (pojo != null) {
			for (Field f : pojo.getClass().getDeclaredFields()) {
				System.out.println(f.toGenericString());
			}
		}
	}

	static enum ClassRecord {
		INSTANCE;

		String levelDelimiter = "---";

		Field[] fields;
		Class<?> previousType = Foo.class;

		public final Field[] getFields() {
			return fields;
		}

		public final void setFields(Field[] fields) {
			this.fields = fields;
		}

		public final Class<?> getPreviousType() {
			return previousType;
		}

		public final void setPreviousType(Class<?> previousType) {
			this.previousType = previousType;
		}

		public final String getLevelDelimiter() {
			return levelDelimiter;
		}

		static class Foo {
		}
	}
}
