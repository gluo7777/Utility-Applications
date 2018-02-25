package org.william.apps.utility_apps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.william.apps.model.GlossDef;

public class JsonUtilTest {
	
	final String json = "{\r\n" + 
			"   \"glossary\":{\r\n" + 
			"      \"title\":\"example glossary\",\r\n" + 
			"      \"GlossDiv\":{\r\n" + 
			"         \"title\":\"S\",\r\n" + 
			"         \"GlossList\":{\r\n" + 
			"            \"GlossEntry\":{\r\n" + 
			"               \"ID\":\"SGML\",\r\n" + 
			"               \"SortAs\":\"SGML\",\r\n" + 
			"               \"GlossTerm\":\"Standard Generalized Markup Language\",\r\n" + 
			"               \"Acronym\":\"SGML\",\r\n" + 
			"               \"Abbrev\":\"ISO 8879:1986\",\r\n" + 
			"               \"GlossDef\":{\r\n" + 
			"                  \"description\":\"A meta-markup language, used to create markup languages such as DocBook.\",\r\n" + 
			"                  \"types\":[\r\n" + 
			"                     \"GML\",\r\n" + 
			"                     \"XML\"\r\n" + 
			"                  ]\r\n" + 
			"               },\r\n" + 
			"               \"GlossSee\":\"markup\"\r\n" + 
			"            }\r\n" + 
			"         }\r\n" + 
			"      }\r\n" + 
			"   }\r\n" + 
			"}";

	@Test
	public void test() {
		System.out.println(JsonUtil.INSTANCE.deserializeNestedObject(json, GlossDef.class));
	}

}
