package json;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonExctractor {


    private List<String> classNames;
    private List<String> constantConsistencies;
    private List<String> variableConsistencies;
    private List<String> methodConsistencies;
    private List<String> classConsistencies;
    private List<Boolean> styleCases;


    public JsonExctractor() {
        classNames = new ArrayList<>();
        constantConsistencies = new ArrayList<>();
        variableConsistencies = new ArrayList<>();
        methodConsistencies = new ArrayList<>();
        classConsistencies = new ArrayList<>();
        styleCases = new ArrayList<>();

    }

    public void parse(String path) {

        JSONParser jsonParser = new JSONParser();
        Gson gson = new Gson();
        List<String> jsonKeys = new ArrayList<>();

        try {

            // Read the json object from the current directory
            Object obj = jsonParser.parse(new FileReader(path));

            // Cast the read object as a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Iterate though keys in the json object and add them in the jsonKeys list
            for (int i = 0; i < jsonObject.keySet().toArray().length; i++) {
                String name = jsonObject.keySet().toArray()[i].toString();
                jsonKeys.add(name);
            }
            // Sort the keys array (since the json does not preserve their indexex here)
            Collections.sort(jsonKeys);

            // Iterate through every key in the json object and save the appropriate fileds in the corresponding arrays.
            for (int i = 1; i < jsonKeys.size() + 1; i++) {
                Object o = jsonObject.get("Class" + i);
                JSONObject jObj = (JSONObject) o;
                addClassName(jObj.get("name").toString());
                addConstantPercentage(jObj.get("constant-consistency").toString());
                addVariablePercentage(jObj.get("variable-consistency").toString());
                addMethodPercentage(jObj.get("method-consistency").toString());
                addClassPercentage(jObj.get("class-consistency").toString());
                addGetStyleCase((Boolean) jObj.get("Snake_Case"));

            }


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public List<String> getConstantConsistencies() {
        return constantConsistencies;
    }

    public List<String> getVariableConsistencies() {
        return variableConsistencies;
    }

    public List<String> getMethodConsistencies() {
        return methodConsistencies;
    }

    public List<String> getClassConsistencies() {
        return classConsistencies;
    }

    public void addClassName(String className) {
        classNames.add(className);
    }

    public void addConstantPercentage(String constantP) {
        constantConsistencies.add(constantP);
    }

    public void addVariablePercentage(String variableP) {
        variableConsistencies.add(variableP);
    }

    public void addMethodPercentage(String methodP) {
        methodConsistencies.add(methodP);
    }

    public void addClassPercentage(String classP) {
        classConsistencies.add(classP);
    }

    public List<Boolean> getStyleCases() {
        return styleCases;
    }

    public void addGetStyleCase(Boolean style) {
        styleCases.add(style);
    }

}
