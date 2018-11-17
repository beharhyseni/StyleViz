package preprocess;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreProcessor {


	private static final String CHARSET = "UTF-8";

	/**
	 * Return a Declaration for each .java file in root
	 *
	 * @param root  The root directory containing Java files
	 * @return      A list of Declarations for each Java file in root
	 */
	public static List<Declaration> process(String root) {
		File[] javaFiles = getJavaFiles(root);

		// Setup AST Parser
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		// Get all Declarations
		List<Declaration> declarations = new ArrayList<>();
		for (File file : javaFiles) {
			Declaration declaration = processDeclaration(file, parser);
			if (declaration != null) declarations.add(declaration);
		}

		return declarations;
	}


	/**
	 * Process the given Java file using the given parser to create a Declaration instance
	 *
	 * @param file      The Java file to process
	 * @param parser    The parser to create an AST with
	 * @return          A Declaration instance of the given file
	 */
	private static Declaration processDeclaration(File file, ASTParser parser) {

		// Get source code as a String
		String source;
		try { source = getSource(file, CHARSET); }
		catch (IOException e) { return null; }

		parser.setSource(source.toCharArray());

		// Parse into AST
		final CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);

		// Process Declaration
		return DeclarationCreator.makeDeclaration(compilationUnit, file.getName());
	}

	/**
	 * Return all Java Files in root
	 *
	 * @param root  The root directory
	 * @return      All files ending with .java (i.e. all java files)
	 */
	private static File[] getJavaFiles(String root) {
		File dir = new File(root);
		return dir.listFiles((dir1, name) -> name.endsWith(".java"));
	}


	/**
	 * 	Return the contents of the file as a string
	 *
	 * @param sourceFile        The source file
	 * @param charset           The character encoding
	 * @return                  The contents of the source file as a String
	 * @throws IOException      If failed to read contents into String
	 */
	private static String getSource(File sourceFile, String charset) throws IOException {
		return FileUtils.readFileToString(sourceFile, charset);
	}

}
