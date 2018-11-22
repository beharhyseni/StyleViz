package analysis.writer;

import analysis.DeclarationProcessor;
import analysis.SnakeDeclarationIdentifier;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileGenerator {

    private static String SNAKE_FILE_NAME = "snake";
    private static String CAMEL_FILE_NAME = "camel";
    private boolean isProcessingSnake = false;
    private boolean hasCheckedBefore = false;

    public FileGenerator() {};

    public void writeToFiles(JSONObject jsonObject, String fileNameCount){

        String fileName;

        if(isProcessingSnake){
            fileName = SNAKE_FILE_NAME + fileNameCount +".json";
        }else{
            fileName = CAMEL_FILE_NAME + fileNameCount +".json";
        }

        File jsonFile = new File(fileName);
        FileWriter writer;

        try {
            System.out.println("Start Writing " + fileName);
            writer = new FileWriter(jsonFile);
            writer.write(jsonObject.toJSONString());
            writer.close();
            System.out.println("Finish Writing " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void processJSONObject(JSONObject jsonObjectBase) {

        if(jsonObjectBase.size() == 0){
            System.out.println("JSONObject is null");
            return;
        }

        int loopTracker = 1;
        int whileLoopTracker = 1;

        Object[] keys = jsonObjectBase.keySet().toArray();

        while (keys.length > 0) {
            if(whileLoopTracker <= 5) { // limit to five
                JSONObject jsonObject = new JSONObject();
                for(int i = 0 ; i < loopTracker ; i++){
                    if(loopTracker > keys.length) {
                        whileLoopTracker = 6;
                        break;
                    }else{
                        jsonObject.put(keys[i], jsonObjectBase.get(keys[i]));
                        if(!hasCheckedBefore) checkIfItIsSnake((JSONObject) jsonObjectBase.get(keys[i]));
                        writeToFiles(jsonObject, String.valueOf(loopTracker));
                    }
                }

                whileLoopTracker++;
                loopTracker = whileLoopTracker;
            }else{
                break;
            }

        }

    }

    private void checkIfItIsSnake(JSONObject jsonObject) {

        if(jsonObject.get("Snake_Case").equals(true)){
            isProcessingSnake = true; // this is for file name purpose
        };
        hasCheckedBefore = true; // no need to check again

    }

}
