package org.william.apps.examples;

import java.util.LinkedList;
import java.util.List;

public class JavaUpCast {

	static class Obj<T> {
		public final void printT(T t) {
			System.out.println(t);
		}
	}

	public static void main(String[] args) {
		List<Obj<?>> objs = new LinkedList<>();
		Obj<String> o1 = new Obj<>();
		objs.add(o1);
//		objs.get(0).printT("str");
	}
	
	// no
//	static void dosomething(Obj<String> o) {}
//	static void dosomething(Obj<Object> o) {}
}
