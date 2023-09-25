import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TokenizationEachSentence {
    public static void main(String[] args) {
        try {
            // Loading the sentence detection model
            InputStream sentenceModelIn = new FileInputStream("D:/software/en-sent.bin");
            SentenceModel sentenceModel = new SentenceModel(sentenceModelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);

            // Loading the tokenizer model
            InputStream tokenizerModelIn = new FileInputStream("D:/software/en-token.bin");
            TokenizerModel tokenizerModel = new TokenizerModel(tokenizerModelIn);
            TokenizerME tokenizer = new TokenizerME(tokenizerModel);

            // Reading the text from a file
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Tasin/Desktop/news article.txt");
            byte[] fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);
            fileInputStream.close();

            String text = new String(fileData, "UTF-8");

            // Performing sentence detection
            String[] sentences = sentenceDetector.sentDetect(text);

            // Tokenizing each sentence into words
            for (String sentence : sentences) {
                String[] tokens = tokenizer.tokenize(sentence);

                // Printing the tokens (words)
                for (String token : tokens) {
                    System.out.println(token);
                }
            }

            // Cleaning up
            sentenceModelIn.close();
            tokenizerModelIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

