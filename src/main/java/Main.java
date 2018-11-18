import com.google.gson.Gson;
import json.JsonExctractor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<String> classNames = new ArrayList<>();
        List<String> constantConsistencies = new ArrayList<>();
        List<String> variableConsistencies = new ArrayList<>();
        List<String> methodConsistencies = new ArrayList<>();
        List<String> classConsistencies = new ArrayList<>();
        List<String> jsonKeys = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        JsonExctractor jsonExctractor = new JsonExctractor();
        Gson gson = new Gson();
        try {
            // Read the json object from the current directory
            Object obj = jsonParser.parse(new FileReader("test.json"));

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
                classNames.add(jObj.get("name").toString());
                constantConsistencies.add(jObj.get("constant-consistency").toString());
                variableConsistencies.add(jObj.get("variable-consistency").toString());
                methodConsistencies.add(jObj.get("method-consistency").toString());
                classConsistencies.add(jObj.get("class-consistency").toString());

            }

            System.out.println(classNames);
            System.out.println(classConsistencies);
            System.out.println(constantConsistencies);
            System.out.println(variableConsistencies);
            System.out.println(methodConsistencies);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
