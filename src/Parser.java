import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public Graph<String> parse(String fileName) {
        Graph<String> graph = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int numVariables = 0;
            int numClauses = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("c")) {
                    continue; // Comment line, skip it
                }
                if (line.startsWith("p")) {
                    // Line indicating the number of variables and clauses
                    String[] parts = line.split(" ");
                    numVariables = Integer.parseInt(parts[2]);
                    numClauses = Integer.parseInt(parts[3]);
                    // Create a new graph with double the size (each variable has a positive and a negative literal)
                    graph = new Graph<>(2 * numVariables);
                    continue;
                }
                // Process clauses
                String[] literals = line.split(" ");
                for (int i = 0; i < literals.length - 1; i++) {
                    int l1 = Integer.parseInt(literals[i]);
                    int l2 = Integer.parseInt(literals[i + 1]);
                    addClause(graph, l1, l2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private void addClause(Graph<String> graph, int l1, int l2) {
        // Calculate positive and negative literals
        int posL1 = Math.abs(l1) - 1;
        int posL2 = Math.abs(l2) - 1;
        int negL1 = posL1 + (l1 < 0 ? graph.order() / 2 : 0);
        int negL2 = posL2 + (l2 < 0 ? graph.order() / 2 : 0);

        try {
            graph.addArc(negL1, posL2, ""); // ¬l1 ⇒ l2
            graph.addArc(negL2, posL1, ""); // ¬l2 ⇒ l1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
