package plagiarism;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class JaccardMetric implements SimilarityMetric {

    @Override
    public double compute(Map<String, Integer> a,
                          Map<String, Integer> b) {

        Set<String> s1 = new HashSet<>(a.keySet());
        Set<String> s2 = new HashSet<>(b.keySet());

        Set<String> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);

        Set<String> union = new HashSet<>(s1);
        union.addAll(s2);

        if (union.isEmpty()) return 0;
        return (intersection.size() * 100.0) / union.size();
    }
}
