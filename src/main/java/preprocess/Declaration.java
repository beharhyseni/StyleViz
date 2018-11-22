package preprocess;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Declaration {

	private String fileName;
	private List<String> classNames;
	private List<String> methodNames;
	private List<String> staticVariableNames;
	private List<String> variableNames;

	private Map<String, Integer> frequencyMap;

	Declaration(String fileName,
	            List<String> classNames,
	            List<String> methodNames,
	            List<String> staticVariableNames,
	            List<String> variableNames,
	            Map<String, Integer> frequencyMap) {
		this.fileName = fileName;
		this.classNames = classNames;
		this.methodNames = methodNames;
		this.staticVariableNames = staticVariableNames;
		this.variableNames = variableNames;
		this.frequencyMap = frequencyMap;
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

	public Map<String, Integer> getFrequencyMap() { return Collections.unmodifiableMap(frequencyMap); }

	public int getNameFrequency(String name) { return frequencyMap.getOrDefault(name, 0); }
}
