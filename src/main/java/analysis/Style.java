package analysis;

public enum Style {

	CAMEL_CASE,             // e.g. someVariableName
	SNAKE_CASE,             // e.g. some_variable_name
	STUDLY_CAPS,            // e.g. SomeClass
	ALL_CAPS;               // e.g. SOME_CONSTANT


	/**
	 * Recognize the naming style used for the given identifier
	 *
	 * @param identifier    The given identifier
	 * @return              The Style of the identifier
	 */
	public static Style recognizeStyle(String identifier) {
		// TODO: Finish Implementation
		return ALL_CAPS; // stub
	}

}
