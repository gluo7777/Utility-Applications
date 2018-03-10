package org.william.apps.strings;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

public interface StringFunction extends Function<String, String> {
	public static class Factory {
		public static StringFunction defaultStringFunction() {
			return s -> s;
		}
		
		public static StringFunction createCapitalizer() {
			return s -> capitalize(s);
		}

		public static StringFunction createReplacer(String regex, String replacement) {
			return new StringFunction() {
				Pattern p = Pattern.compile(regex);

				@Override
				public String apply(String input) {
					return p.matcher(input).replaceAll(replacement);
				}
			};
		}
		
		public static StringFunction createCompositeFunction(StringFunction...functions) {
			StringFunction s = functions[0];
			for(int i=1; i<functions.length; i++)
				s = s.andThen(functions[i]);
			return s;
		}
	}
	
	default public StringFunction andThen(StringFunction f) {
		Objects.requireNonNull(f);
		return s -> f.apply(this.apply(s));
	}
}
