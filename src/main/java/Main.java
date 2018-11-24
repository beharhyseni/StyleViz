import image.ImageCreator;
import json.JsonExctractor;

public class Main {


    public static void main(String[] args) {
        JsonExctractor jsonExctractor = new JsonExctractor();
        jsonExctractor.parse("json5.json");
        System.out.println(jsonExctractor.getStyleCases());
//        ImageCreator imageCreator = new ImageCreator(jsonExctractor);
//        imageCreator.drawImage();


    }


}
