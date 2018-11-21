import analysis.CamelDeclarationIdentifier;
import analysis.DeclarationProcessor;
import analysis.SnakeDeclarationIdentifier;
import preprocess.Declaration;
import preprocess.PreProcessor;

import java.io.IOException;
import java.util.List;

public class StyleViz {

    public static void main(String[] args) throws IOException {

        PreProcessor preProcessor = new PreProcessor();
        List<Declaration> declarations = preProcessor.process("");
        DeclarationProcessor camel = new CamelDeclarationIdentifier();
        camel.processDeclaration(declarations); // TODO output list of JSON object
        DeclarationProcessor snake = new SnakeDeclarationIdentifier();
        snake.processDeclaration(declarations); // TODO output list of JSON object

    }
}
