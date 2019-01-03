package analysis.provider;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpDictionaryDataProvider extends AbstractHttpDataProvider {

    private static final String DICTIONARY_API_BASE = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/";
    private static final String DICTIONARY_API_KEY = "?key=27d6c7af-3158-43d8-9966-e19fdd248a7f";
    private String word;

    public HttpDictionaryDataProvider(String word) {
        super();
        this.word = word;
    }


    @Override
    protected URL getURL() throws MalformedURLException {
        String request;

        request = DICTIONARY_API_BASE + this.word + DICTIONARY_API_KEY;

        System.out.println(request);

        return new URL(request);
    }

}
