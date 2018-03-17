package org.william.apps.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BaseUtil {
	INST;
	
	public <T> Set<T> arrayToSet(T[] items){
		return IntStream.range(0, items.length).mapToObj(i -> items[i]).collect(Collectors.toSet());
	}
}
