package org.william.apps.file;

import java.nio.file.Path;
import java.util.List;

public final class Operation {
	private final List<ReadPredicate<String>> predicates;
	private final List<VisitFunction<Path>> visitFunctions;
	private final List<PostFunction> postFunctions;

	private Operation(List<ReadPredicate<String>> predicates, List<VisitFunction<Path>> visitFunctions,
			List<PostFunction> postFunctions) {
		super();
		this.predicates = predicates;
		this.visitFunctions = visitFunctions;
		this.postFunctions = postFunctions;
	}

	public static Operation newVisitOperation(List<ReadPredicate<String>> predicates,
			List<VisitFunction<Path>> visitFunctions) {
		return new Operation(predicates, visitFunctions, null);
	}

	public static Operation newPostOperation(List<ReadPredicate<String>> predicates, List<PostFunction> postFunctions) {
		return new Operation(predicates, null, postFunctions);
	}
	
	public static Operation newPostOperation(List<PostFunction> postFunctions) {
		return new Operation(null, null, postFunctions);
	}

	public final List<ReadPredicate<String>> getPredicates() {
		return predicates;
	}

	public final List<VisitFunction<Path>> getVisitFunctions() {
		return visitFunctions;
	}

	public final List<PostFunction> getPostFunctions() {
		return postFunctions;
	}

}
