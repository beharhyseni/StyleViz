import analysis.CamelDeclarationIdentifier;
import analysis.DeclarationProcessor;
import analysis.SnakeDeclarationIdentifier;
import analysis.provider.DataProvider;
import analysis.provider.HttpDictionaryDataProvider;
import analysis.writer.FileGenerator;
import org.json.simple.JSONObject;
import preprocess.Declaration;
import preprocess.PreProcessor;

import java.io.IOException;
import java.util.List;

public class StyleViz {

    public static void main(String[] args) throws IOException {


        List<Declaration> declarations = PreProcessor.process("");

        System.out.println("Pre-process complete.");

        DeclarationProcessor camel = new CamelDeclarationIdentifier();
        camel.processDeclaration(declarations);
//        DeclarationProcessor snake = new SnakeDeclarationIdentifier();
//        snake.processDeclaration(declarations);

        System.out.println("Data analysis complete.");

//        FileGenerator fileGenerator = new FileGenerator();
//        fileGenerator.processJSONObject(CamelDeclarationIdentifier.camelJSON);
//        fileGenerator.processJSONObject(SnakeDeclarationIdentifier.snakeJSON);
//
//        System.out.println("JSON FILES GENERATED.");

    }
}
