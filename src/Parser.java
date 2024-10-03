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
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return graph;
        }

        private void addClause(Graph<String> graph, int l1, int l2) throws Exception {
            int n = graph.order() / 2;  // Number of variables

            // Convert literals to indices (positive and negative)
            int indexL1 = literalToIndex(l1, n);
            int indexL2 = literalToIndex(l2, n);

            int negIndexL1 = literalToIndex(-l1, n);  // Negation of l1
            int negIndexL2 = literalToIndex(-l2, n);  // Negation of l2

            // Add implications to the graph
            graph.addArc(negIndexL1, indexL2, ""); // ¬l1 -> l2
            graph.addArc(negIndexL2, indexL1, ""); // ¬l2 -> l1
        }

        private int literalToIndex(int literal, int n) {
            // Positive literals map to [0, n-1], negative literals map to [n, 2n-1]
            return (literal > 0) ? (literal - 1) : (n + Math.abs(literal) - 1);
        }



    }
