package analysis;

import preprocess.Declaration;

import java.util.List;

public class SnakeDeclarationIdentifier extends DeclarationProcessor {

    private List<Declaration> declarations;
    private boolean snakeCase = true;

    private List<String> goodClassNames;
    private List<String> badClassNames;
    private List<String> goodMethodNames;
    private List<String> badMethodNames;
    private List<String> goodStaticVariableNames;
    private List<String> badStaticVariableNames;
    private List<String> goodVariableNames;
    private List<String> badVariableNames;

    public SnakeDeclarationIdentifier() { }


    @Override
    public void checkClassName(List<String> classNames) {
        //TODO
    }

    @Override
    public void checkMethodName(List<String> methodNames) {
        //TODO
    }

    @Override
    public void checkStaticVariableName(List<String> staticVariableNames) {
        //TODO
    }

    @Override
    public void checkVariableName(List<String> variableNames) {
        //TODO
    }

}
