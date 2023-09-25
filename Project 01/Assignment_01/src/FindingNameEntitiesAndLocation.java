import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.*;
import opennlp.tools.util.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FindingNameEntitiesAndLocation {
    public static void main(String[] args) throws Exception {
        // Loading the NER model for person names and locations
        TokenizerModel tokenizerModel = new TokenizerModel(new FileInputStream("D:/software/en-token.bin"));
        Tokenizer tokenizer = new TokenizerME(tokenizerModel);

        InputStream personModelStream = new FileInputStream("D:/software/en-ner-person.bin");
        TokenNameFinderModel personModel = new TokenNameFinderModel(personModelStream);
        NameFinderME personNameFinder = new NameFinderME(personModel);

        InputStream locationModelStream = new FileInputStream("D:/software/en-ner-location.bin");
        TokenNameFinderModel locationModel = new TokenNameFinderModel(locationModelStream);
        NameFinderME locationNameFinder = new NameFinderME(locationModel);

        // Reading text from a file
        String filePath = "C:/Users/Tasin/Desktop/news article.txt";
        String text = readTextFromFile(filePath);

        // Tokenizing the text
        String[] tokens = tokenizer.tokenize(text);

        // Finding the person names
        Span[] personSpans = personNameFinder.find(tokens);
        for (Span span : personSpans) {
            System.out.println("Person: " + span.toString() + " - " + tokens[span.getStart()]);
        }

        // Finding locations
        Span[] locationSpans = locationNameFinder.find(tokens);
        for (Span span : locationSpans) {
            System.out.println("Location: " + span.toString() + " - " + tokens[span.getStart()]);
        }

        // Cleaning up resources
        personNameFinder.clearAdaptiveData();
        locationNameFinder.clearAdaptiveData();
    }

    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }
}

