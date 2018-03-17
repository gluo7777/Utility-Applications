package org.william.apps.util;

import java.util.Arrays;

public class GlossDef {
	private String description;
	private String[] types;

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String[] getTypes() {
		return types;
	}

	public final void setTypes(String[] types) {
		this.types = types;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GlossDef [description=").append(description).append(", types=").append(Arrays.toString(types))
				.append("]");
		return builder.toString();
	}

}
