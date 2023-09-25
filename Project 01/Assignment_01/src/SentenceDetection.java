import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SentenceDetection {
    public static void main(String[] args) {
        try {
            // Loading the sentence detection model
            InputStream modelIn = new FileInputStream("D:/software/en-sent.bin"); // Replace with the actual path to the model file
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME detector = new SentenceDetectorME(model);

            // Reading the text from a file
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Tasin/Desktop/news article.txt"); // Replace with the actual path to your text file
            byte[] fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);
            fileInputStream.close();

            String text = new String(fileData, "UTF-8");

            // Performing sentence detection
            String[] sentences = detector.sentDetect(text);

            // Printing the detected sentences
            for (String sentence : sentences) {
                System.out.println(sentence);
            }

            // Cleaning up
            modelIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

