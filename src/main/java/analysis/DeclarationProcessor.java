package analysis;

import preprocess.Declaration;

import java.util.List;

public abstract class DeclarationProcessor {

    protected static final String SNAKE_CASE_KEY = "Snake_Case";
    protected static final String NAME_KEY = "name";
    protected static final String CLASS_KEY = "class-consistency";
    protected static final String STATIC_VARIABLE_KEY = "constant-consistency";
    protected static final String VARIABLE_KEY = "variable-consistency";
    protected static final String METHOD_KEY = "method-consistency";

    public DeclarationProcessor() { }

    public void processDeclaration(List<Declaration> declarations){
        for (Declaration d : declarations){
            // TODO d.getFileName map
            checkClassName(d.getClassNames());
            checkMethodName(d.getMethodNames());
            checkStaticVariableName(d.getStaticVariableNames());
            checkVariableName(d.getVariableNames());
        }
    }
    public void isInDictionary(String word) {
        // TODO call API
    }

    public abstract void checkClassName(List<String> classNames);

    public abstract void checkMethodName(List<String> methodNames);

    public abstract void checkStaticVariableName(List<String> staticVariableNames);

    public abstract void checkVariableName(List<String> variableNames);
}
