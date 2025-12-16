package plagiarism;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.*;

public class TextDocument extends Document {

    public TextDocument(java.nio.file.Path path) {
        super(path);
    }

    @Override
    public Map<String, Integer> getTermFrequency()
            throws UnreadableFileException {

        Map<String, Integer> freq = new HashMap<>();

        try {
            String content = Files.readString(path).toLowerCase();
            Matcher m = Pattern.compile("[a-zA-Z]+").matcher(content);

            while (m.find()) {
                String word = m.group();
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        } catch (IOException e) {
            throw new UnreadableFileException("Cannot read: " + path);
        }
        return freq;
    }
}
