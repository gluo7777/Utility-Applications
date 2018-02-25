package org.william.apps.experiment;

public class JavaReferencesArePassedByCopy {
	static class Foo {
		int x;
		int y;

	}

	static void alterFoo(Foo copyOfRef) {
		copyOfRef.x = 10;
		copyOfRef.x = 100;
	}
	
	/**
	 * Cannot swap objects in Java by references alone
	 * @param copyOfRef
	 */
	static void reassignFooRef(Foo copyOfRef) {
		Foo newVal = new Foo();
		newVal.x = 3;
		newVal.y = 3;
		// re-assigning copy of reference to foo and not original reference.
		// this assignment operation will be "lost" after method invocation ends.
		copyOfRef = newVal; 
	}

	public static void main(String[] args) {
		Foo ref = new Foo();
		ref.x = 15;
		ref.y = 15;
		alterFoo(ref);
		System.out.println(ref.x + " " + ref.y); // 100 15
		reassignFooRef(ref);
		System.out.println(ref.x + " " + ref.y); // 100 15
	}
}
