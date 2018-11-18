import image.ImageCreator;
import json.JsonExctractor;

public class Main {


    public static void main(String[] args) {
        JsonExctractor jsonExctractor = new JsonExctractor();
        jsonExctractor.parse("test.json");
        ImageCreator imageCreator = new ImageCreator();
        imageCreator.drawImage();


    }


}
