package plagiarism;
import java.nio.file.Path;
import java.util.Map;

public abstract class Document {
    protected Path path;

    public Document(Path path) {
        this.path = path;
    }

    public String getName() {
        return path.getFileName().toString();
    }

    public abstract Map<String, Integer> getTermFrequency()
            throws UnreadableFileException;
}
