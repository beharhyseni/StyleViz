package analysis;

import org.json.simple.JSONObject;
import preprocess.Declaration;

import java.util.ArrayList;
import java.util.List;


public class CamelDeclarationIdentifier extends DeclarationProcessor {

    public static JSONObject camelJSON = new JSONObject();

    public static int currentClass;

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
    public void initializeJSON(int classIterator) {

        camelJSON.put("Class"+Integer.toString(classIterator), "");
        //SnakeDeclarationIdentifier.snakeJSON.put("Class"+Integer.toString(classIterator), "");
    }

    @Override
    public void checkClassName(List<String> classNames) {
        // TODO use goodClassNames badClassNames
        // TODO goodClassNames.size / classNames.size x 100 (no percentage) return string
        // TODO update JSON
        for (String name: classNames) {
            List<String> substringsOfName = new ArrayList<>();
            boolean validName = true;

            if (Character.isUpperCase(name.charAt(0))) {
                int indexOfLastCap = 0;
                // populate substringsOfName with k-1 substrings of class name with k total substrings
                for (int i=1; i<name.length(); i++) {
                    if (Character.isUpperCase(name.charAt(i))) {
                        String subWord = name.substring(indexOfLastCap, i);
                        substringsOfName.add(subWord);
                        indexOfLastCap = i;
                    }
                }
                // add kth substring to substringsOfName
                String lastChar = Character.toString(name.charAt(name.length()-1));
                String lastSubstring = name.substring(indexOfLastCap, name.length()-1).concat(lastChar);
                substringsOfName.add(lastSubstring);

                // loop through and check each substring
                for (String substring: substringsOfName) {
                    if (!DeclarationProcessor.isInDictionary(substring)) {
                        validName = false;
                    }
                }
                if (validName) {
                    goodClassNames.add(name);
                } else {
                    badClassNames.add(name);
                }
            } else {
                // name is not valid
                badClassNames.add(name);
            }
        }
        // TODO: update JSON with class names

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
