package analysis;

import analysis.provider.DataProvider;
import org.json.simple.JSONObject;
import preprocess.Declaration;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class SnakeDeclarationIdentifier extends DeclarationProcessor {


    public static JSONObject snakeJSON = new JSONObject();

    public static int currentClass;

    private int prevClassCount = 0;
    private int prevMethodCount = 0;
    private int prevStaticVariableCount = 0;
    private int prevVariableCount = 0;

    private boolean snakeCase = true;

    private List<String> goodClassNames = new ArrayList<>();
    private List<String> badClassNames = new ArrayList<>();
    private List<String> goodMethodNames = new ArrayList<>();
    private List<String> badMethodNames = new ArrayList<>();
    private List<String> goodStaticVariableNames = new ArrayList<>();
    private List<String> badStaticVariableNames = new ArrayList<>();
    private List<String> goodVariableNames = new ArrayList<>();
    private List<String> badVariableNames = new ArrayList<>();

    public SnakeDeclarationIdentifier() { }

    @Override
    public void initializeJSON(int classIterator) {

        JSONObject emptyJSON = new JSONObject();
        snakeJSON.put("Class"+Integer.toString(classIterator), emptyJSON);
    }

    private List<String> getSubstrings(String name) {
        List<String> substringsOfName = new ArrayList<>();
        int indexOfLastCap = 0;
        // populate substringsOfName with k-1 substrings of class name with k total substrings
        for (int i=1; i<name.length(); i++) {
            if ((name.charAt(i) == '_')) {
                String subWord = name.substring(indexOfLastCap, i);
                substringsOfName.add(subWord);
                indexOfLastCap = i;
            }
        }
        // add kth substring to substringsOfName
        String lastChar = Character.toString(name.charAt(name.length()-1));
        String lastSubstring = name.substring(indexOfLastCap, name.length()-1).concat(lastChar);
        substringsOfName.add(lastSubstring);

        return substringsOfName;
    }

    private boolean containsVerifiedSubstrings(List<String> substringsOfName) {

        boolean isValidName = true;

        for (String substring: substringsOfName) {
            if (!DeclarationProcessor.isInDictionary(substring)) {
                isValidName = false;
            }
        }
        return isValidName;
    }

    private void updateJSON(String key, List<String> newValue, List<String> originalValue, int prevTypeCount) {
        JSONObject classValue = (JSONObject) snakeJSON.get("Class"+Integer.toString(currentClass));

        double actualNewValue = newValue.size() - prevTypeCount;
        String percentString;

        // if originalValue is empty, no names exist for this category of names; this check helps avoid / by 0
        if (originalValue.size() == 0) {
            percentString = "0";
        }
        else {
            double decimalPercent = actualNewValue/originalValue.size() * 100;
            int integerPercent = (int) decimalPercent;
            percentString = Integer.toString(integerPercent);
        }
        classValue.put(key, percentString);
    }

    @Override
    public void checkClassName(List<String> classNames) {

        String className = "";
        for (String name: classNames) {

            List<String> substringsOfName;
            boolean isValidName;

            if (Character.isUpperCase(name.charAt(0))) {

                substringsOfName = getSubstrings(name);
                // loop through and check each substring
                isValidName = containsVerifiedSubstrings(substringsOfName);

                if (isValidName) {
                    goodClassNames.add(name);
                    className = name;
                } else {
                    badClassNames.add(name);
                }
            } else {
                // name is not valid because doesn't start with a capital
                badClassNames.add(name);
            }
        }
        JSONObject classValue = (JSONObject) camelJSON.get("Class"+Integer.toString(currentClass));
        classValue.put(NAME_KEY, className);

        // find right class in JSON and insert name key and percentage
        updateJSON(CLASS_KEY, goodClassNames, classNames, prevClassCount);
        prevClassCount = goodClassNames.size();
    }

    @Override
    public void checkMethodName(List<String> methodNames) {

        boolean constructorIncluded = false;

        for (String method: methodNames) {

            List<String> substringsOfName;

            // check if constructor has been accounted for
            if (Character.isUpperCase(method.charAt(0))) {
                if (!constructorIncluded) {
                    for (String className: goodClassNames) {
                        if (className.equals(method)) {
                            // constructor has been verified to equal name of corresponding class
                            goodMethodNames.add(method);
                            constructorIncluded = true;
                        }
                    }
                } else {
                    // method is not a constructor but starts with a capital = bad
                    badMethodNames.add(method);
                }
            } else {
                // method starts with a lowercase; check if one- or multi-worded
                substringsOfName = getSubstrings(method);

                if (containsVerifiedSubstrings(substringsOfName)) {
                    goodMethodNames.add(method);
                } else {
                    badMethodNames.add(method);
                }
            }
        }
        updateJSON(METHOD_KEY, goodMethodNames, methodNames, prevMethodCount);
        prevMethodCount = goodMethodNames.size();
    }

    @Override
    public void checkVariableName(List<String> variableNames) {

        for (String variable: variableNames) {

            List<String> substringsOfName;

            if (Character.isUpperCase(variable.charAt(0))) {
                // variable starts with a capital, reject variable name
                badVariableNames.add(variable);
            } else {
                // variable starts with a lowercase; check if one- or multi-worded
                substringsOfName = getSubstrings(variable);

                if (containsVerifiedSubstrings(substringsOfName)) {
                    goodVariableNames.add(variable);
                } else {
                    goodVariableNames.add(variable);
                }
            }
        }

        updateJSON(VARIABLE_KEY, goodVariableNames, variableNames, prevVariableCount);
        prevVariableCount = goodVariableNames.size();


        JSONObject classValue = (JSONObject) snakeJSON.get("Class"+Integer.toString(currentClass));
        classValue.put(SNAKE_CASE_KEY, snakeCase);

    }

    public void checkSnakeCase(String name, List<String> goodList, List<String> badList) {
        if (Character.isLowerCase(name.charAt(0))) {
            List<String> listOfSubWord = new ArrayList<>();
            int indexOfLastWord = 0;
            for (int i = 1; i < name.length(); i++) {
                // Check for underscore
                if (name.charAt(i) == '_') {
                    addWordToList(name, indexOfLastWord, listOfSubWord, i);
                }
                if (listOfSubWord.size() != 0) {
                    for (String word : listOfSubWord) {
                        // check each word in the list if it exists in the dictionary
                        // call dataProvider
                        addToGoodBadList(word, goodList, badList);
                    }
                    // check each word in the list if it exists in the dictionary
                }
            }
        }
    }

    public void addToGoodBadList(String word, List<String> goodList, List<String> badList){
        if (DeclarationProcessor.isInDictionary(word)) {
            goodList.add(word);
        }
        else {
            badList.add(word);
        }
    }

    private void addWordToList(String name, int indexOfLastWord, List<String> listOfSubWord, int i) {
        String subWord = name.substring(indexOfLastWord, i - 1);
        listOfSubWord.add(subWord);
        indexOfLastWord = i + 1;
    }

    @Override
    public void checkStaticVariableName(List<String> staticVariableNames) {

        for (String staticVariable: staticVariableNames) {

            boolean isValidConstant = true;
            for (int i = 0; i < staticVariable.length(); i++) {
                if (!Character.isUpperCase(staticVariable.charAt(i)) && !Character.toString(staticVariable.charAt(i)).equals("_")) {
                    isValidConstant = false;
                }
            }
            if (!isValidConstant) {
                badStaticVariableNames.add(staticVariable);
            } else {
                // static variable is verified to only consist of capitals and underscores
                // split on underscores, any invalid word results in entire word being incorrect
                boolean containsValidSubstrings = true;
                for (String substring: staticVariable.split("_")) {
                    if (!isInDictionary(substring)) {
                        containsValidSubstrings = false;
                    }
                }
                if (containsValidSubstrings) {
                    goodStaticVariableNames.add(staticVariable);
                } else {
                    badStaticVariableNames.add(staticVariable);
                }
            }
        }
        updateJSON(STATIC_VARIABLE_KEY, goodStaticVariableNames, staticVariableNames, prevStaticVariableCount);
        prevStaticVariableCount = goodStaticVariableNames.size();
    }



}
