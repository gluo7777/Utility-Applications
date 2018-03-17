package org.william.apps.utils;

import java.util.LinkedList;
import java.util.List;

import org.william.apps.functions.PostFunction;
import org.william.apps.functions.VisitFunction;
import org.william.apps.predicates.VisitPredicate;

public final class Operation {
	private final List<VisitPredicate> predicates;
	private final List<VisitFunction> visitFunctions;
	private final List<PostFunction> postFunctions;

	private Operation(List<VisitPredicate> predicates, List<VisitFunction> visitFunctions,
			List<PostFunction> postFunctions) {
		super();
		this.predicates = predicates;
		this.visitFunctions = visitFunctions;
		this.postFunctions = postFunctions;
	}
	
	public static Operation newInstance() {
		return new Operation(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
	}

	public static Operation newVisitOperation(List<VisitPredicate> predicates,
			List<VisitFunction> visitFunctions) {
		return new Operation(predicates, visitFunctions, null);
	}

	public static Operation newPostOperation(List<VisitPredicate> predicates, List<PostFunction> postFunctions) {
		return new Operation(predicates, null, postFunctions);
	}
	
	public static Operation newPostOperation(List<PostFunction> postFunctions) {
		return new Operation(null, null, postFunctions);
	}

	public final List<VisitPredicate> getPredicates() {
		return predicates;
	}

	public final List<VisitFunction> getVisitFunctions() {
		return visitFunctions;
	}

	public final List<PostFunction> getPostFunctions() {
		return postFunctions;
	}

}
