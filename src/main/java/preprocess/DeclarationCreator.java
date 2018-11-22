package preprocess;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeclarationCreator extends ASTVisitor {

	enum Type {
		STATIC,
		VARIABLE,
	}
	private Type type = Type.VARIABLE;


	private String fileName;
	private List<String> classNames;
	private List<String> methodNames;
	private List<String> staticVariableNames;
	private List<String> variableNames;
	private Map<String, Integer> frequencyMap;

	private DeclarationCreator(String fileName) {
		this.fileName = fileName;
		this.classNames = new ArrayList<>();
		this.methodNames = new ArrayList<>();
		this.staticVariableNames = new ArrayList<>();
		this.variableNames = new ArrayList<>();
		this.frequencyMap = new HashMap<>();
	}

	/**
	 * Return a Declaration instance corresponding to unit
	 *
	 * @param unit  The compilation unit
	 * @return      A Declaration instance
	 */
	private Declaration build(CompilationUnit unit) {
		unit.accept(this);
		return new Declaration(fileName,
				               classNames,
				               methodNames,
				               staticVariableNames,
				               variableNames,
				               frequencyMap);
	}

	/**
	 * Return a Declaration for the respective compilation unit
	 *
	 * @param unit          The compilation unit (root of AST)
	 * @param fileName      The name of the .java file for given AST
	 * @return              A Declaration instance
	 */
	static Declaration makeDeclaration(final CompilationUnit unit, String fileName) {
		DeclarationCreator creator = new DeclarationCreator(fileName);
		return creator.build(unit);
	}


	private void updateList(List<String> container, String name) {
		int count = frequencyMap.getOrDefault(name, 0);
		if (count == 0) {
			frequencyMap.put(name, 1);
			container.add(name);
		}
		else {
			frequencyMap.put(name, count + 1);
		}
	}


	/* ********** Visit Methods ********** */

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		// Enum values are treated as static variables
		updateList(staticVariableNames, node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		// Enums are treated as classes
		updateList(classNames, node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		for (Object o : node.modifiers()) {
			Modifier m = (Modifier) o;
			if (m.isStatic()) {
				type = Type.STATIC;
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		updateList(methodNames, node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		updateList(classNames, node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		updateList(variableNames, node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		String name = node.getName().getIdentifier();
		switch (type) {
			case STATIC:
				updateList(staticVariableNames, name);
				break;
			case VARIABLE:
				updateList(variableNames, name);
				break;
		}
		type = Type.VARIABLE;
		return true;
	}

}