package org.william.apps.predicates;

import java.io.IOException;

public interface PostPredicate extends FilePredicate<IOException> {
	static class Factory implements FilePredicate.Factory{
		public static PostPredicate hasException() {
			return (p,e) -> e != null;
		}
	}
}
