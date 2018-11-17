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

	Declaration build(CompilationUnit unit) {
		visit(unit);
		return new Declaration( fileName,
								classNames,
								methodNames,
								staticVariableNames,
								variableNames);
	}

	static Declaration makeDeclaration(final CompilationUnit unit, String fileName) {
		DeclarationCreator creator = new DeclarationCreator(fileName);
		return creator.build(unit);
	}

	/* ********** Visit Methods ********** */
	// TODO: Implement required visitor methods


	@Override
	public boolean visit(ClassInstanceCreation node) {
		return true;
	}

	@Override
	public boolean visit(CompilationUnit node) {
		return true;
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(ImportDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(SimpleName node) {
		return true;
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationExpression node) {
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		return true;
	}

}