package analysis;

import preprocess.Declaration;
import java.util.List;


public class CamelDeclarationIdentifier extends DeclarationProcessor {

    private boolean snakeCase = false;

    private List<String> goodClassNames;
    private List<String> badClassNames;
    private List<String> goodMethodNames;
    private List<String> badMethodNames;
    private List<String> goodStaticVariableNames;
    private List<String> badStaticVariableNames;
    private List<String> goodVariableNames;
    private List<String> badVariableNames;


    public CamelDeclarationIdentifier(){ }


    @Override
    public void checkClassName(List<String> classNames) {
        // TODO use goodClassNames badClassNames
        // TODO goodClassNames.size / classNames.size x 100 (no percentage) return string
        // TODO update JSON
        for (String name: classNames) {
            if (Character.isUpperCase(name.charAt(0))) {
                // valid class name
                // check if one word (split on capital letters)
                // if one word, call DataProvider
                // if >1 word, call DataProvider on each word
                int lastCap = 0;
                for (int i=1; i<name.length()-1; i++) {
                    if (Character.isUpperCase(name.charAt(i))) {
                        String subWord = name.substring(lastCap, i);
                    }
                }
            } else {
                // name is not valid
            }
        }
    }

    @Override
    public void checkMethodName(List<String> methodNames) {
        // TODO use goodMethodNames badMethodNames
        // TODO goodMethodNames.size / methodNames.size x 100 (no percentage) return string
        // TODO update JSON
    }

    @Override
    public void checkStaticVariableName(List<String> staticVariableNames) {
        // TODO use goodStaticVariableNames badStaticVariableNames
        // TODO goodStaticVariableNames.size / staticVariableNames.size x 100 (no percentage) return string
        // TODO update JSON
    }

    @Override
    public void checkVariableName(List<String> variableNames) {
        // TODO use goodVariableNames badVariableNames
        // TODO goodVariableNames.size / variableNames.size x 100 (no percentage) return string
        // TODO update JSON
    }

}
