package plagiarism;
import java.util.Scanner;

public class PlagiarismCLI {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter submissions directory path:");
        String dir = sc.nextLine();

        SimilarityMetric metric = new CosineMetric(); // Strategy
        PlagiarismPipeline pipeline = new PlagiarismPipeline(metric);

        pipeline.run(dir);
    }
}
