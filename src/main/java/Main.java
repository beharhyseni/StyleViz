import image.ImageCreator;
import json.JsonExctractor;

public class Main {


    public static void main(String[] args) {
        JsonExctractor jsonExctractor = new JsonExctractor();
        jsonExctractor.parse("json5.json");
        ImageCreator imageCreator = new ImageCreator(jsonExctractor);
        imageCreator.drawImage();


    }


}
