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
    public void initializeJSON(int classIterator) {

        JSONObject emptyJSON = new JSONObject();
        snakeJSON.put("Class"+Integer.toString(classIterator), emptyJSON);
        //SnakeDeclarationIdentifier.snakeJSON.put("Class"+Integer.toString(classIterator), "");
    }

    public float returnConsistency(List<String> goodList, List<String> badList) {
        float percentage = 0;
        if ((goodList.size() + badList.size()) != 0) {
            percentage = (goodList.size() * 100.0f / (goodList.size() + badList.size()));
        }
        return percentage;
    }

    private void updateJSON(String key, List<String> newValue, List<String> originalValue, int prevTypeCount) {
        JSONObject classValue = (JSONObject) snakeJSON.get("Class"+Integer.toString(currentClass));

        int actualNewValue = newValue.size() - prevTypeCount;
        String percentage = Integer.toString(actualNewValue/originalValue.size() * 100);
        classValue.put(key, percentage);
    }

    @Override
    public void checkClassName(List<String> classNames) {
        for (String name: classNames) {
            if (Character.isUpperCase(name.charAt(0))) {
                // valid class name
                // check if one word (split on capital letters)
                // if one word, call DataProvider
                // if >1 word, call DataProvider on each word
                int lastCap = 0;
                List<String> listOfSubWord = new ArrayList<>();
                for (int i = 1; i < name.length(); i++) {
                    if (name.charAt(i) == '_') {
                        String subWord = name.substring(lastCap, i-1);
                        listOfSubWord.add(subWord);
                        lastCap = i;
                    }
                }
                if (listOfSubWord.size() != 0) {
                    for (String word : listOfSubWord) {
                        addToGoodBadList(word, goodClassNames, badClassNames);
                        // call dataProvider on word
                    }
                }
            } else {
                // name is not valid (percent will remain 0)
                badClassNames.add(name);
            }
        }
        updateJSON(NAME_KEY, goodClassNames, classNames, prevClassCount);
        prevClassCount = goodClassNames.size();
    }

    @Override
    public void checkMethodName(List<String> methodNames) {
        for (String name : methodNames) {
            checkSnakeCase(name, goodMethodNames, badMethodNames);
        }
        updateJSON(METHOD_KEY, goodMethodNames, methodNames, prevMethodCount);
        prevMethodCount = goodMethodNames.size();
    }



    @Override
    public void checkVariableName(List<String> variableNames) {
        for (String name : variableNames) {
            checkSnakeCase(name, goodVariableNames, badVariableNames);
        }
        updateJSON(VARIABLE_KEY, goodVariableNames, variableNames, prevMethodCount);
        prevMethodCount = goodVariableNames.size();
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

    @Override
    public void checkStaticVariableName(List<String> staticVariableNames) {
        for (String name : staticVariableNames) {
            if (Character.isUpperCase(name.charAt(0))) {
                int indexOfLastWord = 0;
                List<String> listOfSubWord = new ArrayList<>();
                for (int i = 1; i < name.length(); i++) {
                    if (Character.isLowerCase(name.charAt(i))) {
                        badStaticVariableNames.add(name);
                    } else {
                        if (name.charAt(i) == '_') {
                            addWordToList(name, indexOfLastWord, listOfSubWord, i);
                        }
                    }
                }
                if (listOfSubWord.size() != 0) {
                    for (String word : listOfSubWord) {
                        addToGoodBadList(word, goodStaticVariableNames, badStaticVariableNames);
                    }
                }
            }
        }
        updateJSON(STATIC_VARIABLE_KEY, goodStaticVariableNames, staticVariableNames, prevMethodCount);
        prevMethodCount = goodStaticVariableNames.size();
    }

    private void addWordToList(String name, int indexOfLastWord, List<String> listOfSubWord, int i) {
        String subWord = name.substring(indexOfLastWord, i - 1);
        listOfSubWord.add(subWord);
        indexOfLastWord = i + 1;
    }

}
