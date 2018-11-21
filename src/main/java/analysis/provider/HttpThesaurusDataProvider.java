package analysis.provider;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpThesaurusDataProvider extends AbstractHttpDataProvider{

    private static final String DICTIONARY_API_BASE = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/";
    private static final String DICTIONARY_API_KEY = "?key=2b54e0b9-3332-4626-bcfe-0c01e6eea61e";
    private String word;

    public HttpThesaurusDataProvider(String word) {
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
