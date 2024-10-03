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
                        continue;
                    }
                    if (line.startsWith("p")) {

                        String[] parts = line.split(" ");
                        numVariables = Integer.parseInt(parts[2]);
                        numClauses = Integer.parseInt(parts[3]);
                        //System.out.println(numVariables);
                       // System.out.println(numClauses);

                        graph = new Graph<>(2 * numVariables);
                        continue;
                    }

                    String[] literals = line.split(" ");

                    addClause(graph, Integer.parseInt(literals[0]), Integer.parseInt(literals[1]));

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
