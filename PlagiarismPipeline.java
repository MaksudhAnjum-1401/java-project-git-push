package plagiarism;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.*;

public class PlagiarismPipeline {

    private final SimilarityMetric metric;

    public PlagiarismPipeline(SimilarityMetric metric) {
        this.metric = metric;
    }

    public void run(String directory) throws Exception {

        List<Document> docs = new ArrayList<>();
        for (Path p : Files.list(Paths.get(directory))
                .filter(f -> f.toString().endsWith(".txt"))
                .toList()) {

            docs.add(new TextDocument(p));
        }

        int n = docs.size();
        double[][] matrix = new double[n][n];
        List<Map<String, Integer>> vectors = new ArrayList<>();

        for (Document d : docs) {
            vectors.add(d.getTermFrequency());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] =
                        metric.compute(vectors.get(i), vectors.get(j));
            }
        }

        printTerminal(docs, matrix);
        exportCSV(docs, matrix);
    }

    private void printTerminal(List<Document> docs, double[][] m) {

        System.out.println("\nSimilarity Matrix (%)\n");
        System.out.print("\t");
        docs.forEach(d -> System.out.print(d.getName() + "\t"));
        System.out.println();

        for (int i = 0; i < docs.size(); i++) {
            System.out.print(docs.get(i).getName() + "\t");
            for (int j = 0; j < docs.size(); j++) {
                System.out.printf("%.2f\t", m[i][j]);
            }
            System.out.println();
        }
    }

    private void exportCSV(List<Document> docs, double[][] m)
            throws Exception {

        try (PrintWriter pw = new PrintWriter("similarity_matrix.csv")) {
            pw.print("File");
            docs.forEach(d -> pw.print("," + d.getName()));
            pw.println();

            for (int i = 0; i < docs.size(); i++) {
                pw.print(docs.get(i).getName());
                for (int j = 0; j < docs.size(); j++) {
                    pw.printf(",%.2f", m[i][j]);
                }
                pw.println();
            }
        }
        System.out.println("\n✔ similarity_matrix.csv generated");
    }
}
