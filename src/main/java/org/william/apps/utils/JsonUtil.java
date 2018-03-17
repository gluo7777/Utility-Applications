package org.william.apps.utils;

import java.util.Map.Entry;

import org.william.apps.utils.pojo.TestPojo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public enum JsonUtil {
	INSTANCE;
	
	private final JsonParser parser = new JsonParser();
	private final Gson gson = new Gson(); 
	
	public TestPojo jsonToTestPojo(String json) {
//		JsonObject parser.parse(json);
		return null;
	}
	
	/**
	 * recursively walks the json structure to search for the clazz
	 * @param json - to parse
	 * @param clazz - to deserialize json into
	 * @return deserialized class or null if the name of the class is not found
	 */
	public <T> T deserializeNestedObject(String json, Class<T> clazz) {
		return walkJsonStructure(clazz, parser.parse(json));
	}

	protected <T> T walkJsonStructure(Class<T> clazz, JsonElement e) {
		T obj = null;
		if(e.isJsonObject()) {
			JsonObject o = e.getAsJsonObject();
			if(o.has(clazz.getSimpleName()))
				obj = gson.fromJson(o.get(clazz.getSimpleName()), clazz);
			else {
				for(Entry<String, JsonElement> key : o.entrySet()) {
					obj = walkJsonStructure(clazz, key.getValue());
					if(obj != null) break;
				}
			}
		}
		return obj;
	}
}
