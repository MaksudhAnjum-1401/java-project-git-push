package plagiarism;
import java.util.Map;

public interface SimilarityMetric {
    double compute(Map<String, Integer> d1,
                   Map<String, Integer> d2);
}
