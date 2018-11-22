package preprocess;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

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

	private DeclarationCreator(String fileName) {
		this.fileName = fileName;
		this.classNames = new ArrayList<>();
		this.methodNames = new ArrayList<>();
		this.staticVariableNames = new ArrayList<>();
		this.variableNames = new ArrayList<>();
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
				               variableNames);
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



	/* ********** Visit Methods ********** */

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		// Enum values are treated as static variables
		staticVariableNames.add(node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		// Enums are treated as classes
		classNames.add(node.getName().getIdentifier());
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
		methodNames.add(node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		classNames.add(node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		variableNames.add(node.getName().getIdentifier());
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		String name = node.getName().getIdentifier();
		switch (type) {
			case STATIC:
				staticVariableNames.add(name);
				break;
			case VARIABLE:
				variableNames.add(name);
				break;
		}
		type = Type.VARIABLE;
		return true;
	}

}