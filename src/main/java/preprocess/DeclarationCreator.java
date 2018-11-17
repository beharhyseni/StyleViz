package preprocess;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

public class DeclarationCreator extends ASTVisitor {
	private String fileName;
	private List<String> classNames;
	private List<String> methodNames;
	private List<String> staticVariableNames;
	private List<String> variableNames;

	DeclarationCreator(String fileName) {
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
	Declaration build(CompilationUnit unit) {
		unit.accept(this);
		return new Declaration( fileName,
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
		String name = node.getName().getIdentifier();
		if (name != null) staticVariableNames.add(name);
		return true;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		// Enums are treated as classes
		String name = node.getName().getIdentifier();
		if (name != null) classNames.add(name);
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) { return true; }

	@Override
	public boolean visit(ImportDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		String name = node.getName().getIdentifier();
		if (name != null) methodNames.add(name);
		return true;
	}

	@Override
	public boolean visit(PackageDeclaration node) { return true; }

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		String name = node.getName().getIdentifier();
		if (name != null) variableNames.add(name);
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		String name = node.getName().getIdentifier();
		if (name != null) classNames.add(name);
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		String name = node.getName().getIdentifier();
		if (name != null) variableNames.add(name);
		return true;
	}

}