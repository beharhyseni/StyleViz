package preprocess;

import java.util.List;

public class Declaration {

	private String fileName;
	private List<String> classNames;
	private List<String> methodNames;
	private List<String> staticVariableNames;
	private List<String> variableNames;

	Declaration(String fileName,
	            List<String> classNames,
	            List<String> methodNames,
	            List<String> staticVariableNames,
	            List<String> variableNames) {
		this.fileName = fileName;
		this.classNames = classNames;
		this.methodNames = methodNames;
		this.staticVariableNames = staticVariableNames;
		this.variableNames = variableNames;
	}

	public String getFileName() {
		return fileName;
	}

	public List<String> getClassNames() {
		return classNames;
	}

	public List<String> getMethodNames() {
		return methodNames;
	}

	public List<String> getStaticVariableNames() {
		return staticVariableNames;
	}

	public List<String> getVariableNames() {
		return variableNames;
	}
}
