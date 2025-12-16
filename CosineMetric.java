package plagiarism;
import java.util.Map;

public class CosineMetric implements SimilarityMetric {

    @Override
    public double compute(Map<String, Integer> a,
                          Map<String, Integer> b) {

        double dot = 0, magA = 0, magB = 0;

        for (String key : a.keySet()) {
            int x = a.get(key);
            magA += x * x;
            if (b.containsKey(key)) {
                dot += x * b.get(key);
            }
        }
        for (int y : b.values()) magB += y * y;

        if (magA == 0 || magB == 0) return 0;
        return (dot / (Math.sqrt(magA) * Math.sqrt(magB))) * 100;
    }
}
